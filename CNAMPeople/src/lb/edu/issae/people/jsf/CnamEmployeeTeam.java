package lb.edu.issae.people.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="team", eager=true)
@SessionScoped
public class CnamEmployeeTeam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String EMPLOYEES_FILE ="employees.txt";
	private final String SEPARATOR =";";
	
	private Employee employee;
	private List<Employee> employeeList = new ArrayList<Employee>();
	private FileUtils file;

	public CnamEmployeeTeam() {
		init();
	}

	public void init() {
		file =new FileUtils(EMPLOYEES_FILE,SEPARATOR);
		setUpEmployeeList();
	}
	
	public void setUpEmployeeList() {
		List<String> line = file.getDataList();
		
		Iterator<String> itr = line.iterator();
		
		while(itr.hasNext()) {
			String[] data = line.toString().split(SEPARATOR);
			int i = 0;
			
			employee = new Employee();
			
			employee.setID(Integer.parseInt(data[i++]));
			employee.setFirstName(data[i++]);
			employee.setLastName(data[i++]);
			employee.setDegree(data[i++]);
			employee.setTitle(data[i++]);
			employee.setPhoto(data[i++]);
			employee.setDescription(data[i++]);
			
			//Add to employeeList
			employeeList.add(employee);
		}
		//re initialize employee object 
		employee = new Employee();		
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

	public String getSEPARATOR() {
		return SEPARATOR;
	}

	public String getEMPLOYEES_FILE() {
		return EMPLOYEES_FILE;
	}

	public FileUtils getFile() {
		return file;
	}

	public void setFile(FileUtils file) {
		this.file = file;
	}

}
