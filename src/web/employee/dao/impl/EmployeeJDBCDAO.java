package web.employee.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.employee.dao.EmployeeDAO_interface;
import web.employee.vo.EmployeeVO;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Hotel_10?serverTimezone=Asia/Taipei";
	String userid = "peter";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (emp_name, id_number, emp_phone, emp_mail, password, hiredate) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT emp_id, emp_name, id_number, emp_phone, emp_mail, password, hiredate FROM EMPLOYEE order by emp_id";
	private static final String GET_ONE_STMT = "SELECT emp_id, emp_name, id_number, emp_phone, emp_mail, password, hiredate FROM EMPLOYEE where emp_id = ?";
	private static final String DELETE = "DELETE FROM EMPLOYEE where emp_id = ?";
	private static final String UPDATE = "UPDATE EMPLOYEE set emp_name=?, id_number=?, emp_phone=?, emp_mail=?, password=?, hiredate=? where emp_id = ?";
	private static final String LOGIN = "SELECT emp_id, emp_name, id_number, emp_phone, emp_mail, password, hiredate FROM EMPLOYEE where emp_mail = ?";
	
	@Override
	public EmployeeVO findByEMPMAIL(String emp_mail) {

		EmployeeVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LOGIN);

			pstmt.setString(1, emp_mail);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmployeeVO();
				empVO.setEmp_id(rs.getInt("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setId_number(rs.getString("id_number"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_mail(rs.getString("emp_mail"));
				empVO.setPassword(rs.getString("password"));
				empVO.setHiredate(rs.getDate("hiredate"));
				

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return empVO;
	}

	@Override
	public void insert(EmployeeVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, empVO.getEmp_id());
			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setString(2, empVO.getId_number());
			pstmt.setString(3, empVO.getEmp_phone());
			pstmt.setString(4, empVO.getEmp_mail());
			pstmt.setString(5, empVO.getPassword());
			pstmt.setDate(6, empVO.getHiredate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(EmployeeVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setString(2, empVO.getId_number());
			pstmt.setString(3, empVO.getEmp_phone());
			pstmt.setString(4, empVO.getEmp_mail());
			pstmt.setString(5, empVO.getPassword());
			pstmt.setDate(6, empVO.getHiredate());
			pstmt.setInt(7, empVO.getEmp_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer emp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public EmployeeVO findByPrimaryKey(Integer emp_id) {

		EmployeeVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmployeeVO();
				empVO.setEmp_id(rs.getInt("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setId_number(rs.getString("id_number"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_mail(rs.getString("emp_mail"));
				empVO.setPassword(rs.getString("password"));
				empVO.setHiredate(rs.getDate("hiredate"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return empVO;
	}

	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmployeeVO();
				empVO.setEmp_id(rs.getInt("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setId_number(rs.getString("id_number"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_mail(rs.getString("emp_mail"));
				empVO.setPassword(rs.getString("password"));
				empVO.setHiredate(rs.getDate("hiredate"));
				list.add(empVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();

		// 新增
//		EmployeeVO empVO1 = new EmployeeVO();
//		empVO1.setEmp_name("吳永志1");
//		empVO1.setId_number("E0000015");		
//		empVO1.setEmp_phone("0922333444");
//		empVO1.setEmp_mail("bigwu@tibame.com");
//		empVO1.setPassword("password");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		dao.insert(empVO1);

		// 修改
//		EmployeeVO empVO2 = new EmployeeVO();
//		
//		empVO2.setEmp_name("吳永志2");
//		empVO2.setId_number("E0000222");		
//		empVO2.setEmp_phone("0922555666");
//		empVO2.setEmp_mail("bigwu2@tibame.com");
//		empVO2.setPassword("password");
//		empVO2.setHiredate(java.sql.Date.valueOf("2005-02-01"));	
//		empVO2.setEmp_id(8014);
//		dao.update(empVO2);
		//查詢帳號
		EmployeeVO empVO2 = dao.findByEMPMAIL("kobe@nbamail.com.tw");
		System.out.print(empVO2.getEmp_id() + ",");
		System.out.print(empVO2.getEmp_name() + ",");
		System.out.print(empVO2.getId_number() + ",");
		System.out.print(empVO2.getEmp_phone() + ",");
		System.out.print(empVO2.getEmp_mail() + ",");
		System.out.print(empVO2.getPassword() + ",");
		System.out.print(empVO2.getHiredate());
		
		// 刪除
//		dao.delete(8013); 

		// 查詢
//		EmployeeVO empVO3 = dao.findByPrimaryKey(8001);
//		System.out.print(empVO3.getEmp_name() + ",");
//		System.out.print(empVO3.getId_number() + ",");
//		System.out.print(empVO3.getEmp_phone() + ",");
//		System.out.print(empVO3.getEmp_mail() + ",");
//		System.out.print(empVO3.getPassword() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.println("");
//		System.out.println("---------------------------------------------------------------");

		// 查詢
//		List<EmployeeVO> list = dao.getAll();
//		for (EmployeeVO aEmp : list) {
//			System.out.print(aEmp.getEmp_id() + ",");
//			System.out.print(aEmp.getEmp_name() + ",");
//			System.out.print(aEmp.getId_number() + ",");
//			System.out.print(aEmp.getEmp_phone() + ",");
//			System.out.print(aEmp.getEmp_mail() + ",");
//			System.out.print(aEmp.getPassword() + ",");
//			System.out.print(aEmp.getHiredate());
//			System.out.println();
//		}
		System.out.println("成功");
	}

}
