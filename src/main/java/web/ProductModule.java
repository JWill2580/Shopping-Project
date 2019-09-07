/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ProductsCollectionDAOInterface;
import org.jooby.Jooby;

/**
 *
 * @author wiljo912
 */
public class ProductModule extends Jooby {

    public ProductModule(ProductsCollectionDAOInterface db) {
        get("/api/products", () -> db.getProducts());
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return db.getThroughID(id);
        });
    }
    
    
    
}
