package vn.vhc.live.erp;

import vn.vhc.live.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.TextView;


public class ProgressingDlg extends Activity implements Runnable {        
        private ProgressDialog pd;
        public static boolean isStop=false;
        public static int index=0;
        public void onCreate(Bundle icicle) {
                super.onCreate(icicle);
                setContentView(R.layout.progress);
                showProcessing();
        }
        public void showProcessing()
        {
        	 pd = ProgressDialog.show(this, "Đang xử lý..", "Đang xử lý dữ liệu", true,
                                false);
                Thread thread = new Thread(this);
                thread.start();                
        }
        public void run() 
        {
        		while(!isStop)
        		{
        			try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
                handler.sendEmptyMessage(0);
        }
        private Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                        pd.dismiss();                       
                }
        };
}
