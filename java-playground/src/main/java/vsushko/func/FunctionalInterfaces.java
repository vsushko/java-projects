package vsushko.func;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * @author vsushko
 */
public class FunctionalInterfaces {

    public static void main(String[] args) {
        // BiConsumer
        // Represents an operation that accepts two input arguments and returns no result
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        // accepts two input arguments and returns no result
        BiConsumer<Integer, String> biConsumer =
                (key, value) -> System.out.println("Key:" + key + " Value:" + value);
        map.forEach(biConsumer);

        BiConsumer<String, String> biConsumer2 = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
        };
        biConsumer2.accept("String 1", "String 2");

        BiConsumer<Integer, Integer> biConsumer3 = (i1, i2) -> System.out.println(i1 + i2);
        BiConsumer<Integer, Integer> biConsumer4 = (i1, i2) -> System.out.println(i1 - i2);

        biConsumer3.andThen(biConsumer4).accept(10, 11);
        // ------------------------------------------------------------------------------------
        // BiFunction
        // accepts two arguments and produces a result
        BiFunction<Integer, Integer, String> biFunction = (num1, num2) -> "Result: " + (num1 + num2);
        System.out.println(biFunction.apply(20, 25));

        BiFunction<Integer, Integer, Integer> biFunction2 = (i1, i2) -> i1 + i2;
        Function<Integer, Integer> function1 = (n) -> n * n;

        // using andThen()
        System.out.println(biFunction2.andThen(function1).apply(2, 4));
        System.out.println(biFunction2.andThen(function1).apply(3, 5));
        // ------------------------------------------------------------------------------------
        // BinaryOperator
        // Represents an operation upon two operands of the same type, producing a result
        // of the same type as the operands
        BinaryOperator<Integer> operator1 = (a, b) -> a + b;
        System.out.println(operator1.apply(5, 7));

        BinaryOperator<String> operator2 = (s1, s2) -> s1 + s2;
        System.out.println(operator2.apply("s1 ", "s2"));

        BinaryOperator<Integer> operator = (a, b) -> a + b;
        Function<Integer, Integer> function = n -> n * 2;

        // Using andThen()
        System.out.println(operator.andThen(function).apply(1, 6));

        Comparator<Integer> comparator = (a, b) -> (a.compareTo(b));

        // Using maxBy()
        BinaryOperator<Integer> opMax = BinaryOperator.maxBy(comparator);
        System.out.println("Max: " + opMax.apply(5, 6));
        System.out.println("Max: " + opMax.apply(9, 6));

        // Using minBy()
        BinaryOperator<Integer> opMin = BinaryOperator.minBy(comparator);
        System.out.println("Min: " + opMin.apply(5, 6));
        System.out.println("Min: " + opMin.apply(9, 6));
        // ------------------------------------------------------------------------------------
        // BiPredicate
        // Represents a predicate (boolean-valued function) of two arguments
        BiPredicate<Integer, String> condition1 = (i, s) -> i > 20;
        BiPredicate<Integer, String> condition2 = (i, s) -> s.startsWith("T");

        System.out.println(condition1.and(condition2).test(10, "One"));
        System.out.println(condition1.and(condition2).test(30, "Two"));
        System.out.println(condition1.or(condition2).test(30, "Three"));
        System.out.println(condition1.and(condition2).negate().test(30, "Three"));
        // ------------------------------------------------------------------------------------
        // BooleanSupplier
        // represents a supplier with boolean-valued results
        BooleanSupplier booleanSupplier = () -> true;
        System.out.println(booleanSupplier.getAsBoolean());

        int firstNumber = 0, secondNumber = 1;
        booleanSupplier = () -> firstNumber > secondNumber;
        System.out.println(booleanSupplier.getAsBoolean());

        booleanSupplier = () -> "String".equals("String");
        System.out.println(booleanSupplier.getAsBoolean());
        // ------------------------------------------------------------------------------------
        // Consumer
        // Represents an operation that accepts a single input argument and returns no result
        Consumer<Integer> consumer1 = i -> {
            System.out.println(i + i);
        };
        Consumer<Integer> consumer2 = i -> {
            System.out.println(i - i);
        };
        consumer1.andThen(consumer2).accept(1);

