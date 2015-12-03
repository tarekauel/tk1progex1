package clients;

public interface ComboBoxObject<T> {
  T getObject();

  @Override
  String toString();
}