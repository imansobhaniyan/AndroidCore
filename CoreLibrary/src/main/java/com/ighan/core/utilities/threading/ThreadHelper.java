package com.ighan.core.utilities.threading;

import android.os.Handler;
import android.os.Looper;

public class ThreadHelper {

    public enum TimeoutPeriod {
        IMMEDIATE(0),
        MILLISECONDS_100(100),
        MILLISECONDS_300(300),
        MILLISECONDS_500(500),
        A_SECOND(1000),
        A_MINUTE(60 * 1000);

        private final int period;

        TimeoutPeriod(int period) {
            this.period = period;
        }

        public int getPeriod() {
            return period;
        }
    }

    public static void runWithTimeout(final TimeoutPeriod timeout, Runnable code) {
        runWithTimeout(timeout.getPeriod(), code);
    }

    public static void runWithTimeout(final long timeout, Runnable code) {
        new Thread(() -> {
            if (timeout != TimeoutPeriod.IMMEDIATE.getPeriod())
                try {
                    Thread.sleep(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            code.run();
        }).start();
    }

    public static void updateUi(Runnable r) {
        new Handler(Looper.getMainLooper()).post(r);
    }
}