        Consumer<Integer> consumerInt = i -> {
            System.out.println("Integer value = " + i);
        };
        consumerInt.accept(5);
        consumerInt.accept(8);
        // ------------------------------------------------------------------------------------
        // DoubleBinaryOperator
        // Represents an operation upon two double-valued operands and producing a double-valued result
        DoubleBinaryOperator dBinaryOperator1 = (a, b) -> (a + b);
        DoubleBinaryOperator dBinaryOperator2 = (a, b) -> (a - b);
        DoubleBinaryOperator dBinaryOperator3 = (a, b) -> (a * b);
        DoubleBinaryOperator dBinaryOperator4 = (a, b) -> (a / b);

        System.out.println(dBinaryOperator1.applyAsDouble(5, 6));
        System.out.println(dBinaryOperator2.applyAsDouble(8, -10));
        System.out.println(dBinaryOperator3.applyAsDouble(8, 5));
        System.out.println(dBinaryOperator4.applyAsDouble(8, 4));
        // ------------------------------------------------------------------------------------
        // DoubleConsumer
        // Represents an operation that accepts a single double-valued argument and returns no result
        DoubleConsumer doubleConsumer = (a) -> System.out.println(a);
        // Using accept()
        doubleConsumer.accept(4.5);
        DoubleConsumer doubleConsumer2 = (b) -> System.out.println(b * 2);
        // Using andThen()
        doubleConsumer.andThen(doubleConsumer2).accept(10);
        // ------------------------------------------------------------------------------------
        // DoubleFunction
        // Represents a function that accepts a double-valued argument and produces a result
        DoubleFunction doubleFunction = (x) -> x * 2;
        System.out.println(doubleFunction.apply(3.14));
        DoubleFunction<String> doubleFunctionString = (x) -> "double value: " + x;
        System.out.println(doubleFunctionString.apply(0.123));
        // ------------------------------------------------------------------------------------
        // DoublePredicate
        // Represents a predicate (boolean-valued function) of one double-valued argument
        DoublePredicate notZero = (x) -> x == 0;
        DoublePredicate moreThanOne = (x) -> x > 1;
        DoublePredicate lessThanTen = (x) -> x < 10;
        System.out.println(notZero.negate().or(lessThanTen).and(moreThanOne).test(21.12));
        System.out.println(notZero.negate().or(lessThanTen).and(moreThanOne).test(0.12));
        // ------------------------------------------------------------------------------------
        // DoubleSupplier
        // Represents a supplier of double-valued results
        DoubleSupplier randomSupplier = () -> Math.random() * 10000;
        System.out.println(randomSupplier.getAsDouble());
        double firstDouble = 8;
        double secondDouble = 7;
        DoubleSupplier divisionSupplier = () -> firstDouble / secondDouble;
        System.out.println(divisionSupplier.getAsDouble());
        // ------------------------------------------------------------------------------------
        // DoubleToIntFunction
        // Represents a function that accepts a double-valued argument and produces an int-valued result
        DoubleToIntFunction doubleToIntFunction = (d) -> (int) d;

        System.out.println(doubleToIntFunction.applyAsInt(4.5));
        System.out.println(doubleToIntFunction.applyAsInt(Double.MAX_VALUE));

        DoubleToIntFunction doubleToIntFunction2 = (d) -> {
            Double doubleValue = new Double(d);
            return doubleValue.intValue();
        };

        System.out.println(doubleToIntFunction2.applyAsInt(45.846));
        System.out.println(doubleToIntFunction2.applyAsInt(99.9089));
        // ------------------------------------------------------------------------------------
        // DoubleToLongFunction
        // Represents a function that accepts a double-valued argument and produces a long-valued result
        DoubleToLongFunction dtlFunction1 = (d) -> (int) d;
        System.out.println(dtlFunction1.applyAsLong(45.50));
        System.out.println(dtlFunction1.applyAsLong(Double.MAX_VALUE));

