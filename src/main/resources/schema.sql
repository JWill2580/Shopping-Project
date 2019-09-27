/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  wiljo912
 * Created: 9/08/2019
 */

create table PRODUCT (
    Product_ID varchar(50),
    Product_Name varchar(50) not null,
    Description varchar(50),
    Category varchar(50) not null,
    List_Price decimal(5, 2) not null,
    Quantity_In_Stock decimal(5, 2) not null,
    constraint Product_ID primary key (Product_ID)
);

create table CUSTOMER (
    Customer_ID varchar(50) not null auto_increment,
    Username varchar(50) not null unique,
    First_Name varchar(50) not null,
    Surname varchar(50) not null,
    Password varchar(50) not null,
    Email varchar(50) not null,
    Shipping varchar(50) not null,

    constraint Customer_ID primary key (Customer_ID)
);



create table SALE (
    Sale_ID integer auto_increment,
    Date timestamp not null,
    Status varchar(50) not null,
    Customer_ID varchar(50),

    constraint Sale_ID primary key (Sale_ID),
    constraint Customer_FK foreign key(Customer_ID) references CUSTOMER  
);

create table SALEITEM (
    Sale_ID integer auto_increment,
    Product_ID varchar(50),
    Quantity_Purchased decimal(5,2) not null,
    Sale_Price decimal(5,2) not null,

    constraint Sale_Item_PK primary key(Sale_ID, Product_ID), 
    constraint Sale_FK foreign key(Sale_ID) references SALE,
    constraint Product_FK foreign key(Product_ID) references PRODUCT
);




