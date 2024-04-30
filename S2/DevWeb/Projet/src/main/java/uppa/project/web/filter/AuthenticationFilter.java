package uppa.project.web.filter;

import jakarta.persistence.EntityManager;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.User;

public class AuthenticationFilter  implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpSession session = httpRequest.getSession(true);

    // Vérifier si l'utilisateur est connecté en vérifiant la présence d'une session
    boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

    // Si l'utilisateur est connecté, laisser passer la requête
    if (isLoggedIn) {
      User currentUser = (User) session.getAttribute("user");
      try {
        // Met à jour l'utilisateur en session
        DAO<User> userDAO = new Game_JPA_DAO_Factory().getDAOUser();
        session.removeAttribute("user");
        EntityManager em = EntityManagerProvider.getInstance();

        em.getTransaction().begin();
        session.setAttribute("user", userDAO.findById(currentUser.getId().intValue()));
        em.getTransaction().commit();

        // Passe la requête au filtre suivant
        chain.doFilter(request, response);
      } catch (DAOException e) {
        throw new RuntimeException(e);
      }
    } else {
      // Sinon, rediriger vers la page de connexion
      httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
    }
  }
}
