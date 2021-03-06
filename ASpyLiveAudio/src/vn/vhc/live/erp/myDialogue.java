package vn.vhc.live.erp;

import vn.vhc.live.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class myDialogue {
	private Context mContext;

	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	public myDialogue(Context mContext) {
		this.mContext = mContext;
	}

	public Products showDialoge(String title, final Products products) {
		final Dialog dialog = new Dialog(mContext,android.R.style.Theme_Dialog);
		dialog.setContentView(R.layout.dialogue_agency);
		dialog.setTitle(title);
		Button btnSave = (Button) dialog.findViewById(R.id.btn_ok);
		final EditText editName = (EditText) dialog
				.findViewById(R.id.edit_title);
		final EditText editAddress = (EditText) dialog
				.findViewById(R.id.edit_address);
		final EditText editNote = (EditText) dialog
				.findViewById(R.id.edit_note);
		editName.setText(products.ProductName);
		editAddress.setText(products.Address);
		editNote.setText(products.Note);
		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				products.ProductName = editName.getText().toString();
				products.Address = editAddress.getText().toString();
				products.Note = editNote.getText().toString();
				dialog.dismiss();

			}
		});
		Button btnCancel = (Button) dialog.findViewById(R.id.btn_canel_order);
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		dialog.show();
		return products;
	}
}
