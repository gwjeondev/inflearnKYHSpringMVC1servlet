package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV4 controllerV4 = controllerMap.get(requestURI);
        if(controllerV4 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        Map<String, String> paramMap = createParamMap(request); //parameter를 모두 가져온다
        Map<String, Object> model = new HashMap<>(); //v4버전에서 model이 추가됨

        String viewName = controllerV4.process(paramMap, model);//가져온 parameter를 controller에 model과 함께 전달
        MyView view = viewResolver(viewName); //컨트롤러에서 반환한 viewName을 resolver에 전달하여 물리적인 주소로 변환하고 MyView를 반환받는다.

        //view의 render에 model과 req, res를 전달하면 view의 render는 model의 데이터가 있으면 req의 attribute에 set하고 지정된 물리적인 주소를 forward한다.
        view.render(model, request, response);
    }

    //논리적인 주소를 물리적인 주소로 변환
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    //request받은 parameter가 있다면 반복을 통해서 paramMap에 저장후 리턴한다
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}