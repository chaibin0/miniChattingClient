package chatting.view.error;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginError {

  private JFrame frame;

  /**
   * Launch the application.
   */
  public static void go() {

    EventQueue.invokeLater(new Runnable() {

      public void run() {

        try {
          LoginError window = new LoginError();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public LoginError() {

    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {

    frame = new JFrame();
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
  }
}
