/**
 * Created by Nenad on 2/24/2017.
 */

(function () {
    'use strict';

    angular
        .module('app')
        .controller('DemandController', DemandController);

    function DemandController($cookies, $scope, $http, $location) {
        $http.get('/managers/allByMail').success(function (response) {
            $scope.restaurants = response;
        });

        $http.get('/demand/all').success(function (response) {
            $scope.demands = response;
        });

        $scope.goToRestaurant = function (res) {
            $http.get('/restaurants/getByName/'+res.restaurantName).success(function (response) {
                $location.url('/resadm');
            });

        };

        $scope.createDemand = function () {
            console.log($scope.demand)
            $http.put('/demand/create', $scope.demand).success(reloadDemand);
        };

        $scope.remove = function (toRemove) {
            console.log(toRemove)
            $http.put('/demand/remove', toRemove).success(reloadDemand);
        };

        $scope.showOffers = function(dm){
            $http.put('/offer/getByDemand', dm).success(function (response) {
               $scope.offers = response;
            });

        };
        $scope.acceptOffer = function(of){
            $http.put('offer/accept',of).success(function(response){
                reloadDemand();
                $scope.offers = undefined;
                //TODO notify
            });
        };

        $scope.createSupplier = function(){
            $http.put("/supplier/create",$scope.supplier).success(function (response) {
                alert(response.message);
            });
        };

        var reloadDemand = function(){
            $http.get('/demand/all').success(function (response) {
                $scope.demands = response;
            });

        }



    }



})();