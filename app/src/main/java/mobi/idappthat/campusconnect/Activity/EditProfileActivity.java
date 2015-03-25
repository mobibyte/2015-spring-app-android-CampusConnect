package mobi.idappthat.campusconnect.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mobi.idappthat.campusconnect.R;

/**
 * Created by Cameron on 3/17/15.
 */
public class EditProfileActivity extends ActionBarActivity implements View.OnClickListener{

    /**
     * This activity is used to edit the user's profile
     * This should be similar to Roman Nurik's 'DoneDiscard' activity
     * https://lh6.googleusercontent.com/-GOHYeSY5_Uc/UC60RSGrTdI/AAAAAAAApaQ/Us6JnEr2TI4/w628-h1090-no/png-1.png
     * I have the code for this, just worry about the fragment for now
     * - Cameron
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LayoutInflater inflater = (LayoutInflater) getActionBar().getThemedContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        final View actionBarView = inflater.inflate(R.layout.menu_done_discard, null);

        actionBarView.findViewById(R.id.actionbar_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(
                ActionBar.DISPLAY_SHOW_CUSTOM,
                ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME
                        | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setCustomView(actionBarView,
                new ActionBar.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        Button done = (Button) findViewById(R.id.done);
        Button discard = (Button) findViewById(R.id.discard);
        done.setOnClickListener(this);
        discard.setOnClickListener(this);

        /*Fragment editProfileFragment = new EditProfileFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.edit_profile_container, editProfileFragment)
                .addToBackStack(null)
                .commit();*/


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.discard:
                //clear the edit text by talking to the fragment
                break;
            case R.id.done:
                //save the edit text to somewhere maybe shared prefs or a DB?
                break;
        }
    }
}
