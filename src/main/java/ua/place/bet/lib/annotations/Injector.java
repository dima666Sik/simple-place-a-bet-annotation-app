package ua.place.bet.lib.annotations;

import ua.place.bet.auth.controller.AuthorizationController;
import ua.place.bet.auth.controller.MenuController;
import ua.place.bet.auth.dao.UserDAO;
import ua.place.bet.auth.dao.impl.UserDAOImpl;
import ua.place.bet.auth.service.AuthorizationService;
import ua.place.bet.auth.service.impl.AuthorizationServiceImpl;
import ua.place.bet.core.controller.BetController;
import ua.place.bet.core.dao.BetDAO;
import ua.place.bet.core.dao.impl.BetDAOImpl;
import ua.place.bet.core.service.BetService;
import ua.place.bet.core.service.BetServiceImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
//work
public class Injector {
    private static final Map<Class<?>, Class<?>> ifaceClassMap = new HashMap<>();
    private static final Map<Class<?>, Object> cashHashMap = new HashMap<>();
    private static final Set<Class<?>> classSet = new HashSet<>();

    static {
        ifaceClassMap.put(AuthorizationService.class, AuthorizationServiceImpl.class);
        ifaceClassMap.put(BetService.class, BetServiceImpl.class);
        ifaceClassMap.put(UserDAO.class, UserDAOImpl.class);
        ifaceClassMap.put(BetDAO.class, BetDAOImpl.class);

        classSet.add(AuthorizationController.class);
        classSet.add(BetController.class);
        classSet.add(MenuController.class);
    }

    public static Object getInstance(Class<?> clazz) {
        return getInstance(clazz, cashHashMap);
    }

    private static Object getInstance(Class<?> clazz, Map<Class<?>, Object> cashHashMap) {
        if (cashHashMap.containsKey(clazz)) {
            return cashHashMap.get(clazz);
        }

        Class<?> clazzImpl = findImplementation(clazz);
        Object instanceObject = createInstanceObject(clazzImpl);

        cashHashMap.put(clazz, instanceObject);

        for (Field field : clazzImpl.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                try {
                    Object innerInjectInstance = getInstance(field.getType(), cashHashMap);
                    field.setAccessible(true);
                    field.set(instanceObject, innerInjectInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return instanceObject;
    }


    private static Object createInstanceObject(Class<?> clazzImpl) {
        Object instance = null;
        try {
            Constructor<?> constructor = getConstructor(clazzImpl);

            if (constructor.getParameterCount() > 0) {
                // parameters count more than 0
                Class<?>[] parametersClazz = constructor.getParameterTypes();
                constructor = clazzImpl.getDeclaredConstructor(parametersClazz);

                for (Class<?> clazzParam : parametersClazz) {
                    if (clazzParam == Scanner.class) {
                        instance = constructor.newInstance(new Scanner(System.in));
                    }
                }
                return instance;
            }
            instance = constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Can`t create instance " + clazzImpl.getName());
        }
        return instance;
    }

    private static Constructor<?> getConstructor(Class<?> clazzImpl) {
        if (!clazzImpl.isAnnotationPresent(Component.class)) {
            throw new NoSuchElementException("Annotation @Component are not declared in this class: " + clazzImpl.getName());
        }
        Constructor<?>[] allConstructorsCurrentClassImpl = clazzImpl.getDeclaredConstructors();
        if (allConstructorsCurrentClassImpl.length > 1)
            throw new RuntimeException("Count constructor in class: " + clazzImpl.getName() + " more than 1.");

        return allConstructorsCurrentClassImpl[0];
    }

    private static Class<?> findImplementation(Class<?> clazz) {
        if (clazz.isInterface()) {
            return ifaceClassMap.get(clazz);
        }

        if (classSet.contains(clazz)) {
            return clazz;
        }

        throw new NoSuchElementException("This class not founded: " + clazz.getName());
    }

}
