package dev.rikelmearaujo.devtools.utils;

public class Logger {

    private Logger() {}

    static final String COLOR_ANSI_RESET = "\u001B[0m";
    static final String COLOR_ANSI_RED = "\u001B[31m";
    static final String COLOR_ANSI_GREEN = "\u001B[32m";
    static final String COLOR_ANSI_YELLOW = "\u001B[33m";
    static final String COLOR_ANSI_BLUE = "\u001B[34m";
    static final String COLOR_ANSI_CYAN = "\u001B[36m";

    public static void info(String message) {
        System.out.println(COLOR_ANSI_CYAN + "[INFO] " + message + COLOR_ANSI_RESET);
    }

    public static void warn(String message) {
        System.out.println(COLOR_ANSI_YELLOW + "[WARN] " + message + COLOR_ANSI_RESET);
    }

    public static void error(String message) {
        System.out.println(COLOR_ANSI_RED + "[ERROR] " + message + COLOR_ANSI_RESET);
    }

    public static void success(String message) {
        System.out.println(COLOR_ANSI_GREEN + "[SUCCESS] " + message + COLOR_ANSI_RESET);
    }

    public static void debug(String message) {
        System.out.println(COLOR_ANSI_BLUE + "[DEBUG] " + message + COLOR_ANSI_RESET);
    }

}
