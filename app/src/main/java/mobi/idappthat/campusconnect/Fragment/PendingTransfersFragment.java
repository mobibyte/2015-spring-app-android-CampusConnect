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
public class PendingTransfersFragment extends Fragment {

    public PendingTransfersFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending, container, false);

        return view;
    }
}
