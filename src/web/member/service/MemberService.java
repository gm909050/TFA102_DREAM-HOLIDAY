package web.member.service;

import java.util.List;

import web.member.vo.MemberVO;

public interface MemberService {
	
	boolean addMember(MemberVO member);
	boolean updateMember(MemberVO member);
	boolean deleteMember(Integer id);
	MemberVO getMemberByKey(Integer id);
	List<MemberVO> getAll();
}
