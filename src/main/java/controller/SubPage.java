package controller;

import annotations.WebRoute;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class SubPage implements HttpHandler {

    @WebRoute("/subPage")
    public void handle(HttpExchange httpExchange) throws IOException {

        String response = "<!DOCTYPE html5><html><head></head><body>This is SubPage. <a href='/index'>link to index</a></body></html>";

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
