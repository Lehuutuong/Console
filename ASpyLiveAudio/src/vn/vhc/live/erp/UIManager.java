

package vn.vhc.live.erp;
import vn.vhc.live.HttpData;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.provider.Contacts.Intents.UI;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class UIManager {

	public static UIManager _instance;
	
	public UIManager(){}

	public static UIManager getInstance() {
		// TODO Auto-generated method stub
		if(_instance==null) _instance= new UIManager();
		return _instance;
	}
	public void showProgress()
	{	
//		ProgressingDlg.isStop=true;
//		ProgressingDlg.index=0;
//		
//		NavigateScreen.getInstance().switchDisplay(ProgressingDlg.class);
	}
	public void hideProgress()
	{
		ProgressingDlg.isStop=false;
		ProgressingDlg.index=0;		
		//NavigateScreen.getInstance().switchDisplay(ProgressingDlg.class);
	}
	
	public String refineMsg(String msg)
	{
		
		//if(msg.toLowerCase().startsWith("error:")) msg="Lỗi kết nối mạng: hãy thử lại";//testing
		return msg;
	}
	public void showMsg(String msg)
	{
		msg=refineMsg(msg);
		
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(NavigateScreen.getInstance().currentActivity);

		dlgAlert.setMessage(msg);
		dlgAlert.setTitle("Thông báo");
		//dlgAlert.setPositiveButton("OK", null);
		dlgAlert.setCancelable(true);
		
		
		dlgAlert.setPositiveButton("Ok",
			    new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			          //dismiss the dialog  
			        	dialog.dismiss();
			        }
			    });
		dlgAlert.create().show();
	}
	
	public void showMsgWithToast(String title)
	{
		Toast.makeText(ContextManagerErp.getInstance().getCurrentContext(),title,Toast.LENGTH_LONG).show();
	}
	public void showMsgWithTitlte(String title,String msg)
	{
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(NavigateScreen.getInstance().currentActivity);

		dlgAlert.setMessage(msg);
		dlgAlert.setTitle(title);		
		//dlgAlert.setPositiveButton("OK", null);
		dlgAlert.setCancelable(true);
		
		
		dlgAlert.setPositiveButton("Ok",
			    new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			          //dismiss the dialog  
			        	dialog.dismiss();
			        }
			    });
		dlgAlert.create().show();
	}
	public void showMsgWithTitleOKCancel(String title,String msg,final String idx)
	{
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(NavigateScreen.getInstance().currentActivity);

		dlgAlert.setMessage(msg);
		dlgAlert.setTitle(title);
		//dlgAlert.setPositiveButton("OK", null);
		dlgAlert.setCancelable(true);
		
		
		dlgAlert.setPositiveButton("Xác nhận hoàn tất",
			    new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	UIManager.getInstance().startShowProgress();
			          //dismiss the dialog  
			        	(new HttpData()).getData("reporteds.aspx","idx="+idx+"&mode=update&statusx=done");
			        	UIManager.getInstance().stopShowProgress();
			        	dialog.dismiss();			        	
			        }
			    });
		dlgAlert.setNegativeButton("Cancel",
			    new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			          //dismiss the dialog  
			        	dialog.dismiss();
			        }
			    });
		dlgAlert.create().show();
	}
	public void showMsgNotButtonOk(String msg)
	{
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(NavigateScreen.getInstance().currentActivity);

		dlgAlert.setMessage(msg);
		dlgAlert.setTitle("Information");
		//dlgAlert.setPositiveButton("OK", null);
		dlgAlert.setCancelable(true);
		
		
//		dlgAlert.setPositiveButton("Ok",
//			    new DialogInterface.OnClickListener() {
//			        public void onClick(DialogInterface dialog, int which) {
//			          //dismiss the dialog  
//			        	dialog.dismiss();
//			        }
//			    });
		dlgAlert.create().show();
	}
	private ProgressDialog pd ;
	public static Boolean isstop ;
	private Handler handler= new Handler() ;
	
	
	public void startShowProgress() {
		isstop=false;
		//pd = new ProgressDialog(NavigateScreen.getInstance().currentActivity);	           
	   pd = ProgressDialog.show(NavigateScreen.getInstance().currentActivity, "Đang xử lý..", "Đang xử lý dữ liệu", true,false);	           
	    
		// Do something long
		Runnable runnable = new Runnable() {
			@Override
			public void run() 
			{
				while(!isstop)
        		{
        			try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}               
				handler.post(new Runnable() {
					@Override
					public void run() {
						pd.dismiss();
					}
				});
			}
		};
		new Thread(runnable).start();
	}	
	public void stopShowProgress()
	{
		isstop=true;					
	}
	
	public ProgressDialog pDialog;
	public void showDialog(int id) {
		pDialog = new ProgressDialog(NavigateScreen.getInstance().currentActivity);
		pDialog.setMessage("Loading...");
		pDialog.setIndeterminate(false);
		pDialog.setMax(100);
		pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pDialog.setCancelable(true);
		pDialog.show();		
	}
	
	public void showDialogSpiner() {
		pDialog = new ProgressDialog(NavigateScreen.getInstance().currentActivity);
		pDialog.setMessage("Loading...");
		pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pDialog.setCancelable(true);
		pDialog.show();		
	}
	public void showDialogSpinerWithTitle(String sMsg) {
		pDialog = new ProgressDialog(NavigateScreen.getInstance().currentActivity);
		pDialog.setMessage(sMsg);
		pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pDialog.setCancelable(true);
		pDialog.show();		
	}
	public void setProgress(int percent) 
	{
		pDialog.setProgress(percent);	
	}
	public void setMessage(String msg) 
	{
		pDialog.setMessage(msg);	
	}
	public void dismissDialog() 
	{
		if(pDialog==null) return;
		pDialog.dismiss();	
	}
}
