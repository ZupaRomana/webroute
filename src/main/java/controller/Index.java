package controller;

import annotations.WebRoute;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class Index implements HttpHandler {

    @WebRoute("/index")
    public void handle(HttpExchange httpExchange) throws IOException {

        String response = "<!DOCTYPE html5><html><head></head><body>This is index page. <a href='/subPage'>link to subpage</a></body></html>";

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}
