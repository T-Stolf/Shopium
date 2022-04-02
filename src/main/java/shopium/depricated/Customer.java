package shopium.depricated;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	
	private @Id @GeneratedValue Long UID;
	private String FullName;
	private String UserName;
	private String Password;
	private LocalDateTime DateTimeRegister;
	private String Address;
	
	public Customer() {
	}

	public Customer(String fullName, String userName, String password, LocalDateTime dateTimeRegister,
			String address) {
		super();
		FullName = fullName;
		UserName = userName;
		Password = password;
		DateTimeRegister = dateTimeRegister;
		Address = address;
	}

	public Long getUID() {
		return UID;
	}

	public void setUID(Long uID) {
		UID = uID;
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
		return Objects.hash(Address, DateTimeRegister, FullName, Password, UID, UserName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(Address, other.Address) && Objects.equals(DateTimeRegister, other.DateTimeRegister)
				&& Objects.equals(FullName, other.FullName) && Objects.equals(Password, other.Password)
				&& Objects.equals(UID, other.UID) && Objects.equals(UserName, other.UserName);
	}

	@Override
	public String toString() {
		return "Creator{UID=" + UID + ", FullName='" + FullName + "', UserName='" + UserName + "', Password='" + Password
				+ "', DateTimeRegister='" + DateTimeRegister + "', Address='" + Address + "'}";

	}
}
