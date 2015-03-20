package mobi.idappthat.campusconnect.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.app.Activity;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import mobi.idappthat.campusconnect.R;


public class SignupActivity extends Activity implements View.OnClickListener {

    private EditText et_User, et_Password1, et_Password2;
    private Button b_Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // signup form.
        et_User = (EditText) findViewById(R.id.et_user2);
        et_Password1 = (EditText) findViewById(R.id.et_password1);
        et_Password2 = (EditText) findViewById(R.id.et_password2);

        // sign up button
        b_Signup = (Button) findViewById(R.id.button_signup2);

        //listen to button
        b_Signup.setOnClickListener(this);
    }


    @Override
    public void onClick(View v1) {

        // Validate the sign up data
        boolean validationError = false;
        StringBuilder validationErrorMessage =
                new StringBuilder(getResources().getString(R.string.error_intro));
        if (isEmpty(et_User)) {
            validationError = true;
            validationErrorMessage.append(getResources().getString(R.string.error_blank_username));
        }
        if (isEmpty(et_Password1)) {
            if (validationError) {
                validationErrorMessage.append(getResources().getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
        }
        if (!isMatching(et_Password1, et_Password2)) {
            if (validationError) {
                validationErrorMessage.append(getResources().getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getResources().getString(
                    R.string.error_mismatched_passwords));
        }
        validationErrorMessage.append(getResources().getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(SignupActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // progress dialog
        final ProgressDialog dlg = new ProgressDialog(SignupActivity.this);
        dlg.setTitle("Please wait.");
        dlg.setMessage("Signing up.  Please wait.");
        dlg.show();

        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_id));
        // new Parse user
        ParseUser user = new ParseUser();
        user.setUsername(et_User.getText().toString());
        user.setPassword(et_Password1.getText().toString());
        // Call the Parse signup method
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Show a simple Toast message upon successful registration
                    Toast.makeText(getApplicationContext(),
                            "Successfully Signed up, please log in.",
                            Toast.LENGTH_LONG).show();
                    dlg.hide();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Sign up Error", Toast.LENGTH_LONG)
                            .show();
                }
            }

//            @Override
//            public void done(ParseException e) {
//                dlg.dismiss();
//                if (e != null) {
//                    // Show the error message
//                    Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                } else {
//                    // Start an intent for the main activity
//                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
////                    finish();
//                }
//            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // check if text view is empty
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    //check if text views are matching
    private boolean isMatching(EditText etText1, EditText etText2) {
        if (etText1.getText().toString().equals(etText2.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }


}