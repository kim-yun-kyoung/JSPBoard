package service;

import dao.MemberDao;
import vo.Member;

public class MemberService {
	//서비스가 작업 수행시 DB 명령어 실행 필요할 때 사용할 객체
	private MemberDao dao = MemberDao.getInstance();
	
/////////////////////////////////////////////////	
	private static MemberService instance;
	public static MemberService getInstance() {
		if(instance == null) 
			instance = new MemberService();	
		return instance;
	}
	private MemberService() {}
/////////////////////////////////////////////////	
	public boolean joinMember(Member member) {
		if(dao.insert(member)>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public String login(String id, String pw) { 
		String loginId = dao.selectIdUsingIdPw(id, pw);
		return loginId;
	}
	
	//아이디 중복 검사
	public String duplicateCheckId(String id) {
		return dao.duplicateCheckId(id);
	}
	

}
