package uppa.project.provider;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvProvider {
  static Dotenv instance;

  public static Dotenv getInstance() {
    if (instance == null) {
      instance = Dotenv.configure().load();
    }
    return instance;
  }
}