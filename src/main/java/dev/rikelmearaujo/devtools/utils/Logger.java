package dev.rikelmearaujo.devtools.utils;

public class Logger {

    private Logger() {}

    public static boolean debug = false;

    static final String COLOR_ANSI_RESET = "\u001B[0m";
    static final String COLOR_ANSI_RED = "\u001B[31m";
    static final String COLOR_ANSI_GREEN = "\u001B[32m";
    static final String COLOR_ANSI_YELLOW = "\u001B[33m";
    static final String COLOR_ANSI_BLUE = "\u001B[34m";
    static final String COLOR_ANSI_CYAN = "\u001B[36m";

    public static void info(Object message) {
        System.out.println(COLOR_ANSI_CYAN + "[INFO] " + message.toString() + COLOR_ANSI_RESET);
    }

    public static void warn(Object message) {
        System.out.println(COLOR_ANSI_YELLOW + "[WARN] " + message.toString() + COLOR_ANSI_RESET);
    }

    public static void error(Object message) {
        System.out.println(COLOR_ANSI_RED + "[ERROR] " + message.toString() + COLOR_ANSI_RESET);
    }

    public static void success(Object message) {
        System.out.println(COLOR_ANSI_GREEN + "[SUCCESS] " + message.toString() + COLOR_ANSI_RESET);
    }

    public static void debug(Object message) {
        if(!debug) return;
        System.out.println(COLOR_ANSI_BLUE + "[DEBUG] " + message.toString() + COLOR_ANSI_RESET);
    }

}
