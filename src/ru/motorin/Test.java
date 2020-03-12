//package ru.motorin;
//
//import io.vavr.control.Try;
//
//import static io.vavr.API.Case;
//import static io.vavr.API.Match;
//import static io.vavr.Predicates.instanceOf;
//
//public class Test {
//
//    public static void main(String[] args) {
//
//        // given
//        Response defaultResponse = new Response("b");
//
//        HttpClient httpClient = () -> {
//            throw new RuntimeException("critical problem");
//        };
//
//        // when
//        Try<Response> recovered = new VavrTry(httpClient).getResponse()
//                .recover(r -> Match(r).of(
//                        Case(instanceOf(RuntimeException.class), defaultResponse)
//                ));
//
//
//    }
//}
//
//interface HttpClient {
//    Response call() throws RuntimeException;
//}
//
//class Response {
//    public final String id;
//
//    public Response(String id) {
//        this.id = id;
//    }
//}
//
//class VavrTry {
//    private HttpClient httpClient;
//
//    public Try<Response> getResponse() {
//        return Try.of(httpClient::call);
//    }
//
//    public VavrTry(HttpClient client) {
//        httpClient = client;
//    }
//    // standard constructors
//}
//
