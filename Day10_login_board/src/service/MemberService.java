package service;

import dao.MemberDao;
import vo.Member;

public class MemberService {
	//���񽺰� �۾� ����� DB ��ɾ� ���� �ʿ��� �� ����� ��ü
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
	
	//���̵� �ߺ� �˻�
	public String duplicateCheckId(String id) {
		return dao.duplicateCheckId(id);
	}
	

}