        DoubleToLongFunction dtlFunction2 = (d) -> {
            Double doubleVal = new Double(d);
            return doubleVal.longValue();
        };
        System.out.println(dtlFunction2.applyAsLong(789.456));
        // ------------------------------------------------------------------------------------
        // DoubleUnaryOperator
        // Represents an operation on a single double-valued operand that produces a double-valued result
        DoubleUnaryOperator duOperator = (d) -> d * d;
        System.out.println(duOperator.applyAsDouble(4.5));
        System.out.println(duOperator.applyAsDouble(15.7));

        DoubleUnaryOperator duOperator1 = (d) -> d * d;
        DoubleUnaryOperator duOperator2 = (d) -> d + 1000;

        // Using andThen()
        System.out.println(duOperator1.andThen(duOperator2).applyAsDouble(4.5));
        // Using compose()
        System.out.println(duOperator1.compose(duOperator2).applyAsDouble(4.5));
        System.out.println(duOperator1.compose(duOperator2).applyAsDouble(4.5));
        // ------------------------------------------------------------------------------------
        // Function
        // Represents a function that accepts one argument and produces a result
        Function<Integer, String> isEvenOrOddFunction = (t) -> {
            if (t % 2 == 0) {
                return t + " is even number";
            } else {
                return t + " is odd number";
            }
        };

        System.out.println(isEvenOrOddFunction.apply(5));
        System.out.println(isEvenOrOddFunction.apply(8));

        Function<Integer, String> fizzOrBuzzFunction = (i) -> {
            if (i % 15 == 0) {
                return "FizzBuzz";
            } else if (i % 3 == 0) {
                return "Fizz";
            } else if (i % 5 == 0) {
                return "Buzz";
            } else {
                return String.valueOf(i);
            }
        };
        for (int i = 1; i <= 100; i++) {
            System.out.println(fizzOrBuzzFunction.apply(i));
        }

        Function<Integer, Integer> minusFiveFunction = t -> (t - 5);
        Function<Integer, Integer> multipliesTwoFunction = t -> (t * 2);

        // Using andThen() method
        System.out.println(minusFiveFunction.andThen(multipliesTwoFunction).apply(50));
        // Using compose function
        System.out.println(minusFiveFunction.compose(multipliesTwoFunction).apply(50));
        // ------------------------------------------------------------------------------------
        // IntBinaryOperator
        // Represents an operation upon two int-valued operands and producing an int-valued result
        IntBinaryOperator ibOperator1 = (a, b) -> a + b;
        System.out.println(ibOperator1.applyAsInt(5, 4));

        IntBinaryOperator ibOperator2 = (a, b) -> a - b;
        System.out.println(ibOperator2.applyAsInt(5, 4));

        IntBinaryOperator ibOperator3 = (a, b) -> a * b;
        System.out.println(ibOperator3.applyAsInt(5, 4));
        // ------------------------------------------------------------------------------------
        // IntConsumer
        // Represents an operation that accepts a single int-valued argument and returns no result
        IntConsumer intConsumer = a -> System.out.println(a * a);
        intConsumer.accept(5);
        intConsumer.accept(10);

        IntConsumer intConsumer1 = a -> System.out.println(a * a);
        IntConsumer intConsumer2 = a -> System.out.println(a * 100);

        // Using andThen()
        intConsumer1.andThen(intConsumer2).accept(5);
        intConsumer1.andThen(intConsumer2).accept(50);
        // ------------------------------------------------------------------------------------
        // IntFunction
        // Represents a function that accepts an int-valued argument and produces a result
        IntFunction<String> stringIntFunction = (x) -> Integer.toString(x);
        System.out.println(stringIntFunction.apply(3).length());
        // ------------------------------------------------------------------------------------
        // IntPredicate
        // Represents a predicate (boolean-valued function) of one int-valued argument
        IntPredicate intPredicate = a -> a > 0;
        System.out.println(intPredicate.test(5));
        System.out.println(intPredicate.test(-5));

        IntPredicate intPredicate1 = a -> a > 0;
        IntPredicate intPredicate2 = a -> a == 10;

