package com.example.library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    EditText mname, bname, bissued, breturned;
    Button insert, update, delete, view;
    SADBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mname = findViewById(R.id.mname);
        bname = findViewById(R.id.bname);
        bissued = findViewById(R.id.bissued);
        breturned = findViewById(R.id.breturned);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new SADBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mnameTXT = mname.getText().toString();
                String bnameTXT = bname.getText().toString();
                String bissuedTXT = bissued.getText().toString();
                String breturnedTXT = breturned.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(mnameTXT, bnameTXT, bissuedTXT, breturnedTXT);
                if(checkinsertdata==true)
                    Toast.makeText(SecondActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SecondActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mnameTXT = mname.getText().toString();
                String bnameTXT = bname.getText().toString();
                String bissuedTXT = bissued.getText().toString();
                String breturnedTXT = breturned.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(mnameTXT, bnameTXT, bissuedTXT, breturnedTXT);
                if(checkupdatedata==true)
                    Toast.makeText(SecondActivity.this, "New Update", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SecondActivity.this, "New Entry Not Update", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = mname.getText().toString();
                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if(checkdeletedata==true)
                    Toast.makeText(SecondActivity.this, "New Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SecondActivity.this, "New Entry Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(SecondActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Member Name :"+res.getString(0)+"\n");
                    buffer.append("Book Name :"+res.getString(1)+"\n");
                    buffer.append("Book Issued Date :"+res.getString(2)+"\n");
                    buffer.append("Book Returned Date :"+res.getString(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Book Deals");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}