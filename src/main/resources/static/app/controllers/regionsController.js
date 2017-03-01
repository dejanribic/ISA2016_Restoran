/**
 * Created by Nenad on 3/1/2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegionsController', RegionsController);

    RegionsController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function RegionsController($cookies, $scope, $http, $location) {

        $http.get('/regionz/all').success(function (response) {
            $scope.regions = response;
        });

        $scope.createRegion = function () {
            $http.put('/regionz/create', $scope.region).success(reloadRegions);
        };

        var reloadRegions = function () {
            $http.get('/regionz/all').success(function (response) {
                $scope.regions = response;
            });

        }
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