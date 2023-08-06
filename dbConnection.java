package status;

import java.sql.*;

public class dbConnection {
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	static float[][] vs = new float[4][3];
	public dbConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� �˻� ����");
			
			String url = "jdbc:mysql://localhost:3306/status";
			String user = "xxxx";
			String password = "xxxx";

			conn = DriverManager.getConnection(url, user, password);
			System.out.println("�����ͺ��̽� ���� ����");
			String sql="select * from log";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �˻� ����");
		} catch(SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����(Ŀ�ؼ�)");
		}
		
	}
	public void dbClose() {
		try {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(pstmt!=null)
				pstmt.close();
		} catch(Exception e) {
			System.out.println("close fail");
		}
	}
	
	public void update(String id, String i, String enemy,
			String WL, int point, String map, String ys) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� �˻� ����");
			
			String url = "jdbc:mysql://localhost:3306/status";
			String user = "root";
			String password = "1234";

			conn = DriverManager.getConnection(url, user, password);
			System.out.println("�����ͺ��̽� ���� ����");
			String sql="Insert INTO log VALUES ('" + id +"','"+
			i+"','"+enemy+"','"+WL+"',"+point+",'"+map+"','"+ys+"')";
			//stmt.executeUpdate(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �˻� ����");
		} catch(SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����(update)");
		}
		dbClose();
	}
	
	public void showStatus() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� �˻� ����");
			
			String url = "jdbc:mysql://localhost:3306/status";
			String user = "root";
			String password = "1234";

			conn = DriverManager.getConnection(url, user, password);
			System.out.println("�����ͺ��̽� ���� ����");
			String sql="Select * from log";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �˻� ����");
		} catch(SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����(Show)");
		}
		//dbClose();
	}
	
	public int count() {
		int i = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� �˻� ����");
			
			String url = "jdbc:mysql://localhost:3306/status";
			String user = "root";
			String password = "1234";

			conn = DriverManager.getConnection(url, user, password);
			System.out.println("�����ͺ��̽� ���� ����");
			String sql = "Select count(*) from log";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			i = rs.getInt("count(*)");
			
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �˻� ����");
		} catch(SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����(count)");
		}
		//dbClose();
		return i;
	}
	
	public void deleteAll() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� �˻� ����");
			
			String url = "jdbc:mysql://localhost:3306/status";
			String user = "root";
			String password = "1234";

			conn = DriverManager.getConnection(url, user, password);
			System.out.println("�����ͺ��̽� ���� ����");
			String sql0 = "Delete from log";
			pstmt = conn.prepareStatement(sql0);
			pstmt.executeUpdate();
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �˻� ����");
		} catch(SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����(Delete)");
		}
	}
	
	public void idSearch(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� �˻� ����");
			
			String url = "jdbc:mysql://localhost:3306/status";
			String user = "root";
			String password = "1234";

			conn = DriverManager.getConnection(url, user, password);
			System.out.println("�����ͺ��̽� ���� ����");
			String sql1 = "select * from log where id = '" + id+"'";
			/*pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();*/
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �˻� ����");
		} catch(SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����(Search)");
		}
		
	}
	public void winRate(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� �˻� ����");
			
			String url = "jdbc:mysql://localhost:3306/status";
			String user = "root";
			String password = "1234";

			conn = DriverManager.getConnection(url, user, password);
			System.out.println("�����ͺ��̽� ���� ����");
			String sql1 = "select enemyptz,sum(winlose = '�¸�'),sum(win"
					+ "lose= '�й�'),Round(sum(winlose='�¸�')/count(*)"
					+ ",4) from log where id = '" + id+"'"
							+ "group by enemyptz";
			/*pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();*/
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �˻� ����");
		} catch(SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����(Search)");
		}
		
	}
	
	
}
