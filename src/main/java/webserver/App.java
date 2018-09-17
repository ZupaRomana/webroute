package webserver;

import annotations.WebRoute;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String[] controllers = {"IndexController", "PageController"};
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        for (String controller : controllers) {
            Class obj = Class.forName("webserver." + controller);

            Constructor constructor = obj.getConstructor();
            Method[] methods = obj.getMethods();

            for(Method method : methods) {
                Annotation annotation = null;
                if (method.getName().equals("handle")) {
                    annotation = method.getAnnotation(WebRoute.class);
                }
                if (annotation instanceof WebRoute) {
                    WebRoute webRoute = (WebRoute) annotation;
                    server.createContext(webRoute.path(), (HttpHandler) constructor.newInstance());
                }
            }
        }

        server.setExecutor(null);
        server.start();
    }
}
