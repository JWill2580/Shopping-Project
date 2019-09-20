/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"use strict";

class ShoppingCart {

    constructor() {
        this.saleItems = new Array();
    }

    reconstruct(sessionData) {
        for (let SaleItem of sessionData.saleItems) {
            this.addItem(Object.assign(new SaleItem(), SaleItem));
        }
    }

    getItems() {
        return this.SaleItem;
    }

    addItem(item) {
        this.saleItems.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.SaleItem) {
            total += item.getTotal();
        }
        return total;
    }

}

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

