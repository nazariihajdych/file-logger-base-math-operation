package ua.hillel.myFileLogger.Logger;


import ua.hillel.myFileLogger.FileMaxSizeReachedException.FileMaxSizeReachedException;
import ua.hillel.myFileLogger.LoggerConfiguration;

public class StdoutLogger extends MyLogger{


    @Override
    public void info(String message) throws FileMaxSizeReachedException {

    }

    @Override
    public void debug(String message) throws FileMaxSizeReachedException {

    }
}
