/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"use strict";

class SaleItem {

    constructor(product, quantity) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchase = quantity;
            this.salePrice = product.listPrice;
        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchase;
    }

}

class ShoppingCart {

    constructor() {
        this.saleItems = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.saleItems) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
    }

    getItems() {
        return this.saleItems;
    }

    addItem(item) {
        this.saleItems.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.saleItems) {
            total += item.getItemTotal();
        }
        return total;
    }

}



var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

module.factory('productDAO', function ($resource) {//potential issue regarding naming of productDAO
    return $resource('/api/products/:id');
});


module.factory('categoryDAO', function ($resource) {
    return $resource('/api/categories/:cat');
});



module.controller('ProductController', function (productDAO, categoryDAO) {
    /*alert("in controller");*/
    this.products = productDAO.query();
    this.categories = categoryDAO.query();

    // click handler for the category filter buttons
    this.selectCategory = function (selectedCat) {
        this.products = categoryDAO.query({"cat": selectedCat});
    };
    this.allCategories = function () {
        this.products = productDAO.query();
    }

});

module.factory('registerDAO', function ($resource) {
    return $resource('/api/register');
});

module.factory('signInDAO', function ($resource) {
    return $resource('/api/customers/:username');
});



module.controller('CustomerController', function (registerDAO, signInDAO, $window, $sessionStorage) {
    this.signInMessage = "Please sign in to continue.";
    this.registerCustomer = function (customer) {
        registerDAO.save(null, customer,
                // success callback
                        function () {
                            $window.location = 'sign-in.html';
                        },
                        // error callback
                                function (error) {
                                    console.log(error);
                                }
                        );
                    };

            // alias 'this' so that we can access it inside callback functions
            let ctrl = this;
            this.signIn = function (username, password) {
                // get customer from web service
                signInDAO.get({'username': username},
                        // success
                                function (customer) {
                                    // also store the retrieved customer
                                    $sessionStorage.customer = customer;
                                    // redirect to home
                                    $window.location.href = 'index.html';
                                },
                                // fail
                                        function () {
                                            ctrl.signInMessage = 'Sign in failed. Please try again.';
                                        }
                                );
                            };
                    this.checkSignIn = function () {
                        // has the customer been added to the session?
                        if ($sessionStorage.customer) {
                            this.signedIn = true;
                            this.welcome = "Welcome " + $sessionStorage.customer.firstname;
                        } else {
                            this.signedIn = false;
                        }
                    };
                    this.signOut = function () {
                      this.signedIn = false;
                      $window.location.href = 'index.html';
                      $sessionStorage.$reset();
                    };
                });
                
/*Lab10*/

module.factory('saleDAO', function ($resource) {
    return $resource('/api/sales');
});


module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});


module.controller('SaleController', function(cart, $sessionStorage, $window){
    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.selectedProduct = $sessionStorage.selectedProduct;
    
    this.buyButton = function (selectedProduct) {
        $sessionStorage.selectedProduct = selectedProduct;
        $window.location.href = 'quantity-to-purchase.html';

    }
    
    this.addToCart = function (quantity){
        $sessionStorage.quantity = quantity;
        let saleItem = new SaleItem($sessionStorage.selectedProduct, $sessionStorage.quantity);
        cart.addItem(saleItem);
        $sessionStorage.cart = cart;
        $window.location.href = 'view-products.html';   
    }
    
    this.checkOut = function (cart){
        cart.setCustomer($sessionStorage.customer);
        saleDAO.save(cart);
        delete sessionStorage.cart;
        $window.location.href = 'thank-you.html';   
    }
});
