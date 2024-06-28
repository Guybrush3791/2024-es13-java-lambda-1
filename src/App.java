import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

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

        int max = values.stream()
                .max((v1, v2) -> v1 - v2)
                .get();

        System.out.println("max: " + max);

        List<Employee> emps = Arrays.asList(
                new Employee("Mario", 1000),
                new Employee("Luigi", 2000),
                new Employee("Peach", 3000),
                new Employee("Toad", 4000),
                new Employee("Bowser", 5000));

        System.out.println("-----------------------------------");
        System.out.println(emps);
        System.out.println("-----------------------------------");

        int maxSalary = Integer.MIN_VALUE;
        Employee maxEmp = null;
        for (Employee e : emps)
            if (maxSalary < e.getSalary()) {
                maxSalary = e.getSalary();
                maxEmp = e;
            }
        System.out.println(maxEmp);

        System.out.println("-----------------------------------");

        Employee maxEmp2 = emps.stream()
                .max((e1, e2) -> e1.getSalary() - e2.getSalary())
                .get();
        System.out.println(maxEmp2);

        System.out.println("-----------------------------------");

        int maxSalary2 = emps.stream()
                .map(Employee::getSalary)
                .max((s1, s2) -> s1 - s2)
                .get();
        System.out.println("maxSalary2: " + maxSalary2);

        System.out.println("-----------------------------------");

        emps.stream()
                .map(Employee::getSalary)
                .forEach(System.out::println);

        System.out.println("-----------------------------------");

        int acc = 1;
        for (int v : values)
            acc *= v;
        System.out.println("acc: " + acc);

        System.out.println("-----------------------------------");

        int sum = values.stream()
                // .reduce(0, (v1, v2) -> v1 + v2);
                .reduce(1, (v1, v2) -> v1 * v2);
        System.out.println("sum: " + sum);

        System.out.println("-----------------------------------");

        int sumSalary = emps.stream()
                .map(Employee::getSalary)
                .reduce(0, (s1, s2) -> s1 + s2);
        System.out.println("sum Salary: " + sumSalary);

        System.out.println("-----------------------------------");

        Integer[] arrValues = values.stream()
                .toArray(Integer[]::new);

        Employee[] arrEmps = emps.stream()
                .toArray(Employee[]::new);

        List<Integer> listValue = values.stream()
                .filter(v -> v % 2 == 0)
                .toList();

        System.out.println("-----------------------------------");

        String strRes = "";
        for (int x = 0; x < listValue.size(); x++) {

            strRes += listValue.get(x);
            if (x < listValue.size() - 1)
                strRes += ", ";
        }

        System.out.println("strRes: " + strRes);

        System.out.println("-----------------------------------");

        String strRes2 = listValue.stream()
                .map(v -> "(" + v.toString() + ")")
                .collect(Collectors.joining(", "));
        System.out.println("strRes2: " + strRes2);

        System.out.println("-----------------------------------");

        Optional<Integer> optValue = values.stream()
                .filter(v -> v % 2 == 0)
                .findFirst();

        if (optValue.isPresent()) {

            int actualValue = optValue.get();
            System.out.println("optValue: " + actualValue);
        } else
            System.out.println("optValue: not found");

        // values = values.stream()
        // .map(v -> v * 2)
        // .toList();

        System.out.println("The end");
    }
}
