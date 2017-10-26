package service;

import java.util.Date;
import java.util.List;

import dao.BoardDao;
import vo.Article;
import vo.ArticlePage;

public class BoardService {
	private BoardDao dao = BoardDao.getInstance();
	//singleton���� ����
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
		// �� �Խñ��� ���� DB ���� ��ȸ
		int totalArticleCount = dao.selectArticleCount();

		// �� ������ �� ���
		int totalPage = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE > 0) {
			totalPage++;
		}

		// �ϴ� ���� ������
		int startPage = (page - 1) / 10 * 10 + 1;

		// �ϴ� �� ������
		int endPage = startPage + 9;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// limit ������ ���
		int startRow = (page - 1) * COUNT_PER_PAGE;

		// DB ���� ���� �������� ������ �Խñ۵� ��ȸ
		List<Article> articleList = dao.selectArticleList(startRow, COUNT_PER_PAGE);

		// �� �������� ������ ��� ������ ��Ƽ� �۾� �Ϸ�
		return new ArticlePage
				(articleList, startPage, endPage, page, totalPage);
	}	
	
	//������ ������ �̿ϼ� article�� ������ �� ä���� DB�� �߰�
	public boolean write(Article article){
		article.setWriteTime(new Date());
		article.setReadCount(0);
		if(dao.insert(article) > 0) {
			return true;			
		}else {
			return false;
		}
	}
	

		
	//��� DB insert
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
	//������ ������ �� ��ȣ�� ���� DB���� ��������
	//�� ���� �� �ش� ���� ��ȸ���� �����ϰ� �� ����.
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



















