package shopium.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Admin {
	
	private @Id @GeneratedValue Long AID;
	private String Fullname;
	private String Password;
	private LocalDateTime DateTimeRegister;
	private String Address;
	
	public Admin() {}
	
	public Admin( String fullname, String password, LocalDateTime dateTimeRegister, String address) {
		super();
		Fullname = fullname;
		Password = password;
		DateTimeRegister = dateTimeRegister;
		Address = address;
	}
	
	public Long getAID() {
		return AID;
	}
	public void setAID(Long aID) {
		AID = aID;
	}
	public String getFullname() {
		return Fullname;
	}
	public void setFullname(String fullname) {
		Fullname = fullname;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
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
		return Objects.hash(AID, Address, DateTimeRegister, Fullname, Password);
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
		return Objects.equals(AID, other.AID) && Objects.equals(Address, other.Address)
				&& Objects.equals(DateTimeRegister, other.DateTimeRegister) && Objects.equals(Fullname, other.Fullname)
				&& Objects.equals(Password, other.Password);
	}
	@Override
	public String toString() {
		return "Admin{AID=" + AID + ", Fullname='" + Fullname + "', Password='" + Password + "', DateTimeRegister='"
				+ DateTimeRegister + "', Address='" + Address + "'}";
	}

}
