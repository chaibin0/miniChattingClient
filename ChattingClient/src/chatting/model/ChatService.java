package chatting.model;

import chatting.domain.Account;
import chatting.domain.Rooms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


public class ChatService {



  /**
   * 채팅방에서 메시지를 보내는 메소드이다.
   * @param roomNumber 방번호
   * @param message 메시지
   * @param writer 클라이언트 출력 소켓
   */
  public static void sendMessage(int roomNumber, String message, PrintWriter writer) {

    Account account = Account.getAccount();
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

    Account account = Account.getAccount();
    Rooms.getRooms().remove(roomNumber);
    writer.println("out" + "&" + account.getUserId() + "&" + roomNumber);
    writer.flush();
  }

  /**
   * 서버에서 메시지를 받을 경우 처리하는 메소드이다.
   * 
   * @param reader 서버로부터 데이터를 받는 소켓
   * @param textArea 채팅 대화창
   * @param list 채팅 참가자 리스트뷰
   * @param members 현재 채팅 참가자
   * @param frame 채팅창 화면
   * @throws IOException 데이터가 제대로 받지 못했을 경우 발생하는 에러
   * @throws SocketException 서버와 연결이 끊어졌을 경우 에러
   * @throws ArrayIndexOutOfBoundsException 인덱스 오버플로우가 일어났을 경우 에러
   */
  public void run(BufferedReader reader, JTextArea textArea, JList<String> list,
      DefaultListModel<String> members, JFrame frame)
      throws IOException, SocketException, ArrayIndexOutOfBoundsException {

    String message;
    
    while (true) {
      while ((message = reader.readLine()) != null) {
        String[] order = message.split("&", -1);
        if (order[0].equals("sendMessage")) {
          textArea.append(order[1] + " : " + order[3] + "\n");

        }

        if (order[0].equals("in")) {
          members.addElement(order[1]);
          list.setModel(members);
          list.setSelectedIndex(0);
          textArea.append(order[1] + "님이 입장하였습니다.\n");

        }

        if (order[0].equals("out")) {
          members.removeElement(order[1]);
          list.setModel(members);
          list.setSelectedIndex(0);
          textArea.append(order[1] + "님이 퇴장하였습니다.\n");
        }
        
        
        list.repaint();
        textArea.repaint();
        frame.validate();
      }
    }


  }

}
