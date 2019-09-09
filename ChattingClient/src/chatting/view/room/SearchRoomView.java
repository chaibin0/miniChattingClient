package chatting.view.room;

import chatting.model.RoomService;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class SearchRoomView {

  private JFrame frame;

  private static JTextField titleField;

  private static JTextField numberField;

  private static JRadioButton titleRadioButton;

  private static JRadioButton numberRadioButton;

  private JButton btnNewButton;

  /**
   * 채팅방 검색 뷰 객체 생성하는 메소드.
   */
  public SearchRoomView() {

    initialize();
  }

  /**
   * 채팅방 검색 뷰를 보여주는 메소드.
   */
  public static void go() {

    SearchRoomView searchRoomView = new SearchRoomView();
    searchRoomView.frame.setVisible(true);
  }

  private void initialize() {

    frame = new JFrame();
    frame.setType(Type.POPUP);
    frame.setBounds(100, 100, 450, 370);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JLabel titleLabel = new JLabel("제목");
    titleLabel.setBounds(60, 96, 57, 15);
    frame.getContentPane().add(titleLabel);

    titleField = new JTextField();
    titleField.setBounds(129, 93, 116, 21);
    frame.getContentPane().add(titleField);
    titleField.setColumns(10);

    titleRadioButton = new JRadioButton("제목으로 검색");
    titleRadioButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        titleField.setEditable(true);
        numberRadioButton.setSelected(false);
        numberField.setText("");
        numberField.setEditable(false);
      }
    });
    titleRadioButton.setSelected(true);
    titleRadioButton.setBounds(270, 92, 121, 23);
    frame.getContentPane().add(titleRadioButton);

    numberField = new JTextField();
    numberField.setEditable(false);
    numberField.setBounds(129, 140, 116, 21);
    frame.getContentPane().add(numberField);
    numberField.setColumns(10);

    numberRadioButton = new JRadioButton("번호로 검색");
    numberRadioButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        numberField.setEditable(true);
        titleRadioButton.setSelected(false);
        titleField.setText("");
        titleField.setEditable(false);
      }
    });
    numberRadioButton.setBounds(270, 139, 121, 23);
    frame.getContentPane().add(numberRadioButton);

    JLabel lblNewLabel = new JLabel("방번호");
    lblNewLabel.setBounds(60, 143, 57, 15);
    frame.getContentPane().add(lblNewLabel);

    JButton searchButton = new JButton("검색하기");
    searchButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        if (titleRadioButton.isSelected()) {
          Vector<Vector<String>> roomList = RoomService.searchRoom("title", titleField.getText());
          RoomView.search(roomList);
        }

        if (numberRadioButton.isSelected()) {
          Vector<Vector<String>> roomList = RoomService.searchRoom("number", numberField.getText());
          RoomView.search(roomList);
        }
      }
    });
    searchButton.setBounds(187, 275, 97, 23);
    frame.getContentPane().add(searchButton);

    btnNewButton = new JButton("취소");
    btnNewButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        frame.dispose();
      }
    });
    btnNewButton.setBounds(296, 275, 97, 23);
    frame.getContentPane().add(btnNewButton);

  }
}
