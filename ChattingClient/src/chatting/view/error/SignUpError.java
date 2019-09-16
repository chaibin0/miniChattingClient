package chatting.view.error;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SignUpError {

  private static JFrame frame;

  /**
   * 회원가입 실패시 나타나는 뷰.
   * 
   * @param message 회원가입 실패한 메시지 내용.
   */
  public static void go(String message) {
    frame.setTitle("SignUp Error - Chatting");
    frame.setSize(new Dimension(300, 300));
    frame.setPreferredSize(new Dimension(300, 300));

    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setType(Type.POPUP);
    frame.getContentPane().setLayout(null);
    frame.getContentPane().setBackground(new Color(238, 232, 170));

    JLabel label = new JLabel(message);
    label.setBounds(69, 89, 172, 15);
    frame.getContentPane().add(label);

    JButton checkButton = new JButton("확인");
    checkButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        frame.dispose();
      }
    });
    checkButton.setBounds(91, 147, 97, 23);
    frame.getContentPane().add(checkButton);
    frame.setVisible(true);
  }
}
