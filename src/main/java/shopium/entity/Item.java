package shopium.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Item {

	private @Id @GeneratedValue String IID;
	private String CID;
	private String IName;
	private String Photo;
	private String Description;
	private String Type;
	private int Price;
	private int Stock;
	
	public Item() {
	}

	public Item(String cID, String iName, String photo, String description, String type, int price,
			int stock) {
		super();
		CID = cID;
		IName = iName;
		Photo = photo;
		Description = description;
		Type = type;
		Price = price;
		Stock = stock;
	}

	public String getIID() {
		return IID;
	}

	public void setIID(String iID) {
		IID = iID;
	}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}

	public String getIName() {
		return IName;
	}

	public void setIName(String iName) {
		IName = iName;
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
		return Objects.hash(CID, Description, IID, IName, Photo, Price, Stock, Type);
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
		return Objects.equals(CID, other.CID) && Objects.equals(Description, other.Description)
				&& Objects.equals(IID, other.IID) && Objects.equals(IName, other.IName)
				&& Objects.equals(Photo, other.Photo) && Price == other.Price && Stock == other.Stock
				&& Objects.equals(Type, other.Type);
	}

	@Override
	public String toString() {
		return "Item{IID='" + IID + "', CID='" + CID + "', IName='" + IName + "', Photo='" + Photo + "', Description='"
				+ Description + "', Type='" + Type + "', Price='" + Price + "', Stock='" + Stock + "'}";
	}
	
	
	
}
