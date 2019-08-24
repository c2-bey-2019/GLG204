package lb.edu.issae.people.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="team", eager=true)
@SessionScoped
public class CnamEmployeeTeam {
	
	private Employee employee;
	private List<Employee> employeeList = new ArrayList<Employee>();

	public CnamEmployeeTeam() {
		// TODO Auto-generated constructor stub
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
