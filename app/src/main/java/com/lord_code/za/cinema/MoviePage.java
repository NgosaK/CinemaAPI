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
import com.lord_code.za.cinema.repositories.rest.RestMovieAPI;
import com.lord_code.za.gallery.R;
import com.lord_code.za.cinema.model.movie;

import java.util.LinkedList;
import java.util.List;


public class MoviePage extends Activity {

    Button btnBack, btnSave;
    EditText eId,eTitle,eDuration,eGenre,ePrice,eType;
    CheckBox checkBox;

    //ARTWORK
    static movie movie = new movie();
    List<movie> movies = new LinkedList<movie>();
    RestMovieAPI restMovie = new RestMovieAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_work_page);

        SetUpGUI();

        Bundle extras = getIntent().getExtras();

        if (extras != null)
        {
            movie.setId(extras.getInt("movieID"));
            movie.setDuration(extras.getString("movieDuration"));
            movie.setType(extras.getString("movieType"));
            movie.setPrice(extras.getString("moviePrice"));
            movie.setGenre(extras.getString("movieGenre"));
            movie.setTitle(extras.getString("movieTitle"));

            eId.setText(movie.getId()+"");
            eTitle.setText(movie.getTitle()+"");
            eDuration.setText(movie.getDuration()+"");
            eGenre.setText(movie.getGenre()+"");
            ePrice.setText(movie.getPrice()+"");
            eType.setText(movie.getType()+"");

            eId.setEnabled(false);
        }

    }

    private void SetUpGUI()
    {
        eId = (EditText)findViewById(R.id.editText4);
        eTitle = (EditText)findViewById(R.id.editText3);
        eDuration = (EditText)findViewById(R.id.editText5);
        eGenre = (EditText)findViewById(R.id.editText6);
        ePrice = (EditText)findViewById(R.id.editText18);
        eType = (EditText)findViewById(R.id.editText19);

        btnBack = (Button)findViewById(R.id.button5);
        btnBack.setBackgroundResource(R.drawable.butt);
        btnBack.setOnClickListener(listener);

        btnSave = (Button)findViewById(R.id.button6);
        btnSave.setBackgroundResource(R.drawable.butt);
        btnSave.setOnClickListener(listener);

        checkBox = (CheckBox)findViewById(R.id.checkBox3);
    }



    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {

                case R.id.button5:
                {
                    finish();
                    break;
                }
                case R.id.button6:
                {
                    if(ValidateFields())
                    {
                        movie.setTitle(eTitle.getText().toString());
                        movie.setDuration(eDuration.getText().toString());
                        movie.setGenre(eGenre.getText().toString());
                        movie.setId(Long.parseLong(eId.getText().toString()));
                        movie.setType(eType.getText().toString());
                        movie.setPrice(ePrice.getText().toString());

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
        return (!eId.getText().equals("") && !eTitle.getText().equals("") &&
                !eDuration.getText().equals("") && !eGenre.getText().equals("") && !ePrice.getText().equals("") &&
        !eType.getText().equals(""));
    }




    private class HttpRequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                if(checkBox.isChecked())
                {
                    movie.setId(0);
                }

                restMovie.post(movie);
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
        getMenuInflater().inflate(R.menu.menu_art_work_page, menu);
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
