package model.packets;

import java.io.Serializable;

/**
 * Created by tarek on 02/02/16.
 */
public class StartStopSignal implements Serializable {

  private final boolean running;

  public StartStopSignal(boolean running) {
    this.running = running;
  }

  public boolean isRunning() {
    return running;
  }
}
