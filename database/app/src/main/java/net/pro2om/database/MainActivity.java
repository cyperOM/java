package net.pro2om.database;

import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    Button Add2task,ViewTasks,del_TASK;
    EditText inText,del_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Add2task = findViewById(R.id.addBtn);
        ViewTasks= findViewById(R.id.viewBtn);
        inText = findViewById(R.id.inputTask);
        del_TASK = findViewById(R.id.button_del);
        del_ID = findViewById(R.id.editTextNumber);
        mydb = new DatabaseHelper(this);
        add2db();
        Viewall();
    }
    public void add2db(){
        Add2task.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       boolean isInsterted = mydb.addData(inText.getText().toString());

                       if (isInsterted)
                           Toast.makeText(MainActivity.this,"Task inserted",Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void Viewall() {
        ViewTasks.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getData();
                        if (res.getCount() == 0) {

                        showmsg("Erroe"," No data found...");
                            return;
                        }
                    StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID : "+ res.getString(0)+"\n");
                            buffer.append("Tasks : "+ res.getString(1)+"\n\n");

                        }

                        //show All DATA
                        showmsg("data",buffer.toString());
                    }
                }
        );
    }
    public void delTask(){
        del_TASK.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Integer dealated =  mydb.deleteName(del_ID.getText().toString());
                    }
                }
        );

    }
    public void showmsg(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}

