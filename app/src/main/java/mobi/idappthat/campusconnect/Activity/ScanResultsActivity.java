package mobi.idappthat.campusconnect.Activity;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobi.idappthat.campusconnect.Adapter.UserResultsAdapter;
import mobi.idappthat.campusconnect.Model.User;
import mobi.idappthat.campusconnect.R;

/**
 * Created by Cameron on 3/18/15.
 * Bluetooth should get data and add it to a list which can be used to fill your list view
 * -Anthony
 * TODO: Alot of work still needs to be done with discovery/transfers and performance
 */
public class ScanResultsActivity extends ActionBarActivity {

    private RecyclerView rvResults;
    private CheckBox cbSelectAll;
    private UserResultsAdapter resultsAdapter;

    private List<User> tempUsers;

    private static final int REQUEST_ENABLE_BT = 1;
    private static BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice device;
    private Intent receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_results);

        initializeBluetooth();
        discoverDevices();
        setDiscoverable();


        /* Temp view items - Fake data */
        tempUsers = new ArrayList<>();
        tempUsers.add(new User("123", "Cameron Moreau"));
        tempUsers.add(new User("123", "Jack Hammer"));
        tempUsers.add(new User("123", "Jacob Slug"));
        tempUsers.add(new User("123", "James Doug"));

        /* Inflate layout and set properties */
        rvResults = (RecyclerView) findViewById(R.id.rvResults);
        cbSelectAll = (CheckBox) findViewById(R.id.cbSelectAll);

        rvResults.setLayoutManager(new LinearLayoutManager(this));
        rvResults.setItemAnimator(new DefaultItemAnimator());

        /* Select All button */
        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for(int i = 0; i < rvResults.getChildCount(); i++) {
                    View item = rvResults.getChildAt(i);
                    UserResultsAdapter.ViewHolder holder = new UserResultsAdapter.ViewHolder(item);
                    holder.updateChecked(isChecked);
                }
            }
        });

        resultsAdapter = new UserResultsAdapter(tempUsers, R.layout.row_user_result, this);
        rvResults.setAdapter(resultsAdapter);
    }

    @Override
    protected void onPause() {
        mBluetoothAdapter.cancelDiscovery();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mBluetoothAdapter.cancelDiscovery();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(bReceiver);
        mBluetoothAdapter.cancelDiscovery();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        if(resCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Bluetooth turned on",
                Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth is rquired for scan",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //If there is no bluetooth kill the app
        if(mBluetoothAdapter == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Your devices does not support bluetooth!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    });
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);
            Log.d("Bluetooth", "Bluetooth request sent");
        }
    }

    private final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.d("Bluetooth", "searching...");
            String action = intent.getAction();
            //Needs a sync to get parse data for better data
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                tempUsers.add(new User(getName(device), getMacAddress(device)));
                resultsAdapter.notifyDataSetChanged();
                Log.d("Bluetooth", "User found");
            }
        }
    };

    public String getMacAddress(BluetoothDevice d) {
        return d.getAddress();
    }

    private String getName(BluetoothDevice d) {
        return d.getName();
    }

    public void setDiscoverable() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        //startActivityForResult(discoverableIntent, RESULT_OK);
    }

    //TODO: check for already paired devices for a ease & performance
    public void discoverDevices() {
        mBluetoothAdapter.startDiscovery();
        this.registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        Log.d("Bluetooth", "starting discovery");
    }

    private void getUserInfo() {
        // TODO Parse student mac address look up here
    }


}
