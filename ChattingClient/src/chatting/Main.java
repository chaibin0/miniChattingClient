package chatting;

import chatting.view.login.LoginView;

public class Main {
  
  /**
   * 로그인 화면으로 보내준다.
   */
  public static final void main(String[] args) {

    LoginView loginView = new LoginView();
    loginView.go();

  }


}
