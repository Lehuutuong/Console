package vn.vhc.live.erp;

import java.util.ArrayList;
import java.util.List;

import vn.vhc.live.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ProcessOrderListAdapter extends ArrayAdapter<Products> {
	public static  ArrayList<Products> original;
	private ArrayList<Products> fitems;
	private Filter filter;
	private Activity mcontext;
	private LayoutInflater inflater;
	public static String NewNumber = "";
	public static String OdlName = "";
	public int createOrder = 0;
	public static int viewisNull=0;
	ListView pGridView = null;
	String[] spinnerValues ;
	String[] spinnerSubs ;
	public ProcessOrderListAdapter(Activity context,
			int textViewResourceId, List<Products> objects) {
		super(context, textViewResourceId, objects);
		this.original = new ArrayList<Products>(objects);
		this.fitems = new ArrayList<Products>(objects);
		this.mcontext = context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		if (filter == null)
			filter = new PkmnNameFilter();

		return filter;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			inflater = mcontext.getLayoutInflater();
			convertView = inflater.inflate(R.layout.processes_order_listitem, parent,
					false);

			holder.txtName = (TextView) convertView
					.findViewById(R.id.txt_nameproduct);
			holder.editNumber = (TextView) convertView
					.findViewById(R.id.edit_product);
			holder.ckcBox = (CheckBox) convertView
					.findViewById(R.id.ckc_prdouct);
			holder.txtID = (TextView) convertView
					.findViewById(R.id.txt_productid);
			holder.btnUnit=(Button)convertView.findViewById(R.id.btn_unit);
			viewisNull=1;
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		if (fitems == null) {
			fitems = new ArrayList<Products>(original);
		}
		final Products prudcts = fitems.get(position);// getItem(position);
		holder.txtName.setText(prudcts.ProductName);
		holder.editNumber.setText(prudcts.ProductNumber);
		holder.txtID.setText(prudcts.ID);
		holder.ckcBox.setSelected(false);
		holder.btnUnit.setText(prudcts.UnitTtile);
		if(viewisNull==1){
			holder.ckcBox.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try{
						int postionOld = -1;
						Products prouctList;// = new Products();
						String id = holder.txtID.getText().toString();
						postionOld=position;
						holder.ckcBox.setChecked(false);
						Products productAdd=original.get(postionOld);
						ProcessOrderSearchAdapter.original.add(productAdd);
						//Processes_Order.arrListStore.add(productAdd);
						original.remove(postionOld);
						//Processes_Order.arrListActive.remove(postionOld);
						getFilter().filter("");
					}
					catch(Exception e){
						
					}
				}
			});
			holder.btnUnit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int postionOld = position;
					Products prouctList;// = new Products();
					//String id = holder.txtID.getText().toString();
					//String id = holder.txtID.getText().toString();
					//postionOld= LoadDataGrid(id);
					if(postionOld>-1){
						try{
							prouctList=original.get(postionOld);
							final Dialog dialog= new Dialog(mcontext);
							dialog.setContentView(R.layout.custom_dialog);
							dialog.setTitle(prouctList.ProductName);
							final Spinner mySpiner=(Spinner)dialog.findViewById(R.id.spinner_show);
							final SpinerApdater spinerAdapter = new SpinerApdater(mcontext,R.layout.custom_spiner,prouctList.DatasourceText,prouctList.DatasourceId,postionOld,"search");
							mySpiner.setAdapter(spinerAdapter);
						    Button btnSave=(Button)dialog.findViewById(R.id.btn_ok);
						    Button btnCancel=(Button)dialog.findViewById(R.id.btn_canel_order);
							btnCancel.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									dialog.dismiss();
								}
							});
						    final EditText editText=(EditText)dialog.findViewById(R.id.edit_txt);
							editText.setText(prouctList.ProductNumber);
							editText.selectAll();
							editText.requestFocus();
							editText.postDelayed(new Runnable() {
			                    @Override
			                    public void run() {
			                        InputMethodManager keyboard = (InputMethodManager)
			                       mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
			                        keyboard.showSoftInput(editText, 0);
			                    }
			                },200);
							btnSave.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									int kx=mySpiner.getSelectedItemPosition();
									original.get(spinerAdapter.index).UnitID=original.get(spinerAdapter.index).DatasourceId[kx];
								    original.get(spinerAdapter.index).UnitTtile=original.get(spinerAdapter.index).DatasourceText[kx];
									original.get(spinerAdapter.index).ProductNumber=editText.getText()+"";
									//Processes_Order.arrListActive.get(spinerAdapter.index).UnitID=original.get(spinerAdapter.index).DatasourceId[kx];
									//Processes_Order.arrListActive.get(spinerAdapter.index).ProductNumber=editText.getText()+"";
									dialog.dismiss();
								}
							});
							for(int i=0;i<prouctList.DatasourceId.length;i++){
								if(prouctList.DatasourceId[i]==prouctList.UnitID){
									mySpiner.setSelection(i);
									break;
								}
							}
							dialog.show();
						}
						catch(Exception e){
							Toast.makeText(mcontext, e.toString(),Toast.LENGTH_LONG).show();
						}
					}
				
				}
			});
		}
		return convertView;
	}
	public int LoadDataGrid(String Id) {

//		for (int i = 0; i < Processes_Order.arrListActive.size(); i++) {
//			//if (Processes_Order.arrListActive.get(i).ID == Id) {
//			//	return i;
//			//}
//		}
		return -1;

	}
	private class PkmnNameFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			// TODO Auto-generated method stub
			FilterResults filterResult = new FilterResults();
			String prefix = constraint.toString().toLowerCase();

			if (prefix == null || prefix.length() == 0) {
				ArrayList<Products> list = new ArrayList<Products>(original);
				filterResult.values = list;
				filterResult.count = list.size();
			} else {
				prefix = UtilErp.TrimVietnameseMark(prefix);
				final ArrayList<Products> list = new ArrayList<Products>(
						original);
				final ArrayList<Products> nlist = new ArrayList<Products>();
				int count = list.size();
				for (int i = 0; i < count; i++) {
					final Products products = list.get(i);
					final String value = products.ProductNameE.toLowerCase();
					if (value.contains(prefix)) {
						nlist.add(products);
					}
				}
				filterResult.values = nlist;
				filterResult.count = nlist.size();

				Log.d("Size result1", nlist.size() + "");
			}
			return filterResult;

		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			// TODO Auto-generated method stub
			fitems = (ArrayList<Products>) results.values;
			clear();
			int count = fitems.size();
			for (int i = 0; i < count; i++) {
				Products pkmn = (Products) fitems.get(i);
				add(pkmn);
				// notifyDataSetInvalidated();
			}
			// TODO Auto-generated method stub
			Log.d("Size result", count + "");
			
		}
		
	}
	static class ViewHolder {
		TextView editNumber;
		TextView txtName;
		TextView txtID;
		CheckBox ckcBox;
		Button btnUnit;
	}
}
