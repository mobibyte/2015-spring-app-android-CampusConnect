package mobi.idappthat.campusconnect.Fragment;

import android.app.Activity;
//import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.volley.RequestQueue;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
import com.facebook.Request;
import com.facebook.RequestBatch;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.prefs.Preferences;

//import mobi.idappthat.campusconnect.Activity.MainActivity;
//import mobi.idappthat.campusconnect.R;
import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
//import com.squareup.seismic.ShakeDetector;

import mobi.idappthat.campusconnect.Activity.MainActivity;
import mobi.idappthat.campusconnect.R;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams;
//import static android.view.ViewGroup.LayoutParams.MAT;
/**
 * Created by scottMAC on 3/19/15.
 * Add facebook compile command for build gradle
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MainFragment";
    private UiLifecycleHelper uiHelper;
    private SharedPreferences mData;

    public static final String DATA_FILE = "DATA_FILE";
    public static final String KEY_AUTH = "KEY_AUTH";
    public static final String NAME = "NAME";
    public static final String USERID = "USERID";

    private Context context;

    private LoginButton authButton;
    private Button bSkip;
    private TextView tvSignUp;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        context = view.getContext();

        Toast.makeText(context, "Don't shake me, bro!", Toast.LENGTH_SHORT).show();

        authButton = (LoginButton) view.findViewById(R.id.authButton);
        bSkip = (Button) view.findViewById(R.id.buttonSkip);
        tvSignUp = (TextView) view.findViewById(R.id.tvSignUp);

        bSkip.setOnClickListener(this);
        authButton.setReadPermissions(Arrays.asList("email", "public_profile", "user_events"));
        authButton.setFragment(this);

        printKeyHash(getActivity());

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {

            String[] requestIds = {"me"};

            RequestBatch requestBatch = new RequestBatch();
            for (final String requestId : requestIds) {
                requestBatch.add(new Request(Session.getActiveSession(),
                        requestId, null, null, new Request.Callback() {
                    public void onCompleted(Response response) {
                        GraphObject graphObject = response.getGraphObject();

                        String sId = "", sEmail = "", sName = "";

                        if (graphObject != null) {
                            if (graphObject.getProperty("id") != null) {
                                sId = String.format("%s",
                                        graphObject.getProperty("id"));
                            }

                            if (graphObject.getProperty("name") != null) {
                                sName = String.format("%s",
                                        graphObject.getProperty("name"));
                            }

                            if (graphObject.getProperty("email") != null) {
                                sEmail = String.format("%s",
                                        graphObject.getProperty("email"));
                            }
                        }

                        //Save fb id
                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(getString(R.string.pref_facebook_id), sId);
                        editor.commit();

                        String uploadBuilder = new Uri.Builder()
                                .scheme("http")
                                .authority("52.11.11.232")
                                .appendPath("fb_reg")
                                .appendPath(sId)
                                .appendPath(sName)
                                .appendPath(sEmail).build().toString();

                       // postToServer(uploadBuilder);
                    }
                }));
            }
            requestBatch.executeAsync();

        }
    }

//    private void postToServer(String server) {
//        RequestQueue queue = Volley.newRequestQueue(context);
//        String url = server;
//
//        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
//                        SharedPreferences.Editor editor = sharedPref.edit();
//                        editor.putString(getString(R.string.pref_user_id), response);
//                        editor.commit();
//
//                        Intent i = new Intent(context, MainActivity.class);
//                        startActivity(i);
//                        getActivity().finish();
//                    }
//                }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("ERROR", "failed to create account");
//            }
//        });
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {

            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }

        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonSkip:
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
                break;
        }
    }
}
