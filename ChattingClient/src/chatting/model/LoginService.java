package chatting.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import chatting.domain.Account;
import chatting.view.error.LoginError;
import chatting.view.error.LoginExistError;
import chatting.view.error.ServerConnectError;
import chatting.view.error.SignUpError;

public class LoginService {

  // PrintWriter writer;
  public boolean findByUserIdAndPwd(String userId, String pwd) {

    try (Socket sock = new Socket("127.0.0.1", 5001);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

      writer.write("find " + userId + " " + pwd + "\n");
      writer.flush();

      // 로그인 확인 결과
      String message = "";
      boolean flag = false;
      LocalDateTime start = LocalDateTime.now();
      start = start.plusSeconds(10);
      while (start.isAfter(LocalDateTime.now()) && (message = reader.readLine()) != null) {
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

  public static boolean signUp(String userId, String pwd, String name) {

    try (Socket sock = new Socket("127.0.0.1", 5001);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

      writer.println("signup " + userId + " " + pwd + " " + name);
      writer.flush();

      // 로그인 확인 결과
      String message = "";
      while ((message = reader.readLine()) != null) {
        if (message.equals("fail")) {
          SignUpError error = new SignUpError();
          error.go();
          return false;
        } else if (message.equals("exist")) {
          SignUpError error = new SignUpError();
          error.go();
        } else {

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
}
