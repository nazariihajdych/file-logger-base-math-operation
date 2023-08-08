package ua.hillel.myFileLogger.Logger;


import ua.hillel.myFileLogger.FileMaxSizeReachedException.FileMaxSizeReachedException;
import ua.hillel.myFileLogger.LoggerConfiguration;
import ua.hillel.myFileLogger.LoggerConfigurationLoader.FileLoggerConfigurationLoader;
import ua.hillel.myFileLogger.LoggingLevel;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class FileLogger extends MyLogger {

    private final String configPath;
    private final FileLoggerConfigurationLoader loader;
    private LoggerConfiguration config;

    public FileLogger(FileLoggerConfigurationLoader loader, String configPath) {
        this.configPath = configPath;
        this.loader = loader;
        this.config = loader.load();
    }


    public void info(String message) throws FileMaxSizeReachedException {
        if (config.getLevel().getVal() <= LoggingLevel.INFO.getVal()) {
            String logMessage = String.format(config.getFormat(), LocalDateTime.now(), "INFO", message);
            writeToLogFile(logMessage);
        }
    }


    public void debug(String message) throws FileMaxSizeReachedException {
        if (config.getLevel().getVal() <= LoggingLevel.DEBUG.getVal()) {
            String logMessage = String.format(config.getFormat(), LocalDateTime.now(), "DEBUG", message);
            writeToLogFile(logMessage);
        }

    }

    private void writeToLogFile(String logMessage) throws FileMaxSizeReachedException {


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss:SSSS");

        File logFile = new File(config.getFilePath());


        if (logFile.length() + logMessage.length() >= config.getMaxFileSize()) {
            logFile = new File(logFile.getParent() + "/log_" + dateFormat.format(new Date()) + ".txt");


            try (RandomAccessFile randomAccessFile = new RandomAccessFile(configPath, "rw")) {
                randomAccessFile.seek(6);
                randomAccessFile.write(logFile.getPath().getBytes());
                config = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(config.getFilePath(), true))) {
            bufferedWriter.write(logMessage);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Помилка запису в файл " + e.getMessage());
        }

        float acceptableSizeCoff = 1.8f;

        if (logFile.length() > (config.getMaxFileSize() * acceptableSizeCoff)) {
            throw new FileMaxSizeReachedException(config.getMaxFileSize(), (int) logFile.length(), config.getFilePath());
        }


    }

    public LoggerConfiguration getConfig() {
        return config;
    }

}
