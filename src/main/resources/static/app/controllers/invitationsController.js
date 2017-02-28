(function () {
    'use strict';

    angular
        .module('app')
        .controller('InvitationsController', InvitationsController);

    InvitationsController.$inject = ['$scope', '$http', '$cookies', '$location'];

    function InvitationsController($scope, $http, $cookies, $location) {

        if ($cookies.get("name") != null && $cookies.get("name") != "")
            $scope.profileName = $cookies.get("name");
        else {
            $scope.profileName = $cookies.get("email");
        }

        Date.prototype.toDateInputValue = (function () {
            var local = new Date(this);
            local.setDate(local.getDate() + 1);
            // Offset vremenske zone
            //local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
            return local
        });

        $scope.notConfirmed = new Array();
        $scope.confirmed = new Array();

        var refresh = function () {


            $http.get('/invitations/activeNotConfirmed/' + $cookies.get('email')).success(function (response) {

                var allNotConfirmed = response;

                for (var i = 0; i < allNotConfirmed.length; i++) {
                    (function (i) {
                        $http.get('/users/getOne/' + allNotConfirmed[i].guestEmail).success(function (response) {
                            allNotConfirmed[i].fullName = (response.name + ' ' + response.surname);
                            $http.get('/reservations/getOne/' + allNotConfirmed[i].reservationId).success(function (response) {
                                var datum = new Date(response.start);
                                allNotConfirmed[i].ispisDatum = (datum.getDate() + '/' + datum.getMonth() + '/' + datum.getFullYear() + '/' + datum.getHours() + ':' + datum.getMinutes() + ':' + datum.getSeconds());
                            });
                        });
                        $scope.notConfirmed.push(allNotConfirmed[i]);
                    })(i);
                }
            });

            $http.get('/invitations/activeConfirmed/' + $cookies.get('email')).success(function (response) {

                var allConfirmed = response;

                for (var i = 0; i < allConfirmed.length; i++) {
                    (function (i) {
                        $http.get('/users/getOne/' + allConfirmed[i].guestEmail).success(function (response) {
                            allConfirmed[i].fullName = (response.name + ' ' + response.surname);
                            $http.get('/reservations/getOne/' + allConfirmed[i].reservationId).success(function (response) {
                                var datum = new Date(response.start);
                                allConfirmed[i].ispisDatum = (datum.getDate() + '/' + datum.getMonth() + '/' + datum.getFullYear() + '/' + datum.getHours() + ':' + datum.getMinutes() + ':' + datum.getSeconds());
                            });
                        });
                        $scope.confirmed.push(allConfirmed[i]);
                    })(i);
                }
            });
        }

        refresh();

        $scope.potvrdi = function (invite) {
            $http.put('/invitations/confirmInvite', invite).success(function () {
                $scope.notConfirmed = new Array();
                $scope.confirmed = new Array();
                refresh();
            });
        };

        $scope.otkazi = function (invite) {
            $http.put('/invitations/cancelInvite', invite).success(function () {
                $scope.notConfirmed = new Array();
                $scope.confirmed = new Array();
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
