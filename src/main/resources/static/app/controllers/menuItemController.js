/**
 * Created by Nenad on 2/23/2017.
 * Controller for menu items
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('MenuItemController', MenuItemController);

    MenuItemController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function MenuItemController($cookies, $scope, $http, $location) {

        $http.get('/menu-item/all').success(function (response) {
            $scope.menuItems = response;
        });
        $http.get('/restaurants/getActive').success(function (response) {
            $scope.restaurant = response;
        });
        $http.get('/restaurants/getRating').success(function (response) {
            $scope.restaurantRating = response;
        });
        $http.get('/menu-item/allTypes').success(function (response) {
            $scope.menuItemTypes = response;
        });

        $scope.createMenuItem = function () {
            $scope.menuItem.type_name = $( "#type option:selected" ).text();
            $scope.menuItem.restname = $scope.restaurant.name;
            $http.put('/menu-item/create', $scope.menuItem).success(reloadMenuItems);
        };

        $scope.remove = function (toRemove) {
            console.log(toRemove)
            $http.put('/menu-item/remove', toRemove).success(reloadMenuItems);
        };

        var reloadMenuItems = function(){
            $http.get('/menu-item/all').success(function (response) {
                $scope.menuItems = response;
            });

        }



    }



})();

