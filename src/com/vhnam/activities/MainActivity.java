package com.vhnam.activities;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.vhnam.observer.R;
import com.vhnam.servers.WebServer;

public class MainActivity extends Activity implements Observer {
	private TextView mTxtNumber;
	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		mTxtNumber = (TextView) findViewById(R.id.txtNumber);
		mTxtNumber.setText("Loading...");
		 WebServer webServer = WebServer.getInstance();
		 webServer.addObserver(this);
		 webServer.getDataFromServer();
	}

	@Override
	public void update(Observable observable, final Object data) {
		if (observable instanceof WebServer) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					mTxtNumber.setText((Integer) data + "");
				}
			});
		}
	}
}
