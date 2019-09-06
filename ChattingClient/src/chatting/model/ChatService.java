package chatting.model;

import java.io.PrintWriter;
import chatting.domain.Account;
import chatting.domain.Rooms;

public class ChatService {

  /**
   * 채팅방에서 메시지를 보내는 메소드이다.
   * 
   * @param message 메시지
   * @param writer 클라이언트 출력 소켓
   * @param reader 서버 입력 소켓
   */
  public static void sendMessage(int roomNumber, String message, PrintWriter writer) {

    System.out.println("sendMessage : " + message);
    Account account = Account.getAccount();
    // 데이터 전송
    writer.println("sendMessage" + "&" + account.getUserId() + "&" + roomNumber + "&" + message);
    writer.flush();

  }

  /**
   * 서버에게 채팅방에 나간다는 것을 요청하는 메소드.
   * 
   * @param roomNumber 채팅방 번호
   * @param writer 클라이언트 출력 소켓
   */
  public static void outOfChatRoom(int roomNumber, PrintWriter writer) {

    System.out.println("outOfChatRoom()");
    Account account = Account.getAccount();
    Rooms.getRooms().remove(roomNumber);
    // 데이터 전송
    writer.println("out" + "&" + account.getUserId() + "&" + roomNumber);
    writer.flush();
  }

}
