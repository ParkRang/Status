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
		dbConnection dbconnection = new dbConnection();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		setTitle("전적 기록");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		
		JLabel label1 = new JLabel("ID");
		JTextField TF1 = new JTextField(10);
		JLabel label2 = new JLabel("  종족");
		JTextField TF2 = new JTextField(1);
		JLabel label3 = new JLabel("  상대");
		JTextField TF3 = new JTextField(1);
		JRadioButton win, lose;
		win = new JRadioButton("승");
		lose = new JRadioButton("패");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(win);
		buttonGroup.add(lose);
		JLabel label4 = new JLabel("  포인트");
		JTextField TF4 = new JTextField(4);
		JLabel label5 = new JLabel("  맵");
		JLabel label6 = new JLabel("ID                    종족                     상대                       승패                      점수                     맵                          시간                                  ");
		JTextField TF5 = new JTextField(10);
		JTextArea TA = new JTextArea(25, 60);
		JScrollPane scrollPane = new JScrollPane(TA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JButton button1 = new JButton("  전적확인");
		JButton button2 = new JButton("  전적기록");
		
		JButton button3 = new JButton("전적초기화");
		JTextField TF6 = new JTextField(8);
		JButton button4 = new JButton("아이디 검색");
		JButton button5 = new JButton("승률 계산");
		container.add(label1);
		container.add(TF1);
		container.add(label2);
		container.add(TF2);
		container.add(label3);
		container.add(TF3);
		container.add(win);
		container.add(lose);
		container.add(label4);
		container.add(TF4);
		container.add(label5);
		container.add(TF5);
		container.add(button1);
		container.add(button2);
		container.add(label6);
		container.add(TA);
		container.add(scrollPane);
		container.add(button3);
		container.add(TF6);
		container.add(button4);
		container.add(button5);
		scrollPane.setViewportView(TA);
		
		
		setVisible(true);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dbconnection.showStatus();
				TA.setText("");
				try {
					while(dbconnection.rs.next()) {
					String id = dbconnection.rs.getString("id");
					String my = dbconnection.rs.getString("myPTZ");
					String enemy = dbconnection.rs.getString("enemyPTZ");
					String WL = dbconnection.rs.getString("WinLose");
					int LP = dbconnection.rs.getInt("LP");
					String map = dbconnection.rs.getString("MAP");
					String Ys = dbconnection.rs.getString("YMDHMS");
					TA.append(id + "\t"+ my + "\t" + enemy + 
							"\t" + WL + "\t" + LP
							+ "\t" + map + "\t" + Ys+"\n");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dbconnection.dbClose();
			}
			
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String WL = "";
				
				int point = 0;
				String ys = SDF.format(new Date());
				point = Integer.parseInt(TF4.getText());
				if(win.isSelected()) {
					WL = "승리";
				dbconnection.update(TF1.getText(), TF2.getText().toUpperCase(), 
						TF3.getText().toUpperCase(), WL, 
						point, TF5.getText(), ys);
				}
				else { 
					WL = "패배";
				dbconnection.update(TF1.getText(), TF2.getText().toUpperCase(), 
						TF3.getText().toUpperCase(), WL, 
						point, TF5.getText(), ys);
				}
				TF2.setText("");
				TF3.setText("");
				TF4.setText("");
				TF5.setText("");
				buttonGroup.clearSelection();
			}
			//d.dbClose();
		});
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dbconnection.deleteAll();
				TA.setText("");
			}
			
		});
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sId = TF6.getText();
				TA.setText("");
				dbconnection.idSearch(sId);
				try {
					while(dbconnection.rs.next()) {
					String id = dbconnection.rs.getString("id");
					String my = dbconnection.rs.getString("myPTZ");
					String enemy = dbconnection.rs.getString("enemyPTZ");
					String WL = dbconnection.rs.getString("WinLose");
					int LP = dbconnection.rs.getInt("LP");
					String map = dbconnection.rs.getString("MAP");
					String Ys = dbconnection.rs.getString("YMDHMS");
					TA.append(id + "\t"+ my + "\t" + enemy + 
							"\t" + WL + "\t" + LP
							+ "\t" + map + "\t" + Ys+"\n");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dbconnection.dbClose();
			}
			
		});
		
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sId = TF6.getText();
				dbconnection.winRate(sId);
				try {
					while(dbconnection.rs.next()) {
						String enemy = dbconnection.rs.getString(1);
						String sWin = dbconnection.rs.getString(2);
						String sLose = dbconnection.rs.getString(3);
						double rate = dbconnection.rs.getDouble(4)*100;
						TA.append("vs " + enemy+"\t승리 : "+ sWin + "\t패배 : "
						+ sLose +"\t승률 : " + rate + "%\n" );
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
				dbconnection.dbClose();
			}
			
		});
	}
}
