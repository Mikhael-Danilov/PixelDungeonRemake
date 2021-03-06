package com.coner.android.util;

import android.content.Context;
import android.content.res.Configuration;

import com.coner.pixeldungeon.remake.EventCollector;

import org.acra.ACRA;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by mike on 01.03.2016.
 */
public class Util {
	static public String stackTraceToString(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK)
				>= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	static public String toString(Exception e) {
		return e.getMessage() + "\n" + Util.stackTraceToString(e) + "\n";
	}

	static public void storeEventInAcra(String eventKey,Exception e) {
		EventCollector.logException(e);
		if(!ACRA.isInitialised()){
			return;
		}
		ACRA.getErrorReporter().putCustomData(eventKey, toString(e));
	}

	static public void storeEventInAcra(String eventKey,String str) {
		if(!ACRA.isInitialised()){
			return;
		}
		ACRA.getErrorReporter().putCustomData(eventKey, str);
	}

	public static boolean isConnectedToInternet() {
		InetAddress ipAddr;
		try {
			ipAddr = InetAddress.getByName("google.com");
		} catch (UnknownHostException e) {
			return false;
		}

		return !ipAddr.toString().equals("");
	}
}
