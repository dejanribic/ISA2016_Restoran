(function () {
    'use strict';

    angular
        .module('app')
        .controller('ReservationController', ReservationController);

    ReservationController.$inject = ['$cookies', '$http', '$scope'];
    function ReservationController($cookies, $http, $scope) {

        Date.prototype.toDateInputValue = (function () {
            var local = new Date(this);
            local.setDate(local.getDate() + 1);
            // Offset vremenske zone
            //local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
            return local
        });

        //var datum = $scope.datum;
        //$scope.datum.ispis = (datum.getDate() + '/' + datum.getMonth() + '/' + datum.getFullYear() + '/' + datum.getTime());

        $scope.reservationsWithInvitations = new Array();

        var refresh = function () {
            $http.get('/reservations/allActive/' + $cookies.get('email')).success(function (response) {

                $scope.reservations = response;
                console.log(response);

                var currRes = response;
                for (var i = 0; i < response.length; i++) {

                    var datum = currRes.start;
                    currRes.start.ispis = (datum.getDate() + '/' + datum.getMonth() + '/' + datum.getFullYear() + '/' + datum.getTime());
                    $scope.reservationsWithInvitations.push(currRes[i]);
                    console.log(currRes[i]);
                }


                /*
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


                 */
            });
            console.log($scope.reservationsWithInvitations);
        }

        var currentReservation;
        $scope.getInvitable = function (res) {

            //OVDE SKINUTI currRes.start.ispis

            // delete res.start.ispis

            // var propertyToDelete = "ispis";
            // delete res[ispis];

            currentReservation = res;
            $http.put('/invitations/invitableFriends', res).success(function (response) {
                console.log("I got the data I requested!");
                $scope.invitable = response;
            });
        }


        $scope.invite = function (invited_id) {
            $http.put('/invitations/add/' + invited_id, currentReservation).success(function (response) {
                console.log("I got the data I requested!");
                $scope.getInvitable(currentReservation);
            });
            refresh();
        }
        refresh();
    }
})();
