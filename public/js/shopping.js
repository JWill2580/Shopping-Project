/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"use strict";

var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

module.factory('productDAO', function ($resource) {//potential issue regarding naming of productDAO
    return $resource('/api/products/:id');
});

module.factory('categoryDAO', function ($resource) {
    return $resource('/api/categories/:cat');
});

module.controller('ProductController', function (productDAO) {
    //alert("in controller")
    this.products = productDAO.query();
    this.categories = categoryDAO.query();

});

