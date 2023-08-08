package ua.hillel.myFileLogger;

public class LoggerConfiguration {

    private String filePath;
    private LoggingLevel level;
    private int maxFileSize;
    private String format;

    public LoggerConfiguration(String filePath, LoggingLevel level, int maxFileSize, String format) {
        this.filePath = filePath;
        this.level = level;
        this.maxFileSize = maxFileSize;
        this.format = format;
    }

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LoggingLevel getLevel() {
        return level;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public String getFormat() {
        return format;
    }
}
