package ua.place.bet.main;

import ua.place.bet.auth.controller.MenuController;
import ua.place.bet.lib.annotations.Injector;

public class Main {
    public static void main(String[] args) {
        MenuController menuController = (MenuController) Injector.getInstance(MenuController.class);
        menuController.menuControl();
    }
}