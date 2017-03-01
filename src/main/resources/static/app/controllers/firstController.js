/**
 * Created by Viktor on 3/1/2017.
 */


(function () {
    'use strict';

    angular
        .module('app')
        .controller('FirstController', FirstController);

    FirstController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function FirstController($cookies, $scope, $http, $location) {

        $scope.password = "";
        $scope.email = $cookies.get('email');
        $scope.rname = $cookies.get('rname');

        $scope.change = function ()
        {
            $http.get('/first/change' +'/'+ $scope.email+'/'+ $scope.rname + '/' +  $scope.password).success(function (response) {

                  $location.url('/restorani');

        })
    }}
})();
