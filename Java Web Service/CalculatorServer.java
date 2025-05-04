import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CalculatorServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/add", new AddHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started at http://localhost:8000/add");
    }

    static class AddHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                Map<String, String> params = parseQuery(body);

                int num1 = Integer.parseInt(params.getOrDefault("num1", "0"));
                int num2 = Integer.parseInt(params.getOrDefault("num2", "0"));
                int result = num1 + num2;

                String response = String.valueOf(result);
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String response = "Only POST supported";
                exchange.sendResponseHeaders(405, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }

        private Map<String, String> parseQuery(String query) throws IOException {
            Map<String, String> map = new HashMap<>();
            for (String pair : query.split("&")) {
                String[] parts = pair.split("=");
                if (parts.length == 2) {
                    map.put(URLDecoder.decode(parts[0], "UTF-8"),
                            URLDecoder.decode(parts[1], "UTF-8"));
                }
            }
            return map;
        }
    }
}
