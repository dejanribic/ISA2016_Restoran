(function () {
    'use strict';

    //noinspection JSUnresolvedFunction
    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function LoginController($cookies, $scope, $http, $location) {

        $scope.register = function () {

            // Password confirm
            var pass1 = document.getElementById("passwordreg").value;
            var pass2 = document.getElementById("confirm-password").value;
            if (pass1 != pass2) {
                alert("Passwords Do not match");
                document.getElementById("passwordreg").style.borderColor = "#E34234";
                document.getElementById("confirm-password").style.borderColor = "#E34234";
            }
            else {
                // User creation
                $http.put('/users/create', $scope.newUser).success(function (response) {
                    $location.url('/restorani');
                });
            }
        };


        $scope.exists = function () {
            //noinspection JSUnresolvedVariable
            $http.put('/users/exists', $scope.loggingUser).success(function (response) {

                // User Types:
                // Nista = 0
                // Guest = 1
                // Manager = 2
                // Supplier = 3
                // Sys_Manager = 4
                // Employee = 5

                $cookies.put('email', response.email);
                $cookies.put('type', response.type);
                $cookies.put('name', response.name);

                /*
                 console.log(response);
                 console.log(response.email);
                 console.log(response.password);
                 console.log(response.type);
                 console.log("Cookie email: " + $cookies.get('email'));
                 console.log("Cookie type: " + $cookies.get('type'));
                 */

                if (response.type == 1) {
                }
                else if (response.type == 2) {
                }
                else if (response.type == 3) {
                }
                else if (response.type == 4) {
                }
                else if (response.type == 5) {
                }
                else if (response.type = 0) {
                }

                $location.url('/restorani');
            });
        };

        $scope.forgotPass = function () {
            $http.post('/users/forgotPass', $scope.loggingUser).success(function (response) {
                console.log("Zaboravio sifru");
                $location.url('/');
            });
        };
    }
})();
