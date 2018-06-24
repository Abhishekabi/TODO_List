package com.abhishek.abima.todo_list;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Dbhelper dbhelper;
    ListView listView;
    CustomAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper = new Dbhelper(this);
        loadTask();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addTask:
                final EditText editText = new EditText(this);
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle("Add Your Task")
                        .setMessage("What's Your task ?")
                        .setView(editText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String task = editText.getText().toString();
                                dbhelper.insertValues(task);
                                loadTask();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadTask() {
        listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> taskList = dbhelper.getTaskList();
        arrayAdapter = new CustomAdapter(this, taskList);
        listView.setAdapter(arrayAdapter);
    }

    public void deleteTask(View view) {
        try {
            int i = listView.getPositionForView(view);
            String task = arrayAdapter.getItem(i);
            dbhelper.deleteValues(task);
            loadTask();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }


}

