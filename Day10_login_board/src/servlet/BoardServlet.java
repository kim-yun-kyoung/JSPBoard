package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.Article;
import vo.ArticlePage;

@WebServlet("/board")
public class BoardServlet extends HttpServlet{
	private BoardService service = BoardService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		String path = "";
		
		if(task == null ||  task.equals("boardList")) {
			//���� ��û ������ ���� �Ķ���� p ó��
			String pageStr = request.getParameter("p");
			int page = 1;
			if(pageStr != null && !pageStr.isEmpty()) {
				page = Integer.parseInt(pageStr);
			}
			//���񽺿��� �ش� ������ ������ ���� ��û
			ArticlePage articlePage = service.makePage(page);
			
			request.setAttribute("articlePage", articlePage);
			path = "board_list.jsp";
		}else if(task.equals("writeForm")) {
			HttpSession session = request.getSession();
			String loginId = (String)
					session.getAttribute("loginId");
			if(loginId==null || loginId.isEmpty()) {
				// �α��� �ȵ� ����ڰ� �۾��� ��û ������ ���
				// �α��� ������ �ȳ��ϱ�
				path = "login_form.jsp";
			}else {
				path = "write_form.jsp";
			}
		}else if(task.equals("replyForm")) {
			String b_list = request.getParameter("b_list");
			String b_level = request.getParameter("b_level");
			String b_ridx = request.getParameter("b_ridx");
			
//			int list = 0, level = 0, ridx = 0;
			int list = Integer.parseInt(b_list);
			int level = Integer.parseInt(b_level);
			int ridx = Integer.parseInt(b_ridx);
			System.out.println(list+","+level+","+ridx);
			HttpSession session = request.getSession();
			String loginId = (String)session.getAttribute("loginId");
			
			if(loginId == null && loginId.isEmpty()) {
				path = "login_form.jsp";
			}else {
				request.setAttribute("task", task);
				request.setAttribute("b_list", list);
				request.setAttribute("b_level", level);
				request.setAttribute("b_ridx", ridx);
				
				path = "reply_form.jsp";
			}
		}else if(task.equals("read")) {
			// �ۼ��ڰ� ���� �� ������ ��ȸ�� ���� �Ƚ�Ŵ.
			HttpSession session = request.getSession();
			String loginId = (String)session.getAttribute("loginId");
			String articleNumStr = request.getParameter("articleNum");
			int articleNum = Integer.parseInt(articleNumStr);
			
			Article article = service.read(loginId, articleNum);
			if(article!=null) {
				request.setAttribute("article", article);
				path = "read.jsp";
			}else {
				path = "article_not_found.jsp";
			}
		}else if(task.equals("updateForm")) {
			// �� �б⿡�� �����ϱ� ������ �� �� ��ȣ �ޱ�
			String articleNumStr = request.getParameter("articleNum");
			int articleNum = 0;
			if(articleNumStr != null && articleNumStr.length()>0) {
				articleNum = Integer.parseInt(articleNumStr);
			}
			//��ȸ�� ���� ���� ���� �� ��ȸ�ϴ� ���� ���
			Article original = service.readWithoutReadCount(articleNum);
			request.setAttribute("original", original);
			path = "update_form.jsp";
		}else if(task.equals("deleteForm")) {
			String articleNumStr = request.getParameter("articleNum");
			int articleNum = 0;
			if(articleNumStr != null && articleNumStr.length() > 0) {
				articleNum = Integer.parseInt(articleNumStr);
			}
			if(service.delete(articleNum)) {
				path = "delete_success.jsp";
			}else {
				path = "delete_fail.jsp";
			}
		}
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String task = request.getParameter("task");
		String path = "";
		
		if(task.equals("write")) {
			Article article = new Article();
			article.setWriter(request.getParameter("writer"));
			article.setTitle(request.getParameter("title"));
			article.setContents(request.getParameter("contents"));
			boolean result = service.write(article);
			if(result) {
				path = "write_success.jsp";
			}else {
				path = "write_fail.jsp";
			}
		}else if(task.equals("reply_write")) {//��� �Է�
			Article article = new Article();
			article.setWriter(request.getParameter("writer"));
			article.setTitle(request.getParameter("title"));
			article.setContents(request.getParameter("contents"));
			
			article.setB_list(Integer.parseInt(request.getParameter("b_list")));
			article.setB_level(Integer.parseInt(request.getParameter("b_level")));
			article.setB_ridx(Integer.parseInt(request.getParameter("b_ridx")));
			
			boolean result = service.replyWrite(article);
			if(result) {
				path = "reply_write_success.jsp";
			}else {
				path = "reply_write_fail.jsp";
			}
			
			
			
		}else if(task.equals("update")) {
			Article updateArticle = new Article();
			updateArticle.setTitle(request.getParameter("title"));
			updateArticle.setContents(request.getParameter("contents"));
			// ������ �۹�ȣ �Ķ���� ���ڿ��� ���ڷ� ��ȯ
			String articleNumStr = request.getParameter("articleNum");
			int articleNum = 0;
			if(articleNumStr!=null && articleNumStr.length()>0) {
				articleNum = Integer.parseInt(articleNumStr);
			}
			updateArticle.setArticleNum(articleNum);
			// ������ ������� ���񽺿��� ����
			if(service.update(updateArticle)) {
				request.setAttribute("articleNum", updateArticle.getArticleNum());
				path = "update_success.jsp";
			}else {
				path = "update_fail.jsp";
			}	
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
