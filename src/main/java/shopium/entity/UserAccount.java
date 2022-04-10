package shopium.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserAccount {

	private @Id @GeneratedValue Long UserID;
	private String FullName;
	private String userName;
	private String Password;
	private LocalDateTime DateTimeRegister;
	private String Address;
	private String Role;
	
	public UserAccount() {}
	
	public UserAccount(String password, String fullName, String userName, LocalDateTime dateTimeRegister, String address, String role) {
		super();
		FullName = fullName;
		Password = password;
		this.userName = userName;
		DateTimeRegister = dateTimeRegister;
		Address = address;
		Role = role;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
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
		return userName;
	}
	public void setUserName(String userName) {
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

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(Address, DateTimeRegister, FullName, Password, Role, UserID, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		return Objects.equals(Address, other.Address) && Objects.equals(DateTimeRegister, other.DateTimeRegister)
				&& Objects.equals(FullName, other.FullName) && Objects.equals(Password, other.Password)
				&& Objects.equals(Role, other.Role) && Objects.equals(UserID, other.UserID)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User{UserID=" + UserID + ", FullName='" + FullName + "', UserName='" + userName + ", Password='" + Password + "', DateTimeRegister='"
				+ DateTimeRegister + "', Address='" + Address + "', Role='" + Role + "'}";
	}
	
	

	
}
