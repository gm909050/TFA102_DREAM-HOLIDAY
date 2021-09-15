package web.member.service.impl;

import java.util.List;

import web.member.dao.MemberDAO_interface;
import web.member.dao.impl.MemberJDBCDAO;
import web.member.vo.MemberVO;



public class MemberService {

	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberJDBCDAO();
	}
	
	public MemberVO addMember(String name, String idNumber, String phone,
			String email, String password) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setName(name);
		memberVO.setIdNumber(idNumber);
		memberVO.setPhone(phone);
		memberVO.setEmail(email);
//		memberVO.setMemberLevel(memberLevel);
//		memberVO.setMemberPoint(memberPoint);
		memberVO.setPassword(password);
		dao.insert(memberVO);
		
		return memberVO;
	}
	public MemberVO updateMember(Integer memberId,String name, String idNumber, String phone,
			String email, String password) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberId(memberId);
		memberVO.setName(name);
		memberVO.setIdNumber(idNumber);
		memberVO.setPhone(phone);
		memberVO.setEmail(email);
		memberVO.setPassword(password);
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public void deleteMember(Integer memberId) {
		dao.delete(memberId);
	}

//	public MemberVO getOneMember(Integer memberId) {
//		return dao.findByPrimaryKey(memberId);
//	}
	public MemberVO findByPrimaryKey(String email) {
		
		return dao.findByPrimaryKey(email);
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}

	public MemberVO setAttribute(String string, MemberVO memberVO) {
		return null;
	}

	
			
	
	
}
