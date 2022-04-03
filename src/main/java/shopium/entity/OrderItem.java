package shopium.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {
	
	private @Id @GeneratedValue Long OrderItemID;
	private Long UserID;
	private Long OrderID;
	
	public OrderItem() {}
	public OrderItem(Long orderItemID, Long userID, Long orderID) {
		super();
		OrderItemID = orderItemID;
		UserID = userID;
		OrderID = orderID;
	}
	
	public Long getOrderItemID() {
		return OrderItemID;
	}
	public void setOrderItemID(Long orderItemID) {
		OrderItemID = orderItemID;
	}
	public Long getUserID() {
		return UserID;
	}
	public void setUserID(Long userID) {
		UserID = userID;
	}
	public Long getOrderID() {
		return OrderID;
	}
	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(OrderID, OrderItemID, UserID);
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
		return Objects.equals(OrderID, other.OrderID) && Objects.equals(OrderItemID, other.OrderItemID)
				&& Objects.equals(UserID, other.UserID);
	}
	@Override
	public String toString() {
		return "OrderItems {OrderItemID=" + OrderItemID + ", UserID=" + UserID + ", OrderID=" + OrderID + "}";
	}
	
	
}
