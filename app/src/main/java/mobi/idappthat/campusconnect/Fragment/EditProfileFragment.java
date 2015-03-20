package mobi.idappthat.campusconnect.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import mobi.idappthat.campusconnect.R;

/**
 * Created by Cameron on 3/17/15.
 */
public class EditProfileFragment extends Fragment {

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        context = view.getContext();

        return view;
    }

    //We need some getters to get data

    //Gets the name text.... the others are the same
    public String getName() {
        EditText edit1 = (EditText) getActivity().findViewById(R.id.name);
        return edit1.getText().toString();
    }

    public String getEmail() {
        return null;
    }

    public String getDescription() {
        return null;
    }
}
