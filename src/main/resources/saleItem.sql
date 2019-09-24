/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  admin
 * Created: 24/09/2019
 */

create table SALEITEM (
    Sale_ID varchar(50) not null auto_increment,
    Product_ID varchar(50),
    Quantity_Purchased decimal(5,2) not null,
    Sale_Price decimal(5,2) not null,

    /*Create  product linking foreign keys*/
    /*Create Sale Item collection variable linking foreign keys*/
    constraint Sale_Item_PK primary key(Sale_ID, Product_ID), 
    constraint Sale_FK foreign key(Sale_ID) references SALE,
    constraint Product_FK foreign key(Product_ID) references PRODUCT
);



 
