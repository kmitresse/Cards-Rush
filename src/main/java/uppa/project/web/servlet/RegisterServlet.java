/*
 * RegisterServlet.java, 20/03/2024
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
import uppa.project.bean.RegisterBean;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;
import uppa.project.web.translation.Translator;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

  public void init() {
  }

  /**
   * Affichage de la page d'inscription
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   * @throws ServletException si une erreur de servlet survient
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (request.getSession().getAttribute("translator") == null) {
      request.getSession().setAttribute("language", "FR");
      request.getSession().setAttribute("translator", Translator.generateTranslator(request.getSession(), request.getServletContext()));
    }
    request.setAttribute("current", "register");
    request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
  }

  /**
   * Gestion de l'inscription
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    Translator translator = (Translator) request.getSession().getAttribute("translator");
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();


    RegisterBean registerBean = new RegisterBean()
      .setTranslator(translator)
      .setUsername(request.getParameter("username"))
      .setEmail(request.getParameter("email"))
      .setPassword(request.getParameter("password"))
      .setConfirmPassword(request.getParameter("repassword"))
      .setBirth(request.getParameter("birth"))
      .setGender(request.getParameter("gender"))
      ;

    Gson gson = new Gson();
    HttpResponse httpResponse;
    if (registerBean.validate()) {
      httpResponse = new HttpResponse(
        HttpResponseCode.OK,
        "Register success"
      );
    } else {
      httpResponse = registerBean.getError();
    }

    out.println(gson.toJson(httpResponse));
    out.flush();
  }

  public void destroy() {
  }
}
