package web.oneday.dao;

import java.io.FileInputStream;
import java.io.IOException;
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

import web.oneday.service.impl.ONEDAY_interface;
import web.oneday.vo.ONEDAYVO;

public class ONEDAYJNDIDAO implements ONEDAY_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_MEM = "INSERT INTO ONEDAY (picture_name, picture_explanation, picture) VALUES(?,?,?);";
	private static final String GET_ALL_MEM = "SELECT picture_id, picture_name, picture_explanation FROM ONEDAY order by picture_id";
	private static final String GET_ONE_MEM = "SELECT picture_id, picture_name, picture_explanation, picture FROM ONEDAY where picture_id = ?";
	private static final String DELETE = "DELETE FROM ONEDAY where picture_id = ?";
	private static final String UPDATE = "UPDATE ONEDAY set  picture_name=?, picture_explanation=?, picture=? where picture_id = ?";
	
	

	@Override
	public void insert(ONEDAYVO ONEDAYVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_MEM);

			pstmt.setString(1, ONEDAYVO.getPicturename());
			pstmt.setString(2, ONEDAYVO.getPictureexplanation());
			pstmt.setBytes(3, ONEDAYVO.getPicture());

			pstmt.executeUpdate();

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
	public void update(ONEDAYVO ONEDAYVO) {
		Connection con = null;// 建立連線
		PreparedStatement pstmt = null;// 動態新增欄位
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ONEDAYVO.getPicturename());
			pstmt.setString(2, ONEDAYVO.getPictureexplanation());
			pstmt.setBytes(3, ONEDAYVO.getPicture());
			pstmt.setInt(4,ONEDAYVO.getPictureid());

			pstmt.executeUpdate();

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
	public void delete(Integer picture_id) {
		Connection con = null;// 建立連線
		PreparedStatement pstmt = null;// 動態欄位
		try {
			con = ds.getConnection();
			// 動態刪除欄位
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, picture_id);

			pstmt.executeUpdate();// 執行更改檔案

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
	public ONEDAYVO findByPrimaryKey(Integer venueId) {
	
		return null;
	}

	@Override
	public List<ONEDAYVO> getAll() {
		List<ONEDAYVO> list = new ArrayList<ONEDAYVO>();
		ONEDAYVO ONEDAYVO = null;
		Connection con = null;// 建立連線
		PreparedStatement pstmt = null;// 動態欄位
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_MEM);
			rs = pstmt.executeQuery();// 執行更改檔案
			
			while(rs.next()) {
				ONEDAYVO = new ONEDAYVO();
				ONEDAYVO.setPictureid(rs.getInt("Picture_id"));
				ONEDAYVO.setPicturename(rs.getString("picture_name"));
				ONEDAYVO.setPictureexplanation(rs.getString("picture_explanation"));
				
				list.add(ONEDAYVO);
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
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
	
	
	public byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()]; //檔案大小長度
		fis.read(buffer); //將位元讀入BYTE陣列
		fis.close();
		return buffer;
	}
	
	
//	public static void main(String[] args) {
//		ONEDAYJDBCDAO onejdbc = new ONEDAYJDBCDAO();
//		ONEDAYVO onevo = new ONEDAYVO();
//		
//		onevo.setPictureid(1);
//		onevo.setPicturename("一日景點");
//		onevo.setPictureexplanation("此景點非常好看");
//		byte[] pic = onevo.getPictureByteArray("img/a.jpg");
//		onevo.setPicture(pic);
//		
//		}
}
