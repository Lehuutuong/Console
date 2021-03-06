package vn.vhc.live.erp;

import java.util.Vector;

import vn.vhc.live.R;

import android.app.TabActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class FrmReportTab extends TabActivity {
	public  String[] DATATEXT = new String[] {"xxx"};
	public  static Vector<String> lstProduct= new Vector<String>();
	public  static Vector<String> lstUnit= new Vector<String>();
	public  static Vector<String> lstTitle= new Vector<String>();
	
	public  static String note = "";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frmordertab);
		setTitle("Báo cáo công việc");
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
 
		// Apple tab
//		Intent intentDone = new Intent().setClass(this, LstReportOther.class);
//		intentDone.putExtra("typeid", "other");
		
//		TabSpec tabSpecDone = tabHost
//		  .newTabSpec("CV Hoàn tất")
//		  .setIndicator("CV Hoàn tất")
//		  //.setIndicator("", ressources.getDrawable(R.drawable.icon))
//		  .setContent(intentDone);
//		
//		// Android tab
//		Intent intentAndroid = new Intent().setClass(this, LstReportOrder.class);
//		intentAndroid.putExtra("typeid", "order");
//		intentAndroid.putExtra("status", "confirmedrequest");
//		
//		TabSpec tabSpecAndroid = tabHost
//		  .newTabSpec("Yêu cầu đặt hàng")
//		  .setIndicator("Yêu cầu đặt hàng")
//		  //.setIndicator("", ressources.getDrawable(R.drawable.ic_launcher))
//		  .setContent(intentAndroid);
// 
//		// Apple tab
//		Intent intentApple = new Intent().setClass(this, LstReportOrderDone.class);
//		intentApple.putExtra("typeid", "order");
//		intentApple.putExtra("status", "done");
//		
//		TabSpec tabSpecApple = tabHost
//		  .newTabSpec("Đ/h Hoàn tất")
//		  .setIndicator("Đ/h Hoàn tất")
//		  //.setIndicator("", ressources.getDrawable(R.drawable.icon))
//		  .setContent(intentApple);
		
		
		/*
		// Windows tab
		Intent intentWindows = new Intent().setClass(this, WindowsActivity.class);
		TabSpec tabSpecWindows = tabHost
		  .newTabSpec("Windows")
		  .setIndicator("", ressources.getDrawable(R.drawable.icon_windows_config))
		  .setContent(intentWindows);
 
		// Blackberry tab
		Intent intentBerry = new Intent().setClass(this, BlackBerryActivity.class);
		TabSpec tabSpecBerry = tabHost
		  .newTabSpec("Berry")
		  .setIndicator("", ressources.getDrawable(R.drawable.icon_blackberry_config))
		  .setContent(intentBerry);
		 */
		// add all tabs 
		//tabHost.addTab(tabSpecDone);
		//tabHost.addTab(tabSpecAndroid);
		//tabHost.addTab(tabSpecApple);
		
		//tabHost.addTab(tabSpecWindows);
		//tabHost.addTab(tabSpecBerry);
 
		//set Windows tab as default (zero based)
		
		NavigateScreen.getInstance().setCurrentDisplay(this);
		ContextManagerErp.getInstance().setCurrentContext(this);
	       
		tabHost.setCurrentTab(0);
	}
	@Override
	public void onResume() {
        super.onResume();
        NavigateScreen.getInstance().setCurrentDisplay(this);
    } 

}
