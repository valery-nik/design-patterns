package ru.motorin.functions;

import java.util.List;
import java.util.function.Function;

public interface BusinessObjectService {

    BusinessObject createBusinessObject(String params);

    BusinessObject transform(BusinessObject model);

    BusinessObject transform2(BusinessObject model, String params);

    List<String> validate(BusinessObject model);

    //Процедурный стиль - "как делать"
    default List<String> process1(String params) {
        BusinessObject model = createBusinessObject(params);
        BusinessObject transformed = transform(model);

        return validate(transformed);
    }

    //  Процедурный стиль, но добавка чейна вызовов. Аля функциональность.
    //  Данная трансформация тоже совсем не улучшила читабельность, т.к. она стала с права на лево.
    //  А если еще расширить сигнатуру метода transform доп параметрами, то данный чейн рефакторинг окончательно убъет
    //  читабельность кода.
    default List<String> process2(String params) {
        return validate(transform(createBusinessObject(params)));
    }


    // Функциональный стиль - релизован подход "что делать"
    default List<String> process(String params) {
        // Если присмотреться, то все три метода сервиса - это функции!
        // А значит можно реализовать их композицию/чейнинг - см. first
        return first(this::createBusinessObject)
                .andThen(this::transform)
                .andThen(businessObject -> this.transform2(businessObject, params))
                .andThen(this::validate)
                .apply(params); // запуск на исполнение
    }

    default <K, V> Function<K, V> first(Function<K, V> f) {
        return f;
    }
}
