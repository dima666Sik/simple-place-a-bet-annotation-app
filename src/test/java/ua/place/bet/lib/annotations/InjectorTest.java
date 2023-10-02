package ua.place.bet.lib.annotations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.place.bet.auth.controller.AuthorizationController;
import ua.place.bet.auth.controller.MenuController;
import ua.place.bet.auth.service.AuthorizationService;
import ua.place.bet.core.controller.BetController;
import ua.place.bet.core.service.BetService;


class InjectorTest {
    private Object actual;

    @Test
    public void getInstanceAuthorizationServiceInstance() {
        actual = Injector.getInstance(AuthorizationService.class);

        Assertions.assertTrue(actual instanceof AuthorizationService,
                "Injector should be able to generate instance of AuthorizationService");
    }

    @Test
    public void getInstanceAuthorizationControllerInstance() {
        actual = Injector.getInstance(AuthorizationController.class);

        Assertions.assertTrue(actual instanceof AuthorizationController,
                "Injector should be able to generate instance of AuthorizationController");
    }

    @Test
    public void getInstanceMenuControllerInstance() {
        actual = Injector.getInstance(MenuController.class);

        Assertions.assertTrue(actual instanceof MenuController,
                "Injector should be able to generate instance of MenuController");
    }

    @Test
    public void getInstanceBetServiceInstance() {
        actual = Injector.getInstance(BetService.class);

        Assertions.assertTrue(actual instanceof BetService,
                "Injector should be able to generate instance of BetService");
    }

    @Test
    public void getInstanceBetControllerInstance() {
        actual = Injector.getInstance(BetController.class);

        Assertions.assertTrue(actual instanceof BetController,
                "Injector should be able to generate instance of BetController");
    }
}