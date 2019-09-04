package chatting.view.error;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RoomInError extends JFrame {

  private static final long serialVersionUID = 1L;

  public RoomInError() {

    setType(Type.POPUP);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    getContentPane().setLayout(null);

    JLabel lblNewLabel = new JLabel("방이 존재하지 않습니다.");
    lblNewLabel.setBounds(142, 111, 162, 21);
    getContentPane().add(lblNewLabel);

    JButton exitButton = new JButton("확인");
    exitButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        setVisible(false);
      }
    });
    exitButton.setBounds(173, 166, 97, 23);
    getContentPane().add(exitButton);
  }
}
