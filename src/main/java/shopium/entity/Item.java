package shopium.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

	private @Id @GeneratedValue Long ItemID;

	private Long userID;
	private String itemName;
	private String Photo;
	private String description;
	private int Price;
	private int Stock;
	
	public Item() {
	}

	public Item(Long userID, String iName, String photo, String description, int price,
			int stock) {
		super();

		this.userID = userID;
		itemName = iName;
		Photo = photo;
		this.description = description;
		Price = price;
		Stock = stock;
	}

	public Long getItemID() {
		return ItemID;
	}

	public void setItemID(Long itemID) {
		ItemID = itemID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ItemID, Photo, Price, Stock, description, itemName, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(ItemID, other.ItemID) && Objects.equals(Photo, other.Photo) && Price == other.Price
				&& Stock == other.Stock && Objects.equals(description, other.description)
				&& Objects.equals(itemName, other.itemName) && Objects.equals(userID, other.userID);
	}

	@Override
	public String toString() {
		return "Item [ItemID=" + ItemID + ", userID=" + userID + ", itemName=" + itemName + ", Photo=" + Photo
				+ ", description=" + description + ", Price=" + Price + ", Stock=" + Stock + "]";
	}


	
	
}
