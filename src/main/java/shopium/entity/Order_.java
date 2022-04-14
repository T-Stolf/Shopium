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
	private LocalDateTime dateTime;
	private int Cost;
	private int ItemNum;
	private Status Status;

	
	public Order_() {}

	public Order_(Long uID, LocalDateTime dateTime, int cost, int itemNum, Status status) {
		super();

		this.userID = uID;
		this.dateTime = dateTime;
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
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		this.Cost = cost;
	}

	public int getItemNum() {
		return ItemNum;
	}

	public void setItems(int itemNum) {
		this.ItemNum = itemNum;
	}
	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		this.Status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Cost, ItemNum, OrderID, Status, dateTime, userID);
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
		return Cost == other.Cost && ItemNum == other.ItemNum && Objects.equals(OrderID, other.OrderID)
				&& Status == other.Status && Objects.equals(dateTime, other.dateTime)
				&& Objects.equals(userID, other.userID);
	}

	@Override
	public String toString() {
		return "Order_ [OrderID=" + OrderID + ", userID=" + userID + ", dateTime=" + dateTime + ", Cost=" + Cost
				+ ", ItemNum=" + ItemNum + ", Status=" + Status + "]";
	}
	
}
