package chatting.view.room;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import chatting.model.RoomService;
import chatting.view.chat.ChatView;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MakeRoomView {

  private JFrame frame;

  private JTextField textField;

  /**
   * Launch the application.
   * 
   * @param sock
   * @param reader
   * @param writer
   */
  public static void go() {

    EventQueue.invokeLater(new Runnable() {

      public void run() {

        try {
          MakeRoomView window = new MakeRoomView();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });


  }

  /**
   * Create the application.
   * 
   * @param sock
   * @param reader
   * @param writer
   */
  public MakeRoomView() {

    initialize();
  }

  /**
   * Initialize the contents of the frame.
   * 
   * @param sock
   * @param reader
   * @param writer
   */
  private void initialize() {

    frame = new JFrame();
    frame.setType(Type.POPUP);
    frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    panel.setLayout(null);

    textField = new JTextField();
    textField.setBounds(174, 27, 165, 34);
    panel.add(textField);
    textField.setColumns(10);

    JLabel titleLabel = new JLabel("제목");
    titleLabel.setBounds(42, 36, 57, 15);
    panel.add(titleLabel);

    JButton makeButton = new JButton("생성");
    makeButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        int roomNumber = RoomService.makeRoom(textField.getText());
        if (roomNumber != 0) {
          frame.dispose();
          ChatView chatView = new ChatView();
//          RoomService.getRoom();
          chatView.go(roomNumber);
        }
      }
    });


    makeButton.setBounds(257, 214, 97, 23);
    panel.add(makeButton);
  }
}
