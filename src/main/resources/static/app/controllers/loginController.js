(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$cookies', '$scope', '$http', '$location'];
    //AccountController.$inject = ['$scope'];

    function LoginController($cookies, $scope, $http, $location) {

        $scope.register = function () {
            console.log("usao");
            $http.put('/guests/create', $scope.newUser).success(function (response) {
                console.log("Poslao sam nest!");

            });
        };

        $scope.exists = function () {
            $http.put('/sysman/exists', $scope.loggingUser).success(function (response) {
                if ($scope.loggingUser != null) {
                    console.log($scope.loggingUser);
                    console.log(response);
                    $cookies.put('name', response.name);
                    $cookies.put('id', response.user_id);
                    var favoriteCookie = $cookies.get('name');
                    console.log(favoriteCookie);
                    console.log($cookies.get('id'));
                    // //var favoriteCookie2 = $cookies.get('id');
                    // //console.log(favoriteCookie2);
                    $location.url('/restorani');
                } else {
                    $location.url('/');
                }
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
