package mobi.idappthat.campusconnect.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobi.idappthat.campusconnect.R;

/**
 * Created by Cameron on 3/17/15.
 */
public class EditProfileFragment extends Fragment {

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        context = view.getContext();

        return view;
    }
}
