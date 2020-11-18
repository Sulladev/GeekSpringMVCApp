package ru.pirozhkov.springcourse.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pirozhkov.springcourse.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {

    private List<Product> productList;
    private static int PRODUCT_COUNT;


    @PostConstruct
    public void init () {
        productList = new ArrayList<>();
        productList.add( new Product(++PRODUCT_COUNT, "Mavic", "videography", "DJI", 1200));
        productList.add( new Product(++PRODUCT_COUNT, "Phantom", "survey" , "DJI",1400));
        productList.add( new Product(++PRODUCT_COUNT, "Inspire", "filmmaking", "DJI", 2200));
        productList.add( new Product(++PRODUCT_COUNT, "Matrice", "construction", "DJI" , 4500));
        productList.add( new Product(++PRODUCT_COUNT, "EVO II", "photogrammetry", "Autel" , 1700));
    }


    public List<Product> getAllProducts () {
        return  productList;
    }


    public Product getProductById (int id) {
        return productList.stream().filter(product -> product.getId() == id).findAny().orElse(null);
    }

    public void addNewProduct (Product product) {
        product.setId(++PRODUCT_COUNT);
        productList.add(product);
    }

    public void update(int id, Product updatedProduct) {
        Product productToBeUpdated = getProductById(id);
        productToBeUpdated.setTitle(updatedProduct.getTitle());
        productToBeUpdated.setBrand(updatedProduct.getBrand());
        productToBeUpdated.setDescription(updatedProduct.getDescription());
        productToBeUpdated.setPrice(updatedProduct.getPrice());

    }

    public void delete(int id) {
        productList.removeIf(p -> p.getId() == id);
    }


}
