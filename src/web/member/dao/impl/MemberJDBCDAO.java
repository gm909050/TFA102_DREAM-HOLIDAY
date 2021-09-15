package web.member.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.member.dao.MemberDAO_interface;
import web.member.vo.MemberVO;


public class MemberJDBCDAO implements MemberDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Hotel_10?serverTimezone=Asia/Taipei";
	String userid = "peter";
	String passwd = "123456";
	
	
	private static final String INSERT_STMT =
			"INSERT INTO MEMBER(name, id_number, phone, email, password) VALUES(?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT member_id, name, id_number, phone, email,  password FROM member order by member_id";
	private static final String GET_ONE_STMT =
			"SELECT member_id, name, id_number, phone, email,  password FROM member where email = ?";
	private static final String UPDATE = 
			"UPDATE MEMBER SET name=?, id_number=?, phone=?, email=?,  password=? where member_id = ? ";
	private static final String DELETE =
			"DELETE FROM MEMBER where member_id=?";
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			Class.forName(driver);
            System.out.println("success");

            con = DriverManager.getConnection(url, userid, passwd);
            System.out.println("連線成功");
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, memberVO.getMemberId());
			pstmt.setString(1, memberVO.getName());
			pstmt.setString(2, memberVO.getIdNumber());
			pstmt.setString(3, memberVO.getPhone());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.setString(5, memberVO.getPassword());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}
	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, memberVO.getName());
			pstmt.setString(2, memberVO.getIdNumber());
			pstmt.setString(3, memberVO.getPhone());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.setString(5, memberVO.getPassword());
			pstmt.setInt(6, memberVO.getMemberId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}
	@Override
	public void delete(Integer memberId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberId);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}
	@Override
	public MemberVO findByPrimaryKey(String email) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getInt("member_id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setIdNumber(rs.getString("id_number"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setPassword(rs.getString("password"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return memberVO;
	}
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getInt("member_id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setIdNumber(rs.getString("id_number"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setPassword(rs.getString("password"));
				list.add(memberVO); // Store the row in the list
			}
			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();
		//新增
//				MemberVO memberVO1 = new MemberVO();
//				memberVO1.setName("qwdqwd");
//				memberVO1.setIdNumber("A9203849172");
//				memberVO1.setPhone("0910293821");
//				memberVO1.setEmail("q0soaq@gmail.com");
//				memberVO1.setPassword("qefwqfdwqdijqeo21e");
//				dao.insert(memberVO1);

//		MemberVO memberVO = dao.findByPrimaryKey("asdasd@asdasd");
//		System.out.println(memberVO.getName());
				 //更新
//				MemberVO memberVO2 = new MemberVO();
//				memberVO2.setMemberId(1020);
//				memberVO2.setName("王八蛋");
//				memberVO2.setIdNumber("A920qwe1172");
//				memberVO2.setPhone("0910293821");
//				memberVO2.setEmail("q0soaq@gmail.com");
//				memberVO2.setPassword("1234567wqd8");
//				dao.update(memberVO2);
				// �R��
//				dao.delete(2);
//
				// 找主見
				MemberVO memberVO3 = dao.findByPrimaryKey("EEE@gmail.com");
				System.out.print(memberVO3.getMemberId() + ",");
				System.out.print(memberVO3.getName() + ",");
				System.out.print(memberVO3.getIdNumber() + ",");
				System.out.print(memberVO3.getPhone() + ",");
				System.out.print(memberVO3.getEmail() + ",");
				System.out.println("---------------------");

//				// �d��
//				List<MemberVO> list = dao.getAll();
//				for (MemberVO Member : list) {
//					System.out.print(Member.getMemberId() + ",");
//					System.out.print(Member.getName() + ",");
//					System.out.print(Member.getIdNumber() + ",");
//					System.out.print(Member.getPhone() + ",");
//					System.out.print(Member.getEmail() + ",");
//					System.out.print(Member.getPassword());
//					System.out.println();
//		}
	}
}