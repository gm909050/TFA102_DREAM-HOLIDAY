package web.member.service.impl;

import java.util.List;

import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;
import web.member.service.MemberService;
import web.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao dao;
	
	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}

	@Override
	public boolean addMember(MemberVO member) {
		return dao.insert(member) > 0;
	}

	@Override
	public boolean updateMember(MemberVO member) {
		return dao.updateByKey(member) > 0;
	}

	@Override
	public boolean deleteMember(Integer id) {
		return dao.deleteByKey(id) > 0;
	}

	@Override
	public MemberVO getMemberByKey(Integer id) {
		return dao.selectByKey(id);
	}

	@Override
	public List<MemberVO> getAll() {
		return dao.selectAll();
	}

}
