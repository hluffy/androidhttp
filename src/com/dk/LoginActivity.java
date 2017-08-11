package com.dk;


import org.json.JSONException;
import org.json.JSONObject;

import com.dk.http.PostUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	private EditText username;
	private EditText password;
	private Button myButton;
	private String mesg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		bindView();
		
	}
	
	private void bindView(){
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		myButton = (Button)findViewById(R.id.myButton);
		
		myButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		final String nameString = username.getText().toString();
		final String passwordString  = password.getText().toString();
		System.out.println("---------------------------");
		System.out.println(nameString);
		System.out.println(passwordString);
		new Thread(){
			public void run() {
				mesg = PostUtil.loginByPost(nameString, passwordString);
				handler.sendEmptyMessage(0x001);
			};
		}.start();
		
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==0x001){
				try {
					JSONObject json = new JSONObject(mesg);
//					Toast.makeText(LoginActivity.this, json.getString("message"), Toast.LENGTH_SHORT).show();
					Toast.makeText(LoginActivity.this, mesg, Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
	};
	
	
	
	


}
