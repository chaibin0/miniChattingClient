package chatting.view.room;

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
import chatting.model.RoomService;
import chatting.view.chat.ChatView;



public class RoomView {

  private static JFrame frame;

  private static JPanel panel;

  private static JTable table;

  private static DefaultTableModel tableModel;

  private static JButton reFreshButton;

  /**
   * Launch the application.
   * 
   * @param reader
   * @param writer
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
   * Initialize the contents of the frame.
   * 
   * @param reader
   * @param writer
   */
  private static void initialize(Vector<Vector<String>> roomList) {

    frame = new JFrame();
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

    scrollPane.setBounds(30, 30, 333, 398);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


    frame.getContentPane().add(scrollPane);

    panel = new JPanel();
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
          chatView.go(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
        }
      }
    });

    panel.add(table);
    JButton enterButton = new JButton("생성");
    enterButton.setBounds(76, 438, 70, 23);
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
    exitButton.setBounds(158, 438, 70, 23);
    frame.getContentPane().add(exitButton);

    reFreshButton = new JButton("새로고침");

    reFreshButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        panel = new JPanel();
        tableModel = (DefaultTableModel) table.getModel();
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
        Vector<Vector<String>> roomList = RoomService.getRoom();
        for (Vector<String> data : roomList) {
          tableModel.addRow(data);
        }
        table.setModel(tableModel);

        frame.repaint();
        frame.revalidate();


      }
    });
    reFreshButton.setBounds(242, 438, 116, 23);
    frame.getContentPane().add(reFreshButton);
    frame.setResizable(false);

  }
}

