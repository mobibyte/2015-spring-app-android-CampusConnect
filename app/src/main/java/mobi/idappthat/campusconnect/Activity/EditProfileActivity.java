package mobi.idappthat.campusconnect.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
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


    /**
     * Yay we created a new fragment!!!
     * Needs a container tho!!!!
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button1 = (Button) findViewById(R.id.done);
        Button button2 = (Button) findViewById(R.id.discard);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    //This is the method we need for our onclick implements and we can loop through buttons too!
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
