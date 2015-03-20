package mobi.idappthat.campusconnect.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import mobi.idappthat.campusconnect.R;

/**
 * Created by Cameron on 3/17/15.
 */
public class ProfileActivity extends ActionBarActivity {

    /**
     * This is where a user can view any profile, theirs or others. Refer to something like this
     * http://assets0.materialup.com/uploads/a02b4f4f-3990-4cc3-8ae2-70465a77429d/material_profile.jpg
     * -Cameron
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    /**
     * @TODO: Check if this profile is the user, if so add an edit button on the menu bar, or FAB
     * -Cameron
     * */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_edit_profile:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
