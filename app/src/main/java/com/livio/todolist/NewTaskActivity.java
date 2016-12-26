package com.livio.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    EditText task, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        task = (EditText) findViewById(R.id.edittext_task);
        comment = (EditText) findViewById(R.id.edittext_comment);
    }

}
