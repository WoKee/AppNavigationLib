package com.fph.appnavigationlib.eventbus;

import com.hwangjr.rxbus.Bus;
import com.hwangjr.rxbus.thread.ThreadEnforcer;

/**
 * Maintains a singleton instance for obtaining the bus. Ideally this would be replaced with a more efficient means
 * such as through injection directly into interested classes.
 */
public class BusProvider {
    /**
     * Check out the bus, like identifier or thread enforcer etc.
     */
	private static class Holder{
		public static final Bus BUS = new Bus(ThreadEnforcer.ANY);
	}

    private BusProvider() {
    }

    public static Bus getInstance() {
        return Holder.BUS;
    }
}
