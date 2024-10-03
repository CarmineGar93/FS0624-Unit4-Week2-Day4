package CarmineGargiulo.Ecommerce;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Order {
    Random random = new Random();
    private long id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private Customer customer;

    public Order(List<Product> products, Customer customer) {
        this.id = random.nextLong(1000,10000);
        this.products = products;
        this.customer = customer;
        this.status = "in progress";
        this.orderDate = LocalDate.of(2021, random.nextInt(1,12), random.nextInt(1, 28));
        this.deliveryDate = orderDate.plusDays(random.nextInt(1,20));
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "deliveryDate=" + deliveryDate +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }
}
