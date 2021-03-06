package vn.vhc.live.erp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import vn.vhc.live.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class TestActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageviewtest);
		
		
		ImageView img=(ImageView)findViewById(R.id.imageView1);
		
		try {
			URL url = new  URL(("http://file1.m4me.vn/upload/20121128/405a18b6-e559-46a9-8138-6371b791de3eOnew%20%28SHINee%29.jpg"));
			
			Bitmap bmp;	
			bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			img.setImageBitmap(bmp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String refineURL(String urlImage) throws Exception
	{		
		String[] finalSeperated=urlImage.split("/");
		
		ArrayList<String> completeUrl= new ArrayList<String>();
		for(int jx=0;jx<=finalSeperated.length-1;jx++)
		{
			if(jx==finalSeperated.length-1) completeUrl.add(URLEncoder.encode(finalSeperated[jx], "UTF-8"));
			else completeUrl.add(finalSeperated[jx]);
		}
		ArrayList<String> completeUrlFix = new ArrayList<String>();
	    StringBuilder newUrl = new StringBuilder();
	    for(String string : completeUrl) {
	        if(string.contains("+")) {
	            String newString = string.replace("+", "%20");
	            completeUrlFix.add(newString);
	        } else {
	            completeUrlFix.add(string);
	        }
	    }
	    for(int jx=0;jx<=completeUrlFix.size()-1;jx++)
		{
	    	if(jx==0)newUrl.append(completeUrlFix.get(jx));
	    	else newUrl.append("/"+completeUrlFix.get(jx));
	    	
		}
//	    for(String string : completeUrlFix) {
//	        newUrl.append(string);
//	    }
	    System.out.println(newUrl.toString());
	    return newUrl.toString();
	}
	
}
