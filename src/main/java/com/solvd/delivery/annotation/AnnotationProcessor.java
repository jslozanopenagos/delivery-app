package com.solvd.delivery.annotation;

import com.solvd.delivery.model.users.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationProcessor {
    protected static final Logger LOGGER = LogManager.getLogger(AnnotationProcessor.class);

    public void invoke(Object controller, User user, String methodName, Object... args) {
        try {
            Class<?>[] paramTypes = Arrays.stream(args)
                    .map(Object::getClass)
                    .toArray(Class<?>[]::new);

            Method method = controller.getClass().getMethod(methodName, paramTypes);

            if (method.isAnnotationPresent(RequiresLogin.class)) {
                RequiresLogin annotation = method.getAnnotation(RequiresLogin.class);

                if (user == null) {
                    LOGGER.error("Access denied: login required for {}", methodName);
                    return;
                }

                String[] requiredRoles = annotation.roles();
                if (requiredRoles.length > 0) {
                    String userRole = user.getRole().name();
                    boolean allowed = Arrays.stream(requiredRoles)
                            .anyMatch(r -> r.equalsIgnoreCase(userRole));

                    if (!allowed) {
                        LOGGER.error("Access denied: {} can not call {}",userRole, methodName);
                        return;
                    }
                }
            }

            method.invoke(controller, args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}