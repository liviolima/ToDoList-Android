package adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.livio.todolist.R;


import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;
import model.ToDoItem;

/**
 * Created by livio on 27/12/16.
 */

public class ToDoRealmAdapter extends RealmBasedRecyclerViewAdapter<ToDoItem, ToDoRealmAdapter.ViewHolder> {
    Realm realm;

    public ToDoRealmAdapter(Context context, RealmResults<ToDoItem> realmResults, boolean automaticUpdate, boolean animateResults) {
        super(context, realmResults, automaticUpdate, animateResults);

        Realm.init(getContext());
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(realmConfig);
    }

    public class ViewHolder extends RealmViewHolder{
        public LinearLayout linearLayoutTextAndCheckBox;
        public TextView todoTextView;
        public CheckBox checkBoxDone;

        public ViewHolder(final LinearLayout container) {
            super(container);
            this.todoTextView = (TextView) container.findViewById(R.id.todo_text_view);
            this.checkBoxDone = (CheckBox) container.findViewById(R.id.checkbox_done);
            this.linearLayoutTextAndCheckBox = (LinearLayout) container.findViewById(R.id.linearlayout_text_and_checkbox);

            checkBoxDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBoxDone.isChecked()){
                        todoTextView.setPaintFlags(todoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    else{
                        todoTextView.setPaintFlags(todoTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                    saveUsingRealm();
                }
            });

            linearLayoutTextAndCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBoxDone.isChecked()){
                        todoTextView.setPaintFlags(todoTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        checkBoxDone.setChecked(false);
                    }
                    else{
                        todoTextView.setPaintFlags(todoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        checkBoxDone.setChecked(true);
                    }
                    saveUsingRealm();
                }
            });

            todoTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBoxDone.isChecked()){
                        todoTextView.setPaintFlags(todoTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        checkBoxDone.setChecked(false);
                    }
                    else{
                        todoTextView.setPaintFlags(todoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        checkBoxDone.setChecked(true);
                    }
                    saveUsingRealm();
                }
            });
        }
        private void saveUsingRealm(){
            realm.beginTransaction();
            int positionRecord = getAdapterPosition();
            ToDoItem toDoItem = realmResults.get(positionRecord);
            toDoItem.setDone(checkBoxDone.isChecked());
            realm.commitTransaction();
        }
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.to_do_item_view, viewGroup, false);
        return new ViewHolder((LinearLayout) v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindRealmViewHolder(final ViewHolder viewHolder, final int position) {
        final ToDoItem toDoItem = realmResults.get(position);
        viewHolder.checkBoxDone.setChecked(toDoItem.getDone());
        viewHolder.todoTextView.setText(toDoItem.getDescription());
        if (viewHolder.checkBoxDone.isChecked()) {
            viewHolder.todoTextView.setPaintFlags(viewHolder.todoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            viewHolder.todoTextView.setPaintFlags(viewHolder.todoTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }



}
