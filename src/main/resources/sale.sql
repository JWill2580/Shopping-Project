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
    Date date(50) not null unique, /*Check this (date) in lab*/
    First_Name varchar(50) not null,
    Status varchar(50) not null,
    /*Create Customer variable linking foreign keys*/
    /*Create Sale Item collection variable linking foreign keys*/


    constraint Sale_ID primary key (Sale_ID)
);

