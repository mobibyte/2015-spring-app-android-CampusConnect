package mobi.idappthat.campusconnect.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobi.idappthat.campusconnect.Adapter.UserResultsAdapter;
import mobi.idappthat.campusconnect.Model.User;
import mobi.idappthat.campusconnect.R;

/**
 * Created by Cameron on 3/18/15.
 */
public class ScanResultsActivity extends ActionBarActivity {

    private RecyclerView rvResults;
    private CheckBox cbSelectAll;
    private UserResultsAdapter resultsAdapter;

    private List<User> tempUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_results);

        /* Temp view items - Fake data */
        tempUsers = new ArrayList<>();
        tempUsers.add(new User("123", "Cameron Moreau"));
        tempUsers.add(new User("123", "Jack Hammer"));
        tempUsers.add(new User("123", "Jacob Slug"));
        tempUsers.add(new User("123", "James Doug"));

        /* Inflate layout and set properties */
        rvResults = (RecyclerView) findViewById(R.id.rvResults);
        cbSelectAll = (CheckBox) findViewById(R.id.cbSelectAll);

        rvResults.setLayoutManager(new LinearLayoutManager(this));
        rvResults.setItemAnimator(new DefaultItemAnimator());

        /* Select All button */
        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for(int i = 0; i < rvResults.getChildCount(); i++) {
                    View item = rvResults.getChildAt(i);
                    UserResultsAdapter.ViewHolder holder = new UserResultsAdapter.ViewHolder(item);
                    holder.updateChecked(isChecked);
                }
            }
        });

        resultsAdapter = new UserResultsAdapter(tempUsers, R.layout.row_user_result, this);
        rvResults.setAdapter(resultsAdapter);
    }
}
