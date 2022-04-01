package shopium.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

	private @Id @GeneratedValue Long IID;
	private String CID;
	private String name;
	private String Photo;
	private String Description;
	private String Type;
	private int Price;
	private int Stock;
	
	public Item() {
	}

	public Item(String cID, String name, String photo, String description, String type, int price,
			int stock) {
		super();
		this.CID = cID;
		this.name = name;
		this.Photo = photo;
		this.Description = description;
		this.Type = type;
		this.Price = price;
		this.Stock = stock;
	}

	public Long getIID() {
		return IID;
	}

	public void setIID(Long iID) {
		this.IID = iID;
	}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		this.CID = cID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		this.Photo = photo;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		this.Type = type;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		this.Price = price;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		this.Stock = stock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CID, Description, IID, name, Photo, Price, Stock, Type);
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
				&& Objects.equals(IID, other.IID) && Objects.equals(name, other.name)
				&& Objects.equals(Photo, other.Photo) && Price == other.Price && Stock == other.Stock
				&& Objects.equals(Type, other.Type);
	}

	@Override
	public String toString() {
		return "Item{IID=" + IID + "', CID=" + CID + "', Nname'" + name + "', Photo='" + Photo + "', Description='"
				+ Description + "', Type='" + Type + "', Price='" + Price + "', Stock='" + Stock + "'}";
	}
	
	
	
}
