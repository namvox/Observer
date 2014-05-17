package com.vhnam.servers;

import java.util.Observable;

public class WebServer extends Observable {
	private static WebServer mWebServer = new WebServer();

	public static WebServer getInstance() {
		return mWebServer;
	}

	public void getDataFromServer() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					setChanged();
					notifyObservers(i);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(runnable).start();

	}
}
