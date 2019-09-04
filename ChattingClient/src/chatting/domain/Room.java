package chatting.domain;


import java.util.List;

public class Room implements java.io.Serializable {

  /**
   * serialVersion UID
   */
  private static final long serialVersionUID = -2632141805781194824L;


  private int roomId;

  private String name;
  
  private int count;

  private List<Account> users;

  private boolean isPrivate;


  public int getRoomId() {

    return roomId;
  }


  public int getCount() {

    return count;
  }


  public List<Account> getUsers() {

    return users;
  }


  public boolean isPrivate() {

    return isPrivate;
  }


  public void setRoomId(int roomId) {

    this.roomId = roomId;
  }


  public void setCount(int count) {

    this.count = count;
  }


  public void setUsers(List<Account> users) {

    this.users = users;
  }


  public void setPrivate(boolean isPrivate) {

    this.isPrivate = isPrivate;
  }


  public String getName() {

    return name;
  }


  public void setName(String name) {

    this.name = name;
  }


}
