package vn.vhc.live.erp;

import vn.vhc.live.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressActivity extends Activity {
	private Handler handler;
	private ProgressBar progress;

	
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progressbar);
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		handler = new Handler();
	}
	public void startProgress(View view) {
		// Do something long
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 10; i++) {
					final int value = i;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						@Override
						public void run() {
							progress.setProgress(value);
						}
					});
				}
			}
		};
		new Thread(runnable).start();
	}
}
