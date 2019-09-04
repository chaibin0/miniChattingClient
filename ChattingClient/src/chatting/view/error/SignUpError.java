package chatting.view.error;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SignUpError extends JFrame {

  private static final long serialVersionUID = 1L;

  public SignUpError() {

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setType(Type.POPUP);
    getContentPane().setLayout(null);

    JLabel label = new JLabel("회원가입 실패하였습니다.");
    label.setBounds(130, 109, 215, 15);
    getContentPane().add(label);

    JButton checkButton = new JButton("확인");
    checkButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        dispose();
      }
    });
    checkButton.setBounds(158, 195, 97, 23);
    getContentPane().add(checkButton);
  }

  public void go() {

    setVisible(true);
  }
}