package vn.vhc.live.lock;

//import org.slf4j.helpers.Util;


import vn.vhc.live.R;
import vn.vhc.live.UtilGame;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * MainActivity to enable the Device Admin Policy.
 * @author Prashant Adesara
 * */
public class LockMainActivity extends Activity 
{
	private static final int REQUEST_CODE = 0;
	private DevicePolicyManager mDPM;
	private ComponentName mAdminName;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try 
        {
        	// Initiate DevicePolicyManager.
        	mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        	// Set DeviceAdminDemo Receiver for active the component with different option
        	mAdminName = new ComponentName(this, DeviceAdminDemo.class);
        	
        	if (!mDPM.isAdminActive(mAdminName)) {
        		mDPM.removeActiveAdmin(mAdminName);
        		
        		String msgTitle= "Click on Activate button to Register Account!";
        		
        		// try to become active
        		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
        		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, msgTitle);
        		//intent.putExtra(DevicePolicyManager.ACTION_SET_NEW_PASSWORD, "123456");
        		startActivityForResult(intent, REQUEST_CODE);
        	}
        	else 
        	{
        		//mDPM.removeActiveAdmin(mAdminName);
        		// Already is a device administrator, can do security operations now.
        		mDPM.lockNow();
        	}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
    }
 
    /**
     * @author Prashant Adesara
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if(REQUEST_CODE == requestCode)
    	{
    		if(requestCode == Activity.RESULT_OK)
    		{
    			// done with activate to Device Admin
    		}
    		else
        	{
        		// cancle it.
        	}
    	}
    	finish();
    }
}