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

	private @Id @GeneratedValue Long orderID;
	private Long userID;
	private LocalDateTime dateTime;
	private int cost;
	private int itemNum;
	private Status status;
	
	public Order_() {}

	public Order_(Long uID, LocalDateTime dateTime, int cost, int itemNum, Status status) {
		super();
		userID = uID;
		dateTime = dateTime;
		cost = cost;
		itemNum = itemNum;
		status = status;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long oID) {
		orderID = oID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long uID) {
		userID = uID;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItems(int itemNum) {
		this.itemNum = itemNum;
	}
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cost, dateTime, itemNum, orderID, status, userID);
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
		return cost == other.cost && Objects.equals(dateTime, other.dateTime) && Objects.equals(itemNum, other.itemNum)
				&& Objects.equals(orderID, other.orderID) && status == other.status && Objects.equals(userID, other.userID);
	}

	@Override
	public String toString() {
		return "Creator{OrderID=" + orderID + ", UserID='" + userID + "', DateTime='" + dateTime + ", 'Status='" + status + "', Cost='" + cost + "', ItemNum="
				+ itemNum + "}";
	}
	
}
