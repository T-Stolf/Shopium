package shopium.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {
	
	private @Id @GeneratedValue Long OrderItemID;
	private Long userID;
	private Long orderID;
	
	public OrderItem() {}
	public OrderItem(Long orderItemID, Long userID, Long orderID) {
		super();
		OrderItemID = orderItemID;
		this.userID = userID;
		this.orderID = orderID;
	}
	
	public Long getOrderItemID() {
		return OrderItemID;
	}
	public void setOrderItemID(Long orderItemID) {
		OrderItemID = orderItemID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getOrderID() {
		return this.orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(orderID, OrderItemID, userID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(orderID, other.orderID) && Objects.equals(OrderItemID, other.OrderItemID)
				&& Objects.equals(userID, other.userID);
	}
	@Override
	public String toString() {
		return "OrderItems {OrderItemID=" + OrderItemID + ", UserID=" + userID + ", OrderID=" + orderID + "}";
	}
	
	
}
