package ua.hillel.myFileLogger.LoggerConfigurationLoader;

import ua.hillel.myFileLogger.LoggerConfiguration;

import java.io.FileNotFoundException;

public abstract class LoggerConfigurationLoader {
    public abstract LoggerConfiguration load() throws FileNotFoundException;
}
