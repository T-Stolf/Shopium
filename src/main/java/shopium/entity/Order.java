package shopium.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Order {
	private @Id @GeneratedValue String OID;
	private String UID;
	private LocalDateTime DateTime;
	private int Cost;
	private int ItemNum;
	
	public Order() {}

	public Order(String uID, LocalDateTime dateTime, int cost, int itemNum) {
		super();
		UID = uID;
		DateTime = dateTime;
		Cost = cost;
		ItemNum = itemNum;
	}

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public LocalDateTime getDateTime() {
		return DateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		DateTime = dateTime;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}

	public int getItemNum() {
		return ItemNum;
	}

	public void setItemNum(int itemNum) {
		ItemNum = itemNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Cost, DateTime, ItemNum, OID, UID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Cost == other.Cost && Objects.equals(DateTime, other.DateTime) && ItemNum == other.ItemNum
				&& Objects.equals(OID, other.OID) && Objects.equals(UID, other.UID);
	}

	@Override
	public String toString() {
		return "Creator{OID='" + OID + "', UID='" + UID + "', DateTime='" + DateTime + "', Cost='" + Cost + "', ItemNum='"
				+ ItemNum + "'}";
	}
	
}
