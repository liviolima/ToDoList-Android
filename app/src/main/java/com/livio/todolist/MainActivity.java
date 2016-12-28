package com.livio.todolist;


import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import adapter.ToDoRealmAdapter;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import model.ToDoItem;


public class MainActivity extends AppCompatActivity {
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDialog();
            }
        });


        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(realmConfig);


        RealmResults<ToDoItem> toDoitems = realm
                .where(ToDoItem.class)
                .findAll();

        ToDoRealmAdapter toDoRealmAdapter = new ToDoRealmAdapter(this, toDoitems, true, true);
        RealmRecyclerView realmRecyclerView = (RealmRecyclerView) findViewById(R.id.realm_recycler_view);
        realmRecyclerView.setAdapter(toDoRealmAdapter);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(realm != null){
            realm.close();
            realm = null;
        }
    }

    private void inputDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //builder.setTitle("Criar nova tarefa");

        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.to_do_dialog_view, null);
        final EditText input = (EditText) dialogView.findViewById(R.id.input);

        builder.setView(dialogView);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addToDoItem(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.show();
        input.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE ||
                                (event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                            dialog.dismiss();
                            addToDoItem(input.getText().toString());
                            return true;
                        }
                        return false;
                    }
                });
    }

    private void addToDoItem(String toDoItemText) {
        if (toDoItemText == null || toDoItemText.length() == 0) {
            Toast
                    .makeText(this, "Insira alguma tarefa!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        realm.beginTransaction();
        ToDoItem todoItem = realm.createObject(ToDoItem.class, System.currentTimeMillis());
        todoItem.setDescription(toDoItemText);
        realm.commitTransaction();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.about:
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Sobre");
                alertDialog.setMessage("Desenvolvido por Lívio Lima.");
                alertDialog.show();
                break;
        }
        return true;
    }




}
