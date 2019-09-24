/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  admin
 * Created: 24/09/2019
 */

create table SALE (
    Sale_ID varchar(50) not null auto_increment,
    Date_ date(50) not null unique, /*Check this (date) in lab*/
    Status varchar(50) not null,
    Customer_ID varchar(50),

    constraint Sale_ID primary key (Sale_ID),
    constraint Customer_FK foreign key(Customer_ID) references CUSTOMER
    
);

