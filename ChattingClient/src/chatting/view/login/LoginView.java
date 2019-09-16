package chatting.view.login;

import chatting.model.LoginService;
import chatting.view.error.LoginError;
import chatting.view.room.RoomView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;


public class LoginView {

  private JFrame frame;

  private JPanel loginPanel;

  private JTextField loginField;

  private JLabel loginLabel;

  private JLabel pwdLabel;

  private JButton loginButton;

  private JButton signUpButton;

  private JPasswordField pwdField;


  /**
   * Login화면을 보여주는 메소드이다.
   * 
   * @wbp.parser.entryPoint
   */
  public void go() {

    guiSetup();

  }

  private void guiSetup() {

    frame = new JFrame("Login - Chatting");
    frame.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {

        System.exit(1);
      }
    });

    loginPanel = new JPanel();
    loginPanel.setBackground(new Color(240, 230, 140));
    loginPanel.setBounds(0, 0, 394, 471);
    loginLabel = new JLabel("아이디");
    loginLabel.setBounds(71, 191, 48, 15);
    pwdLabel = new JLabel("패스워드");
    pwdLabel.setBounds(71, 233, 62, 15);
    loginField = new JTextField(10);
    loginField.setBounds(171, 188, 116, 21);
    loginButton = new JButton("로그인");
    loginButton.setBounds(138, 295, 106, 36);
    loginPanel.setLayout(null);

    loginPanel.add(loginLabel);
    loginPanel.add(loginField);
    loginPanel.add(pwdLabel);
    loginPanel.add(loginButton);

    loginButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        LoginService loginService = new LoginService();
        if (loginService.findByUserIdAndPwd(loginField.getText(), pwdField.getPassword())) {
          frame.setVisible(false);
          RoomView.go();
        }
      }
    });

    frame.setResizable(false);
    frame.getContentPane().setLayout(null);
    frame.getContentPane().add(loginPanel);

    signUpButton = new JButton("회원가입");
    signUpButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        SignUpView signUpView = new SignUpView();
        signUpView.setVisible(true);
      }
    });
    signUpButton.setBounds(138, 347, 106, 36);
    loginPanel.add(signUpButton);

    pwdField = new JPasswordField();
    pwdField.setBounds(171, 230, 116, 21);
    loginPanel.add(pwdField);
    
    JLabel titleLabel = new JLabel("CHATTING");
    titleLabel.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setBounds(107, 81, 178, 36);
    loginPanel.add(titleLabel);
    frame.setSize(400, 500);
    frame.setVisible(true);
  }
}
