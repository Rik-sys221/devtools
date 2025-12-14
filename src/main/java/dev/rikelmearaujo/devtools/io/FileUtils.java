package dev.rikelmearaujo.devtools.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import dev.rikelmearaujo.devtools.utils.Logger;

public class FileUtils {
    
    private FileUtils() {}

    public static String readText (String filePath) {
        return readText(Path.of(filePath));
    }
    public static String readText (Path filePath) {
        try {
            if (!Files.exists(filePath)) {
                Logger.error("Arquivo não encontrado: " + filePath);
                return null;
            }
            String content = Files.readString(filePath);
            Logger.success("Arquivo de texto lido com sucesso: " + filePath);
            return content;
        } catch (IOException e) {
            Logger.error("Erro ao ler arquivo de texto: " + e.getMessage());
            return null;
        }
    }

    public static void writeText(String filePath, String content) {
        writeText(Path.of(filePath), content);
    }
    public static void writeText(Path filePath, String content) {
        try {
            if (!Files.exists(filePath.getParent())) {
                mkdir(filePath.getParent());
                Logger.success("Diretório do pacote criado: " + filePath.getParent().toString());
            }
            Files.writeString(filePath, content);
            Logger.success("Conteúdo escrito no arquivo com sucesso: " + filePath);
        } catch (IOException e) {
            Logger.error("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void mkdir(String dirPath) {
        mkdir(Path.of(dirPath));
    }
    public static void mkdir(Path dirPath) {
        try {
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
                Logger.success("Diretório criado com sucesso: " + dirPath);
            } else {
                Logger.info("Diretório já existe: " + dirPath);
            }
        } catch (IOException e) {
            Logger.error("Erro ao criar diretório: " + e.getMessage());
        }
    }

    public static File createFile(String filePath) {
        return createFile(Path.of(filePath));
    }
    public static File createFile(Path filePath) {
        File res = null;
        try {
            if (!Files.exists(filePath.getParent())) {
                mkdir(filePath.getParent());
                Logger.success("Diretório do pacote criado: " + filePath.getParent().toString());
            }
            if (!Files.exists(filePath)) {
                res = Files.createFile(filePath).toFile();
                Logger.success("Arquivo criado com sucesso: " + filePath);
            } else {
                res = filePath.toFile();
                Logger.info("Arquivo já existe: " + filePath);
            }
        } catch (IOException e) {
            Logger.error("Erro ao criar arquivo: " + e.getMessage());
        }
        return res;
    }

}