package ru.geekbrains.jsf_webb_app.shop.service;

import ru.geekbrains.jsf_webb_app.shop.entities.CategoryView;
import ru.geekbrains.jsf_webb_app.shop.entities.Product;
import ru.geekbrains.jsf_webb_app.shop.entities.ProductView;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("rest")
public interface ProductServiceAPI {

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductView> getProducts();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ProductView getProductById(@QueryParam("id") Long id);

    @GET
    @Path("/product/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductView> getProductByName(@PathParam("name") String name);

    @GET
    @Path("/prod-cat/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductView> getProductsByCategoryId(@PathParam("id") Long id);

    @GET
    @Path("/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryView getCategoryById(@PathParam("id") Long id);

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryView getCategoryByName(@QueryParam("name") String name);

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    void updateProduct(ProductView product);

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    void addProduct(ProductView product);

    @DELETE
    @Path("/delete/{id}")
    void deleteProduct(@PathParam("id") Long id);
}
