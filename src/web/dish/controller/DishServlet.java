package web.dish.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import web.dish.service.DishService;
import web.dish.service.impl.DishServiceImpl;
import web.dish.vo.Dish;

@WebServlet("/dish/DishServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class DishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getItem_for_update".equals(action)) { // 來自listAllDish.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer dishid = new Integer(req.getParameter("dishid"));
				
				/***************************2.開始查詢資料****************************************/
				DishService service = new DishServiceImpl();
				Dish dishVO = service.getDishByKey(dishid);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("dishVO", dishVO);
				String url = "/dish/update_dish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_dish.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/dish/listAllDish.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_dish.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer dishid = new Integer(req.getParameter("dishid").trim());
				
				String dishName = req.getParameter("dishName");
				String dishNameReg = "^[(\u4e00-\u9fa5)]{1,10}$";
				if (dishName == null || dishName.trim().length() == 0) {
					errorMsgs.add("菜餚名稱: 請勿空白");
				} else if(!dishName.trim().matches(dishNameReg)) {
					errorMsgs.add("菜餚名稱: 只能是中文字 , 且長度必需在1到10之間");
	            }
	
				Integer dishPrice = null;
				try {
					dishPrice = new Integer(req.getParameter("dishPrice").trim());
				} catch (NumberFormatException e) {
					dishPrice = 0;
					errorMsgs.add("請填數字.");
				}
				
				String dishIntro = req.getParameter("dishIntro").trim();
				if (dishIntro == null || dishIntro.trim().length() == 0) {
					errorMsgs.add("菜餚介紹: 請勿空白");
				}
				
				Integer dishType = new Integer(req.getParameter("dishType"));
				Integer dishStatus = new Integer(req.getParameter("dishStatus"));
				
				Dish dishVO = new Dish();
				dishVO.setDishId(dishid);
				dishVO.setDishName(dishName);
				dishVO.setDishPrice(dishPrice);
				dishVO.setDishIntro(dishIntro);
				dishVO.setDishType(dishType);
				dishVO.setDishStatus(dishStatus);
				
				Part dishPicture = req.getPart("uploadFile");
				DishService dishSvc = new DishServiceImpl();
				byte[] buf = null;
				
				String filename = getFileNameFromPart(dishPicture);
				if (filename!= null && dishPicture.getContentType()!=null) { //有圖直接加入VO傳送
					InputStream in = dishPicture.getInputStream();
					buf = new byte[in.available()];
					in.read(buf);
					in.close();
				} else { //沒有圖，從DB抓舊圖填裝VO
					Dish dishVOfromServer = dishSvc.getDishByKey(dishid);
					buf = dishVOfromServer.getDishPicture();
				}
				dishVO.setDishPicture(buf);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dishVO", dishVO); // 含有輸入格式錯誤的dishVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/dish/update_dish.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				boolean result = dishSvc.updateDish(dishVO);
				
				if(result) {
					req.setAttribute("dishVO", dishVO);
					String url = "/dish/listAllDish.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
				}
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dish/update_dish.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllDish.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer dishid = new Integer(req.getParameter("dishid").trim());
				/***************************2.開始刪除資料***************************************/
				DishService dishSvc = new DishServiceImpl();
				dishSvc.deleteDish(dishid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/dish/listAllDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/dish/listAllDish.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) { // 來自addDish.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String dishName = req.getParameter("dishName");
				String dishNameReg = "^[(\u4e00-\u9fa5)]{1,10}$";
				if (dishName == null || dishName.trim().length() == 0) {
					errorMsgs.add("菜餚名稱: 請勿空白");
				} else if(!dishName.trim().matches(dishNameReg)) {
					errorMsgs.add("菜餚名稱: 只能是中文字 , 且長度必需在1到10之間");
	            }
				
				Integer dishPrice = null;
				try {
					dishPrice = new Integer(req.getParameter("dishPrice").trim());
				} catch (NumberFormatException e) {
					dishPrice = 0;
					errorMsgs.add("菜餚價格: 請填數字.");
				}
				
				String dishIntro = req.getParameter("dishIntro").trim();
				if (dishIntro == null || dishIntro.trim().length() == 0) {
					errorMsgs.add("菜餚介紹: 請勿空白");
				}
				
				Integer dishType = new Integer(req.getParameter("dishType"));
				Integer dishStatus = new Integer(req.getParameter("dishStatus"));

				Dish dishVO = new Dish();
				dishVO.setDishName(dishName);
				dishVO.setDishPrice(dishPrice);
				dishVO.setDishIntro(dishIntro);
				dishVO.setDishType(dishType);
				dishVO.setDishStatus(dishStatus);
				
				Part dishPicture = req.getPart("uploadFile");
				String filename = getFileNameFromPart(dishPicture);
				if (filename!= null && dishPicture.getContentType()!=null) {
					InputStream in = dishPicture.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					dishVO.setDishPicture(buf);
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dishVO", dishVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/dish/addDish.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DishService dishSvc = new DishServiceImpl();
				boolean result = dishSvc.addDish(dishVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				if(result) {
					String url = "/dish/listAllDish.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/dish/addDish.jsp");
				failureView.forward(req, res);
			}
		}
		
//		DishService service = new DishServiceImpl();
//		try {
//			//test add member
//			Dish dish = new Dish();
//			dish.setDishName("竹筍炒肉絲");
//			dish.setDishPrice(120);
//			dish.setDishIntro("小孩的夢魘");
//			dish.setDishType(0);
//			dish.setDishStatus(2);
//			dish.setDishPicture(service.getPictureByteArray(getServletContext().getRealPath("images/1.png")));

			//test update member
//			Dish dish = new Dish();
//			dish.setDishId(19);
//			dish.setDishName("竹筍炒肉絲");
//			dish.setDishPrice(120);
//			dish.setDishIntro("只是一道菜");
//			dish.setDishType(2);
//			dish.setDishStatus(4);
//			dish.setDishPicture(service.getPictureByteArray(getServletContext().getRealPath("items/FC_Barcelona.png")));

//			boolean result = service.addDish(dish);
//			boolean result = service.updateDish(dish);
//			boolean result = service.deleteDish(new Integer(19));
//			res.getWriter().append(result+"");
			
			//select one
//			Dish dish = service.getDishByKey(new Integer(8));
//			if(dish != null) {
//				res.getWriter().append(dish.toString());
//			}
			
			//select all
//			StringBuilder sb = new StringBuilder();
//			List<Dish> list = service.getAll();
//			Consumer<Dish> dishConsumer = p -> {
//				sb.append(p.toString());
//				sb.append("<br>");
//			};
//			list.stream().forEach(dishConsumer);
//			res.getWriter().append(sb);
			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		//getName()是為了IE而使用的，因為它會將選取的檔案路徑整個放進來
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
