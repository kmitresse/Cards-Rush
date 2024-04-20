/*
 * LoginServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.web.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import uppa.project.bean.LoginBean;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setAttribute("current", "login");
    request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    LoginBean loginBean = new LoginBean()
      .setUsername(request.getParameter("username"))
      .setPassword(request.getParameter("password"))
    ;

    Gson gson = new Gson();
    HttpResponse httpResponse;
    if (loginBean.validate()) {
      request.getSession().setAttribute("user", loginBean.getUser());

      httpResponse = new HttpResponse(HttpResponseCode.OK, "Login success");
    } else {
      httpResponse = loginBean.getError();
    }
    out.println(gson.toJson(httpResponse));
    out.flush();
  }

  public void destroy() {
  }
}
