
(function () {
    'use strict';

    angular
        .module('app')
        .controller('OrderController', OrderController);

    OrderController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function OrderController($cookies, $scope, $http, $location) {



        $scope.etype = $cookies.get('etype');

        //   $scope.etype = $cookies.get('etype') ;
        $scope.rname = $cookies.get('rname');
        $scope.mail = $cookies.get('email');

    /*    $scope.checktype = function (x,y) {
            $http.get('/orders/checktype/'+x+'/'+y ).success(function (response) {
                console.log("pozvo sam te");
                $scope.test = response.type_name;

            });
        }
      */
        $http.get('/orders/all').success(function (response) {
                $scope.orders = [];
                angular.forEach(response,function(value,index) {
                    $http.get('/orders/checktype/'+value.menuitemname+'/'+value.restname ).success(function (res) {
                        $scope.temp=res.type_name;
                        if ($scope.temp == $scope.etype)
                            $scope.orders.push(value);
                    });
                })
        });

        $scope.done = function (a, b, c, d, e) {
            $http.get('/orders/done/' + a + '/' + b + '/' + c + '/' + d + '/' + e ).success(function (response) {
                $scope.orders = [];
                angular.forEach(response,function(value,index) {
                    $http.get('/orders/checktype/'+value.menuitemname+'/'+value.restname ).success(function (res) {
                        $scope.temp=res.type_name;
                        if ($scope.temp == $scope.etype)
                            $scope.orders.push(value);
                    });
                })

            });
        }

        $scope.accept = function (a, b, c, d, e) {
            $http.get('/orders/accept/' + a + '/' + b + '/' + c + '/' + d + '/' + e + '/' + $cookies.get('email')).success(function (response) {
                $scope.orders = [];
                angular.forEach(response,function(value,index) {
                    $http.get('/orders/checktype/'+value.menuitemname+'/'+value.restname ).success(function (res) {
                        $scope.temp=res.type_name;
                        if ($scope.temp == $scope.etype)
                            $scope.orders.push(value);
                    });
                })

            });
        }


    }
})();
