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

    // new: generate a Java class file under srcRoot following package structure.
    // - srcRoot: root directory where package folders should be created (e.g. "src/main/java")
    // - packageName: full package (can be null/empty)
    // - className: simple class name (no .java)
    // - fields: map of fieldName -> type (e.g. "id" -> "Long")
    // - methods: map of method signature -> body (signature must include modifiers and return type,
    //            e.g. "public void doSomething(String s)"; body can be multiple lines)
    public static void generateJavaClass(String srcRoot, String packageName, String className, Map<String,String> fields, Map<String,String> methods) {
        generateJavaClass(Path.of(srcRoot), packageName, className, fields, methods);
    }

    public static void generateJavaClass(Path srcRootPath, String packageName, String className, Map<String,String> fields, Map<String,String> methods) {
        try {
            String packagePath = (packageName == null || packageName.isBlank()) ? "" : packageName.replace('.', File.separatorChar);
            Path dir = packagePath.isEmpty() ? srcRootPath : srcRootPath.resolve(packagePath);
            if (!Files.exists(dir)) {
                mkdir(dir);
                Logger.success("Diretório do pacote criado: " + dir.toString());
            }

            Path file = dir.resolve(className + ".java");

            StringBuilder sb = new StringBuilder();
            if (packageName != null && !packageName.isBlank()) {
                sb.append("package ").append(packageName).append(";\n\n");
            }
            
            sb.append("import java.util.*;\n\n");
            sb.append("//Auto-generated class\n");

            sb.append("public class ").append(className).append(" {\n\n");

            if (fields != null && !fields.isEmpty()) {
                for (Map.Entry<String,String> e : fields.entrySet()) {
                    String name = e.getKey();
                    String type = e.getValue();
                    sb.append("    private ").append(type).append(" ").append(name).append(";\n");
                }
                sb.append("\n");
                // simple getters and setters
                for (Map.Entry<String,String> e : fields.entrySet()) {
                    String name = e.getKey();
                    String type = e.getValue();
                    String cap = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                    sb.append("    public ").append(type).append(" get").append(cap).append("() {\n");
                    sb.append("        return this.").append(name).append(";\n");
                    sb.append("    }\n\n");
                    sb.append("    public void set").append(cap).append("(").append(type).append(" ").append(name).append(") {\n");
                    sb.append("        this.").append(name).append(" = ").append(name).append(";\n");
                    sb.append("    }\n\n");
                }
            }

            if (methods != null && !methods.isEmpty()) {
                for (Map.Entry<String,String> m : methods.entrySet()) {
                    String signature = m.getKey();
                    String body = m.getValue() == null ? "" : m.getValue();
                    sb.append("    ").append(signature).append(" {\n");
                    if (!body.isBlank()) {
                        String[] lines = body.split("\\r?\\n");
                        for (String line : lines) {
                            sb.append("        ").append(line).append("\n");
                        }
                    }
                    sb.append("    }\n\n");
                }
            }

            sb.append("}\n");

            Files.writeString(file, sb.toString());
            Logger.success("Classe Java criada com sucesso: " + file.toString());
        } catch (IOException e) {
            Logger.error("Erro ao gerar classe Java: " + e.getMessage());
        } catch (Exception e) {
            Logger.error("Erro inesperado ao gerar classe Java: " + e.getMessage());
        }
    }

}