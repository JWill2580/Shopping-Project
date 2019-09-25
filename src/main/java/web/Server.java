/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;


import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;
import dao.*;

/**
 *
 * @author wiljo912
 */
public class Server extends Jooby {

    private JDBCManageProducts db = new JDBCManageProducts();

    private JDBCManageCustomers custdb = new JDBCManageCustomers();
    private JDBCManageSales saledb = new JDBCManageSales();
   
    public Server() {
        port(8080);
        //get("/", () -> "Hello World");

        use(new Gzon());        
        use(new ProductModule(db));
        use(new CustomerModule(custdb)); 
        use(new SaleModule(saledb));
        use(new AssetModule());

}
    

    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");
        Server server = new Server();
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });
        // wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
    }
}
