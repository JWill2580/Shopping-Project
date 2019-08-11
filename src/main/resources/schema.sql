/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  wiljo912
 * Created: 9/08/2019
 */

create table Product (
    Product_ID varchar(50),
    Product_Name varchar(50) not null,
    Description varchar(50),
    Category varchar(50) not null,
    List_Price decimal(5, 2) not null,
    Quantity_In_Stock decimal(5, 2) not null,
    constraint Product_ID primary key (Product_ID)
);
