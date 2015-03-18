package mobi.idappthat.campusconnect.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mobi.idappthat.campusconnect.Model.User;
import mobi.idappthat.campusconnect.R;

/**
 * Created by Cameron on 3/18/15.
 */
public class UserResultsAdapter extends RecyclerView.Adapter<UserResultsAdapter.ViewHolder> {

    private List<User> users;
    private Context context;
    private int rowLayout;

    public UserResultsAdapter(List<User> users, int rowLayout, Context context) {
        this.users = users;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        User user = users.get(i);
        viewHolder.name.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}
