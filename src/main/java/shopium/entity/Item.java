package shopium.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

	private @Id @GeneratedValue Long ItemID;
	private Long UserID;
	private String ItemName;
	private String Photo;
	private String Description;
	private int Price;
	private int Stock;
	
	public Item() {
	}

	public Item(Long userID, String iName, String photo, String description, int price,
			int stock) {
		super();
		UserID = userID;
		ItemName = iName;
		Photo = photo;
		Description = description;
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
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
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
		return Objects.hash(UserID, Description, ItemID, ItemName, Photo, Price, Stock);
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
		return Objects.equals(UserID, other.UserID) && Objects.equals(Description, other.Description)
				&& Objects.equals(ItemID, other.ItemID) && Objects.equals(ItemName, other.ItemName)
				&& Objects.equals(Photo, other.Photo) && Price == other.Price && Stock == other.Stock;
	}

	@Override
	public String toString() {
		return "Item{ItemID=" + ItemID + "', UserID=" + UserID + "', ItemName='" + ItemName + "', Photo='" + Photo + "', Description='"
				+ Description + "', Price='" + Price + "', Stock='" + Stock + "'}";
	}
	
	
	
}
