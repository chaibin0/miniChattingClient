package chatting.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityService {

  /**
   * 비밀번호를 MessageDigest를 통해 데이터를 암호화시킵니다.
   * 
   * @param input 암호화할 데이터
   * @return
   */
  public static String encrypt(String input) {

    try {
      MessageDigest md = MessageDigest.getInstance("MD2");
      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger no = new BigInteger(1, messageDigest);
      String hashtext = no.toString(16);
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext;
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
