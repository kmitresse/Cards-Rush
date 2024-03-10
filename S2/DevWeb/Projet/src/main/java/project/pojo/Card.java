package project.pojo;

public class Card {

  public enum Color{coeur, carreau, pique, trèfle}

  public enum Value{un, deux, trois, quatre, cinq, six, sept, huit, neuf, dix, valet, dame, roi}
  private Color color;

  private Value value;

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
