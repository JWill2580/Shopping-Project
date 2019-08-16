/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;

/**
 *
 * @author wiljo912
 */
public class SaleItem {
    private BigDecimal quantityPurchase;
    private BigDecimal salePrice;
    private Product product;
    private Sale sale;

    public SaleItem(BigDecimal quantityPurchase, BigDecimal salePrice, Product product, Sale sale) {
        this.quantityPurchase = quantityPurchase;
        this.salePrice = salePrice;
        this.product = product;
        this.sale = sale;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
    public BigDecimal getQuantityPurchase() {
        return quantityPurchase;
    }

    public void setQuantityPurchase(BigDecimal quantityPurchase) {
        this.quantityPurchase = quantityPurchase;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    
    public BigDecimal getItemTotal(){
        return salePrice.multiply(quantityPurchase);
    }
}
