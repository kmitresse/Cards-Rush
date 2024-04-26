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
import java.util.ArrayList;
import java.util.List;
import uppa.project.bean.ProfileBean;
import uppa.project.bean.RegisterBean;
import uppa.project.database.pojo.Game;
import uppa.project.database.pojo.Player;
import uppa.project.database.pojo.User;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;

@WebServlet(name = "profileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//    request.setAttribute("current", "profile");
    request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    ProfileBean profileBean = new ProfileBean()
      .setId(request.getParameter("id"))
      .setOldEmail(request.getParameter("oldEmail"))
      .setEmail(request.getParameter("email"))
      .setPassword(request.getParameter("password"))
      .setGender(request.getParameter("gender"))
      ;

    Gson gson = new Gson();
    HttpResponse httpResponse;
    if (profileBean.validate()) {
      request.getSession().setAttribute("user", profileBean.getUser());
      httpResponse = new HttpResponse(
        HttpResponseCode.OK,
        "Register success"
      );
    } else {
      httpResponse = profileBean.getError();
    }
    out.println(gson.toJson(httpResponse));
    out.flush();
  }

  public void destroy() {
  }
}
