package uppa.project.web.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AlreadyLoggedFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpSession session = httpRequest.getSession(false);

    // Vérifier si l'utilisateur est connecté en vérifiant la présence d'une session
    boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

    // Si l'utilisateur n'est pas connecté, laisser passer la requête
    if (!isLoggedIn) {
      chain.doFilter(request, response);
    } else {
      // Sinon, rediriger vers la page du menu principal
      httpResponse.sendRedirect(httpRequest.getContextPath() + "/main-menu");
    }
  }
}
