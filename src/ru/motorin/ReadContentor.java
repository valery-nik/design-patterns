package ru.motorin;

import io.vavr.control.Try;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static java.net.Proxy.Type.HTTP;

public class ReadContentor {

    /**
     * Процедурный/императивный подход - говорим как надо сделать.
     * Минусы:
     * - временные переменные
     * - обработка ошибок
     * - что бы понять, что для чего этот код - нужно вникнуть как он работает!
     *
     * @param location
     * @return
     */
    public static String readContent(String location) {
        try {

            URL url = new URL(location);
            if (!"http".equals(url.getProtocol())) {
                throw new UnsupportedOperationException("Protocol is not http");
            }

            URLConnection urlConnection = url.openConnection();
            try (InputStream inputStream = urlConnection.getInputStream()) {
                return read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading location " + location, e);
        }
    }

    private static String read(InputStream inputStream) {
        return "null";
    }

    /**
     * ФП - мы по попунктно говорим, что мы хотим сделать. У нас в коде нет реализации того как мы это хотим делать.
     * Этот код более понятен, т.к. содержит только бизнес логику, нет отвлекающего кода реализации
     * Еще плюсы:
     * - нет временных переменных
     * - нет сложной логики обработки ошибок
     * - клиент этого метода может заюзать стратегию ретраев...
     *
     * @param location
     * @return
     */
    public static Try<String> readContentFunctional(String location) {
        return Try.of(() -> new URL(location))
                .filter(url -> HTTP.equals(url.getProtocol()))
                .mapTry(URL::openConnection)
                .mapTry(URLConnection::getInputStream)
                .map(ReadContentor::read);
    }

    public static void main(String[] args) {

//        Predicate<RuntimeException> instanceOfRuntimeException = instanceOf(RuntimeException.class);
//        readContentFunctional("wwww.ya.ru")
//                .recover(r -> Match(r).of(
//                        Case(instanceOf(RuntimeException.class), new RuntimeException(""))
////                        Case(instanceOfRuntimeException, new RuntimeException()) //,
////                        Case(predicate, "defaultResponse"),
////                        Case(instanceOf(IllegalArgumentException.class), "defaultResponse")
//                ));
    }
}