        // Using and()
        System.out.println(intPredicate1.and(intPredicate2).test(5));
        // Using or()
        System.out.println(intPredicate1.or(intPredicate2).test(12));
        // Using negate()
        System.out.println(intPredicate2.negate().test(10));
        // ------------------------------------------------------------------------------------
        // IntSupplier
        // Represents a supplier of int-valued results
        IntSupplier supplier1 = () -> Integer.MAX_VALUE;
        System.out.println(supplier1.getAsInt());

        int firstInt = 5, secondInt = 10;
        IntSupplier supplier2 = () -> firstInt * secondInt;
        System.out.println(supplier2.getAsInt());
        // ------------------------------------------------------------------------------------
        // IntToDoubleFunction
        // Represents a function that accepts an int-valued argument and produces a double-valued result
        IntToDoubleFunction intToDoubleFunction = (a) -> (a / 3d);

        System.out.println(intToDoubleFunction.applyAsDouble(9));
        System.out.println(intToDoubleFunction.applyAsDouble(22));
        // ------------------------------------------------------------------------------------
        // IntToLongFunction
        // Represents a function that accepts an int-valued argument and produces a long-valued result
        IntToLongFunction intToLongFunction = a -> (long) a;
        System.out.println(intToLongFunction.applyAsLong(45));

        IntToLongFunction intToLongFunction2 = a -> (long) (a * 10);
        System.out.println(intToLongFunction2.applyAsLong(50));
        // ------------------------------------------------------------------------------------
        // IntUnaryOperator
        // Represents an operation on a single int-valued operand that produces an int-valued result
        IntUnaryOperator intUnaryOperator = a -> a * 10;

        System.out.println(intUnaryOperator.applyAsInt(10));
        System.out.println(intUnaryOperator.applyAsInt(12));

        IntUnaryOperator intUnaryOperator1 = a -> a * 10;
        IntUnaryOperator intUnaryOperator2 = a -> a * a;

        // Using andThen()
        System.out.println(intUnaryOperator1.andThen(intUnaryOperator2).applyAsInt(10));
        // Using compose()
        System.out.println(intUnaryOperator1.compose(intUnaryOperator2).applyAsInt(10));
        // ------------------------------------------------------------------------------------
        // LongBinaryOperator
        // Represents an operation upon two long-valued operands and producing a long-valued result
        LongBinaryOperator addition = (a, b) -> a + b;
        LongBinaryOperator subtraction = (a, b) -> a - b;
        LongBinaryOperator multiplication = (a, b) -> a * b;
        LongBinaryOperator division = (a, b) -> a / b;

        System.out.println(addition.applyAsLong(5, 6));
        System.out.println(subtraction.applyAsLong(10, 6));
        System.out.println(multiplication.applyAsLong(8, 3));
        System.out.println(division.applyAsLong(8, 4));
        // ------------------------------------------------------------------------------------
        // LongConsumer
        // Represents an operation that accepts a single long-valued argument and returns no result
        LongConsumer consumer = (a) -> {
            long b = a * a;
            System.out.println(b);
        };
        consumer.accept(10);
        consumer.accept(100);
        consumer.accept(1000);

        LongConsumer longConsumer1 = a -> {
            long square = a * a;
            System.out.println("Square=" + square);
        };

        LongConsumer longConsumer2 = a -> {
            long cube = a * a * a;
            System.out.println("Cube=" + cube);
        };

        // Using andThen() method
        longConsumer1.andThen(longConsumer2).accept(10);
        System.out.println();
        longConsumer2.andThen(longConsumer1).accept(15);
        // ------------------------------------------------------------------------------------
        // LongFunction
        // Represents a function that accepts a long-valued argument and produces a result
        // A function to check whether a given number is greater than zero or not.
        LongFunction<Boolean> longFunction1 = (a) -> a > 0;

        System.out.println(longFunction1.apply(50));
        System.out.println(longFunction1.apply(-20));

