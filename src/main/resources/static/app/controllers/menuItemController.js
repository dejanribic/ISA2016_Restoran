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
            $scope.initMap();
        });
        $http.get('/restaurants/getRating').success(function (response) {
            $scope.restaurantRating = response;
        });
        $http.get('/menu-item/allTypes').success(function (response) {
            $scope.menuItemTypes = response;
        });

        $scope.initMap = function () {
            var latLong = "45.24073530080248,19.84867662191391".split(",");
            if ($scope.restaurant.coordinates != undefined) {
                latLong = $scope.restaurant.coordinates.split(",");

            }
            var lat = parseFloat(latLong[0]);
            var long = parseFloat(latLong[1]);

            $scope.coords = {lat: lat, lng: long};
            var mapOptions = {
                center: new google.maps.LatLng(lat, long),
                zoom: 30,
                mapTypeId: google.maps.MapTypeId.HYBRID
            }
            var map = new google.maps.Map(document.getElementById("map"), mapOptions);
            var marker = new google.maps.Marker({
                position: $scope.coords,
                map: map,
                title: $scope.restaurant.name
            });

            map.addListener('click', function (event) {
                $scope.coords['lat'] = event.latLng.lat();
                $scope.coords['lng'] = event.latLng.lng();
                marker.setPosition($scope.coords);
            });


        }
        $scope.createMenuItem = function () {
            $scope.menuItem.type_name = $("#type option:selected").text();
            $scope.menuItem.restname = $scope.restaurant.name;
            $http.put('/menu-item/create', $scope.menuItem).success(reloadMenuItems);
        };

        $scope.setMenuItem = function (toChange) {
            $scope.menuItem = toChange.menuItem;
        };
        $scope.updateRestaurant = function () {
            var coords = String($scope.coords['lat']) + ',' + String($scope.coords['lng']);
            $scope.restaurant.coordinates = coords;
            $http.put('/restaurants/create', $scope.restaurant).success(function (response) {
                alert("Info updated")
            });
        };


        var reloadMenuItems = function () {
            $http.get('/menu-item/all').success(function (response) {
                $scope.menuItems = response;
            });

        }


    }


})();

