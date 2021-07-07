package com.batiaev.java3.lesson7.homework.testing;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TestRunner {
    public static void start(String className) {
        try {
            start(Class.forName(className));
        } catch (ClassNotFoundException e) {
            log.error(className + " - класс не найден", e);
        }
    }

    public static void start(Class<?> classType) {
        try {

            Method[] methods = classType.getMethods();

            Method beforeSuite = getBeforeSuite(methods);
            Method afterSuite = getAfterSuite(methods);

            List<Method> testMethods = new ArrayList<>();
            testMethods.addAll(Arrays.asList(methods));

            if (beforeSuite != null) testMethods.remove(beforeSuite);
            if (afterSuite != null) testMethods.remove(afterSuite);

            testMethods.removeIf(method -> method.getAnnotation(Test.class) == null);

            testMethods.sort((m1, m2) -> {
                Test t1 = m1.getAnnotation(Test.class);
                Test t2 = m2.getAnnotation(Test.class);
                if (t1.priority() == t2.priority()) {
                    return 0;
                } else {
                    return t1.priority() < t2.priority() ? -1 : 1;
                }
            });

            final Object o = classType.newInstance();

            if (o == null) return;

            log.info("Стартуем подготовку к тестированию - " + beforeSuite.getName());
            beforeSuite.invoke(o);
            log.info("Подготовка выполнена - " + beforeSuite.getName());

            testMethods.forEach(method -> executeTest(method, o));

            log.info("Стартуем завершение тестирования - " + afterSuite.getName());
            afterSuite.invoke(o);
            log.info("Тесты завершены - " + afterSuite.getName());

        } catch (Exception e) {
            log.error("Ошибка при выполнении тестов", e);
        }
    }

    private static void executeTest(Method method, Object o) {
        if (method != null && o != null) {
            try {
                log.info("Запускаем тест - " + method.getName());
                method.invoke(o);
                log.info("Тест выполнен успешно - " + method.getName());
            } catch (InvocationTargetException e) {
                log.error("Произошла ошибка при выполнении теста - " + method.getName());
            } catch (IllegalAccessException e) {
                log.error("Нет доступа к методу теста - " + method.getName());
            }
        }
    }

    private static Method getBeforeSuite(Method[] methods) {
        Method beforeSuite = null;
        for (Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeSuite != null) {
                    throw new RuntimeException("больше одного метода с анотацией BeforeSuite");
                } else {
                    beforeSuite = method;
                }
            }
        }
        return beforeSuite;
    }

    private static Method getAfterSuite(Method[] methods) {
        Method afterSuite = null;
        for (Method method : methods) {
            if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterSuite != null) {
                    throw new RuntimeException("больше одного метода с анотацией AfterSuite");
                } else {
                    afterSuite = method;
                }
            }
        }
        return afterSuite;
    }
}
