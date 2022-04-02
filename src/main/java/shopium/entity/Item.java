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
	private String Type;
	private int Price;
	private int Stock;
	
	public Item() {
	}

	public Item(Long cID, String iName, String photo, String description, String type, int price,
			int stock) {
		super();
		UserID = cID;
		ItemName = iName;
		Photo = photo;
		Description = description;
		Type = type;
		Price = price;
		Stock = stock;
	}

	public Long getItemID() {
		return ItemID;
	}

	public void setItemID(Long iID) {
		ItemID = iID;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long cID) {
		UserID = cID;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String iName) {
		ItemName = iName;
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

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
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
		return Objects.hash(UserID, Description, ItemID, ItemName, Photo, Price, Stock, Type);
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
				&& Objects.equals(Photo, other.Photo) && Price == other.Price && Stock == other.Stock
				&& Objects.equals(Type, other.Type);
	}

	@Override
	public String toString() {
		return "Item{ItemID=" + ItemID + "', UserID=" + UserID + "', ItemName='" + ItemName + "', Photo='" + Photo + "', Description='"
				+ Description + "', Type='" + Type + "', Price='" + Price + "', Stock='" + Stock + "'}";
	}
	
	
	
}
