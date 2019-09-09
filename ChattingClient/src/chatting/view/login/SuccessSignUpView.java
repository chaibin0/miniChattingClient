package chatting.view.login;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class SuccessSignUpView extends JFrame {

  private static final long serialVersionUID = 1L;

  /**
   * 회원가입 성공 화면 뷰.
   */
  public static void go() {

    SuccessSignUpView view = new SuccessSignUpView();
    view.setVisible(true);
  }

  /**
   * 회원가입 화면 뷰를 초기화 한다.
   */
  public SuccessSignUpView() {

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(new Dimension(300, 300));
    getContentPane().setLayout(null);

    JLabel showLabel = new JLabel("회원가입 성공");
    showLabel.setBounds(97, 109, 99, 15);
    getContentPane().add(showLabel);

    JButton exitButton = new JButton("확인");
    exitButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        dispose();
      }
    });
    exitButton.setBounds(89, 183, 97, 23);
    getContentPane().add(exitButton);
  }
}
