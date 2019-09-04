package chatting.domain;


public class Account {

  private static Account account;

  // ID
  String userId;

  // user Name
  String name;

  public static Account getAccount() {

    return account;
  }
  
  public static Account newAccount(String userId, String name) {

    account = new Account();
    account.userId = userId;
    account.name = name;
    return account;
  }


  private Account() {}


  public String getUserId() {

    return userId;
  }

  public String getName() {

    return name;
  }


  public void setUserId(String userId) {

    this.userId = userId;
  }


  public void setName(String name) {

    this.name = name;
  }

}
