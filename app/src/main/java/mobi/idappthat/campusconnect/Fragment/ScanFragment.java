<<<<<<< HEAD
package mobi.idappthat.campusconnect.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import mobi.idappthat.campusconnect.Activity.ScanResultsActivity;
import mobi.idappthat.campusconnect.R;

/**
 * Created by Cameron on 2/21/15.
 */
public class ScanFragment extends Fragment {

    private LinearLayout layout;

    public ScanFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);

        layout = (LinearLayout) view.findViewById(R.id.lLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ScanResultsActivity.class);
                getActivity().startActivity(i);
            }
        });

        return view;
    }
}
=======
package mobi.idappthat.campusconnect.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobi.idappthat.campusconnect.R;

/**
 * Created by Cameron on 2/21/15.
 */
public class ScanFragment extends Fragment {

    public ScanFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);

        return view;
    }
}
>>>>>>> f107dba67da015992bbaa48ec4b29a8f8886db0f
