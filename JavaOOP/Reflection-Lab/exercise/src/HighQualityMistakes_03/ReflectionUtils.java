package HighQualityMistakes_03;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectionUtils {

    public ReflectionUtils() throws IllegalAccessException {

        throw new IllegalAccessException("Accessing this constructor is forbidden.");
    }

    public static <T extends Member> Stream<T> filteredMembersByName(T[] members, String filter) {
        return filteredMembers(Arrays.stream(members), m -> m.getName().contains(filter));
    }

    public static <T extends Member> Stream<T> filteredMembers(T[] methods, Predicate<T> predicate) {
        return Arrays.stream(methods)
                .filter(predicate);
    }

    public static <T extends Member> Stream<T> filteredMembers(Stream<T> stream, Predicate<T> predicate) {
        return stream
                .filter(predicate);
    }

    public static <T extends Member> TreeSet<T> collectByName(Stream<T> stream) {
        return  stream.collect(Collectors.toCollection(
                       () -> new TreeSet<>(Comparator.comparing(Member::getName))));
    }
}

