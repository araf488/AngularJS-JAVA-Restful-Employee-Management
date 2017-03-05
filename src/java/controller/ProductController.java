/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Product;
import java.util.List;
import java.util.Map;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.ProductService;

/**
 *
 * @author B14
 */
@Path("/product")
public class ProductController {
    Product product = new Product();
    ProductService productService = new ProductService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getProducts() {

        List<Map<String, String>> listOfProducts = productService.getAllProducts();
        return listOfProducts;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("id") int id) {
        return productService.getProduct(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Product updateProduct(Product product) {
        return productService.updateProduct(product);

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProduct(@PathParam("id") int id) {
        productService.deleteProduct(id);

    }

}
