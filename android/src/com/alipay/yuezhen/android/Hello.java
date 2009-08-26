package com.alipay.yuezhen.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Hello extends Activity {	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    
    private void init(){
    	setContentView(R.layout.main);       
        final Button callButton = (Button) findViewById(R.id.callButton); 
        
        callButton.setOnClickListener(new Button.OnClickListener() { 
        	public void onClick(View v){ 
        		final EditText phoneNumber = (EditText) findViewById(R.id.password);
        		if(phoneNumber.getText().toString().equals("dabige")){        			
        			success();        			
        		}else{
        			wrong();
        		}
        	} 
        }); 
    }
    
    private void wrong(){
    	setContentView(R.layout.wrong);     
    	final Button backButton = (Button) findViewById(R.id.backButton); 
		backButton.setOnClickListener(new Button.OnClickListener() { 
			public void onClick(View v){ 
				init();      
			}
		});
    }
    
    private void success(){
    	setContentView(R.layout.win);
    	final Button successButton = (Button) findViewById(R.id.successButton); 
    	final Button initButton = (Button) findViewById(R.id.initButton); 
    	
		successButton.setOnClickListener(new Button.OnClickListener() { 
			public void onClick(View v){ 
				finish();
			}
		});
		
		initButton.setOnClickListener(new Button.OnClickListener() { 
			public void onClick(View v){ 
				init();
			}
		});
    }
}