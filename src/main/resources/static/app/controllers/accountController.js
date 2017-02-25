(function () {
    'use strict';

    angular
        .module('app')
        .controller('AccountController', AccountController);

    AccountController.$inject = ['$scope', '$http', '$cookies'];

    function AccountController($scope, $http, $cookies) {

        // Cookie loading
        if ($cookies.get("name") != null && $cookies.get("name") != "")
            $scope.profileName = $cookies.get("name");
        else {
            $scope.profileName = $cookies.get("email");
        }

        // Filling the scope
        $http.get('/users/getOne/' + $cookies.get('email')).success(function (response) {
            $scope.user = response;
        });

        $http.get('/guests/friends/' + $cookies.get("email")).success(function (response) {
            console.log(response);
            $scope.friends = response;
        });


        // Profile change
        $scope.izmeniNalog = function () {
            $http.put('/guests/change', $scope.user);
        };

        // Delete friend
        $scope.pripremaZaBrisanje = function (guest) {
            $scope.zaBrisanje = guest;
        };

        $scope.obrisi = function () {
            $http.get('/guests/deleteFriend/' + $cookies.get('email') + '/' + $scope.zaBrisanje.email).success(function (response) {
                refresh();
            });
        };

        var refresh = function () {
            $http.get('/guests/friends/' + $cookies.get("email")).success(function (response) {
                $scope.friends = response;
            });

            $http.get('/guests/requests/' + $cookies.get('id')).success(function (response) {
                console.log("I got the data I requested!");
                $scope.requests = response;
            });

            $http.get('/guests/addable/' + $cookies.get('id')).success(function (response) {
                console.log("I got the data I requested!");
                $scope.addable = response;
            });
        }

        //refresh();


        //// ------------------------------------------------------------------------------------------
        //// DOVDE URADJENO. --------------------------------------------------------------------------
        //// ------------------------------------------------------------------------------------------

        $scope.dodaj = function (friend_id) {
            console.log('/guests/sendRequest/' + $cookies.get('id') + '/' + friend_id);
            $http.get('/guests/sendRequest/' + $cookies.get('id') + '/' + friend_id).success(function (response) {
                console.log("poslo zahtev!");
                refresh();
            });

        };

        $scope.prikayi = function () {
            console.log($scope.file);
        };

        $scope.prihvati = function (friend_id) {
            //value = "acceptRequest/{id}/{friend}")
            $http.get('/guests/acceptRequest/' + $cookies.get('id') + '/' + friend_id).success(function (response) {
                console.log("poslo zahtev!");
                refresh();
            });

        };


        function dataURItoBlob(dataURI) {
            var binary = atob(dataURI.split(',')[1]);
            var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
            var array = [];
            for (var i = 0; i < binary.length; i++) {
                array.push(binary.charCodeAt(i));
            }
            return new Blob([new Uint8Array(array)], {
                type: mimeString
            });
        }


    }
})();
