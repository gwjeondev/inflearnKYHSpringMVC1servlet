package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.  파라미터 전송기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println("paramName = " + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");

        System.out.println();

        System.out.println("단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");

        System.out.println();

        System.out.println("[전체 파라미터 Map 조회] - start");

        Map<String, String[]> parameterMap = request.getParameterMap();
        //System.out.println("parameterMap = " + parameterMap.get("username"));
        for (String s : parameterMap.keySet()) {
            System.out.println("parameterMap = " + parameterMap.get(s)[0]);
            System.out.println("[전체 파라미터 Map 조회] - end");
        }

        System.out.println();

        System.out.println("[이름이 같은 파라미터 조회] - start");
        String[] usernames = request.getParameterValues("username");
        for (String s : usernames) {
            System.out.println("s = " + s);
        }
        System.out.println("[이름이 같은 파라미터 조회] - end");

        response.getWriter().write("ok");
    }
}
