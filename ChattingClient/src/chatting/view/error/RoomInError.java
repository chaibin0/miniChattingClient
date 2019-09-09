package chatting.view.error;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class RoomInError extends JFrame {

  private static final long serialVersionUID = 1L;

  /**
   * 방이 삭제되었거나 존재하지 않을 경우 보여주는 뷰.
   */
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
