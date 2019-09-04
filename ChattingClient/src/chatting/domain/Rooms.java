package chatting.domain;


import java.util.HashSet;
import java.util.Set;

public class Rooms {

  private static Set<Integer> rooms = new HashSet<>();

  private Rooms() {}

  public static Set<Integer> getRooms() {

    return rooms;
  }

}
