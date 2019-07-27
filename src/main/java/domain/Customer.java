/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author wiljo912
 */
public class Customer {
    private static Integer customerID;
    private  String username;
    private  String firstname;
    private  String surname;
    private  String password;
    private  String email;
    private  String shipping;

    public Customer(String username, String firstname, String surname, String password, String email, String shipping) {
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.shipping = shipping;
    }

    public static Integer getCustomerID() {
        return customerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    @Override
    public String toString() {
        return "Customer{" + "username=" + username + ", firstname=" + firstname + ", surname=" + surname + ", password=" + password + ", email=" + email + ", shipping=" + shipping + '}';
    }
    
    
}
