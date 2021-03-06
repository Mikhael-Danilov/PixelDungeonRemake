package com.coner.android.util;

import com.coner.pixeldungeon.remake.EventCollector;

/**
 * Created by DeadDie on 18.03.2016
 */
public class TrackedRuntimeException extends RuntimeException {

	public TrackedRuntimeException( Exception e) {
		super(e);
		EventCollector.logFatalException(e,"");
	}

	public TrackedRuntimeException( String s) {
		super(s);
		EventCollector.logFatalException(this,s);
	}

    public TrackedRuntimeException( String s,Exception e) {
        super(s,e);
        EventCollector.logFatalException(this,s);
    }
}
