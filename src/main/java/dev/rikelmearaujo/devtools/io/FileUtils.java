package dev.rikelmearaujo.devtools.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import dev.rikelmearaujo.devtools.core.Logger;

public class FileUtils {
    
    private FileUtils() {}

    public static String readText (String filePath) {
        try {
            Path path = Path.of(filePath);
            if (!Files.exists(path)) {
                Logger.error("Arquivo não encontrado: " + filePath);
                return null;
            }
            String content = Files.readString(path);
            Logger.success("Arquivo de texto lido com sucesso: " + filePath);
            return content;
        } catch (IOException e) {
            Logger.error("Erro ao ler arquivo de texto: " + e.getMessage());
            return null;
        }
    }

    public static void writeText(String filePath, String content) {
        try {
            Files.writeString(Path.of(filePath), content);
            Logger.success("Conteúdo escrito no arquivo com sucesso: " + filePath);
        } catch (IOException e) {
            Logger.error("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

}
