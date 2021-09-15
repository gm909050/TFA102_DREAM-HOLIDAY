package web.member.dao;

import java.util.*;

import web.member.vo.MemberVO;

public interface MemberDAO_interface {
		public void insert(MemberVO memberVO);//�s�W
		public void update(MemberVO memberVO);//�ק�
		public void delete(Integer memberId);//�R��
		public MemberVO findByPrimaryKey(String email);
		public List<MemberVO> getAll();
		//public MemberVO findByPrimaryKey(String email);
}
