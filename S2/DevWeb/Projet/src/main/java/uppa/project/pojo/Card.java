package uppa.project.pojo;

public class Card {

  public enum Color{coeur, carreau, pique, trefle}

  public enum Value{un, deux, trois, quatre, cinq, six, sept, huit, neuf, dix, valet, dame, roi}
  private final Color color;

  private final Value value;

  public Card(Color color, Value value) {
    this.color = color;
    this.value = value;
  }

  public Color getColor() {
    return color;
  }

  public Value getValue() {
    return value;
  }
}
