(function () {
    'use strict';

    //noinspection JSUnresolvedFunction
    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$cookies', '$scope', '$http', '$location', '$window'];

    function LoginController($cookies, $scope, $http, $location, $window) {

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
                    $location.url('/');
                    $window.location.reload();
                });
            }
        };


        $scope.exists = function () {
            //noinspection JSUnresolvedVariable

            var passLogin = document.getElementById("password").value;

            if (!passLogin.trim()) {
                alert("Password cannot be empty or whitespace!");
                document.getElementById("password").style.borderColor = "#E34234";
            }
            else {
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

                    console.log("zzzzzzzzzzzzzzzzz");
                    console.log($cookies.get('name'));

                    $cookies.put('etype', response.etype);
                    $cookies.put('rname', response.restname);


                    $scope.etype = $cookies.get('etype') ;


                    console.log($cookies.get('etype'));
                    console.log(response.etype);
                    console.log($scope.etype);



/*                    console.log(response.email);
                    console.log(response.password);
                    console.log(response.type);
*/


                    /*
                     console.log(response);
                     console.log(response.email);
                     console.log(response.password);
                     console.log(response.type);
                     console.log("Cookie email: " + $cookies.get('email'));
                     console.log("Cookie type: " + $cookies.get('type'));
                     */


                    if (response.type == 1) {
                        $location.url('/restorani');
                    }
                    else if (response.type == 2) {
                        $location.url('/manager');
                    }
                    else if (response.type == 3) {
                        $location.url('/supplier');
                    }
                    else if (response.type == 4) {
                        $location.url('/sysman');
                    }
                    else if (response.type == 5) {
                        console.log(response.firstlog);
                        if(response.firstlog==0)
                        {
                            console.log("nice try");
                            $location.url('/first');
                        }
                        else
                        $location.url('/restorani');

                    }
                    else if (response.type = 0) {
                    }


                });
            }
        };

        $scope.forgotPass = function () {
            $http.post('/users/forgotPass', $scope.loggingUser).success(function (response) {
                console.log("Zaboravio sifru");
                $location.url('/');
            });
        };
    }
})();
