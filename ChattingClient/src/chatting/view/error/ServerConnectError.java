package chatting.view.error;

import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ServerConnectError {

  public ServerConnectError() {}

  private static JFrame frame;

  /**
   * 서버가 끊어져서 클라이언트와 통신을 못할 때 표시하는 뷰.
   */
  public static void go() {

    frame = new JFrame();
    frame.setTitle("Server Error - Chatting");
    frame.setType(Type.POPUP);
    frame.setBounds(100, 100, 291, 153);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    frame.getContentPane().setBackground(new Color(238, 232, 170));

    JLabel errorLabel = new JLabel("서버가 닫혀있습니다.");
    errorLabel.setBounds(191, 85, 209, 77);
    frame.getContentPane().add(errorLabel);

    JButton btnNewButton = new JButton("확인");
    btnNewButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        frame.dispose();
      }
    });
    btnNewButton.setBounds(201, 184, 97, 23);
    frame.getContentPane().add(btnNewButton);
    frame.setVisible(true);
  }

}
