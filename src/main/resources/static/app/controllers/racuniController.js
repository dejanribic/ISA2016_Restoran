/**
 * Created by Viktor on 2/27/2017.
 */


(function () {
    'use strict';

    angular
        .module('app')
        .controller('RacuniController', RacuniController);

    RacuniController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function RacuniController($cookies, $scope, $http, $location) {



        $scope.etype = $cookies.get('etype');
        $scope.rname = $cookies.get('rname');
        $scope.email = $cookies.get('email');

        console.log($cookies.get('email'));

        $http.get('/racuni/all').success(function (response) {
            $scope.racuni = response;
        });

        $scope.naplati = function (a,b,c,d)
        {
            $http.get('/racuni/naplati/'+a+'/'+b+'/'+c+'/'+d+'/').success(function (response) {
                $scope.racuni = response;
            });
        }

        $scope.accepted = function (a,b,c,d){
          //  alert("uso");
            $http.get('/racuni/accepted/'+a+'/'+b+'/'+c+'/'+d+'/'+ $cookies.get('email')).success(function (response) {
                $scope.racuni = response;
            });
        }


}})();

