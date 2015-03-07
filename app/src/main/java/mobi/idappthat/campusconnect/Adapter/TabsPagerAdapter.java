package mobi.idappthat.campusconnect.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mobi.idappthat.campusconnect.Fragment.GroupsFragment;
import mobi.idappthat.campusconnect.Fragment.PendingTransfersFragment;
import mobi.idappthat.campusconnect.Fragment.ScanFragment;

/**
 * Created by Cameron on 2/21/15.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    private final String[] TITLES = {"Scan", "Pending Transfers", "Groups"};

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new ScanFragment();
            case 1:
                return new PendingTransfersFragment();
            case 2:
                return new GroupsFragment();
            default:
                return null;
        }
    }
}