        // A function to add number of days in current date and return new date.
        LongFunction<Date> addDays = (day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, (int) day);
            return calendar.getTime();
        };

        System.out.println(addDays.apply(5));
        System.out.println(addDays.apply(50));
        // ------------------------------------------------------------------------------------
        // LongPredicate
        // Represents a predicate (boolean-valued function) of one long-valued argument
        LongPredicate longPredicate = (l) -> (l > 0);
        System.out.println(longPredicate.test(50));
        System.out.println(longPredicate.test(-10));

        LongPredicate longPredicate1 = (l) -> (l > 0);
        LongPredicate longPredicate2 = (l) -> (l == 5);

        // Using and() method
        boolean booleanValue = longPredicate1.and(longPredicate2).test(10);
        System.out.println(booleanValue);

        //Using or() method
        booleanValue = longPredicate1.or(longPredicate2).test(10);
        System.out.println(booleanValue);

        //Using negate() method
        booleanValue = longPredicate1.negate().test(10);
        System.out.println(booleanValue);
        // ------------------------------------------------------------------------------------
        // LongSupplier
        // Represents a supplier of long-valued results
        LongSupplier longSupplier1 = () -> 10l;
        System.out.println(longSupplier1.getAsLong());

        LongSupplier longSupplier2 = () -> new Double(451.456).longValue();
        System.out.println(longSupplier2.getAsLong());
        // ------------------------------------------------------------------------------------
        // LongToDoubleFunction
        // Represents a function that accepts a long-valued argument and produces a double-valued result
        LongToDoubleFunction longToDoubleFunction1 = l -> l / 9.0;
        System.out.println(longToDoubleFunction1.applyAsDouble(50));

        LongToDoubleFunction longToDoubleFunction2 = l -> l * 37.45;
        System.out.println(longToDoubleFunction2.applyAsDouble(15));
        // ------------------------------------------------------------------------------------
        // LongToIntFunction
        // Represents a function that accepts a long-valued argument and produces an int-valued result
        LongToIntFunction longToIntFunction1 = (l) -> (int) l;
        System.out.println(longToIntFunction1.applyAsInt(1000L));
        System.out.println(longToIntFunction1.applyAsInt(-45454L));

        LongToIntFunction longToIntFunction2 = (l) -> String.valueOf(l).length();
        System.out.println(longToIntFunction2.applyAsInt(454545L));
        System.out.println(longToIntFunction2.applyAsInt(Long.MAX_VALUE));
        // ------------------------------------------------------------------------------------
        // LongUnaryOperator
        // Represents a function that accepts a long-valued argument and produces an int-valued result
        LongUnaryOperator longUnaryOperator = (l) -> l * l;
        System.out.println(longUnaryOperator.applyAsLong(45L));
        System.out.println(longUnaryOperator.applyAsLong(100L));

        LongUnaryOperator longUnaryOperator1 = (l) -> l + 10;
        LongUnaryOperator longUnaryOperator2 = (l) -> l * 2;

        // Using default method andThen()
        System.out.println(longUnaryOperator1.andThen(longUnaryOperator2).applyAsLong(10));
        System.out.println(longUnaryOperator2.andThen(longUnaryOperator1).applyAsLong(10));

        //Using default method compose()
        System.out.println(longUnaryOperator1.compose(longUnaryOperator2).applyAsLong(10));
        System.out.println(longUnaryOperator2.compose(longUnaryOperator1).applyAsLong(10));
        // ------------------------------------------------------------------------------------
        // ObjDoubleConsumer
        // Represents an operation that accepts an object-valued and a double-valued argument, and returns no result
        ObjDoubleConsumer<String> objDoubleConsumer = (x, y) -> System.out.println(x + " " + y);
        // Example of accept
        objDoubleConsumer.accept("Interface", 2.00);
        // ------------------------------------------------------------------------------------
        // ObjIntConsumer
        // Represents an operation that accepts an object-valued and a int-valued argument, and returns no result
        ObjIntConsumer<String> objIntConsumer = (t, i) -> System.out.println(t + i);
        objIntConsumer.accept("ObjIntConsumer = ", 8);
        // ------------------------------------------------------------------------------------
        // ObjLongConsumer
        // Represents an operation that accepts an object-valued and a long-valued argument, and returns no result
        ObjLongConsumer<String> objLongConsumer = (t, i) -> System.out.println(t + i);
        objLongConsumer.accept("ObjLongConsumer = ", 8L);
        // ------------------------------------------------------------------------------------
        // Predicate
        // Represents a predicate (boolean-valued function) of one argument
        // Predicate String
        Predicate<String> predicateString = s -> s.equals("Hello");
        System.out.println(predicateString.test("Hello"));
        System.out.println(predicateString.test("Hello World"));

        // Predicate integer
        Predicate<Integer> predicateInt = i -> i > 0;
        System.out.println(predicateInt.test(5));
        System.out.println(predicateInt.test(-5));

        Predicate<String> predicate = s -> s.equals("Hello");

        // AND logical operation
        Predicate<String> predicateAnd = predicate.and(s -> s.length() > 4);
        System.out.println(predicateAnd.test("Hello"));
        // OR logical operation
        Predicate<String> predicateOr = predicate.or(s -> s.length() == 10);
        System.out.println(predicateOr.test("Hello"));
        // NEGATE logical operation
        Predicate<String> predicateNegate = predicate.negate();
        System.out.println(predicateNegate.test("Hello"));
        // ------------------------------------------------------------------------------------
        // Supplier
        // Represents a supplier of results
        Supplier<String> supplierStr = () -> "Hello world.";
        Supplier<Number> supplierNum = () -> 40;

        System.out.println(supplierStr.get());
        System.out.println(supplierNum.get());
        // ------------------------------------------------------------------------------------
        // ToDoubleBiFunction
        // Represents a function that accepts two arguments and produces a double-valued result
        ToDoubleBiFunction<Integer, Long> toDoubleBiFunction = (x, y) -> Math.sin(x) + Math.sin(y);
        System.out.println(toDoubleBiFunction.applyAsDouble(Integer.MAX_VALUE, Long.MAX_VALUE));
        // ------------------------------------------------------------------------------------
        // ToDoubleFunction
        // Represents a function that produces a double-valued result
        ToDoubleFunction<Integer> toDoubleFunction = (x) -> Math.sin(x);
        System.out.println(toDoubleFunction.applyAsDouble(Integer.MAX_VALUE));
        // ------------------------------------------------------------------------------------
        // ToIntBiFunction
        // Represents a function that accepts two arguments and produces an int-valued result
        ToIntBiFunction<String, String> toIntBiFunction = (x, y) -> Integer.parseInt(x) + Integer.parseInt(x);
        System.out.println(toIntBiFunction.applyAsInt("2", "3"));
        // ------------------------------------------------------------------------------------
        // ToIntFunction
        // Represents a function that produces an int-valued result
        ToIntFunction<String> toIntFunction = (x) -> Integer.parseInt(x);
        System.out.println(toIntFunction.applyAsInt("2"));
        // ------------------------------------------------------------------------------------
        // ToLongBiFunction
        // Represents a function that accepts two arguments and produces a long-valued result
        ToLongBiFunction<String, String> toLongBiFunction = (x, y) -> Long.parseLong(x) + Long.parseLong(y);
        System.out.println(toLongBiFunction.applyAsLong("2", "2"));
        // ------------------------------------------------------------------------------------
        // ToLongFunction
        // Represents a function that produces a long-valued result
        ToLongFunction<String> i = (x) -> Long.parseLong(x);
        System.out.println(i.applyAsLong("2"));
        // ------------------------------------------------------------------------------------
        // UnaryOperator
        // Represents an operation on a single operand that produces a result of the same type as its operand
        UnaryOperator<Integer> unaryOperator = t -> t * 2;

        System.out.println(unaryOperator.apply(5));
        System.out.println(unaryOperator.apply(10));
        System.out.println(unaryOperator.apply(15));

        UnaryOperator<Integer> unaryOperator1 = t -> t + 10;
        UnaryOperator<Integer> unaryOperator2 = t -> t * 10;

        // Using andThen()
        System.out.println(unaryOperator1.andThen(unaryOperator2).apply(5));
        // Using compose()
        System.out.println(unaryOperator1.compose(unaryOperator2).apply(5));
    }
}
