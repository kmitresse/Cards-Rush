package uppa.project.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import uppa.project.web.translation.Translator;

@WebServlet(name = "translationServlet", value = "/translate")
public class TranslationServlet extends HttpServlet {

  public void init() {
  }

  public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (request.getParameter("language") != null) {
      String language = request.getParameter("language");
      request.getSession().setAttribute("language", language);
      Translator translator = Translator.generateTranslator(request.getSession(), request.getServletContext());
      request.getSession().setAttribute("translator", translator);
    }
  }
}
