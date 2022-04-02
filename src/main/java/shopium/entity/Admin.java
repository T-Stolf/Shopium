package shopium.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Admin {
	
	private @Id @GeneratedValue Long AdminID;
	private String Fullname;
	private String UserName;
//	private String Password;
	private LocalDateTime DateTimeRegister;
	private String Address;
	
	public Admin() {}

	public Admin(Long adminID, String fullname, String userName, LocalDateTime dateTimeRegister, String address) {
		super();
		AdminID = adminID;
		Fullname = fullname;
		UserName = userName;
		DateTimeRegister = dateTimeRegister;
		Address = address;
	}

	public Long getAdminID() {
		return AdminID;
	}

	public void setAdminID(Long adminID) {
		AdminID = adminID;
	}

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public LocalDateTime getDateTimeRegister() {
		return DateTimeRegister;
	}

	public void setDateTimeRegister(LocalDateTime dateTimeRegister) {
		DateTimeRegister = dateTimeRegister;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Address, AdminID, DateTimeRegister, Fullname, UserName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(Address, other.Address) && Objects.equals(AdminID, other.AdminID)
				&& Objects.equals(DateTimeRegister, other.DateTimeRegister) && Objects.equals(Fullname, other.Fullname)
				&& Objects.equals(UserName, other.UserName);
	}

	@Override
	public String toString() {
		return "Admin {AdminID=" + AdminID + ", Fullname='" + Fullname + "', UserName='" + UserName + "', DateTimeRegister='"
				+ DateTimeRegister + "', Address='" + Address + "'}";
	}
	
	
}