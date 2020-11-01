package com.ighan.core.utilities.date;

public class TimeSpan implements Comparable<TimeSpan>, java.io.Serializable, Cloneable {

    public enum TimeUnit {
        MILLISECONDS(1),
        SECONDS(MILLISECONDS.getMilliseconds() * 1000),
        MINUTES(SECONDS.getMilliseconds() * 60),
        HOURS(MINUTES.getMilliseconds() * 60);

        private int milliseconds;

        TimeUnit(int milliseconds) {
            this.milliseconds = milliseconds;
        }

        public int getMilliseconds() {
            return milliseconds;
        }
    }

    private final long time;

    ///region constructors

    public TimeSpan(long time) {
        this.time = time;
    }

    public TimeSpan(TimeUnit units, long value) {
        this(TimeSpan.toMilliseconds(units, value));
    }

    ///endregion

    ///region get time parts

    public long getMilliseconds() {
        return (((this.time % TimeUnit.HOURS.getMilliseconds()) % TimeUnit.MINUTES.getMilliseconds()) % TimeUnit.MILLISECONDS.getMilliseconds())
                / TimeUnit.MILLISECONDS.getMilliseconds();
    }

    public long getTotalMilliseconds() {
        return this.time;
    }

    public long getSeconds() {
        return ((this.time % TimeUnit.HOURS.getMilliseconds()) % TimeUnit.MINUTES.getMilliseconds()) / TimeUnit.SECONDS.getMilliseconds();
    }

    public int getTotalSeconds() {
        return (int) (this.time / TimeUnit.SECONDS.getMilliseconds());
    }

    public int getMinutes() {
        return (int) (this.time % TimeUnit.HOURS.getMilliseconds()) / TimeUnit.MINUTES.getMilliseconds();
    }

    public double getTotalMinutes() {
        return (int) (this.time / TimeUnit.MINUTES.getMilliseconds());
    }

    public int getHours() {
        return (int) (this.time / TimeUnit.HOURS.getMilliseconds());
    }

    ///endregion

    ///region object methods

    public int compareTo(TimeSpan obj) {
        if (this.time < obj.time)
            return -1;
        else if (this.time > obj.time)
            return +1;
        else
            return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TimeSpan)
            return this.time == ((TimeSpan) obj).time;
        else
            return false;
    }

    ///endregion

    ///region operators

    public TimeSpan add(TimeUnit units, long value) {
        return new TimeSpan(units, value);
    }

    public TimeSpan subtract(TimeUnit units, long value) {
        return add(units, -value);
    }

    ///endregion

    ///region static methods

    public static TimeSpan subtract(java.util.Date left, java.util.Date right) {
        return new TimeSpan(left.getTime() - right.getTime());
    }

    public static int compare(TimeSpan first, TimeSpan second) {
        if (first.time < second.time)
            return -1;
        else if (first.time > second.time)
            return +1;
        else
            return 0;
    }

    public static TimeSpan from(int hours, int minutes, int seconds) {
        long totalMillSec = 0;
        totalMillSec += hours * TimeUnit.HOURS.getMilliseconds();
        totalMillSec += minutes * TimeUnit.MINUTES.getMilliseconds();
        totalMillSec += seconds * TimeUnit.SECONDS.getMilliseconds();
        return new TimeSpan(totalMillSec);
    }

    public static TimeSpan parse(String s) throws Exception {
        String str = s.trim();
        String[] st1 = str.split("\\.");
        int days = 0, millsec = 0, totMillSec = 0;
        String data = str;
        switch (st1.length) {
            case 1:
                data = str;
                break;
            case 2:
                if (st1[0].split(":").length > 1) {
                    millsec = Integer.parseInt(st1[1]);
                    data = st1[0];
                } else {
                    days = Integer.parseInt(st1[0]);
                    data = st1[1];
                }
                break;
            case 3:
                days = Integer.parseInt(st1[0]);
                data = st1[1];
                millsec = Integer.parseInt(st1[2]);
                break;
            default:
                throw new Exception("Bad Format");

        }
        String[] st = data.split(":");
        switch (st.length) {
            case 1:
                totMillSec = Integer.parseInt(str) * 24 * 60 * 60 * 1000;
                break;
            case 2:
                totMillSec = (Integer.parseInt(st[0]) * 60 * 60 * 1000) + (Integer.parseInt(st[1]) * 60 * 1000);
                break;
            case 3:
                totMillSec = (Integer.parseInt(st[0]) * 60 * 60 * 1000) + (Integer.parseInt(st[1]) * 60 * 1000) + (
                        Integer.parseInt(st[2]) * 1000);
                break;
            case 4:
                totMillSec =
                        (Integer.parseInt(st[0]) * 24 * 60 * 60 * 1000) + (Integer.parseInt(st[1]) * 60 * 60 * 1000) + (
                                Integer.parseInt(st[2]) * 60 * 1000) + (Integer.parseInt(st[3]) * 1000);
                break;
            default:
                throw new Exception("Bad Format/Overflow");
        }
        totMillSec += (days * 24 * 60 * 60 * 1000) + millsec;
        return new TimeSpan(totMillSec);
    }

    private static long toMilliseconds(TimeUnit units, long value) {
        long millis;
        switch (units) {
            case MILLISECONDS:
            case SECONDS:
            case MINUTES:
            case HOURS:
                millis = value * units.getMilliseconds();
                break;
            default:
                throw new IllegalArgumentException("Unrecognized units: " + units);
        }
        return millis;
    }

    ///endregion
}
