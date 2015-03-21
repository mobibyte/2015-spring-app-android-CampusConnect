package mobi.idappthat.campusconnect.Adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

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

        //Build the circle icon
        ColorGenerator generator = ColorGenerator.MATERIAL;
        TextDrawable icon = TextDrawable.builder()
                .buildRound(user.getName().substring(0, 1), generator.getRandomColor());

        viewHolder.name.setText(user.getName());
        viewHolder.icon.setImageDrawable(icon);
    }

    public void toggleSelectAll(boolean b) {
        for(User user : users) {
            
        }
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public ImageView icon, iconChecked;
        private boolean checked = false;

        public ViewHolder(final View itemView) {
            super(itemView);
            Animator animIn = AnimatorInflater.loadAnimator(itemView.getContext(), R.animator.card_flip_left_in);
            Animator animOut = AnimatorInflater.loadAnimator(itemView.getContext(), R.animator.card_flip_left_out);

            name = (TextView) itemView.findViewById(R.id.tvName);
            icon = (ImageView) itemView.findViewById(R.id.ivIcon);
            iconChecked = (ImageView) itemView.findViewById(R.id.ivIconChecked);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.checked = !checked;
            updateChecked(checked);
        }

        public void updateChecked(boolean checked) {
            this.checked = checked;
            if(checked) {
                icon.setVisibility(View.GONE);
                iconChecked.setVisibility(View.VISIBLE);
            } else {
                icon.setVisibility(View.VISIBLE);
                iconChecked.setVisibility(View.GONE);
            }
        }
    }
}
