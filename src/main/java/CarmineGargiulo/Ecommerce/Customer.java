package CarmineGargiulo.Ecommerce;

import java.util.Random;

public class Customer {
    Random random = new Random();
    private long id;
    private String name;
    private int tier;

    public Customer(String name, int tier) {
        this.id = random.nextLong(1000,10000);
        this.name = name;
        this.tier = tier;
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

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
