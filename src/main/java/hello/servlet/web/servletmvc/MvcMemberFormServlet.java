package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; //WEB-INF: 외부에서 직접 호출하지 않고 컨트롤러, 서블릿를 통해 호출할 수 있게끔 하는 것.
        // 이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다. 우리가 기대하는 것은 항상 컨트롤러를 통해서 JSP를 호출하는 것이다.
        
        //view로 이동
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        //다른 서블릿이나 jsp로 이동할 수 있는 기능, 서버 내부에서 다시 호출이 발생한다.
        requestDispatcher.forward(request, response);
    }
}
