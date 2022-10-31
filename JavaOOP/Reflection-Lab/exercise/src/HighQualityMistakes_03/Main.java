package HighQualityMistakes_03;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.function.Function;

import static HighQualityMistakes_03.ReflectionUtils.*;

public class Main {

    public static void main(String[] args) {

        Class clazz = Reflection.class;

        Method[] methods = clazz.getDeclaredMethods();

        Field [] declaredFields = clazz.getDeclaredFields();

        TreeSet<Method> getMethods = collectByName(filteredMembersByName(methods, "get"));

        TreeSet<Method> setMethods = collectByName(filteredMembersByName(methods, "set"));

        TreeSet<Field> fields = collectByName(Arrays.stream(declaredFields));

        Function<Class<?>, String> formatType = c -> c == int.class ? "class int" : c.toString();

        filteredMembers(fields.stream(), f -> !Modifier.isPrivate(f.getModifiers()))
                .forEach(f -> System.out.println(f.getName() + " must be private!"));

        filteredMembers(fields.stream(), f -> !Modifier.isPublic(f.getModifiers()))
                .forEach(m -> System.out.println(m.getName() + " have to be public!"));

        getMethods.forEach(
                m -> System.out.printf("%s will return %s%n", m.getName(), formatType.apply(m.getReturnType())));

        setMethods.forEach(
                m -> System.out.printf("%s and will set field of %s%n", m.getName(), formatType.apply(m.getParameterTypes()[0])));

        filteredMembers(setMethods.stream(), f -> !Modifier.isPrivate(f.getModifiers()))
                .forEach(f -> System.out.println(f.getName() + " must be public!"));
    }
}

