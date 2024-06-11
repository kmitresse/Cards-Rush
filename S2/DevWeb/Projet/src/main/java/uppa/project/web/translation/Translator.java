package uppa.project.web.translation;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Translator {

  private final String language;

  private JsonParser parser;

  private JsonObject translations;

  public enum Language {EN, FR}

  public Translator(Language language) {
    System.out.println("Creating translator for language: " + language.name());
    this.language = language.name();
    this.parser = new JsonParser();
  }

  public static Translator generateTranslator(HttpSession session , ServletContext context) {
    Translator translator;
    System.out.println("null?" + session.getAttribute("language"));
    System.out.println("language EN ??: " + session.getAttribute("language").equals(Translator.Language.EN.name()));
    if (session.getAttribute("language") != null && session.getAttribute("language").equals(Translator.Language.EN.name())) {
      translator = new Translator(Translator.Language.EN);
    } else {
      translator = new Translator(Translator.Language.FR);
    }
    translator.setJsonContent(context);
    return translator;
  }

  public void setJsonContent(ServletContext context) {
    InputStream is = context.getResourceAsStream("/WEB-INF/translations.json");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
    //Parse the File as a JSON Object
    JsonElement fileElement = parser.parse(reader);
    this.translations = fileElement.getAsJsonObject();
  }

  public String translate(String text) {
    System.out.println("Translating: " + text);
    System.out.println("langue: " + this.language);
    return this.translations.get(text).getAsJsonObject().get(this.language).getAsString();
  }

  public String getLanguage() {
    return language;
  }
}
