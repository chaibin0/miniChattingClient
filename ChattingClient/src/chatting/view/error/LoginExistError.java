package chatting.view.error;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginExistError {

  private static JFrame frame;

  public static void go() {

    frame = new JFrame();
    frame.setType(Type.POPUP);
    frame.setBounds(100, 100, 291, 153);

    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JLabel lblNewLabel = new JLabel("이미 로그인 중입니다.");
    lblNewLabel.setBounds(191, 85, 209, 77);
    frame.getContentPane().add(lblNewLabel);

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
