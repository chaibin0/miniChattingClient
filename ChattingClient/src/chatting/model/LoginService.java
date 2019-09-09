package chatting.model;

import chatting.domain.Account;
import chatting.view.error.LoginError;
import chatting.view.error.LoginExistError;
import chatting.view.error.ServerConnectError;
import chatting.view.error.SignUpError;
import chatting.view.login.SuccessSignUpView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginService {

  /**
   * 서버에게 아이디 비밀번호가 일치하는지 확인하고 일치하면 로그인하는 메소드. 자신의 이름을 데이터를 받고 성공여부를 반환
   * 
   * @param userId 아이디
   * @param pwd 비밀번호
   * @return 로그인 성공하면 true 실패하면 false가 반환됩니다.
   */
  public boolean findByUserIdAndPwd(String userId, char[] pwd) {

    try (Socket sock = new Socket("127.0.0.1", 5001);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

      writer.write("find" + "&" + userId + "&" + SecurityService.encrypt(new String(pwd)) + "\n");
      writer.flush();

      // 로그인 확인 결과
      String message = "";
      boolean flag = false;
      while ((message = reader.readLine()) != null) {
        flag = true;
        if (message.equals("nothing")) {
          LoginError.go();
          return false;
        } else if (message.equals("exist")) {
          LoginExistError.go();
          return false;
        } else {
          Account.newAccount(userId, message);
          System.out.println("로그인 성공 userID");
          return true;
        }
      }
      if (!flag) {
        System.out.println("로그인 실패");
        return false;

      }
    } catch (ConnectException e) {
      System.out.println("서버 접속을 실패하였습니다");
      ServerConnectError.go();
      e.printStackTrace();
    } catch (UnknownHostException e) {
      System.out.println("서버 접속을 실패하였습니다");
      ServerConnectError.go();
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;

  }

  /**
   * 회원가입하는 메소드.
   * 
   * @param userId 유저아이디
   * @param pwd 비밀번호
   * @param name 이름
   * @return 회원가입이 성공하면 'true'로 반환합니다.
   */
  public static boolean signUp(String userId, char[] pwd, String name) {

    if (isNull(userId, pwd, name)) {
      SignUpError error = new SignUpError("모든 데이터를 입력해주세요.");
      error.go();
      return false;
    }
    if (!checkUserId(userId) || !checkPwd(new String(pwd))) {
      SignUpError error = new SignUpError("아이디 비밀번호 형식이 잘못 됐습니다.");
      error.go();
      return false;
    }

    try (Socket sock = new Socket("127.0.0.1", 5001);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

      writer.println(
          "signup" + "&" + userId + "&" + SecurityService.encrypt(new String(pwd)) + "&" + name);
      writer.flush();

      String message = "";
      while ((message = reader.readLine()) != null) {
        if (message.equals("fail")) {
          SignUpError error = new SignUpError("회원가입이 실패하였습니다.");
          error.go();
          return false;
        } else if (message.equals("exist")) {
          SignUpError error = new SignUpError("이미 존재하는 아이디입니다.");
          error.go();
        } else {
          SuccessSignUpView.go();
          System.out.println("회원가입 성공");
          return true;
        }
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  private static boolean checkPwd(String pwd) {

    for (int i = 0; i < pwd.length(); i++) {
      if (!(pwd.charAt(i) == '&') && !(pwd.charAt(i) == ' ')) {
        return false;
      }
    }
    return false;
  }

  private static boolean isNull(String userId, char[] pwd, String name) {

    return userId.isEmpty() && (pwd.length == 0) && name.isEmpty();
  }

  private static boolean checkUserId(String userId) {

    for (int i = 0; i < userId.length(); i++) {
      if (!(userId.charAt(i) >= '0' && userId.charAt(i) <= '9')
          && !(userId.charAt(i) >= 'a' && userId.charAt(i) <= 'z') && !(userId.charAt(i) == '&')) {
        return false;
      }
    }

    return true;
  }
}
