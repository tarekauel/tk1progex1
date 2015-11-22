package client;

import impl.IGameClient;
import impl.IGameServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements IGameClient {

  private final FlyView f;
  private final IGameServer s;

  public static void main(String[] args) {
    if (args.length < 1) {
      throw new IllegalArgumentException("No player name provided");
    }
    Registry r;
    try {
      r = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
      IGameServer s = (IGameServer) r.lookup("/server");
      new Client(s, args[0]);
    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }

  private Client(IGameServer s, String playerName) throws RemoteException {
    this.f = new FlyView(this);
    this.s = s;
    if (!s.login(playerName, this)) {
      throw new RuntimeException("Couldn't log in");
    }
  }

  @Override
  public void receiveFlyHunted(String playerName, int newPoints) throws RemoteException {
    f.setPlayerScore(playerName, newPoints);
  }

  @Override
  public void receiveFlyPosition(int x, int y) throws RemoteException {
    f.setFlyPosition(x, y);
  }

  @Override
  public void playerJoined(String playerName) throws RemoteException {
    f.addPlayer(playerName);
  }

  @Override
  public void playerLeft(String playerName) throws RemoteException {
    f.removePlayer(playerName);
  }

  public void logout() {
    try {
      s.logout(this);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public void onFlyHunted() {
    try {
      s.huntFly(this);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}
