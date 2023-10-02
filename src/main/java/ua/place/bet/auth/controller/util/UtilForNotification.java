package ua.place.bet.auth.controller.util;

import lombok.Getter;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class UtilForNotification {
    public static Consumer<String> printerNotification = System.out::print;
    public static Consumer<String> printerNewLineNotification = System.out::println;
}
