package chatting.view.login;

import javax.swing.JFrame;
import javax.swing.JTextField;
import chatting.model.LoginService;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;


public class SignUpView extends JFrame {

  private static final long serialVersionUID = 1L;

  private JTextField userIdField;

  private JTextField pwdField;

  private JTextField nameField;

  public SignUpView() {

    setName("Chatting signUp");
    setResizable(false);
    setSize(new Dimension(400, 500));
    getContentPane().setPreferredSize(new Dimension(400, 500));

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setType(Type.POPUP);
    getContentPane().setLayout(null);

    userIdField = new JTextField();
    userIdField.setBounds(170, 88, 155, 44);
    getContentPane().add(userIdField);
    userIdField.setColumns(10);

    pwdField = new JTextField();
    pwdField.setBounds(170, 172, 155, 44);
    getContentPane().add(pwdField);
    pwdField.setColumns(10);

    nameField = new JTextField();
    nameField.setBounds(170, 245, 155, 44);
    getContentPane().add(nameField);
    nameField.setColumns(10);

    JLabel userIdLabel = new JLabel("아이디");
    userIdLabel.setBounds(63, 102, 57, 15);
    getContentPane().add(userIdLabel);

    JLabel pwdLabel = new JLabel("비밀번호");
    pwdLabel.setBounds(63, 186, 57, 15);
    getContentPane().add(pwdLabel);

    JLabel nameLabel = new JLabel("이름");
    nameLabel.setBounds(63, 259, 57, 15);
    getContentPane().add(nameLabel);

    JButton signUpButton = new JButton("등록");
    signUpButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        LoginService.signUp(userIdField.getText(), pwdField.getText(), nameField.getText());
        setVisible(false);
      }
    });
    signUpButton.setBounds(46, 376, 97, 23);
    getContentPane().add(signUpButton);

    JButton cancelButton = new JButton("취소");
    cancelButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        setVisible(false);
      }
    });
    cancelButton.setBounds(228, 376, 97, 23);
    getContentPane().add(cancelButton);
  }
}
