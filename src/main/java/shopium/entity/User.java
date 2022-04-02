package shopium.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	private @Id @GeneratedValue Long UserID;
	private String FullName;
	private String UserName;
	private LocalDateTime DateTimeRegister;
	private String Address;
	
	
	public User() {}
	
	public User(Long userID, String fullName, String userName, LocalDateTime dateTimeRegister, String address) {
		super();
		UserID = userID;
		FullName = fullName;
		UserName = userName;
		DateTimeRegister = dateTimeRegister;
		Address = address;
	}
	
	public Long getUserID() {
		return UserID;
	}
	public void setUserID(Long userID) {
		UserID = userID;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
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
		return Objects.hash(Address, DateTimeRegister, FullName, UserID, UserName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(Address, other.Address) && Objects.equals(DateTimeRegister, other.DateTimeRegister)
				&& Objects.equals(FullName, other.FullName) && Objects.equals(UserID, other.UserID)
				&& Objects.equals(UserName, other.UserName);
	}

	@Override
	public String toString() {
		return "User{UserID=" + UserID + ", FullName='" + FullName + "', UserName='" + UserName + "', DateTimeRegister='"
				+ DateTimeRegister + "', Address='" + Address + "'}";
	}
	
	

	
}
