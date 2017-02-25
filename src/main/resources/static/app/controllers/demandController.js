/**
 * Created by Nenad on 2/24/2017.
 */

(function () {
    'use strict';

    angular
        .module('app')
        .controller('DemandController', DemandController);

    function DemandController($cookies, $scope, $http, $location) {

        $http.get('/demand/all').success(function (response) {
            $scope.demands = response;
        });

        $scope.create = function () {
            console.log($scope.demand)
            $http.put('/demand/create', $scope.demand).success(reloadMenuItems);
        };

        $scope.remove = function (toRemove) {
            console.log(toRemove)
            $http.put('/demand/remove', toRemove).success(reloadMenuItems);
        };

        var reloadMenuItems = function(){
            $http.get('/demand/all').success(function (response) {
                $scope.demands = response;
            });

        }



    }



})();