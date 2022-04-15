package shopium.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {
	
	private @Id @GeneratedValue Long OrderItemID;
	private Long orderID;
	private Long itemID;
	
	public OrderItem() {}
	public OrderItem(Long orderItemID, Long orderID, Long itemID) {
		super();
		OrderItemID = orderItemID;
		this.orderID = orderID;
		this.itemID = itemID;
	}
	
	public Long getOrderItemID() {
		return OrderItemID;
	}
	public void setOrderItemID(Long orderItemID) {
		OrderItemID = orderItemID;
	}
	public Long getOrderID() {
		return this.orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	public Long getItemID() {
		return itemID;
	}
	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(OrderItemID, itemID, orderID);
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
		return Objects.equals(OrderItemID, other.OrderItemID) && Objects.equals(itemID, other.itemID)
				&& Objects.equals(orderID, other.orderID);
	}
	@Override
	public String toString() {
		return "OrderItem [OrderItemID=" + OrderItemID  + ", orderID=" + orderID + ", itemID="
				+ itemID + "]";
	}
	
	
}
