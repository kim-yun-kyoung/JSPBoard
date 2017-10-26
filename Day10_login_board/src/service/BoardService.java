package service;

import java.util.Date;
import java.util.List;

import dao.BoardDao;
import vo.Article;
import vo.ArticlePage;

public class BoardService {
	private BoardDao dao = BoardDao.getInstance();
	//singleton패턴 적용
	private static BoardService instance = new BoardService();
	public static BoardService getInstance() {
		if(instance == null)
			instance = new BoardService();
		return instance;
	}
	private BoardService() {}
	/////////////////////////////////////////////////////////////
	private static final int COUNT_PER_PAGE=10;
	public ArticlePage makePage(int page) {
		// 총 게시글의 갯수 DB 에서 조회
		int totalArticleCount = dao.selectArticleCount();

		// 총 페이지 수 계산
		int totalPage = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE > 0) {
			totalPage++;
		}

		// 하단 시작 페이지
		int startPage = (page - 1) / 10 * 10 + 1;

		// 하단 끝 페이지
		int endPage = startPage + 9;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// limit 시작행 계산
		int startRow = (page - 1) * COUNT_PER_PAGE;

		// DB 에서 현재 페이지에 보여질 게시글들 조회
		List<Article> articleList = dao.selectArticleList(startRow, COUNT_PER_PAGE);

		// 한 페이지에 보여질 모든 데이터 담아서 작업 완료
		return new ArticlePage
				(articleList, startPage, endPage, page, totalPage);
	}	
	
	//서블릿이 전달한 미완성 article에 나머지 값 채워서 DB에 추가
	public boolean write(Article article){
		article.setWriteTime(new Date());
		article.setReadCount(0);
		if(dao.insert(article) > 0) {
			return true;			
		}else {
			return false;
		}
	}
	

		
	//답글 DB insert
	public boolean replyWrite(Article article) {
		article.setWriteTime(new Date());
		article.setReadCount(0);
		
		article.setB_level(article.getB_level()+1);
		
		int ridxUpdate = dao.replyRidxUpdate(article.getB_list(), article.getB_ridx()+1);
		System.out.println("service replyWrite irdxUpdate : "+ridxUpdate);
		
		article.setB_ridx(article.getB_ridx()+1);
		
		if(dao.replyInsert(article)>0) {
			return true;
		}else {
			return false;
		}
	}
	//서블릿이 전달한 글 번호와 글을 DB에서 꺼내오기
	//글 읽을 때 해당 글의 조회수도 증가하게 할 예정.
	public Article read(String loginId, int articleNum) {
		Article article = dao.select(articleNum);
		System.out.println("service read : "+article.toString());
		if(article==null || article.getWriter().equals(loginId)) {
			return article;
		} else {
			dao.updateReadCount(articleNum);
			article.setReadCount(article.getReadCount()+1);
			return article;
		}
	}
	
	public Article readWithoutReadCount(int articleNum) {
		return dao.select(articleNum);
	}
	
	public boolean update(Article updateArticle) {
		updateArticle.setWriteTime(new Date());
		if(dao.update(updateArticle)>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean delete(int articleNum) {
		if(dao.delete(articleNum) > 0) {
			return true;
		}else {
			return false;
		}
	}
}



















