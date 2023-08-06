package status;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame {
	
	Frame() throws SQLException{
		dbConnection d = new dbConnection();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String ys = SDF.format(new Date());
		//System.out.println(ys);
		setTitle("전적 기록");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		//JPanel c = new JPanel();
		c.setLayout(new FlowLayout());
		//c.setLayout(new BorderLayout());
		
		JLabel L1 = new JLabel("ID");
		JTextField TF1 = new JTextField(10);
		JLabel L2 = new JLabel("  종족");
		JTextField TF2 = new JTextField(1);
		JLabel L3 = new JLabel("  상대");
		JTextField TF3 = new JTextField(1);
		JRadioButton W, L;
		W = new JRadioButton("승");
		L = new JRadioButton("패");
		ButtonGroup g = new ButtonGroup();
		g.add(W);
		g.add(L);
		JLabel L4 = new JLabel("  포인트");
		JTextField TF4 = new JTextField(4);
		JLabel L5 = new JLabel("  맵");
		JLabel L6 = new JLabel("ID                    종족                     상대                       승패                      점수                     맵                          시간                                  ");
		JTextField TF5 = new JTextField(10);
		JTextArea TA = new JTextArea(25, 60);
		JScrollPane SP = new JScrollPane(TA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JButton b1 = new JButton("  전적확인");
		JButton b2 = new JButton("  전적기록");
		
		JButton b3 = new JButton("전적초기화");
		JTextField TF6 = new JTextField(8);
		JButton b4 = new JButton("아이디 검색");
		JButton b5 = new JButton("승률 계산");
		//L6.setSize(5000, 100);
		c.add(L1);
		c.add(TF1);
		c.add(L2);
		c.add(TF2);
		c.add(L3);
		c.add(TF3);
		c.add(W);
		c.add(L);
		c.add(L4);
		c.add(TF4);
		c.add(L5);
		c.add(TF5);
		c.add(b1);
		c.add(b2);
		c.add(L6);
		c.add(TA);
		c.add(SP);
		c.add(b3);
		c.add(TF6);
		c.add(b4);
		c.add(b5);
		SP.setViewportView(TA);
		
		
		setVisible(true);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//int i = d.count();
				d.showStatus();
				TA.setText("");
				try {
					while(d.rs.next()) {
					String id = d.rs.getString("id");
					String i = d.rs.getString("myPTZ");
					String e = d.rs.getString("enemyPTZ");
					String WL = d.rs.getString("WinLose");
					int LP = d.rs.getInt("LP");
					String map = d.rs.getString("MAP");
					String Ys = d.rs.getString("YMDHMS");
					TA.append(id + "\t"+ i + "\t" + e + 
							"\t" + WL + "\t" + LP
							+ "\t" + map + "\t" + Ys+"\n");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				d.dbClose();
			}
			
		});
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String WL = "";
				
				int p = 0;
				String ys = SDF.format(new Date());
				p = Integer.parseInt(TF4.getText());
				if(W.isSelected()) {
					WL = "승리";
				d.update(TF1.getText(), TF2.getText().toUpperCase(), 
						TF3.getText().toUpperCase(), WL, 
						p, TF5.getText(), ys);
				}
				else { 
					WL = "패배";
				d.update(TF1.getText(), TF2.getText().toUpperCase(), 
						TF3.getText().toUpperCase(), WL, 
						p, TF5.getText(), ys);
				}
				TF2.setText("");
				TF3.setText("");
				TF4.setText("");
				TF5.setText("");
				g.clearSelection();
			}
			//d.dbClose();
		});
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				d.deleteAll();
				TA.setText("");
			}
			
		});
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sId = TF6.getText();
				TA.setText("");
				d.idSearch(sId);
				try {
					while(d.rs.next()) {
					String id = d.rs.getString("id");
					String i = d.rs.getString("myPTZ");
					String en = d.rs.getString("enemyPTZ");
					String WL = d.rs.getString("WinLose");
					int LP = d.rs.getInt("LP");
					String map = d.rs.getString("MAP");
					String Ys = d.rs.getString("YMDHMS");
					TA.append(id + "\t"+ i + "\t" + en + 
							"\t" + WL + "\t" + LP
							+ "\t" + map + "\t" + Ys+"\n");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				d.dbClose();
			}
			
		});
		
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sId = TF6.getText();
				d.winRate(sId);
				try {
					while(d.rs.next()) {
						String en = d.rs.getString(1);
						String sW = d.rs.getString(2);
						String sL = d.rs.getString(3);
						double r = d.rs.getDouble(4)*100;
						TA.append("vs " + en+"\t승리 : "+ sW + "\t패배 : "
						+ sL +"\t승률 : " + r + "%\n" );
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
				d.dbClose();
			}
			
		});
	}
}
