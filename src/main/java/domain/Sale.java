/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author wiljo912
 */
public class Sale {
    private static Integer saleID;
    private LocalDate date;
    private String status;
    private ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();

    public Sale(LocalDate date, String status) {
        this.date = date;
        this.status = status;
    }

    public Integer getSaleID() {
        return saleID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public BigDecimal getTotal(){
        BigDecimal total = new BigDecimal("0.0");
        for(SaleItem item : saleItems){
            total = total.add(item.getItemTotal());
        }
        return total;
    }
    
    public void addItem(SaleItem item){
        this.saleItems.add(item);
    }
}
