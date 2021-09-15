package web.employee.service;

import java.util.List;

import web.employee.dao.EmployeeDAO_interface;
import web.employee.dao.impl.EmployeeJDBCDAO;
import web.employee.vo.EmployeeVO;

public class EmployeeService {
	
	private EmployeeDAO_interface dao;
	
	public EmployeeService() {
		dao = new EmployeeJDBCDAO();
	}
	public EmployeeVO addEmp(String emp_name,String id_number,String emp_phone,String emp_mail,String password,java.sql.Date hiredate) {
		
		EmployeeVO empVO = new EmployeeVO();
		
		empVO.setEmp_name(emp_name);
		empVO.setId_number(id_number);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_mail(emp_mail);
		empVO.setPassword(password);
		empVO.setHiredate(hiredate);
		dao.insert(empVO);
		
		return empVO;
	}
	
	public EmployeeVO updateEmp(String emp_name,String id_number,String emp_phone,String emp_mail,String password,java.sql.Date hiredate,
			Integer emp_id) {
		
		EmployeeVO empVO = new EmployeeVO();
		
		empVO.setEmp_name(emp_name);
		empVO.setId_number(id_number);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_mail(emp_mail);
		empVO.setPassword(password);
		empVO.setHiredate(hiredate);
		empVO.setEmp_id(emp_id);
		dao.update(empVO);
		
		return empVO;
	}
	
	public void deleteEmp(Integer emp_id) {
		dao.delete(emp_id);
	}

	public EmployeeVO getOneEmp(Integer emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
	public EmployeeVO findByEMPMAIL(String emp_mail) {
		return dao.findByEMPMAIL(emp_mail);
	}
}
