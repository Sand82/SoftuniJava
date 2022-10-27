import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class clazz = Reflection.class;

        Method[] methods = clazz.getDeclaredMethods();

        TreeSet<Method> getMethods = filteredMethods(methods, "get");

        TreeSet<Method> setMethods = filteredMethods(methods, "set");

        Function<Class<?>, String> formatType = c -> c == int.class ? "class int" : c.toString();

        getMethods.forEach(
                m -> System.out.printf("%s will return %s%n", m.getName(), formatType.apply(m.getReturnType())));

        setMethods.forEach(
                m -> System.out.printf("%s and will set field of %s%n", m.getName(), formatType.apply(m.getParameterTypes()[0])));

    }

    private static TreeSet<Method> filteredMethods(Method[] methods, String filter) {

        return Arrays.stream(methods)
                .filter(m -> m.getName().contains(filter))
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(Method::getName))));
    }
}