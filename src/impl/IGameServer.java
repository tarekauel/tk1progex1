package impl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameServer extends Remote {

  boolean login(String playerName, IGameClient client) throws RemoteException;

  void logout(IGameClient client) throws RemoteException;

  void huntFly(IGameClient client) throws RemoteException;

}
