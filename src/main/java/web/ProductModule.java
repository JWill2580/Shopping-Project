/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import org.jooby.Jooby;
import dao.ProductsDAOInterface;

/**
 *
 * @author wiljo912
 */
public class ProductModule extends Jooby {

    public ProductModule(ProductsDAOInterface db) {
        get("/api/products", () -> db.getProducts());
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return db.getThroughID(id);
        });
        get("/api/categories", () -> db.getCategories());
        get("/api/categories/:category", (req) -> {
            String category = req.param("category").value();
            return db.getThroughCategory(category);
        });
    }


    
}
