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

  /**
   * 아이디 객체 생성해주는 메소드.
   * 
   * @param userId 아이디
   * @param name 비밀번호
   * @return Account객체를 반환
   */
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
