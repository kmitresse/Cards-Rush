package uppa.project.json.websocket;

import com.google.gson.Gson;

public class Message {
  private final static Gson gson = new Gson();

  private final String type;
  private final String data;

  public Message(String type, String data) {
    this.type = type;
    this.data = data;
  }

  public String getType() {
    return type;
  }

  public String getData() {
    return data;
  }

  public String toJson() {
    return gson.toJson(this);
  }

  @Override
  public String toString() {
    return "Message{" + "type=" + type + ", data=" + data + '}';
  }
}
