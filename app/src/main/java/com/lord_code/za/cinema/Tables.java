package com.lord_code.za.cinema;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.lord_code.za.cinema.model.beverage;
import com.lord_code.za.cinema.model.manager;
import com.lord_code.za.cinema.repositories.rest.RestManagerAPI;
import com.lord_code.za.cinema.repositories.rest.RestMovieAPI;
import com.lord_code.za.gallery.R;
import com.lord_code.za.cinema.model.movie;
import com.lord_code.za.cinema.repositories.rest.RestBeverageAPI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Tables extends Activity {


    Button but4;
    Spinner spinner;
    String[] items = new String[]{"Please Select...","beverage", "manager", "movie"};
    ArrayList<String> arr = new ArrayList<String>();
    GridView gridview;

    //Beverage
    List<beverage> beverages = new LinkedList<beverage>();
    RestBeverageAPI restBeverage = new RestBeverageAPI();

    //Movie
    List<movie> movies = new LinkedList<movie>();
    RestMovieAPI restMovie = new RestMovieAPI();

    //Manager
    List<manager> managers = new LinkedList<manager>();
    RestManagerAPI restManager = new RestManagerAPI();

    static String nxtIntent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        but4 = (Button)findViewById(R.id.button4);
        but4.setBackgroundResource(R.drawable.butt);
        but4.setOnClickListener(listener);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        gridview = (GridView)findViewById(R.id.gridView);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position, long id)
            {
                if(nxtIntent.equals("movie"))
                {
                    Intent intent = new Intent(getApplicationContext(), MoviePage.class);
                    intent.putExtra("movieID", movies.get(position).getId());
                    intent.putExtra("movieDuration", movies.get(position).getDuration() + "");
                    intent.putExtra("movieGenre", movies.get(position).getGenre() + "");
                    intent.putExtra("moviePrice", movies.get(position).getPrice() + "");
                    intent.putExtra("movieTitle", movies.get(position).getTitle() + "");
                    intent.putExtra("movieType", movies.get(position).getType() + "");
                    startActivity(intent);
                }
                if(nxtIntent.equals("manager"))
                {
                    Intent intent = new Intent(getApplicationContext(), ManagerPage.class);
                    intent.putExtra("managerID", managers.get(position).getId());
                    intent.putExtra("managerName", managers.get(position).getName());
                    intent.putExtra("managerDepartment", managers.get(position).getDepartment());
                    intent.putExtra("managerNumberOfEmployees", managers.get(position).getNumberOfEmployees());
                    intent.putExtra("managerJobTitle", managers.get(position).getJobTitle());
                    intent.putExtra("managerPhoneNumber", managers.get(position).getPhoneNumber());
                    startActivity(intent);
                }
                if(nxtIntent.equals("beverage"))
                {
                    Intent intent = new Intent(getApplicationContext(), BeveragePage.class);
                    intent.putExtra("beverageID", beverages.get(position).getCode());
                    intent.putExtra("beverageCategory", beverages.get(position).getCategory()+"");
                    intent.putExtra("beverageName", beverages.get(position).getName()+"");
                    intent.putExtra("beveragePrice", beverages.get(position).getPrice()+"");
                    intent.putExtra("beverageVolume", beverages.get(position).getVolume()+"");
                    startActivity(intent);
                }
            }
        });

        new HttpRequestTask().execute();
    }


    public void populateGridview(String[] arr2)
    {
        arr.clear();
        for(int i = 0; i<arr2.length;i++)
        {
            arr.add(arr2[i]);
        }

        ArrayAdapter<String> gridAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, arr);
        gridview.setAdapter(gridAdapter);
    }


    //ONCLICK EVENTS
    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button4:
                    finish();
                    break;
            }
        }
    };

    //ITEM SELECTED EVENTS
    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id)
        {
            if(parent.getItemAtPosition(pos).toString() == "Please Select...")
            {
                nxtIntent = "";
                gridview.setAdapter(null);
            }
            if(parent.getItemAtPosition(pos).toString() == "beverage")
            {
                nxtIntent = "beverage";
                if(beverages.size() != 0)
                {
                    String[] temp = new String[beverages.size()];
                    for (int i = 0; i < beverages.size(); i++) {
                        temp[i] = beverages.get(i).getCode() + " " + beverages.get(i).getName() + " " + beverages.get(i).getCategory() + " " + beverages.get(i).getPrice() + " " + beverages.get(i).getVolume();
                    }
                    populateGridview(temp);
                }
                else
                {
                    gridview.setAdapter(null);
                }
            }
            else if(parent.getItemAtPosition(pos).toString() == "manager")
            {
                nxtIntent = "manager";
                if(managers.size() != 0)
                {
                    String[] temp = new String[managers.size()];
                    for (int i = 0; i < managers.size(); i++)
                    {
                        temp[i] = managers.get(i).getId() + " " + managers.get(i).getName() + " " + managers.get(i).getDepartment() + " " + managers.get(i).getJobTitle() + " " + managers.get(i).getNumberOfEmployees() + " " + managers.get(i).getPhoneNumber();
                    }
                    populateGridview(temp);
                }
                else
                {
                    gridview.setAdapter(null);
                }

            }
            else if(parent.getItemAtPosition(pos).toString() == "movie")
            {
                nxtIntent = "movie";
                if(movies.size() != 0)
                {
                    String[] temp = new String[movies.size()];
                    for (int i = 0; i < movies.size(); i++) {
                        temp[i] = movies.get(i).getId() + " " + movies.get(i).getTitle() + " " + movies.get(i).getDuration() + " " + movies.get(i).getPrice() + " " + movies.get(i).getGenre() + " " + movies.get(i).getType();
                    }
                    populateGridview(temp);
                }
                else
                {
                    gridview.setAdapter(null);
                }

            }
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                movies = restMovie.getAll();
                managers = restManager.getAll();
                beverages = restBeverage.getAll();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tables, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}

