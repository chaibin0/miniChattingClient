package chatting.model;

import chatting.domain.Account;
import chatting.domain.Rooms;
import chatting.view.error.RoomInError;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultListModel;

public class RoomService {

  /**
   * 채팅방 리스트를 반환하는 메소드.
   * 
   * @return JTableModel에 저장하기 위한 배열을 반환
   */
  public static Vector<Vector<String>> getRoom() {

    System.out.println("RoomService getRoom()");
    Vector<Vector<String>> rooms = new Vector<>();

    try (Socket sock = new Socket("127.0.0.1", 5001);
        PrintWriter writer = new PrintWriter(sock.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

      // room List 요청
      Account account = Account.getAccount();
      System.out.println("roomList " + account.getUserId());
      writer.println("roomList" + "&" + account.getUserId());
      writer.flush();

      String line = "";
      while ((line = reader.readLine()) != null) {
        if (line.isEmpty()) {
          break;
        }

        // 데이터 분리
        String[] temp = line.split(",");
        System.out.println(line);

        // RoomPanel 템플릿에 데이터를 넣고 저장
        if (Rooms.getRooms().contains(Integer.parseInt(temp[0]))) {
          continue;
        }
        rooms.add(new Vector<>(Arrays.asList(temp[0], temp[1], temp[2] + "명", "입장")));
      }

    } catch (UnknownHostException e) {
      System.out.println("서버와 연결 실패하였습니다.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("데이터를 제대로 가져오지 못했습니다");
      e.printStackTrace();
    }

    return rooms;
  }

  /**
   * 채팅방을 검색하는 메소드.
   * 
   * @param type 카테고리
   * @param name 데이터 정보
   * @return JTableModel에 저장하기 위한 배열을 반환
   */
  public static Vector<Vector<String>> searchRoom(String type, String name) {

    Vector<Vector<String>> rooms = new Vector<>();

    try (Socket sock = new Socket("127.0.0.1", 5001);
        PrintWriter writer = new PrintWriter(sock.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

      // room List 요청
      Account account = Account.getAccount();
      System.out.println("searchRoomList " + account.getUserId() + " " + type + " " + name);
      writer.println("searchRoomList" + "&" + account.getUserId() + "&" + type + "&" + name);
      writer.flush();

      String line = "";
      while ((line = reader.readLine()) != null) {
        if (line.isEmpty()) {
          break;
        }

        // 데이터 분리
        String[] temp = line.split(",");
        System.out.println(line);

        // RoomPanel 템플릿에 데이터를 넣고 저장
        if (Rooms.getRooms().contains(Integer.parseInt(temp[0]))) {
          continue;
        }
        rooms.add(new Vector<>(Arrays.asList(temp[0], temp[1], temp[2] + "명", "입장")));
      }

    } catch (UnknownHostException e) {
      System.out.println("서버와 연결 실패하였습니다.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("데이터를 제대로 가져오지 못했습니다");
      e.printStackTrace();
    }

    return rooms;

  }

  /**
   * 서버에게 만든 채팅방의 데이터를 보내고 확인하는 메소드.
   * 
   * @param title 채팅방 제목
   * @return 서버에게 받은 방번호를 반환합니다.
   */
  public static synchronized int makeRoom(String title) {

    System.out.println("RoomService : makeRoom()");
    int roomNumber = 0;

    try (Socket socket = new Socket("127.0.0.1", 5001);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      Account account = Account.getAccount();
      writer.println("roomMake" + "&" + account.getUserId() + "&" + title);
      writer.flush();

      String line = "";
      while ((line = reader.readLine()) != null) {
        // 에러
        if (line.equals("0")) {
          System.out.println("방생성에 실패하였습니다.");
          return roomNumber;
        }

        // 만들어진 방번호 가져온다.
        roomNumber = Integer.parseInt(line);
      }



    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 입장할 방번호 반환
    return roomNumber;

  }


  /**
   * 서버에게 채팅방을 요청해서 채팅방에 입장하는 메소드.
   * 
   * @param roomNumber 채팅방 번호
   * @param account 계정
   * @param writer 서버 출력 소켓
   * @param reader 서버 입력 소켓
   * @return 채팅방에 들어간 사람들의 리스트를 반환
   */
  public static synchronized DefaultListModel<String> roomIn(int roomNumber, Account account,
      PrintWriter writer, BufferedReader reader) {

    System.out.println("RoomService.roomIn()");
    DefaultListModel<String> model = new DefaultListModel<String>();
    try {

      // 채팅방 입장 요청
      writer.println("roomIn" + "&" + account.getUserId() + "&" + roomNumber);
      writer.flush();

      // 채팅방 사람 리스트를 가져온다
      String line = "";
      while (!(line = reader.readLine()).equals("")) {
        if (line.equals("fail")) {
          throw new IOException();
        }
        System.out.println("line : " + line);
        model.addElement(line);
        Rooms.getRooms().add(roomNumber);
      }

      System.out.println("방 입장 성공");

    } catch (UnknownHostException e) {
      System.out.println("서버 연결 실패");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("방이 없습니다.");
      RoomInError roomInError = new RoomInError();
      roomInError.setVisible(true);
      e.printStackTrace();
    }
    return model;
  }
}
