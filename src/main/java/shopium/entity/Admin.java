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
	private String userName;
	private String Password;
	private LocalDateTime DateTimeRegister;
	private String Address;
	
	public Admin() {}

	public Admin(String password, String fullname, String userName, LocalDateTime dateTimeRegister, String address) {
		super();
		Fullname = fullname;
		this.Password = password;
		this.userName = userName;
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

	public String getuserName() {
		return userName;
	}
	
//	***
	public String getPassword(){
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
//	***
	
	public void setuserName(String userName) {
		this.userName = userName;
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
		return Objects.hash(Address, AdminID, DateTimeRegister, Fullname, userName);
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
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "Admin {AdminID=" + AdminID + ", Fullname='" + Fullname + "', userName='" + userName + "', DateTimeRegister='"
				+ DateTimeRegister + "', Address='" + Address + "'}";
	}
	
	
}