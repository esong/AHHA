package com.meet.ahha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.ArrayList;
import static android.view.View.OnClickListener;

public class MainActivity extends Activity {

    private Database datasource;
    private DatabaseHelper dbh;
    private Button searchButton;
    private Spinner yearSpinner, manufacturerSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbh= new DatabaseHelper (this);
        datasource = new Database(this);
        datasource.open();

        searchButton = (Button) findViewById(R.id.searchButton);
        // button for changing bg color
        searchButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                //Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                //intent.putExtra("current_fps", view.getFPS());
                //startActivity(intent);
                System.out.println("in search");

                List<Car> cars= new ArrayList<Car>();
                cars =dbh.getCars(String.valueOf(yearSpinner.getSelectedItem()),String.valueOf(manufacturerSpinner.getSelectedItem()),"*");
            }
        });


//        yearSpinner = (Spinner) findViewById(R.id.yearInput);
//        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
//        yearSpinner.setAdapter(yearAdapter);
//        manufacturerSpinner = (Spinner) findViewById(R.id.manufactInput);
//        ArrayAdapter<String> manuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
//        yearSpinner.setAdapter(manuAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
