package uppa.project.pojo.json;

import uppa.project.pojo.User;

public class LoginResponse extends HttpResponse {

  private final User user;

  private final String redirect;

  public LoginResponse(int status, User user, String redirect) {
    super(status);
    this.user = user;
    this.redirect = redirect;
  }

  public User getUser() {
    return user;
  }

  public String getRedirect() {
    return redirect;
  }
}
