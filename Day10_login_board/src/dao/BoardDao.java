package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vo.Article;

public class BoardDao {
	// singleton
	private static BoardDao instance;
	public static BoardDao getInstance() {
		if(instance == null)
			instance = new BoardDao();
		return instance;
	}
	private BoardDao() {
		DBUtil.loadDriver();
	}
	////////////////////////////////////////////
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	////////////////////////////////////////////    
	// 1. 총 게시글의 갯수 조회
	public int selectArticleCount() {
		con = DBUtil.makeConnection();
		String sql = "SELECT COUNT(*) FROM BOARD";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // sql 실행

			// 결과 숫자 하나 얻기
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("dao count 에러");
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeCon(con);
		}
		return result;
	}

	// 2. 특정 페이지에 보여질 게시글 조회
	public List<Article> selectArticleList(int startRow, int count){
		con = DBUtil.makeConnection();
		String sql = "SELECT ARTICLE_NUM, TITLE,"
				+ "WRITER, CONTENTS, READ_COUNT,"
				+ "WRITE_TIME, B_LIST, B_LEVEL, B_RIDX FROM BOARD "
				+ "ORDER BY B_LIST DESC,B_RIDX LIMIT ?,?";
		List<Article> articleList = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery(); // SQL 실행

			while(rs.next()) {
				Article article = new Article();
				article.setArticleNum(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setWriter(rs.getString(3));
				article.setContents(rs.getString(4));
				article.setReadCount(rs.getInt(5));
				article.setWriteTime(rs.getTimestamp(6));
				article.setB_list(rs.getInt(7));
				article.setB_level(rs.getInt(8));
				article.setB_ridx(rs.getInt(9));

				articleList.add(article);
			}
		} catch (SQLException e) {
			System.out.println("dao selectArticleList 에러");
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeCon(con);
		}
		return articleList;
	}

	//글쓰기
	public int insert(Article article) {
		con = DBUtil.makeConnection();
		int result = 0;
		
		try {
			String sql = "INSERT INTO BOARD"
					+ "(TITLE,WRITER,CONTENTS,READ_COUNT,WRITE_TIME"
					+ ", B_LIST, B_LEVEL, B_RIDX) VALUES(?,?,?,?,?,0,0,0)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getWriter());
			pstmt.setString(3, article.getContents());
			pstmt.setInt(4, article.getReadCount());
			pstmt.setTimestamp(5,new Timestamp(article.getWriteTime().getTime()));

			result = pstmt.executeUpdate(); // SQL 실행
			
			if(result > 0) {
				String currvalSql = "SELECT MAX(ARTICLE_NUM) FROM BOARD";
				pstmt= con.prepareStatement(currvalSql);
				rs = pstmt.executeQuery();
				int currval = 0;
				if(rs.next()) 
					currval = rs.getInt(1);
				System.out.println("currval : "+currval);
				
				sql = "UPDATE BOARD SET B_LIST = ? WHERE ARTICLE_NUM = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, currval);
				pstmt.setInt(2, currval);
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("dao insert 에러");
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeCon(con);
		}
		return result;
	}
	
	public int replyInsert(Article article) {
		con = DBUtil.makeConnection();
		int result = 0;
		String sql = "INSERT INTO BOARD"
				+ "(TITLE,WRITER,CONTENTS,READ_COUNT,WRITE_TIME"
				+ ", B_LIST, B_LEVEL, B_RIDX) VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getWriter());
			pstmt.setString(3, article.getContents());
			pstmt.setInt(4, article.getReadCount());
			pstmt.setTimestamp(5,new Timestamp(article.getWriteTime().getTime()));
			pstmt.setInt(6, article.getB_list());
			pstmt.setInt(7, article.getB_level());
			pstmt.setInt(8, article.getB_ridx());

			result = pstmt.executeUpdate(); // SQL 실행
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	public int replyRidxUpdate(int list, int ridx) {
		con = DBUtil.makeConnection();
		String sql = "UPDATE BOARD SET B_RIDX = B_RIDX+1 WHERE B_LIST = ? AND B_RIDX >= ?";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, list);
			pstmt.setInt(2, ridx);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("dao reply indx update error");
			e.printStackTrace();
		}
		return result;
	}
	
	///////////////////////////////////////////////////////////
	// 글 읽기 작업에 필요한 메소드
	// 1. 조회수 증가
	public int updateReadCount(int articleNum) {
		con = DBUtil.makeConnection();
		String sql = "UPDATE BOARD SET READ_COUNT=READ_COUNT+1 " 
				+ "WHERE ARTICLE_NUM=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNum);

			result = pstmt.executeUpdate(); // SQL 실행
		} catch (SQLException e) {
			System.out.println("dao update 에러");
			e.printStackTrace();
		} finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeCon(con);
		}
		return result;
	}

	// 2. 해당 번호의 Article 조회
	public Article select(int articleNum) {
		con = DBUtil.makeConnection();
		String sql = "SELECT ARTICLE_NUM,TITLE,WRITER," 
				+ "CONTENTS,WRITE_TIME,READ_COUNT,B_LIST,B_LEVEL,B_RIDX FROM BOARD "
				+ "WHERE ARTICLE_NUM=?";
		Article article = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNum);
			rs = pstmt.executeQuery(); // SQL 실행

			if (rs.next()) {
				article = new Article();
				article.setArticleNum(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setWriter(rs.getString(3));
				article.setContents(rs.getString(4));
				article.setWriteTime(rs.getTimestamp(5));
				article.setReadCount(rs.getInt(6));
				article.setB_list(rs.getInt(7));
				article.setB_level(rs.getInt(8));
				article.setB_ridx(rs.getInt(9));
			}
		} catch (SQLException e) {
			System.out.println("dao select 에러");
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeCon(con);
		}
		return article;
	} 	
	
	//3. 수정하기
	public int update(Article article) {
		con = DBUtil.makeConnection();
		int result = 0;
		String sql = "UPDATE BOARD SET TITLE=?,CONTENTS=?,WRITE_TIME=? "
				+ "WHERE ARTICLE_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContents());
			pstmt.setTimestamp(3, new Timestamp(article.getWriteTime().getTime()));
			pstmt.setInt(4, article.getArticleNum());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeCon(con);
		}
		return result;
	}
	
	//4. 삭제하기
	public int delete(int articleNum) {
		con = DBUtil.makeConnection();
		int result = 0;
		String sql = "DELETE FROM BOARD WHERE ARTICLE_NUM = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("board dao delete error");
			e.printStackTrace();
		} finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeCon(con);
		}
		return result;
	}
}
















