package ua.hillel.myFileLogger.LoggerConfigurationLoader;



import ua.hillel.myFileLogger.LoggerConfiguration;
import ua.hillel.myFileLogger.LoggingLevel;

import java.io.*;

public class FileLoggerConfigurationLoader extends LoggerConfigurationLoader {

    private String filePath;

    public FileLoggerConfigurationLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public LoggerConfiguration load() {
        File configFile = new File(filePath);

        String file = "";
        LoggingLevel level = null;
        int maxSize = 0;
        String format = "";

        String[] splitLine;
        String key, value;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile))) {

            String line;
            while ((line = bufferedReader.readLine()) != null){
                splitLine = line.split(": ");
                key = splitLine[0].trim().toUpperCase();
                value = splitLine[1].trim();

                switch (key){
                    case "FILE":
                        file = value;
                        break;
                    case "LEVEL":
                        level = LoggingLevel.valueOf(value.toUpperCase());
                        break;
                    case "MAX-SIZE":
                        maxSize = Integer.parseInt(value);
                        break;
                    case "FORMAT":
                        format = value;
                        break;
                    default:
                        System.out.println("Невідоме поле: " + key);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено " + filePath);
        } catch (IOException e) {
            System.out.println("Неможливо прочитати файл!");
        }

        return new LoggerConfiguration(file, level, maxSize, format);
    }
}
