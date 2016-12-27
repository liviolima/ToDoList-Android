package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;
import model.ToDoITem;

/**
 * Created by livio on 27/12/16.
 */

public class ToDoRealmAdapter extends RealmBasedRecyclerViewAdapter<ToDoITem, ToDoRealmAdapter.ViewHolder> {

    public ToDoRealmAdapter(Context context, RealmResults<ToDoITem> realmResults, boolean automaticUpdate, boolean animateResults, String animateExtraColumnName) {
        super(context, realmResults, automaticUpdate, animateResults, animateExtraColumnName);
    }

    public class ViewHolder extends RealmViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int i) {

    }
}
