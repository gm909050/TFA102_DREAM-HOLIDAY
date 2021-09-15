package web.order.dao.impl;

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

import web.booking.dao.BookingInfoDao;
import web.booking.dao.impl.BookingInfoDaoImpl;
import web.order.dao.OrderDetailDao;
import web.order.vo.OrderDetail;

public class OrderDetailDaoImpl implements OrderDetailDao {
	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_DETAIL (meal_id,dish_id,dish_price,dish_qty) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE ORDER_DETAIL set meal_id = ?,dish_id = ?,dish_price = ?,dish_qty =? where order_id = ?";
	private static final String DELETE_STMT = 
		"DELETE FROM ORDER_DETAIL where order_id = ?";
	private static final String GET_ONE_STMT = 
		"SELECT order_id, meal_id, dish_id, dish_price, dish_qty FROM ORDER_DETAIL where order_id = ?";
	private static final String GET_ALL_STMT = 
		"SELECT order_id, meal_id, dish_id, dish_price, dish_qty FROM ORDER_DETAIL order by order_id";
	private static final String GET_ALL_BY_MEAL_ID =
		"SELECT order_id, meal_id, dish_id, dish_price, dish_qty FROM ORDER_DETAIL WHERE meal_id = ?";	

	private DataSource dataSource;
	public OrderDetailDaoImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(OrderDetail order) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, order.getMealId());
			pstmt.setInt(2, order.getDishId());
			pstmt.setInt(3, order.getDishPrice());
			pstmt.setInt(4, order.getDishQty());
			
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
	public int updateByKey(OrderDetail order) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, order.getMealId());
			pstmt.setInt(2, order.getDishId());
			pstmt.setInt(3, order.getDishPrice());
			pstmt.setInt(4, order.getDishQty());
			pstmt.setInt(5, order.getOrderId());
			
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
	public OrderDetail selectByKey(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		OrderDetail order = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				order = new OrderDetail();
				order.setOrderId(rs.getInt("order_id"));
				order.setMealId(rs.getInt("meal_id"));
				order.setDishId(rs.getInt("dish_id"));
				order.setDishPrice(rs.getInt("dish_price"));
				order.setDishQty(rs.getInt("dish_qty"));
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
		return order;
	}

	@Override
	public List<OrderDetail> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		OrderDetail order = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				order = new OrderDetail();
				order.setOrderId(rs.getInt("order_id"));
				order.setMealId(rs.getInt("meal_id"));
				order.setDishId(rs.getInt("dish_id"));
				order.setDishPrice(rs.getInt("dish_price"));
				order.setDishQty(rs.getInt("dish_qty"));
				list.add(order);
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
	public List<OrderDetail> getDetailByMealId(Integer mealId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		OrderDetail order = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEAL_ID);
			pstmt.setInt(1, mealId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				order = new OrderDetail();
				order.setOrderId(rs.getInt("order_id"));
				order.setMealId(rs.getInt("meal_id"));
				order.setDishId(rs.getInt("dish_id"));
				order.setDishPrice(rs.getInt("dish_price"));
				order.setDishQty(rs.getInt("dish_qty"));
				list.add(order);
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
	public boolean insertBatchOrdersToDB(List<OrderDetail> list, Integer totalPrice, Integer mealId) {
	
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			//1. insert orders into ORDER_DETAIL
			for(OrderDetail order : list) {
				pstmt.setInt(1, mealId);
				pstmt.setInt(2, order.getDishId());
				pstmt.setInt(3, order.getDishPrice());
				pstmt.setInt(4, order.getDishQty());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			
			/*
			4: ¤wµ²±b 
			 */
			//2. insert totalPrice and change status to BOOKING_INFO (paymentBill , booking_status)
			BookingInfoDao bkDao = new BookingInfoDaoImpl();
			int result = bkDao.updateBookingStatusAndTotalPrice(con, totalPrice, mealId);
			
			if(result > 0) {
				con.commit();
				con.setAutoCommit(true);
				return true;
			} 
			else {
				con.rollback();
				con.setAutoCommit(true);
				return false;
			}
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return false;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					return false;
				}
			}
		}
		return false;
	}

}
