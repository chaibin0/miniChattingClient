package chatting.view.room;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import chatting.view.chat.ChatView;
import javax.swing.JButton;


public class RoomPanel extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private JTextField titleField;

  public RoomPanel(String num, String title, String id) {

    setLayout(null);
    setPreferredSize(new Dimension(320, 50));
    setSize(320, 50);
    JLabel numLabel = new JLabel("num");
    numLabel.setBounds(0, 17, 48, 15);
    add(numLabel);

    titleField = new JTextField();
    titleField.setBounds(44, 0, 198, 50);
    add(titleField);
    titleField.setColumns(10);

    numLabel.setText(num);
    titleField.setText("제목 : " + title + "\r\n" + id);

    JButton EnterButton = new JButton("입장");
    EnterButton.setBounds(242, 0, 78, 50);
    add(EnterButton);
    // idField.setText(id);
    addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent e) {

        String selectedItem = numLabel.getText();
        if (e.getClickCount() == 2) {
          String data = selectedItem;

          ChatView chatView = new ChatView();
          chatView.go(Integer.parseInt(data));
        }
      }
    });

  }
}
