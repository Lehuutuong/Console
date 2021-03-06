package vn.vhc.live.erp;


import vn.vhc.live.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class DialogPicture extends Activity{
	EditText txtChatContent;
	String toUsername;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
		super.onCreate(savedInstanceState);
       
		//init information chat
		String toUsername=null;
		if(getIntent().getExtras()!=null)
		{
			toUsername=  getIntent().getExtras().getString("imgtoshow");
		}
		
        setContentView(R.layout.dialog_chat);
       
//        ImageView close = (ImageView) findViewById(R.id.close);  
//        close.setOnClickListener(new View.OnClickListener() 
//        {  
//        		public void onClick(View v)
//        		{  
//        			finish();
//        		}  
//        });  
        ImageView jpgView = (ImageView) findViewById(R.id.img);        
        Bitmap bitmap = BitmapFactory.decodeFile(toUsername);
        jpgView.setImageBitmap(bitmap);        
        jpgView.setOnClickListener(new View.OnClickListener() 
        {  
        		public void onClick(View v)
        		{  
        			//finis(10001);
        			finish();
        		}  
        });  
	}	
}
