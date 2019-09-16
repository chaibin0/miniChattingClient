package chatting.view.error;

import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class LoginError {

  private static JFrame frame;

  /**
   * 로그인 형식이 맞지 않을 경우 나타나는 뷰.
   */
  public static void go() {
    frame = new JFrame();

    frame.setTitle("Login Error - Chatting");
    frame.getContentPane().setBackground(new Color(238, 232, 170));
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setType(Type.POPUP);
    frame.setBounds(100, 100, 291, 153);
    frame.getContentPane().setLayout(null);

    JLabel label = new JLabel("비밀번호나 아이디를 잘못 입력 하였습니다.");
    label.setBounds(23, 10, 240, 75);
    frame.getContentPane().add(label);

    JButton btnNewButton = new JButton("확인");
    btnNewButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        frame.dispose();
      }
    });
    btnNewButton.setBounds(89, 71, 97, 23);
    frame.getContentPane().add(btnNewButton);
    frame.setVisible(true);
  }

}
