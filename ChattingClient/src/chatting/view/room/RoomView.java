package chatting.view.room;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import chatting.model.RoomService;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class RoomView {

  private static JFrame frame;

  private static JPanel panel;

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
  private static void initialize(List<RoomPanel> roomList) {

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
    scrollPane.setViewportView(panel);
    for (RoomPanel data : roomList) {
      panel.add(data);
    }

    panel.setPreferredSize(new Dimension(300, 300));
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
    exitButton.setBounds(158, 438, 70, 23);
    frame.getContentPane().add(exitButton);

    JButton reFreshButton = new JButton("새로고침");
    reFreshButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        panel = new JPanel();
        List<RoomPanel> roomList = RoomService.getRoom();
        for (RoomPanel data : roomList) {
          panel.add(data);
        }
        
        frame.repaint();
        frame.revalidate();
        

      }
    });
    reFreshButton.setBounds(242, 438, 116, 23);
    frame.getContentPane().add(reFreshButton);
    frame.setResizable(false);

  }

  class RoomList extends JScrollPane {

    JLabel no;

    JLabel contents;

    JLabel owner;

    private static final long serialVersionUID = 1L;

    public RoomList(String no, String contents, String owner) {

      this.no = new JLabel(no);
      this.contents = new JLabel(contents);
      this.owner = new JLabel(owner);
      add(this.no);
      add(this.contents);
      add(this.owner);
    }



  }
}

