package chatting.view.chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import chatting.domain.Account;
import chatting.model.ChatService;
import chatting.model.RoomService;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ChatView {

  private JFrame frame;

  private JTextField textField;

  private JTextArea textArea;

  private JList<String> list;

  private DefaultListModel<String> members;

  private Socket socket;

  private BufferedReader reader;

  private PrintWriter writer;

  private boolean isError = false;

  private JScrollPane scrollPane;

  /**
   * Launch the application.
   * 
   * @param sock
   * @wbp.parser.entryPoint
   */
  public void go(int roomNumber) {

    try {
      socket = new Socket("127.0.0.1", 5001);
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      writer = new PrintWriter(socket.getOutputStream());
      Account account = Account.getAccount();
      members = RoomService.roomIn(roomNumber, account, writer, reader);
      initialize(roomNumber, reader, writer);
      frame.setVisible(true);



      System.out.println("end of chatview()");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Initialize the contents of the frame.
   * 
   * @param writer
   * @param reader
   */
  private void initialize(int roomNumber, BufferedReader reader, PrintWriter writer) {

    frame = new JFrame();
    frame.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {

        ChatService.outOfChatRoom(roomNumber, writer);

      }
    });
    frame.setForeground(Color.WHITE);
    frame.getContentPane().setForeground(Color.WHITE);
    frame.setType(Type.POPUP);
    frame.setBounds(100, 100, 450, 418);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    textArea = new JTextArea(5, 20);
    textArea.setLineWrap(true);
    textArea.setEditable(false);

    scrollPane = new JScrollPane(textArea);
    scrollPane.setAutoscrolls(true);
    scrollPane.setBounds(12, 28, 257, 286);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    frame.getContentPane().add(scrollPane);



    JLabel label = new JLabel("사람목록");
    label.setBounds(321, 56, 56, 17);
    label.setFont(new Font("굴림", Font.PLAIN, 14));
    frame.getContentPane().add(label);

    list = new JList<String>(members);
    list.setBounds(294, 83, 113, 231);
    list.setVisibleRowCount(10);
    frame.getContentPane().add(list);

    textField = new JTextField();
    textField.setBounds(12, 337, 257, 21);
    
    /**
     * ENTER를 할 경우 입력버튼과 동일한 기능으로 처리
     */
    textField.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          if (isError) {
            return;
          }
          ChatService.sendMessage(roomNumber, textField.getText(), writer);
          textField.setText("");
        }
      }
    });
    frame.getContentPane().add(textField);
    textField.setColumns(10);

    JButton btnNewButton = new JButton("입력");
    btnNewButton.setBounds(294, 336, 113, 23);
    btnNewButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        if (isError) {
          return;
        }
        ChatService.sendMessage(roomNumber, textField.getText(), writer);
        textField.setText("");
        textField.requestFocus();
      }
    });
    
    
    Thread ChattingThread = new Thread(new ChattingReader());
    //서버와 항시 연결되어있어야 한다.
    ChattingThread.start();
    frame.getContentPane().add(btnNewButton);



  }

  class ChattingReader implements Runnable {

    @Override
    public synchronized void run() {

      try {
        String message;
        while (true) {
          while ((message = reader.readLine()) != null) {
            String[] order = message.split("&", -1);
            System.out.println("Order : " + order[1]);
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
      } catch (SocketException e) {
        System.out.println("서버와의 접속이 끊어졌습니다");
        textArea.append("서버와의 접속이 끊어졌습니다.\n");
        isError = true;
        e.printStackTrace();
      } catch (IOException e) {
        System.out.println("데이터가 제대로 전송되지 않았습니다");
        textArea.append("데이터가 제대로 전송되지 않았습니다\n");
        isError = true;
        e.printStackTrace();
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("데이터가 제대로 전송되지 않았습니다");
        textArea.append("데이터가 제대로 전송되지 않았습니다\n");
        isError = true;
        e.printStackTrace();
      }

    }

  }
}


