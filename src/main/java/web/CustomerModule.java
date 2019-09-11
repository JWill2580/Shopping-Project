/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerCollectionDAOInterface;
import domain.Customer;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author wiljo912
 */
public class CustomerModule extends Jooby{
    
    
     public CustomerModule(CustomerCollectionDAOInterface db) {
        get("/api/customer/:username", (req) -> {
            String username = req.param("username").value();
            return db.getCustomer(username);
        });
        post("/api/register", (req, rsp) -> {
            Customer customer = req.body().to(Customer.class);
            db.saveCustomer(customer);
            rsp.status(Status.CREATED);
        });
    }

}
