(function () {
    'use strict';

    //noinspection JSUnresolvedFunction
    angular
        .module('app')
        .controller('ReservationController', ReservationController);

    ReservationController.$inject = ['$cookies', '$http', '$scope', '$window', '$location'];
    function ReservationController($cookies, $http, $scope, $window, $location) {

        if ($cookies.get("name") != null && $cookies.get("name") != "")
            $scope.profileName = $cookies.get("name");
        else {
            $scope.profileName = $cookies.get("email");
        }

        $scope.reservationsWithInvitations = [];

        var refresh = function () {
            $http.get('/reservations/allActive/' + $cookies.get('email')).success(function (response) {

                $scope.reservations = response;

                var currRes = response;

                for (var i = 0; i < response.length; i++) {
                    (function (i) {
                        $http.put('/invitations/getInvited', currRes[i]).success(function (response) {
                            $scope.reservationsWithInvitations.push({
                                reservation: currRes[i],
                                invitations: response
                            });
                        });
                    })(i);
                }
            });
        };

        var fillNames = function () {
            for (var i = 0; i < $scope.reservationsWithInvitations.length; i++) {
                for (var j = 0; j < $scope.reservationsWithInvitations[i].invitations.length; j++) {
                    //noinspection JSUnresolvedVariable
                    var email = $scope.reservationsWithInvitations[i].invitations[j].friendEmail;
                    $http.get('/users/getOne/' + email).success(function (response) {
                        var ime = response.name;
                        var prezime = response.surname;
                        //console.log(ime + ' ' + prezime);
                        $scope.reservationsWithInvitations[i].invitations[j].fullName = ime + ' ' + prezime;
                    });
                }
            }
        };

        refresh();
        //setTimeout(fillNames, 5000);

        var currentReservation;

        $scope.getInvitable = function (res) {
            currentReservation = res;
            $http.put('/invitations/invitableFriends', res).success(function (response) {
                $scope.invitable = response;
            });
        };

        $scope.cancel = function (res) {
            $http.put('/reservations/delete', res).success(function () {
                $window.location.reload();
            });
        };

        $scope.showDate = function (date) {
            return Math.round((((new Date(date) - new Date()) % 86400000) % 3600000) / 60000) < 30;
        };

        $scope.invite = function (invited_email) {
            $http.put('/invitations/add/' + invited_email, currentReservation).success(function () {
                $scope.getInvitable(currentReservation);
            });
            $window.location.reload();
        };

        // Log out
        $scope.logout = function () {
            //noinspection JSUnresolvedFunction
            var cookies = $cookies.getAll();
            angular.forEach(cookies, function (v, k) {
                $cookies.remove(k);
            });
            $location.url('/');
        };
    }
})();
