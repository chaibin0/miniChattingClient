package chatting.view.room;

import chatting.model.RoomService;
import chatting.view.chat.ChatView;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



public class RoomView {

  private static JFrame frame;

  private static JPanel panel;

  private static JTable table;

  private static DefaultTableModel tableModel;

  private static JButton reFreshButton;
  private static JLabel listLabel;

  /**
   * 채팅방 리스트를 보여주는 메소드.
   * 
   * @wbp.parser.entryPoint
   */
  public static void go() {

    System.out.println("RoomView go()");
    initialize(RoomService.getRoom());
    EventQueue.invokeLater(new Runnable() {

      public void run() {

        try {
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * 검색해서 나온 채팅방 리스트를 다시 뷰를 업데이트 하는 메소드.
   * 
   * @param roomList 채팅방 리스트
   */
  public static void search(Vector<Vector<String>> roomList) {

    refreshTable(roomList);

  }

  private static void refreshTable(Vector<Vector<String>> roomList) {

    panel = new JPanel();
    tableModel = (DefaultTableModel) table.getModel();
    tableModel.getDataVector().removeAllElements();
    tableModel.fireTableDataChanged();
    for (Vector<String> data : roomList) {
      tableModel.addRow(data);
    }
    table.setModel(tableModel);

    frame.repaint();
    frame.revalidate();

  }

  /**
   * 초기화 메소드.
   * 
   * @param roomList 채팅방 리스트
   */
  private static void initialize(Vector<Vector<String>> roomList) {

    frame = new JFrame();
    frame.setTitle("Room List - Chatting");
    frame.getContentPane().setBackground(new Color(250, 250, 210));
    frame.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {

        System.exit(1);
      }
    });
    frame.setBounds(100, 100, 400, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JList<String> list = new JList<>();

    frame.getContentPane().setLayout(null);

    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setVisibleRowCount(4);
    list.setSize(50, 300);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setSize(new Dimension(400, 400));
    scrollPane.setPreferredSize(new Dimension(400, 400));

    scrollPane.setBounds(30, 55, 340, 373);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


    frame.getContentPane().add(scrollPane);

    panel = new JPanel();
    panel.setBackground(new Color(245, 245, 245));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    scrollPane.setViewportView(panel);

    panel.setPreferredSize(new Dimension(300, 300));
    tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"번호", "제목", "인원수", "입장"});

    for (Vector<String> data : roomList) {
      tableModel.addRow(data);
    }
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    table = new JTable(tableModel);

    table.setAlignmentX(Component.LEFT_ALIGNMENT);
    table.setAlignmentY(Component.TOP_ALIGNMENT);
    table.getColumnModel().getColumn(0).setPreferredWidth(10);
    table.getColumnModel().getColumn(1).setPreferredWidth(230);
    table.getColumnModel().getColumn(2).setPreferredWidth(30);
    table.getColumnModel().getColumn(3).setPreferredWidth(30);

    table.addMouseListener(new MouseAdapter() {

      public void mousePressed(MouseEvent mouseEvent) {

        JTable table = (JTable) mouseEvent.getSource();
        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
          ChatView chatView = new ChatView();
          chatView.go(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()),
              table.getValueAt(table.getSelectedRow(), 1).toString());
        }
      }
    });

    panel.add(table);
    JButton enterButton = new JButton("생성");
    enterButton.setBounds(24, 438, 70, 23);
    frame.getContentPane().add(enterButton);

    // 방생성하는 창 나오게 한다.
    enterButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        MakeRoomView.go();
        frame.setOpacity(1);
      }
    });

    JButton exitButton = new JButton("종료");
    exitButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        System.exit(1);
      }
    });
    exitButton.setBounds(300, 438, 70, 23);
    frame.getContentPane().add(exitButton);

    reFreshButton = new JButton("새로고침");

    reFreshButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        Vector<Vector<String>> roomList = RoomService.getRoom();
        refreshTable(roomList);
      }
    });
    reFreshButton.setBounds(106, 438, 102, 23);
    frame.getContentPane().add(reFreshButton);

    JButton searchButton = new JButton("검색");
    searchButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        SearchRoomView.go();
      }
    });
    searchButton.setBounds(218, 438, 70, 23);
    frame.getContentPane().add(searchButton);
    
    listLabel = new JLabel("채팅방 리스트");
    listLabel.setHorizontalAlignment(SwingConstants.CENTER);
    listLabel.setBounds(145, 30, 119, 15);
    frame.getContentPane().add(listLabel);
    frame.setResizable(false);

  }
}

