package web.dish.dao.impl;

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

import web.dish.dao.DishDao;
import web.dish.vo.Dish;

public class DishDaoImpl implements DishDao {
	private static final String INSERT_STMT = 
		"INSERT INTO DISH_INFO (dish_name, dish_price, dish_intro, dish_type, dish_status, dish_picture) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE DISH_INFO set dish_name = ?, dish_price = ?, dish_intro = ?, dish_type = ?, dish_status = ?, dish_picture = ? where dish_id = ?";
	private static final String DELETE_STMT = 
//		"DELETE FROM DISH_INFO where dish_id = ?";
		"UPDATE DISH_INFO set dish_status = ? where dish_id = ?";
	private static final String GET_ONE_STMT = 
		"SELECT dish_id, dish_name, dish_price, dish_intro, dish_type, dish_status, dish_picture FROM DISH_INFO where dish_id = ?";
	private static final String GET_ALL_STMT = 
//		"SELECT dish_id, dish_name, dish_price, dish_intro, dish_type, dish_status, dish_picture FROM DISH_INFO order by dish_id";
		"SELECT dish_id, dish_name, dish_price, dish_intro, dish_type, dish_status, dish_picture FROM DISH_INFO WHERE dish_status != ? order by dish_id";
	private static final String GET_ALL_ON_SHELF = 
		"SELECT dish_id, dish_name, dish_price FROM DISH_INFO WHERE dish_status = ?;";
	
	private DataSource dataSource;
	public DishDaoImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(Dish dish) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, dish.getDishName());
			pstmt.setInt(2, dish.getDishPrice());
			pstmt.setString(3, dish.getDishIntro());
			pstmt.setInt(4, dish.getDishType());
			pstmt.setInt(5, dish.getDishStatus());
//			pstmt.setBytes(6, getPictureByteArray("items/1.png"));
			pstmt.setBytes(6, dish.getDishPicture());
			
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
			
			pstmt.setInt(1, 3);
			pstmt.setInt(2, id);
			
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
	public int updateByKey(Dish dish) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, dish.getDishName());
			pstmt.setInt(2, dish.getDishPrice());
			pstmt.setString(3, dish.getDishIntro());
			pstmt.setInt(4, dish.getDishType());
			pstmt.setInt(5, dish.getDishStatus());
//			pstmt.setBytes(6, getPictureByteArray("items/FC_Barcelona.png"));
			pstmt.setBytes(6, dish.getDishPicture());
			pstmt.setInt(7, dish.getDishId());
			
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
	public Dish selectByKey(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Dish dish = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dish = new Dish();
				dish.setDishId(rs.getInt("dish_id"));
				dish.setDishName(rs.getString("dish_name"));
				dish.setDishPrice(rs.getInt("dish_price"));
				dish.setDishIntro(rs.getString("dish_intro"));
				dish.setDishType(rs.getInt("dish_type"));
				dish.setDishStatus(rs.getInt("dish_status"));
				dish.setDishPicture(rs.getBytes("dish_picture"));
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
		return dish;
	}

	@Override
	public List<Dish> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Dish> list = new ArrayList<Dish>();
		Dish dish = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, 3);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dish = new Dish();
				dish.setDishId(rs.getInt("dish_id"));
				dish.setDishName(rs.getString("dish_name"));
				dish.setDishPrice(rs.getInt("dish_price"));
				dish.setDishIntro(rs.getString("dish_intro"));
				dish.setDishType(rs.getInt("dish_type"));
				dish.setDishStatus(rs.getInt("dish_status"));
				dish.setDishPicture(rs.getBytes("dish_picture"));
				list.add(dish); // Store the row in the list
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

	@Override
	public List<Dish> getOrderMenuOnShelf() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Dish> list = new ArrayList<Dish>();
		Dish dish = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ON_SHELF);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dish = new Dish();
				dish.setDishId(rs.getInt("dish_id"));
				dish.setDishName(rs.getString("dish_name"));
				dish.setDishPrice(rs.getInt("dish_price"));
				list.add(dish); // Store the row in the list
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
	
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}
}
