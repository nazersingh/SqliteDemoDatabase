package com.example.saini.sqlitedemodatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper databaseHelper;
TextView textView;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        textView=(TextView)findViewById(R.id.tv_data);

        databaseHelper = new DatabaseHelper(this);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_insert:
                insertValue();
                break;
            case R.id.btn_update:
                updateValue();
                break;
            case R.id.btn_delete:
                break;
            case R.id.btn_search:
                searchData();
                break;

        }
    }

    public void insertValue() {
        if (databaseHelper.insert("Nazer", "12345678910", "nazer@gmail.com"))
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show();
    }

    public void updateValue() {
//        Log.e( "updateValue: ",""+Integer.parseInt(cursor.getString(0) ));
        if (databaseHelper.updateData("NazerSinghSaini", "95010057300", "nazer@gmail.com",0))
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show();
    }

    public void searchData()
    {
        cursor= databaseHelper.getDataFromTable("12345678910");
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                for (int i = 0; i <cursor.getColumnCount() ; i++) {
                    Log.e("Columns "," c name "+cursor.getColumnName(i))     ;
                }

                textView.setText(cursor.getString(1) + " , " + cursor.getString(2) + " " + cursor.getString(0));
            }
        }
        else
            Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
    }

}
