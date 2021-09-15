package web.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.member.dao.MemberDao;
import web.member.vo.MemberVO;

public class MemberDaoImpl implements MemberDao{
	private static final String INSERT_STMT = 
		"INSERT INTO MEMBER (name,id_number,phone,email) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE MEMBER set name=?, id_number=?, phone=?, email=? where member_id = ?";
	private static final String DELETE_STMT = 
		"DELETE FROM MEMBER where member_id = ?";
	private static final String GET_ONE_STMT = 
		"SELECT member_id,name,id_number,phone,email FROM MEMBER where member_id = ?";
	private static final String GET_ALL_STMT = 
		"SELECT member_id,name,id_number,phone,email FROM MEMBER order by member_id";
	
	private DataSource dataSource;
	public MemberDaoImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(MemberVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getIdNumber());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			
			return pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
//			return -1;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return -1;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					return -1;
				}
			}
		}
	}

	@Override
	public int deleteByKey(Integer id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
//			return -1;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return -1;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					return -1;
				}
			}
		}
	}

	@Override
	public int updateByKey(MemberVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getIdNumber());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setInt(5, member.getMemberId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
//			return -1;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return -1;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					return -1;
				}
			}
		}
	}

	@Override
	public MemberVO selectByKey(Integer id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO member = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				member = new MemberVO();
				member.setMemberId(rs.getInt("member_id"));
				member.setName(rs.getString("name"));
				member.setIdNumber(rs.getString("id_number"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return member;
	}

	@Override
	public List<MemberVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO member = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO ¤]ºÙ¬° Domain objects
				member = new MemberVO();
				member.setMemberId(rs.getInt("member_id"));
				member.setName(rs.getString("name"));
				member.setIdNumber(rs.getString("id_number"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				list.add(member);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
