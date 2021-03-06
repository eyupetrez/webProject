package com.aykan.domain.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
	@NamedQuery(name = "Department.findByDepartmentName", query = "SELECT d.departmentName FROM Department d"),
	@NamedQuery(name = "Department.findLocationAndEmployeesByDepartmentId", query = "SELECT d FROM Department d LEFT OUTER JOIN FETCH d.location "
			+ "LEFT OUTER JOIN FETCH d.employees WHERE d.departmentId = :departmentId")
})
public class Department {
	
	//-----------------------
	//department.getLocation() hata almam
	//--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long departmentId;
	
	@Column
	private String departmentName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locationId", foreignKey = @ForeignKey(foreignKeyDefinition = "location_fk"))
	private Location location;
	
	//----------------
	//department = sorgu.. 
	//Location location = department.getLocation() hata al�r�m. Session ac�k olsa bile.
	//----------------
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employees;
	
	
	public Department() {
	}

	public Department(String departmentName,Location location) {
		this.departmentName = departmentName;
		this.location = location;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		return true;
	}

	
}
