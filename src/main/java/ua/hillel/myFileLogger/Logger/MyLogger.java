package ua.hillel.myFileLogger.Logger;


import ua.hillel.myFileLogger.FileMaxSizeReachedException.FileMaxSizeReachedException;

public abstract class MyLogger {

    public abstract void info(String message) throws FileMaxSizeReachedException;
    public abstract void debug(String message) throws FileMaxSizeReachedException;

//    за потреби можна додати методи для інших рівнів логування
}
