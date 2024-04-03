package uppa.project.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class SessionServletContextListener implements ServletContextListener {
  public void contextInitialized(ServletContextEvent event){
    Set<HttpSession> logins = new HashSet<HttpSession>();
    event.getServletContext().setAttribute("loginSession", logins);
  }

  public static void addSession(HttpSession session){
    Set<HttpSession> logins = (Set<HttpSession>) session.getServletContext().getAttribute("loginSession");
    logins.add(session);
  }

  public static void removeSession(HttpSession session){
    Set<HttpSession> logins = (Set<HttpSession>) session.getServletContext().getAttribute("loginSession");
    logins.remove(session);
  }

  public void contextDestroyed(ServletContextEvent event){}
}

