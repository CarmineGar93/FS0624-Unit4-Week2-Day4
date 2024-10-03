package CarmineGargiulo.Ecommerce;

import java.util.Random;

public class Product implements Cloneable {
    Random random = new Random();
    private long id;
    private String name;
    private Categories category;
    private double price;

    public Product(String name, Categories category, double price){
        this.id = random.nextLong(1000,10000);
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "category=" + category +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Product clone() {
        try {
            Product clone = (Product) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
