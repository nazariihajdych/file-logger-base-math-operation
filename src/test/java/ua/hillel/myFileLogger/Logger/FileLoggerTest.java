package ua.hillel.myFileLogger.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.hillel.myFileLogger.FileMaxSizeReachedException.FileMaxSizeReachedException;
import ua.hillel.myFileLogger.LoggerConfigurationLoader.FileLoggerConfigurationLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class FileLoggerTest {

    private FileLoggerConfigurationLoader loader;
    private FileLogger fileLogger;

    @BeforeEach
    public void setUp() {
        String configPath = "/Users/nazar/IdeaProjects/file-logger-base-math-operations/src/test/java/ua/hillel/myFileLogger/config_test/config.txt";
        loader = new FileLoggerConfigurationLoader(configPath);
        fileLogger = new FileLogger(loader, configPath);

    }

    @Test
    public void infoTest_success() {

        String message = "This is an info message";

        assertDoesNotThrow(() -> fileLogger.info(message));

        try (BufferedReader reader = new BufferedReader(new FileReader(fileLogger.getConfig().getFilePath()))) {

            String actualMessage = null, line;

            while ((line = reader.readLine()) != null) {
                actualMessage = line;
            }

            assertNotNull(actualMessage, "Повідомлення не записано в файл журналу");
            assertTrue(actualMessage.contains("INFO"), "Повідомлення не має рівня INFO");
            assertTrue(actualMessage.contains(message), "Повідомлення не містить заданий текст");
        } catch (IOException e) {
            fail("Помилка читання файлу: " + e.getMessage());
        }
    }

    @Test
    public void debugTest_ifLevelIsINFO() {
        String message = "This is a debug message";

        assertDoesNotThrow(() -> fileLogger.debug(message));

        try (BufferedReader reader = new BufferedReader(new FileReader(fileLogger.getConfig().getFilePath()))) {
            String actualMessage = lastLogFinder(reader);
            assertFalse(actualMessage.contains(message), "Повідомлення записано в файл журналу, хоча не повинно");

        } catch (IOException e) {
            fail("Помилка читання файлу: " + e.getMessage());
        }
    }

    @Test
    public void writeToLogFileTest_successNewFileCreation() throws FileMaxSizeReachedException {
        String message = "This is message";

        String basePath = fileLogger.getConfig().getFilePath();

        for (int i = 0; i < 6; i++) {
            fileLogger.info(message);
        }

        String newFilePath = fileLogger.getConfig().getFilePath();

        assertNotEquals(basePath, newFilePath);

        //перевірка, чи дані збережені в новому файлі
        try (BufferedReader reader = new BufferedReader(new FileReader(newFilePath))) {
            String actualMessage = lastLogFinder(reader);
            assertTrue(actualMessage.contains(message), "Повідомлення не записано в ротований файл журналу");
        } catch (IOException e) {
            fail("Помилка читання ротованого файлу журналу: " + e.getMessage());
        }
    }

    @Test
    public void debugTest_success() {

        String configDebugLevel = "/Users/nazar/IdeaProjects/file-logger-base-math-operations/src/test/java/ua/hillel/myFileLogger/config_test/config_DEBUG.txt";
        loader = new FileLoggerConfigurationLoader(configDebugLevel);
        fileLogger = new FileLogger(loader, configDebugLevel);

        String infoMessage = "This is an info message";
        String debugMessage = "This is an debug message";

        assertDoesNotThrow(() -> fileLogger.info(infoMessage));
        assertDoesNotThrow(() -> fileLogger.debug(debugMessage));

        try (BufferedReader reader = new BufferedReader(new FileReader(fileLogger.getConfig().getFilePath()))) {
            String actualMessage = lastLogFinder(reader);

            assertNotNull(actualMessage, "Повідомлення не записано в файл журналу");
            assertTrue(actualMessage.contains("DEBUG"), "Повідомлення не має рівня INFO");
            assertTrue(actualMessage.contains(debugMessage), "Повідомлення не містить заданий текст");
        } catch (IOException e) {
            fail("Помилка читання файлу: " + e.getMessage());
        }
    }

    @Test
    public void FileMaxSizeReachedExceptionTest_throwException() {
        String message = "This message will cause the file to exceed the max size.";


        assertThrows(FileMaxSizeReachedException.class, () -> fileLogger.info(message));
    }

    @AfterAll
    public static void cleanUp() {
        File logFilesDirectory = new File("/Users/nazar/IdeaProjects/file-logger-base-math-operations/src/test/java/ua/hillel/myFileLogger/logFile_test");
        File[] fileList = logFilesDirectory.listFiles();

        for (File file : fileList) {
            file.delete();
        }
    }

    private String lastLogFinder(BufferedReader reader) throws IOException {
        String actualMessage = null, line;

        while ((line = reader.readLine()) != null) {
            actualMessage = line;
        }

        return actualMessage;
    }

}
