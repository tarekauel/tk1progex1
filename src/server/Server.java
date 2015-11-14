package server;

import impl.IGameClient;
import impl.IGameServer;

import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Server extends UnicastRemoteObject implements IGameServer {

  // Static field prevents JVM from stopping the server
  private static Registry r;

  private final Random random = new Random();

  private final HashMap<IGameClient, Player> players = new HashMap<>();

  private int x;
  private int y;

  public static void main(String[] args) {

    try {
      r = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
      System.out.println("registry created");
      r.bind("/server", new Server());
      System.out.println("server bound to /server");
    } catch (RemoteException | AlreadyBoundException e) {
      System.err.println("Couldn't initialize server");
    }
  }

  private Server() throws RemoteException {
    setFly();
  }

  @Override
  public synchronized boolean login(String playerName, IGameClient client) throws RemoteException {
    if (players.containsKey(client)) {
      return false;
    } else {
      players.put(client, new Player(playerName));
      client.receiveFlyPosition(this.x, this.y);
      return true;
    }
  }

  @Override
  public synchronized void logout(IGameClient client) throws RemoteException {
    if (players.containsKey(client)) {
      players.remove(client);
    }
  }

  @Override
  public synchronized void huntFly(IGameClient client) throws RemoteException {
    Player p;
    if ((p = players.get(client)) != null) {
      p.hunted();
      notifyAll(p.getName(), p.getPoints());
    }
  }

  private void setFly() {
    this.x = random.nextInt(200);
    this.y = random.nextInt(200);
  }

  private void notifyAll(String playerName, int points) {
    setFly();
    ArrayList<IGameClient> toRemove = new ArrayList<>();
    for (IGameClient client : players.keySet()) {
      try {
        client.receiveFlyHunted(playerName, points);
        client.receiveFlyPosition(this.x, this.y);
      } catch (ConnectException e) {
        toRemove.add(client);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

    for (IGameClient c : toRemove) {
      players.remove(c);
    }
  }
}
