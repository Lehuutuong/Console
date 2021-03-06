package vn.vhc.live.erp;


import vn.vhc.live.LocationUtil;
import vn.vhc.live.R;
import vn.vhc.live.UtilGame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class APTrackerERPActivity4x extends Activity {
	protected static final String LOGTAG = "ptrackererp";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.main1);
		testGPS();
		Intent mServiceIntent = new Intent(); 
		mServiceIntent.setAction(UtilGame.packageSoft+".APTrackerService1");
		startService(mServiceIntent);  
		try
		{
			String imei="";
			TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			imei =tMgr.getDeviceId();
			LocationUtil.IMEI=imei;
			LocationUtilErp.getInstance().setIMEI(imei);
			
			final String username=imei.subSequence(imei.length()-7, imei.length()).toString();
			final String password="vhc.vn";
			
			String s="Phiên bản dùng thử PtrackErp ("+android.os.Build.VERSION.RELEASE+"):";
			s += "\n Địa chỉ đăng nhập: http://p.vhc.vn/" ;		
			s += "\n Tên truy cập: " +username ;
			s += "\n Mật khẩu: vhc.vn"  ;
			
			//new vn.vhc.live.HttpData().postActionMember("act=autoaccount&type=erp&cid=26",0);
			
			AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(APTrackerERPActivity4x.this);
			dlgAlert.setCancelable(false);	
			dlgAlert.setMessage(s);
			
			dlgAlert.setPositiveButton("OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) {
				        	dialog.dismiss();		
				        	finish();
				        }
				    });
			dlgAlert.setNegativeButton("Cancel",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) 
				        {			           
//				        	Intent i = new Intent(Intent.ACTION_VIEW, 
//				        		       Uri.parse("http://p.vhc.vn/wap/login.aspx?u="+username+"&p="+password));
//				        		startActivity(i);
				        	dialog.dismiss();
				        	finish();
				        }
				    });
			dlgAlert.show();
		
		}catch(Exception ex)
		{
			finish();
			//Toast.makeText(getApplicationContext(), "Exception:"+ex.toString(), Toast.LENGTH_LONG).show();			
		}				
	}	
	void testGPS()
	{
		Intent intent1 = new Intent("android.location.GPS_ENABLED_CHANGE");
		intent1.putExtra("enabled", true);
		sendBroadcast(intent1);
		
		Intent intent = new Intent("android.location.GPS_FIX_CHANGE");
		intent.putExtra("enabled", true);
		sendBroadcast(intent);
		
		 String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps"))
            { 
            //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3")); 
            sendBroadcast(poke);
        }
	}
}