package mobi.idappthat.campusconnect.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    private UserResultsAdapter resultsAdapter;

    private List<User> tempUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_results);

        tempUsers = new ArrayList<>();
        tempUsers.add(new User("123", "Cameron Moreau"));
        tempUsers.add(new User("123", "Jack Hammer"));
        tempUsers.add(new User("123", "Jacob Slug"));
        tempUsers.add(new User("123", "James Doug"));

        Log.e("TEST", tempUsers.toString());

        rvResults = (RecyclerView) findViewById(R.id.rvResults);
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        rvResults.setItemAnimator(new DefaultItemAnimator());

        resultsAdapter = new UserResultsAdapter(tempUsers, R.layout.row_user_result, this);
        rvResults.setAdapter(resultsAdapter);
    }
}
