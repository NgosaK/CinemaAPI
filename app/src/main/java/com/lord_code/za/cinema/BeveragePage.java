package com.lord_code.za.cinema;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.lord_code.za.cinema.model.beverage;
import com.lord_code.za.cinema.repositories.rest.RestBeverageAPI;
import com.lord_code.za.gallery.R;

import java.util.LinkedList;
import java.util.List;


public class BeveragePage extends Activity {

    Button btnSave, btnBack;
    EditText eCode,eName,eCategory,ePrice,eVolume;
    CheckBox checkBox;

    //MUSEUM
    com.lord_code.za.cinema.model.beverage beverage = new beverage();
    List<beverage> beverages = new LinkedList<beverage>();
    RestBeverageAPI restBeverage = new RestBeverageAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_page);

        SetUpGUI();

        Bundle extras = getIntent().getExtras();

        if (extras != null)
        {
            beverage.setCode(extras.getInt("beverageID"));
            beverage.setName(extras.getString("beverageName"));
            beverage.setCategory(extras.getString("beverageCategory"));
            beverage.setPrice(extras.getString("beveragePrice"));
            beverage.setVolume(extras.getString("beverageVolume"));

            eCode.setText(beverage.getCode()+"");
            eName.setText(beverage.getName()+"");
            eCategory.setText(beverage.getCategory()+"");
            ePrice.setText(beverage.getPrice()+"");
            eVolume.setText(beverage.getVolume()+"");


            eCode.setEnabled(false);
        }
    }

    void SetUpGUI()
    {
        eCode = (EditText)findViewById(R.id.editText13);
        eName = (EditText)findViewById(R.id.editText14);
        eCategory = (EditText)findViewById(R.id.editText15);
        ePrice = (EditText)findViewById(R.id.editText16);
        eVolume = (EditText)findViewById(R.id.editText17);




        btnSave = (Button)findViewById(R.id.button9);
        btnSave.setBackgroundResource(R.drawable.butt);
        btnSave.setOnClickListener(listener);

        btnBack = (Button)findViewById(R.id.button10);
        btnBack.setBackgroundResource(R.drawable.butt);
        btnBack.setOnClickListener(listener);

        checkBox = (CheckBox)findViewById(R.id.checkBox);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {

                case R.id.button10://btnBack
                {
                    finish();
                    break;
                }
                case R.id.button9://btnSave
                {
                    if(ValidateFields())
                    {
                        beverage.setCode(Long.parseLong(eCode.getText()+""));
                        beverage.setPrice(ePrice.getText()+"");
                        beverage.setCategory(eCategory.getText() + "");
                        beverage.setName(eName.getText() + "");
                        beverage.setVolume(eVolume.getText() + "");

                        HttpRequestTask httpRequestTask = new HttpRequestTask();
                        httpRequestTask.execute();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Fill all required fields",Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }};

    private boolean ValidateFields()
    {
        return (!eCode.getText().equals("") && !eName.getText().equals("") &&
                !ePrice.getText().equals("") && !eCategory.getText().equals("") && !eVolume.getText().equals("")
        );
    }

    private class HttpRequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                if(checkBox.isChecked())
                {
                    beverage.setCode(0);
                }

                restBeverage.post(beverage);
            }
            catch (Exception e)
            {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return "COMPLETE";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_museum_page, menu);
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
