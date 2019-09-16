package chatting.view.error;

import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class RoomInError {

  private static JFrame frame;

  /**
   * 방이 삭제되었거나 존재하지 않을 경우 보여주는 뷰.
   */
  public static void go() {

    frame.setTitle("RoomIn Error - Chatting");
    frame.getContentPane().setBackground(new Color(250, 250, 210));

    frame.setType(Type.POPUP);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setBounds(100, 100, 291, 153);

    frame.getContentPane().setLayout(null);
    frame.getContentPane().setBackground(new Color(238, 232, 170));

    JLabel lblNewLabel = new JLabel("방이 존재하지 않습니다.");
    lblNewLabel.setBounds(72, 29, 162, 21);
    frame.getContentPane().add(lblNewLabel);

    JButton exitButton = new JButton("확인");
    exitButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        frame.setVisible(false);
      }
    });
    exitButton.setBounds(86, 69, 97, 23);
    frame.getContentPane().add(exitButton);
  }



}
