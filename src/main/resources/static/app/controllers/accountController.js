(function () {
    'use strict';

    angular
        .module('app')
        .controller('AccountController', AccountController);

    AccountController.$inject = ['$scope', '$http', '$cookies', '$location'];

    function AccountController($scope, $http, $cookies, $location) {

        $scope.friendSearch = "";

        // Refresh page function
        var refresh = function () {
            $scope.friendSearch = "";

            $http.get('/guests/friends/' + $cookies.get("email")).success(function (response) {
                $scope.friends = response;
            });

            $http.get('/guests/addable/' + $cookies.get('email')).success(function (response) {
                $scope.addable = response;
            });

            $http.get('/guests/requests/' + $cookies.get('email')).success(function (response) {
                $scope.requests = response;
            });
        }

        // Cookie loading
        if ($cookies.get("name") != null) {
            $scope.profileName = $cookies.get("name");
        }
        else if ($cookies.get("email") != null) {
            $scope.profileName = $cookies.get("email");
        } else {
            $scope.profileName = "X";
        }

        // Filling the scope with the current user
        $http.get('/users/getOne/' + $cookies.get('email')).success(function (response) {
            $scope.user = response;
        });

        // Filling the scope with friends, addable friends, and friend requests
        refresh();

        // Profile change
        $scope.izmeniNalog = function () {
            $http.put('/guests/change', $scope.user);
        };

        // Prepare for friend deletion
        $scope.pripremaZaBrisanje = function (guest) {
            $scope.zaBrisanje = guest;
        };

        // Delete friend
        $scope.obrisi = function () {
            $http.get('/guests/deleteFriend/' + $cookies.get('email') + '/' + $scope.zaBrisanje.email).success(function (response) {
                refresh();
            });
        };

        // Add friend
        $scope.dodaj = function (friendEmail) {
            $http.get('/guests/sendRequest/' + $cookies.get('email') + '/' + friendEmail).success(function (response) {
                $scope.trazi();
            });

        };

        // Find friends
        $scope.trazi = function () {
            var friendSearch = $scope.friendSearch;
            $http.get('/guests/addable/' + $cookies.get('email') + '/' + friendSearch).success(function (response) {
                $scope.addable = response;
            });
        }

        // Accept request
        $scope.prihvati = function (email) {
            $http.get('/guests/acceptRequest/' + $cookies.get('email') + '/' + email).success(function (response) {
                refresh();
            });
        };

        // Decline request
        $scope.odbi = function (email) {
            $http.get('/guests/declineRequest/' + $cookies.get('email') + '/' + email).success(function (response) {
                refresh();
            });
        };

        // Log out
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
