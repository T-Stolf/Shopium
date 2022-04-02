package shopium.depricated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Creator {
	
	private @Id @GeneratedValue Long CID;
	private String UserName;
	private String Password;
	private ArrayList<String> Items;
	private LocalDateTime DateTimeRegister;
	private String Address;
	
	public Creator() {
		super();
	}
	public Creator(String userName, String password, ArrayList<String> items,
			LocalDateTime dateTimeRegister, String address) {
		super();
		UserName = userName;
		Password = password;
		Items = items;
		DateTimeRegister = dateTimeRegister;
		Address = address;
	}
	public Long getCID() {
		return CID;
	}
	public void setCID(Long cID) {
		CID = cID;
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
	public ArrayList<String> getItems() {
		return Items;
	}
	public void setItems(ArrayList<String> items) {
		Items = items;
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
		return Objects.hash(Address, CID, DateTimeRegister, Items, Password, UserName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Creator other = (Creator) obj;
		return Objects.equals(Address, other.Address) && Objects.equals(CID, other.CID)
				&& Objects.equals(DateTimeRegister, other.DateTimeRegister) && Objects.equals(Items, other.Items)
				&& Objects.equals(Password, other.Password) && Objects.equals(UserName, other.UserName);
	}
	@Override
	public String toString() {
		return "Creator{CID=" + CID + ", UserName='" + UserName + "', Password='" + Password + "', Items='" + Items
				+ "', DateTimeRegister='" + DateTimeRegister + "', Address='" + Address + "'}";
	}
	
	
	
}
