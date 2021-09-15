package web.employee.dao;

import java.util.List;

import web.employee.vo.EmployeeVO;

public interface EmployeeDAO_interface {
	
    public void insert(EmployeeVO empVO);
    public void update(EmployeeVO empVO);
    public void delete(Integer emp_id);
    public EmployeeVO findByPrimaryKey(Integer emp_id);
    public List<EmployeeVO> getAll();
    public EmployeeVO findByEMPMAIL(String emp_mail);
}
