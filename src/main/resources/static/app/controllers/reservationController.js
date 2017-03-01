(function () {
    'use strict';

    angular
        .module('app')
        .controller('ReservationController', ReservationController);

    ReservationController.$inject = ['$cookies', '$http', '$scope', '$route', '$window', "$location"];
    function ReservationController($cookies, $http, $scope, $route, $window, $location) {

        if ($cookies.get("name") != null && $cookies.get("name") != "")
            $scope.profileName = $cookies.get("name");
        else {
            $scope.profileName = $cookies.get("email");
        }

        $scope.reservationsWithInvitations = new Array();

        var refresh = function () {
            $http.get('/reservations/allActive/' + $cookies.get('email')).success(function (response) {

                $scope.reservations = response;

                var currRes = response;

                /*
                 var x = new Array(1000);
                 for (var i = 0; i < 1000; i++) {
                 x[i] = new Array(20);
                 }*/

                for (var i = 0; i < response.length; i++) {
                    //console.log('Usli smo u rezervaciju broj: ' + i);

                    (function (i) {
                        $http.put('/invitations/getInvited', currRes[i]).success(function (response) {

                            //console.log(response);

                            /*
                             for (var j = 0; j < response.length; j++) {
                             //noinspection JSUnresolvedVariable
                             //console.log(response[j].friendEmail);

                             var ime = null;
                             var prezime = null;

                             //noinspection JSUnresolvedVariable
                             $http.get('/users/getOne/' + response[j].friendEmail).success(function (response) {
                             //console.log(response);
                             //ime = response.name;
                             //prezime = response.surname;
                             //console.log(ime + prezime);
                             });

                             response[j].ime = ime;
                             response[j].prezime = prezime;
                             }

                             */

                            //noinspection JSUnusedAssignment
                            //var datum = new Date(currRes[i].start);
                            //console.log(datum);

                            //currRes[i].ispisDatuma = (datum.getDate() + '/' + datum.getMonth() + '/' + datum.getFullYear() + '/' + datum.getHours() + ':' + datum.getMinutes() + ':' + datum.getSeconds());
                            //console.log(currRes[i].ispisDatuma);

                            $scope.reservationsWithInvitations.push({
                                reservation: currRes[i],
                                invitations: response
                            });
                        });
                    })(i);
                }
            });

            /*
             var k = $scope.reservationsWithInvitations.length;
             //console.log(k);

             for (var brojac1 = 0; brojac1 < k; brojac1++) {

             var p = $scope.reservationsWithInvitations[brojac1].invitations.length;
             console.log(p);

             for (var brojac2 = 0; brojac2 < p; p++) {

             console.log('invite #: ' + $scope.reservationsWithInvitations[brojac1].invitations[brojac2].friendEmail);

             $http.get('/users/getOne/' + $scope.reservationsWithInvitations[brojac1].invitations[brojac2].friendEmail).success(function (response) {
             console.log(response);
             $scope.reservationsWithInvitations[brojac1].invitations[brojac2].ime = response.name;
             $scope.reservationsWithInvitations[brojac1].invitations[brojac2].prezime = response.surname;
             });
             }
             }
             */

            console.log($scope.reservationsWithInvitations);
        }

        refresh();

        var currentReservation;

        $scope.getInvitable = function (res) {

            console.log(res);

            //OVDE SKIDAMO ispisDatuma
            var newRes = jQuery.extend({}, res);
            delete newRes.ispisDatuma;

            console.log(newRes);

            //currentReservation = res;
            currentReservation = newRes;

            $http.put('/invitations/invitableFriends', newRes).success(function (response) {
                $scope.invitable = response;
            });
        }

        $scope.invite = function (invited_email) {
            $http.put('/invitations/add/' + invited_email, currentReservation).success(function (response) {
                $scope.getInvitable(currentReservation);
            });
            $window.location.reload();
        }

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
