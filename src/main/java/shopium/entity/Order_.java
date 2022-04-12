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

<<<<<<< HEAD
	private @Id @GeneratedValue Long OrderID;
	private Long userID;
	private LocalDateTime DateTime;
	private int Cost;
	private int ItemNum;
	private Status Status;
=======
	private @Id @GeneratedValue Long orderID;
	private Long userID;
	private LocalDateTime dateTime;
	private int cost;
	private int itemNum;
	private Status status;
>>>>>>> refs/remotes/origin/SyedBranch
	
	public Order_() {}

	public Order_(Long uID, LocalDateTime dateTime, int cost, int itemNum, Status status) {
		super();
<<<<<<< HEAD
		this.userID = uID;
		DateTime = dateTime;
		Cost = cost;
		ItemNum = itemNum;
		Status = status;
=======
		userID = uID;
		dateTime = dateTime;
		cost = cost;
		itemNum = itemNum;
		status = status;
>>>>>>> refs/remotes/origin/SyedBranch
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long oID) {
		orderID = oID;
	}

	public Long getUserID() {
<<<<<<< HEAD
		return this.userID;
=======
		return userID;
>>>>>>> refs/remotes/origin/SyedBranch
	}

	public void setUserID(Long uID) {
<<<<<<< HEAD
		this.userID = uID;
=======
		userID = uID;
>>>>>>> refs/remotes/origin/SyedBranch
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
<<<<<<< HEAD
		return Objects.hash(Cost, DateTime, ItemNum, OrderID, Status, userID);
=======
		return Objects.hash(cost, dateTime, itemNum, orderID, status, userID);
>>>>>>> refs/remotes/origin/SyedBranch
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
<<<<<<< HEAD
		return Cost == other.Cost && Objects.equals(DateTime, other.DateTime) && Objects.equals(ItemNum, other.ItemNum)
				&& Objects.equals(OrderID, other.OrderID) && Status == other.Status && Objects.equals(userID, other.userID);
=======
		return cost == other.cost && Objects.equals(dateTime, other.dateTime) && Objects.equals(itemNum, other.itemNum)
				&& Objects.equals(orderID, other.orderID) && status == other.status && Objects.equals(userID, other.userID);
>>>>>>> refs/remotes/origin/SyedBranch
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Creator{OrderID=" + OrderID + ", UserID='" + userID + "', DateTime='" + DateTime + ", 'Status='" + Status + "', Cost='" + Cost + "', ItemNum="
				+ ItemNum + "}";
=======
		return "Creator{OrderID=" + orderID + ", UserID='" + userID + "', DateTime='" + dateTime + ", 'Status='" + status + "', Cost='" + cost + "', ItemNum="
				+ itemNum + "}";
>>>>>>> refs/remotes/origin/SyedBranch
	}
	
}
