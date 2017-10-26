package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	// DB 접속 관련 문자열 상수들.
		private static final String DB_DRIVER ="com.mysql.jdbc.Driver";
		private static final String DB_URL ="jdbc:mysql://127.0.0.1:3306/jsp";
		private static final String DB_ID ="root";
		private static final String DB_PW ="sds1501";
		
		public static void loadDriver() {
	        try {
	            Class.forName(DB_DRIVER);
	        } catch (ClassNotFoundException e) {
	            System.out.println("드라이버 로딩오류");
	            e.printStackTrace();
	        }
	    }
		
		public static Connection makeConnection() {
			Connection con = null;
			try {
				con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			} catch (SQLException e) {
				System.out.println("커넥션 생성 오류");
				e.printStackTrace();
			}
			return con;
		}
		
		public static void closeCon(Connection con) {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public static void closePstmt(PreparedStatement pstmt) {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public static void closeRs(ResultSet rs) {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}
