package com.lord_code.za.cinema;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lord_code.za.gallery.R;
import com.lord_code.za.cinema.model.Staff;
import com.lord_code.za.cinema.repositories.rest.RestStaffAPI;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity {

    //static String username = "", password = "";
    private static String text = "";

    Button but1, but2, but3;
    TextView textView1;

    //EditText eusername, epassword;

    //STAFF
    static List<Staff> staffs = new LinkedList<Staff>();
    RestStaffAPI restStaffs = new RestStaffAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but1 = (Button)findViewById(R.id.button);
        but2 = (Button)findViewById(R.id.button2);
        but3 = (Button)findViewById(R.id.button3);
        //textView1 = (TextView)findViewById(R.id.textView2);
       // eusername = (EditText)findViewById(R.id.editText);
       // epassword = (EditText)findViewById(R.id.editText2);

        but1.setBackgroundResource(R.drawable.butt);
        but2.setBackgroundResource(R.drawable.butt);
        but3.setBackgroundResource(R.drawable.butt);

        but1.setOnClickListener(listener);
        but2.setOnClickListener(listener);
        but3.setOnClickListener(listener);

        new HttpRequestTask().execute();

    }


    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {

                case R.id.button:

                    Intent i = new Intent(getApplicationContext(), Tables.class);
                    startActivity(i);
                   /* if(CheckUser())
                    {

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Incorrect credentials",Toast.LENGTH_SHORT).show();
                    }*/
                    break;
                case R.id.button2:
                     i = new Intent(getApplicationContext(), AboutPage.class);
                    startActivity(i);
                    break;
                case R.id.button3:
                    System.exit(0);
                    break;
            }
        }
    };

   /* private boolean CheckUser()
    {
        boolean val = false;
        String username = "", password = "";
        username = eusername.getText().toString();
        password = epassword.getText().toString();

        for(int i = 0;i<staffs.size();i++)
        {
            if((staffs.get(i).getName()).equals(username))
            {
                if((staffs.get(i).getIdnumber()).equals(password))
                {
                    val = true;
                }
            }
        }
        return val;
    }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private class HttpRequestTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try
            {
                staffs = restStaffs.getAll();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }


    }

}
