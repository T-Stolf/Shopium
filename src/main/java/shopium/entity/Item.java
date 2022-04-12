package shopium.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

	private @Id @GeneratedValue Long ItemID;
<<<<<<< HEAD
	private Long userID;
	private String ItemName;
=======
	private Long UserID;
	private String itemName;
>>>>>>> refs/remotes/origin/SyedBranch
	private String Photo;
	private String description;
	private int Price;
	private int Stock;
	
	public Item() {
	}

	public Item(Long userID, String iName, String photo, String description, int price,
			int stock) {
		super();
<<<<<<< HEAD
		this.userID = userID;
		ItemName = iName;
=======
		UserID = userID;
		itemName = iName;
>>>>>>> refs/remotes/origin/SyedBranch
		Photo = photo;
		description = description;
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
<<<<<<< HEAD
		return Objects.hash(userID, Description, ItemID, ItemName, Photo, Price, Stock);
=======
		return Objects.hash(UserID, description, ItemID, itemName, Photo, Price, Stock);
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
		Item other = (Item) obj;
<<<<<<< HEAD
		return Objects.equals(userID, other.userID) && Objects.equals(Description, other.Description)
				&& Objects.equals(ItemID, other.ItemID) && Objects.equals(ItemName, other.ItemName)
=======
		return Objects.equals(UserID, other.UserID) && Objects.equals(description, other.description)
				&& Objects.equals(ItemID, other.ItemID) && Objects.equals(itemName, other.itemName)
>>>>>>> refs/remotes/origin/SyedBranch
				&& Objects.equals(Photo, other.Photo) && Price == other.Price && Stock == other.Stock;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Item{ItemID=" + ItemID + "', UserID=" + userID + "', ItemName='" + ItemName + "', Photo='" + Photo + "', Description='"
				+ Description + "', Price='" + Price + "', Stock='" + Stock + "'}";
=======
		return "Item{ItemID=" + ItemID + "', UserID=" + UserID + "', itemName='" + itemName + "', Photo='" + Photo + "', description='"
				+ description + "', Price='" + Price + "', Stock='" + Stock + "'}";
>>>>>>> refs/remotes/origin/SyedBranch
	}
	
	
	
}
