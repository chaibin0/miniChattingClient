package chatting.view.room;

import chatting.model.RoomService;
import chatting.view.chat.ChatView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MakeRoomView {

  private JFrame frame;

  private JTextField textField;

  /**
   * 채팅방을 만드는 뷰.
   */
  public static void go() {

    MakeRoomView window = new MakeRoomView();
    window.frame.setVisible(true);
  }

  /**
   * 뷰를 초기화해주는 생성자.
   */
  public MakeRoomView() {

    initialize();
  }

  private void initialize() {

    frame = new JFrame();
    frame.setTitle("Make Room - Chatting");
    frame.setType(Type.POPUP);
    frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(250, 250, 210));
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    frame.getContentPane().setBackground(new Color(238, 232, 170));

    panel.setLayout(null);

    textField = new JTextField();
    textField.setBounds(173, 95, 165, 34);
    panel.add(textField);
    textField.setColumns(10);

    JLabel titleLabel = new JLabel("제목");
    titleLabel.setBounds(50, 104, 57, 15);
    panel.add(titleLabel);

    JButton makeButton = new JButton("생성");
    makeButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        int roomNumber = RoomService.makeRoom(textField.getText());
        if (roomNumber != 0) {
          frame.dispose();
          ChatView chatView = new ChatView();
          chatView.go(roomNumber, textField.getText());
        }
      }
    });


    makeButton.setBounds(257, 214, 97, 23);
    panel.add(makeButton);
    
    JLabel createLabel = new JLabel("방만들기");
    createLabel.setHorizontalAlignment(SwingConstants.CENTER);
    createLabel.setBounds(183, 37, 57, 15);
    panel.add(createLabel);
  }
}
