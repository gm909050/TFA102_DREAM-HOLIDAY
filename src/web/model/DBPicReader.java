package web.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/dish/DBPicReader")
public class DBPicReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/png");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String id = req.getParameter("dishId").trim();
			ResultSet rs = stmt.executeQuery(
				"SELECT dish_picture FROM DISH_INFO WHERE dish_id = " + id);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("dish_picture"));
				//資料庫讀取出來是一節一節的，不能用available來承接，跟上傳資料庫不同
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else { //id=4
				//res.sendError(HttpServletResponse.SC_NOT_FOUND); //404
				InputStream in = getServletContext().getResourceAsStream("/images/none2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				in.close();
				out.write(b);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) { //id=null
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/images/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			in.close();
			out.write(b);
		}
	}

	public void init() throws ServletException {
		try { //連線的重複使用，適用於查詢
			Context ctx = new javax.naming.InitialContext();
			//對應到server的 context.xml 的連線帳密
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/peter");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
