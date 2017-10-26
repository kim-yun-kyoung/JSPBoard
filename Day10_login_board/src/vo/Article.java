package vo;

import java.util.Date;

public class Article {
	private int articleNum;
	private String title;
	private String writer;
	private int readCount;
	private Date writeTime;
	private String contents;
	private int b_list;
	private int b_level;
	private int b_ridx;
	
	public Article() {}
	
	public Article(int articleNum, String title, String writer, int readCount
			, Date write_time, String contents, int b_list, int b_level, int b_ridx) {
		this.articleNum = articleNum;
		this.title = title;
		this.writer = writer;
		this.readCount = readCount;
		this.writeTime = write_time;
		this.contents = contents;
		this.b_list = b_list;
		this.b_level = b_level;
		this.b_ridx = b_ridx;
	}
	
	public int getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Date getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Date write_time) {
		this.writeTime = write_time;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getB_list() {
		return b_list;
	}

	public void setB_list(int b_list) {
		this.b_list = b_list;
	}

	public int getB_level() {
		return b_level;
	}

	public void setB_level(int b_level) {
		this.b_level = b_level;
	}

	public int getB_ridx() {
		return b_ridx;
	}

	public void setB_ridx(int b_ridx) {
		this.b_ridx = b_ridx;
	}

	@Override
	public String toString() {
		return "Article [articleNum=" + articleNum + ", title=" + title + ", writer=" + writer + ", readCount="
				+ readCount + ", writeTime=" + writeTime + ", contents=" + contents + ", b_list=" + b_list
				+ ", b_level=" + b_level + ", b_ridx=" + b_ridx + "]";
	}
}
