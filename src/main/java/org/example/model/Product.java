package org.example.model;
//eli byStore el product data (name, price, stock, branch)
// by connects el db m3 el application
//Used f admin editing w user browsing
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
    @Id
    //auto id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //required
    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @Min(1)
    private double price;

    @Min(0)
    @Max(100)
    private Integer discount = 0;

    @NotBlank
    private String branch;

    @Min(0)
    private int stock;

    @NotBlank
    private String imageUrl;

    @NotBlank
    @Size(max = 1000)
    @Column(length = 1000)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount == null ? 0 : discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public double getDiscountPrice() {
        return price - (price * getDiscount() / 100);
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
