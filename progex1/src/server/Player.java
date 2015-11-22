package server;

class Player {

  private final String name;
  private int points = 0;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getPoints() {
    return points;
  }

  public void hunted() {
    ++points;
  }
}
