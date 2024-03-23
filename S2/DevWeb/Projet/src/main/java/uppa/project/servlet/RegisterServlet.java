/*
 * RegisterServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import uppa.project.dao.DAOException;
import uppa.project.dao.jpa.DAO_JPA_User;
import uppa.project.pojo.User;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (request.getSession().getAttribute("user") != null) {
      response.sendRedirect(request.getContextPath() + "/main-menu");
      return;
    }

    request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String birthdate = request.getParameter("birthdate");
    System.out.println(birthdate);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date birth;
    try {
      birth = formatter.parse(birthdate);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    System.out.println("birth: " + birth);
    String gender = request.getParameter("gender");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");
    if (!password.equals(confirmPassword)) {
      response.sendRedirect(request.getContextPath() + "/register?error=matching-password");
      return;
    }
    createAccount(username, email, password, birth, gender);
    response.sendRedirect(request.getContextPath() + "/login?success=account-created");
  }

  public static void createAccount(String username, String email, String password, Date birth, String gender){
    try{
      DAO_JPA_User daoJpaUser = new DAO_JPA_User();
      User user = new User(username, email, password, birth, User.getGender(gender));
      daoJpaUser.create(user);
    } catch (DAOException | IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
  }
  public void destroy() {
  }
}
