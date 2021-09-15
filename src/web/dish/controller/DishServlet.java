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
		
		if ("getItem_for_update".equals(action)) { // �Ӧ�listAllDish.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer dishid = new Integer(req.getParameter("dishid"));
				
				/***************************2.�}�l�d�߸��****************************************/
				DishService service = new DishServiceImpl();
				Dish dishVO = service.getDishByKey(dishid);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("dishVO", dishVO);
				String url = "/dish/update_dish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_dish.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/dish/listAllDish.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_dish.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer dishid = new Integer(req.getParameter("dishid").trim());
				
				String dishName = req.getParameter("dishName");
				String dishNameReg = "^[(\u4e00-\u9fa5)]{1,10}$";
				if (dishName == null || dishName.trim().length() == 0) {
					errorMsgs.add("���a�W��: �ФŪť�");
				} else if(!dishName.trim().matches(dishNameReg)) {
					errorMsgs.add("���a�W��: �u��O����r , �B���ץ��ݦb1��10����");
	            }
	
				Integer dishPrice = null;
				try {
					dishPrice = new Integer(req.getParameter("dishPrice").trim());
				} catch (NumberFormatException e) {
					dishPrice = 0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				String dishIntro = req.getParameter("dishIntro").trim();
				if (dishIntro == null || dishIntro.trim().length() == 0) {
					errorMsgs.add("���a����: �ФŪť�");
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
				if (filename!= null && dishPicture.getContentType()!=null) { //���Ϫ����[�JVO�ǰe
					InputStream in = dishPicture.getInputStream();
					buf = new byte[in.available()];
					in.read(buf);
					in.close();
				} else { //�S���ϡA�qDB���¹϶��VO
					Dish dishVOfromServer = dishSvc.getDishByKey(dishid);
					buf = dishVOfromServer.getDishPicture();
				}
				dishVO.setDishPicture(buf);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dishVO", dishVO); // �t����J�榡���~��dishVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/dish/update_dish.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				boolean result = dishSvc.updateDish(dishVO);
				
				if(result) {
					req.setAttribute("dishVO", dishVO);
					String url = "/dish/listAllDish.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
					successView.forward(req, res);
				}
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dish/update_dish.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // �Ӧ�listAllDish.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer dishid = new Integer(req.getParameter("dishid").trim());
				/***************************2.�}�l�R�����***************************************/
				DishService dishSvc = new DishServiceImpl();
				dishSvc.deleteDish(dishid);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				String url = "/dish/listAllDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/dish/listAllDish.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) { // �Ӧ�addDish.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/

				String dishName = req.getParameter("dishName");
				String dishNameReg = "^[(\u4e00-\u9fa5)]{1,10}$";
				if (dishName == null || dishName.trim().length() == 0) {
					errorMsgs.add("���a�W��: �ФŪť�");
				} else if(!dishName.trim().matches(dishNameReg)) {
					errorMsgs.add("���a�W��: �u��O����r , �B���ץ��ݦb1��10����");
	            }
				
				Integer dishPrice = null;
				try {
					dishPrice = new Integer(req.getParameter("dishPrice").trim());
				} catch (NumberFormatException e) {
					dishPrice = 0;
					errorMsgs.add("���a����: �ж�Ʀr.");
				}
				
				String dishIntro = req.getParameter("dishIntro").trim();
				if (dishIntro == null || dishIntro.trim().length() == 0) {
					errorMsgs.add("���a����: �ФŪť�");
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
				
				/***************************2.�}�l�s�W���***************************************/
				DishService dishSvc = new DishServiceImpl();
				boolean result = dishSvc.addDish(dishVO);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
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
//			dish.setDishName("�˵����׵�");
//			dish.setDishPrice(120);
//			dish.setDishIntro("�p�Ī����L");
//			dish.setDishType(0);
//			dish.setDishStatus(2);
//			dish.setDishPicture(service.getPictureByteArray(getServletContext().getRealPath("images/1.png")));

			//test update member
//			Dish dish = new Dish();
//			dish.setDishId(19);
//			dish.setDishName("�˵����׵�");
//			dish.setDishPrice(120);
//			dish.setDishIntro("�u�O�@�D��");
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
		//getName()�O���FIE�ӨϥΪ��A�]�����|�N������ɮ׸��|��ө�i��
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
