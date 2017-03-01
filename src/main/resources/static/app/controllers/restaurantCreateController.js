/**
 * Created by Nenad on 2/25/2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('RestaurantCreateController', RestaurantCreateController);

    RestaurantCreateController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function RestaurantCreateController($cookies, $scope, $http, $location) {

        $http.get('/restaurants/all').success(function (response) {
            $scope.restaurants = response;
        });

        $scope.createRes = function () {
            $http.put('/restaurants/create', $scope.restaurant).success(reloadRestaurants);
        };
        $scope.createManager = function () {
            $scope.manager.restaurantName =  $scope.activeRestaurantName;
            $http.put('/managers/create', $scope.manager).success(function (response) {
                lM(response.restaurantName);
            });
        };

        $scope.loadManagers = function (res) {
            lM(res.name);
        };
        $scope.removeMan = function (man) {
            $http.put('/managers/remove', man).success(function (response) {
                lM(response.restaurantName);
            });

        };

        $scope.createSystemManager= function () {
            $http.put('/sysman/create', $scope.sysManager).success(function (response) {
                alert(response.message);
            });
        };


        var reloadRestaurants = function () {
            $http.get('/restaurants/all').success(function (response) {
                $scope.restaurants = response;
            });
        };
        var lM = function (name) {
            $scope.activeRestaurantName = name;
            $('#managers-head').text(name + ' managers');
            $http.get('/managers/all/' + name).success(function (response) {
                $scope.managers = response;

            });

        };
        $scope.logout = function () {
            var cookies = $cookies.getAll();
            angular.forEach(cookies, function (v, k) {
                console.log(cookies);
                $cookies.remove(k);
            });
            $location.url('/');
        };


    }


})();