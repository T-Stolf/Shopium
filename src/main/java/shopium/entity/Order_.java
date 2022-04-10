package shopium.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order_ {

	private @Id @GeneratedValue Long OrderID;
	private Long userID;
	private LocalDateTime DateTime;
	private int Cost;
	private int ItemNum;
	private Status Status;
	
	public Order_() {}

	public Order_(Long uID, LocalDateTime dateTime, int cost, int itemNum, Status status) {
		super();
		this.userID = uID;
		DateTime = dateTime;
		Cost = cost;
		ItemNum = itemNum;
		Status = status;
	}

	public Long getOrderID() {
		return OrderID;
	}

	public void setOrderID(Long oID) {
		OrderID = oID;
	}

	public Long getUserID() {
		return this.userID;
	}

	public void setUserID(Long uID) {
		this.userID = uID;
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

	public void setItems(int itemNum) {
		ItemNum = itemNum;
	}
	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Cost, DateTime, ItemNum, OrderID, Status, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order_ other = (Order_) obj;
		return Cost == other.Cost && Objects.equals(DateTime, other.DateTime) && Objects.equals(ItemNum, other.ItemNum)
				&& Objects.equals(OrderID, other.OrderID) && Status == other.Status && Objects.equals(userID, other.userID);
	}

	@Override
	public String toString() {
		return "Creator{OrderID=" + OrderID + ", UserID='" + userID + "', DateTime='" + DateTime + ", 'Status='" + Status + "', Cost='" + Cost + "', ItemNum="
				+ ItemNum + "}";
	}
	
}
