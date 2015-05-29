package pl.allergyfoodadvisor.extras;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusProvider {

    private static BusProvider mInstance = null;
    private Bus mBus = new Bus(ThreadEnforcer.ANY);

    public BusProvider() {

    }

    public static BusProvider getInstance() {
        if (mInstance == null) {
            mInstance = new BusProvider();
        }
        return mInstance;
    }

    public Bus getBus() {
        return this.mBus;
    }
}
