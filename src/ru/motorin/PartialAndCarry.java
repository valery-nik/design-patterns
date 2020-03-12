package ru.motorin;

import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;

/**
 * Частичное применение - процесс фиксации части аргументов функции, который создает другую функцию меньшей мощности.
 *
 * Каррирование - преобразование функции от многих элементов, к функцие берущей свои элементы по одному.
 */
public class PartialAndCarry {

    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static IntBinaryOperator partialSum(int a) {
        return (b, c) -> sum(a, b, c);
    }

    public static IntFunction<IntFunction<IntFunction>> currySum() {
        return a -> b -> c -> sum(a, b, c);
    }

    public static void main(String[] args) {
        IntBinaryOperator func5 = partialSum(5);
        IntBinaryOperator func11 = partialSum(11);

        System.out.println(func5.applyAsInt(1, 2));
        System.out.println(func11.applyAsInt(1, 2));


        // builder fp like code
        System.out.println(currySum()
                .apply(1)
                .apply(2)
                .apply(3));
    }
}
