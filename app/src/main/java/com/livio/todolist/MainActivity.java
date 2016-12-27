package com.livio.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextTask;
    public static final String PREFS_NAME = "Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = (EditText) findViewById(R.id.edittext_task);
    }

    public void buttonRefreshOnServer(View v){
        Toast.makeText(this, "Atualizando no Servidor", Toast.LENGTH_SHORT).show();
    }

    public void imageButtonAddNewTask(View v){
        Toast.makeText(this, editTextTask.getText().toString(), Toast.LENGTH_SHORT).show();

    }

}
