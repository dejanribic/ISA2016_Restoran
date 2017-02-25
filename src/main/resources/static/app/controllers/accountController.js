(function () {
    'use strict';

    angular
        .module('app')
        .controller('AccountController', AccountController);

    AccountController.$inject = ['$scope', '$http', '$cookies'];

    function AccountController($scope, $http, $cookies) {

        $scope.friendSearch = "";

        // Refresh page function
        var refresh = function () {
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
        if ($cookies.get("name") != null && $cookies.get("name") != "")
            $scope.profileName = $cookies.get("name");
        else {
            $scope.profileName = $cookies.get("email");
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
        $scope.dodaj = function (friend_id) {
            $http.get('/guests/sendRequest/' + $cookies.get('email') + '/' + friend_id).success(function (response) {
                refresh();
            });

        };

        // Find friends
        $scope.trazi = function () {
            var friendSearch = $scope.friendSearch;
            $http.get('/guests/addable/' + $cookies.get('email') + '/' + friendSearch).success(function (response) {
            });
        }

        // Accept request
        $scope.prihvati = function (friend_id) {
            //value = "acceptRequest/{id}/{friend}")
            $http.get('/guests/acceptRequest/' + $cookies.get('id') + '/' + friend_id).success(function (response) {
                refresh();
            });
        };
    }
})();
