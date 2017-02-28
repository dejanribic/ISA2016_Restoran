/**
 * Created by Nenad on 2/26/2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('SupplierController', SupplierController);

    function SupplierController($cookies, $scope, $http, $location) {
        $http.get('/demand/allForSupplier').success(function (response){
            $scope.demands = response;
        });
        $http.get('/offer/getBySupplier').success(function (response){
            $scope.offers = response;
        });
        $http.get('/supplier/get').success(function (response){
            $scope.user = response;
            $scope.pass = {'new':'','newr':'','old':'zlzl'};
        });

        $scope.addOffer = function () {
            $http.put('/offer/create',$scope.offerToAdd).success(function (response){
                $scope.offerToAdd=undefined;
                $http.get('/offer/getBySupplier').success(function (response){
                    $scope.offers = response;
                });
            });
        };

        $scope.setDemand = function (dm) {
            $scope.demand = dm;
            $scope.offerToAdd=undefined;
            $scope.offers.forEach(function(offer){
                if(dm.num==offer.offer.demandNum){
                    $scope.offerToAdd=offer.offer;
                    return;
                }
            });

            if($scope.offerToAdd==undefined) {
                $scope.buttonValue ='Add';
                $scope.offerToAdd ={'demandNum':dm.num , 'restaurantName': dm.restaurantName};
            }else{
                $scope.buttonValue ='Modify';
            }
            $scope.offerToAdd.restaurantName = dm.restaurantName;

        };

        $scope.modifyOffer = function (of) {
            $http.put('/offer/create',of).success(function (response){
                $http.get('/offer/getBySupplier').success(function (response){
                    $scope.offers = response;
                });
            });
        };

        $scope.setName = function(){
            $http.put('/supplier/setName/'+$scope.user.name).success(function (response){
                if(response)
                    alert("Name successfully changed.")
            });

        }
        $scope.setPassword = function(){
            $http.put('/supplier/setPassword',$scope.pass).success(function (response){

                    alert(response.message);

            });

        }


    }



})();