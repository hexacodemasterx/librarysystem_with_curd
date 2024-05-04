package com.example.library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText bname, bauthor, bpublisher, bcopy, bbranch;
    Button insert, update, delete, view;
    DBHelper2 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bname = findViewById(R.id.bname);
        bauthor = findViewById(R.id.bauthor);
        bpublisher = findViewById(R.id.bpublisher);
        bcopy = findViewById(R.id.bcopy);
        bbranch = findViewById(R.id.bbranch);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper2(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bnameTXT = bname.getText().toString();
                String bauthorTXT = bauthor.getText().toString();
                String bpublisherTXT = bpublisher.getText().toString();
                String bcopyTXT = bcopy.getText().toString();
                String bbranchTXT = bbranch.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(bnameTXT, bauthorTXT, bpublisherTXT, bcopyTXT, bbranchTXT);
                if(checkinsertdata==true)
                    Toast.makeText(HomeActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bnameTXT = bname.getText().toString();
                String bauthorTXT = bauthor.getText().toString();
                String bpublisherTXT = bpublisher.getText().toString();
                String bcopyTXT = bcopy.getText().toString();
                String bbranchTXT = bbranch.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(bnameTXT, bauthorTXT, bpublisherTXT, bcopyTXT, bbranchTXT);
                if(checkupdatedata==true)
                    Toast.makeText(HomeActivity.this, "New Update", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "New Entry Not Update", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = bname.getText().toString();
                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if(checkdeletedata==true)
                    Toast.makeText(HomeActivity.this, "New Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "New Entry Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(HomeActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Book Name :"+res.getString(0)+"\n");
                    buffer.append("Book Author :"+res.getString(1)+"\n");
                    buffer.append("Book Publisher :"+res.getString(2)+"\n");
                    buffer.append("Copies of Books :"+res.getString(3)+"\n");
                    buffer.append("Department of Book :"+res.getString(4)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Book Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    public void goToSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}