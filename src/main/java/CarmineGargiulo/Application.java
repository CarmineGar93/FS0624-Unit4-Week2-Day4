package CarmineGargiulo;

import CarmineGargiulo.Ecommerce.Categories;
import CarmineGargiulo.Ecommerce.Customer;
import CarmineGargiulo.Ecommerce.Order;
import CarmineGargiulo.Ecommerce.Product;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Faker faker = new Faker(Locale.ITALY);
        Random ran = new Random();
        List<Product> allProducts = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int randomCat = ran.nextInt(1, 5);
            Categories category = switch (randomCat){
                case 1 -> Categories.BABY;
                case 2 -> Categories.BOYS;
                case 3 -> Categories.BOOK;
                case 4 -> Categories.FOOD;
                case 5 -> Categories.ELECTRONIC;
                default -> Categories.JEWELRY;
            };
            allProducts.add(new Product(faker.commerce().productName(),category,ran.nextDouble(20, 300)));
        }
        List<Customer> allCustomers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String randomCustomer = faker.harryPotter().character();
            while(true){
                String finalRandomCustomer = randomCustomer;
                if(allCustomers.stream().anyMatch(customer -> customer.getName().equals(finalRandomCustomer))){
                    randomCustomer = faker.harryPotter().character();
                }
                else break;
            }
            allCustomers.add(new Customer(randomCustomer, ran.nextInt(1,5)));
        }
        List<Order> allOrders = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            List<Product> productsOrdered = new ArrayList<>();
            for (int j = 0; j < ran.nextInt(1,2); j++) {
                productsOrdered.add(allProducts.get(ran.nextInt(0, allProducts.size() - 1)));
            }
            allOrders.add(new Order(productsOrdered, allCustomers.get(ran.nextInt(0, allCustomers.size() - 1))));
        }
        System.out.println("---------------------Products-----------------------------");
        allProducts.forEach(System.out::println);
        System.out.println("---------------------Orders-----------------------------");
        allOrders.forEach(System.out::println);
        System.out.println("---------------------Ordini per cliente---------------------------");
        Map<Customer, List<Order>> ordersByCustomer = allOrders.stream().collect(Collectors.groupingBy(Order::getCustomer));
        ordersByCustomer.forEach(((customer, orders) -> System.out.println(customer + ": " + orders)));
        System.out.println("---------------------Totale speso per cliente---------------------------");
        Map<Customer, Double> totByCustomer = allOrders.stream().collect(Collectors.groupingBy
                (Order::getCustomer, Collectors.summingDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum())));
        totByCustomer.forEach(((customer, totprice) -> System.out.println(customer + " totale speso: " + totprice)));
        System.out.println("---------------------Prodotti piu costosi---------------------------");
        List<Product> mostExpensiveProducts = allProducts.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(10).toList();
        mostExpensiveProducts.forEach(System.out::println);
        System.out.println("---------------------Media ordini---------------------------");
        OptionalDouble averageOrders = allOrders.stream().mapToDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum()).average();
        if(averageOrders.isPresent()) System.out.println("La media degli ordini fatti da tutti i clienti è di: " + averageOrders.getAsDouble());
        else System.out.println("Lista vuota");
        System.out.println("---------------------Totale per categoria---------------------------");
        Map<Categories, Double> totByCategory = allProducts.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
        totByCategory.forEach(((categories, aDouble) -> System.out.println("Category: " + categories + ", totale: " + aDouble)));
        saveOnDisc(allProducts);
    }

    public static void saveOnDisc(List<Product> products){
        File file = new File("src/info.txt");
        String productString = products.stream().map(product -> product.getName() + "@" + product.getCategory() + "@" + product.getPrice()).collect(Collectors.joining("#"));
        try {
            System.out.println("Salvataggio in corso");
            FileUtils.writeStringToFile(file,productString, StandardCharsets.UTF_8);
            try {
                Thread.sleep(2000); //java. lang. InterruptedException senza try/catch
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Salvataggio completato");
        }catch (IOException e){
            System.out.println("errore nel salvataggio del file");
        }

    }
}
