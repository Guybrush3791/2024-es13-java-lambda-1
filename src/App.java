import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class App {
    public static void main(String[] args) throws Exception {

        // testCol1();

        testStreamCollection1();
    }

    public static void testCol1() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        BiFunction<Integer, Integer, Integer> sum = (Integer x, Integer y) -> {

            return x + y;
        };
        BiFunction<Integer, Integer, Integer> pluto = (x, y) -> x - y;

        BiFunction<Integer, Integer, Float> div = (x, y) -> (float) x / y;

        int x = 10;
        int y = 20;

        int xySum = sum.apply(x, y);
        float xyDiv = div.apply(x, y);

        System.out.println("x: " + x + "\ny: " + y + "\nsum: " + xySum + "\ndiv: " + xyDiv);

        int lambdaSum = lambdaTest1(pluto);
        System.out.println("lambdaSum: " + lambdaSum);

        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        int intSum = 0;
        for (Integer v : values)
            intSum += v;

        System.out.println("sum: " + intSum);

        // usa lo stream delle collection per ottenere la somma
        // di tutti i valori della lista values
        System.out.println("sum: " +
                values.stream().reduce((v1, v2) -> v1 + v2)
                        .get());

        System.out.println("-----------------------------------");
        System.out.println("Values:");
        for (Integer v : values)
            if (v % 2 == 0)
                System.out.println(v);

        System.out.println("-----------------------------------");
        System.out.println("Values:");
        // values.stream().forEach(v -> System.out.println(v));
        values.stream()
                .filter(v -> v % 2 == 0)
                .forEach(System.out::println);
    }

    public static int lambdaTest1(BiFunction<Integer, Integer, Integer> op) {

        int x = 100;
        int y = 150;

        return op.apply(x, y);
    }

    public static void testStreamCollection1() {

        List<Integer> values = Arrays.asList(3, 9, 11, 20, 45, 30, 33, -40, 45, 50);

        System.out.println("-----------------------------------");

        for (Integer v : values)
            System.out.println("value: " + v);

        System.out.println("-----------------------------------");

        // values.forEach(System.out::println); // static reference
        values.forEach(v -> System.out.println("value: " + v));

        System.out.println("-----------------------------------");

        boolean isAllPosOld = true;
        for (Integer v : values)
            if (v <= 0) {
                isAllPosOld = false;
                break;
            }
        System.out.println("isAllPosOld: " + isAllPosOld);

        System.out.println("-----------------------------------");

        boolean isAllPos = values.stream()
                .allMatch(v -> v > 0);
        System.out.println("isAllPos: " + isAllPos);

        System.out.println("-----------------------------------");

        values.stream()
                .limit(3)
                .forEach(System.out::println);

        System.out.println("-----------------------------------");

        values.stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println("-----------------------------------");

        Function<Integer, Integer> doubleIt = v -> v * 2;

        values.stream()
                .distinct()
                .filter(v -> v % 2 == 0)
                .map(doubleIt)
                // .map(v -> v * 2)
                .forEach(System.out::println);

        // values = values.stream()
        // .map(v -> v * 2)
        // .toList();

        System.out.println("The end");
    }
}
