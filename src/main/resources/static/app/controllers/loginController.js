(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function LoginController($cookies, $scope, $http, $location) {

        $scope.register = function () {
            var pass1 = document.getElementById("passwordreg").value;
            var pass2 = document.getElementById("confirm-password").value;
            var ok = true;
            if (pass1 != pass2) {
                alert("Passwords Do not match");
                document.getElementById("passwordreg").style.borderColor = "#E34234";
                document.getElementById("confirm-password").style.borderColor = "#E34234";
                ok = false;
            }
            else {
                $http.put('/users/create', $scope.newUser).success(function (response) {
                    console.log("Kreiram novog korisnika!");
                    $location.url('/restorani');
                });
            }
        };

        $scope.exists = function () {
            $http.put('/users/exists', $scope.loggingUser).success(function (response) {
                console.log($scope.loggingUser);

                if ($scope.loggingUser != null) {
                    //console.log($scope.loggingUser);
                    //console.log(response);
                    $cookies.put('name', response.name);
                    $cookies.put('id', response.user_id);

                    //var favoriteCookie = $cookies.get('name');
                    //console.log(favoriteCookie);
                    //console.log($cookies.get('id'));
                    $location.url('/restorani');
                } else {
                    $location.url('/');
                }
            });
        };

        $scope.forgotPass = function () {
            $http.post('/users/forgotPass', $scope.loggingUser).success(function (response) {
                console.log("Zaboravio sifru");
                $location.url('/');
            });
        };
    }

    function passwordVerify() {
        return {
            restrict: 'A', // only activate on element attribute
            require: '?ngModel', // get a hold of NgModelController
            link: function (scope, elem, attrs, ngModel) {
                if (!ngModel) return; // do nothing if no ng-model

                // watch own value and re-validate on change
                scope.$watch(attrs.ngModel, function () {
                    validate();
                });

                // observe the other value and re-validate on change
                attrs.$observe('passwordVerify', function (val) {
                    validate();
                });

                var validate = function () {
                    // values
                    var val1 = ngModel.$viewValue;
                    var val2 = attrs.passwordVerify;

                    // set validity
                    ngModel.$setValidity('passwordVerify', val1 === val2);
                };
            }
        }
    }
})();
