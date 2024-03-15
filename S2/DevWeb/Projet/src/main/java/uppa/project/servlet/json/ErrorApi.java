package uppa.project.servlet.json;

import com.google.gson.Gson;

public class ErrorApi {
  private final int status;
  private final String error;
  private final String message;

  public ErrorApi(int status, String error, String message) {
    this.status = status;
    this.error = error;
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public String getMessage() {
    return message;
  }

  public String toJson() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
