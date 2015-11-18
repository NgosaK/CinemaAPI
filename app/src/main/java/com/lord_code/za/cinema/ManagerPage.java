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
import com.lord_code.za.cinema.model.manager;
import com.lord_code.za.cinema.repositories.rest.RestManagerAPI;
import com.lord_code.za.gallery.R;

import java.util.LinkedList;
import java.util.List;


public class ManagerPage extends Activity {

    Button btnSave, btnBack;
    EditText eId,eName,eJobTitle,eNumberOfEmployees,eDepartment,ePhone;
    CheckBox checkBox;

    //STAFF
    static com.lord_code.za.cinema.model.manager manager = new manager();
    List<manager> managers = new LinkedList<manager>();
    RestManagerAPI restManager = new RestManagerAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_page);

        SetUpGUI();

        Bundle extras = getIntent().getExtras();

        if (extras != null)
        {
            manager.setId(extras.getInt("managerID"));
            manager.setName(extras.getString("managerName"));
            manager.setDepartment(extras.getString("managerDepartment"));
            manager.setJobTitle(extras.getString("managerJobtitle"));
            manager.setNumberOfEmployees(extras.getString("managerNumberOfEmployees"));
            manager.setPhoneNumber(extras.getString("managerPhone"));

            eId.setText(manager.getId()+"");
            eName.setText(manager.getName()+"");
            eJobTitle.setText(manager.getJobTitle()+"");
            ePhone.setText(manager.getPhoneNumber()+"");
            eNumberOfEmployees.setText(manager.getNumberOfEmployees()+"");
            eDepartment.setText(manager.getDepartment()+"");

            eId.setEnabled(false);

            checkBox = (CheckBox)findViewById(R.id.checkBox2);
        }
    }



    private void SetUpGUI()
    {
        eId = (EditText)findViewById(R.id.editText7);
        eName = (EditText)findViewById(R.id.editText8);
        eJobTitle = (EditText)findViewById(R.id.editText9);
        ePhone = (EditText)findViewById(R.id.editText10);
        eNumberOfEmployees = (EditText)findViewById(R.id.editText12);
        eDepartment = (EditText)findViewById(R.id.editText11);

        btnBack = (Button)findViewById(R.id.button8);
        btnBack.setBackgroundResource(R.drawable.butt);
        btnBack.setOnClickListener(listener);

        btnSave = (Button)findViewById(R.id.button7);
        btnSave.setBackgroundResource(R.drawable.butt);
        btnSave.setOnClickListener(listener);
    }


    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {

                case R.id.button8://btnBack
                {
                    finish();
                    break;
                }
                case R.id.button7://btnSave
                {
                    if(ValidateFields())
                    {
                        manager.setId(Long.parseLong(eId.getText() + ""));
                        manager.setJobTitle(eJobTitle.getText() + "");
                        manager.setDepartment(eDepartment.getText() + "");
                        manager.setNumberOfEmployees(eNumberOfEmployees.getText() + "");
                        manager.setPhoneNumber(ePhone.getText() + "");
                        manager.setName(eName.getText() + "");

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
        return (!eId.getText().equals("") && !eName.getText().equals("") &&
                !eNumberOfEmployees.getText().equals("") && !eJobTitle.getText().equals("") &&
                !ePhone.getText().equals("") && !ePhone.getText().equals(""));
    }

    private class HttpRequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                if(checkBox.isChecked())
                {
                    manager.setId(0);
                }
                restManager.post(manager);
            }
            catch (Exception e)
            {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return "success";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_staff_page, menu);
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
