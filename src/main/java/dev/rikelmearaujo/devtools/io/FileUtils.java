package dev.rikelmearaujo.devtools.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import dev.rikelmearaujo.devtools.utils.Logger;

public class FileUtils {
    
    private FileUtils() {}

    public static String readString (String filePath) {
        return readString(Path.of(filePath));
    }
    public static String readString (Path filePath) {
        try {
            if (!Files.exists(filePath)) {
                Logger.error("File not found: " + filePath);
                return null;
            }
            String content = Files.readString(filePath);
            Logger.success("File read successfully: " + filePath);
            return content;
        } catch (IOException e) {
            Logger.error("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static int writeString(String filePath, String content) {
        return writeString(Path.of(filePath), content);
    }
    public static int writeString(Path filePath, String content) {
        try {
            if (!Files.exists(filePath.getParent())) {
                mkdir(filePath.getParent());
                Logger.success("Parent directory created: " + filePath.getParent().toString());
            }
            Files.writeString(filePath, content);
            Logger.success("Content written to file successfully: " + filePath);
            return 0;
        } catch (IOException e) {
            Logger.error("Error writing to file: " + e.getMessage());
            return -1;
        }
    }

    public static int mkdir(String dirPath) {
        return mkdir(Path.of(dirPath));
    }
    public static int mkdir(Path dirPath) {
        try {
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
                Logger.success("Directory created successfully: " + dirPath);
                return 0;
            } else {
                Logger.info("Directory already exists: " + dirPath);
                return 1;
            }
        } catch (IOException e) {
            Logger.error("Error creating directory: " + e.getMessage());
            return -1;
        }
    }

    public static File mkFile(String filePath) {
        return mkFile(Path.of(filePath));
    }
    public static File mkFile(Path filePath) {
        File res = null;
        try {
            if (!Files.exists(filePath.getParent())) {
                mkdir(filePath.getParent());
                Logger.success("Parent directory created: " + filePath.getParent().toString());
            }
            if (!Files.exists(filePath)) {
                res = Files.createFile(filePath).toFile();
                Logger.success("File created successfully: " + filePath);
            } else {
                res = filePath.toFile();
                Logger.info("File already exists: " + filePath);
            }
        } catch (IOException e) {
            Logger.error("Error creating file: " + e.getMessage());
        }
        return res;
    }

}