/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.SalesDAOInterface;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author wiljo912
 */
public class SaleModule extends Jooby {
    public SaleModule(SalesDAOInterface db) {
        get("/api/sales", (req, rsp) -> { 
            Sale sale = req.body().to(Sale.class);
            db.save(sale);
            rsp.status(Status.CREATED);
            System.out.println(sale.toString());
        });
    }
}
