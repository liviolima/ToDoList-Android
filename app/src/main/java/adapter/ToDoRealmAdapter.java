package adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livio.todolist.MainActivity;
import com.livio.todolist.R;


import java.util.Random;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;
import model.ToDoItem;

/**
 * Created by livio on 27/12/16.
 */

public class ToDoRealmAdapter extends RealmBasedRecyclerViewAdapter<ToDoItem, ToDoRealmAdapter.ViewHolder> {

    public ToDoRealmAdapter(Context context, RealmResults<ToDoItem> realmResults, boolean automaticUpdate, boolean animateResults) {
        super(context, realmResults, automaticUpdate, animateResults);
    }


    public class ViewHolder extends RealmViewHolder{

        public TextView todoTextView;
        public ViewHolder(LinearLayout container) {
            super(container);
            this.todoTextView = (TextView) container.findViewById(R.id.todo_text_view);
        }
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.to_do_item_view, viewGroup, false);
        return new ViewHolder((LinearLayout) v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final ToDoItem toDoItem = realmResults.get(position);
        viewHolder.todoTextView.setText(toDoItem.getDescription());
        //viewHolder.itemView.setBackgroundColor(color);

    }

}
