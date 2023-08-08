package ua.hillel.myFileLogger;

public enum LoggingLevel {

    INFO(2),
    DEBUG(1);

    private final int val;

    LoggingLevel(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
