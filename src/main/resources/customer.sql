/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  wiljo912
 * Created: 19/09/2019
 */
create table CUSTOMER (
    Customer_ID varchar(50) not null auto_increment,
    Username varchar(50) not null,
    First_Name varchar(50) not null,
    Surname varchar(50) not null,
    Password varchar(50) not null,
    Email varchar(50) not null,
    Shipping varchar(50) not null,

    constraint Customer_ID primary key (Customer_ID)
);

