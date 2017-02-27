(function () {
    'use strict';

    angular
        .module('app')
        .controller('ReservationController', ReservationController);

    ReservationController.$inject = ['$cookies', '$http', '$scope'];
    function ReservationController($cookies, $http, $scope) {

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

        //var datum = $scope.datum;
        //$scope.datum.ispis = (datum.getDate() + '/' + datum.getMonth() + '/' + datum.getFullYear() + '/' + datum.getTime());

        $scope.reservationsWithInvitations = new Array();

        var refresh = function () {
            $http.get('/reservations/allActive/' + $cookies.get('email')).success(function (response) {

                $scope.reservations = response;

                var currRes = response;
                /*
                 for (var i = 0; i < response.length; i++) {


                 var datum = new Date(currRes[i].start);
                 currRes[i].ispisDatuma = (datum.getDate() + '/' + datum.getMonth() + '/' + datum.getFullYear() + '/' + datum.getHours() + ':' + datum.getMinutes() + ':' + datum.getSeconds());
                 $scope.reservationsWithInvitations.push(currRes[i]);
                 }
                 */

                for (var i = 0; i < response.length; i++) {
                    //console.log('Usli smo u rezervaciju broj: ' + i);

                    (function (i) {
                        $http.put('/invitations/getInvited', currRes[i]).success(function (response) {

                            //console.log(response2);

                            for (var j = 0; j < response.length; j++) {
                                //noinspection JSUnresolvedVariable
                                //console.log(response2[j].friendEmail);

                                //noinspection JSUnresolvedVariable
                                $http.get('/users/getOne/' + response[j].friendEmail).success(function (response) {
                                    //console.log(response);
                                    var ime = response.name;
                                    var prezime = response.surname;
                                    console.log(ime + prezime);
                                    //response2[j].ime = ime;
                                    //response2[j].prezime = surname;
                                });
                            }

                            //noinspection JSUnusedAssignment
                            var datum = new Date(currRes[i].start);
                            currRes[i].ispisDatuma = (datum.getDate() + '/' + datum.getMonth() + '/' + datum.getFullYear() + '/' + datum.getHours() + ':' + datum.getMinutes() + ':' + datum.getSeconds());


                            $scope.reservationsWithInvitations.push({
                                reservation: currRes[i],
                                invitations: response
                            });
                        });
                    })(i);
                }
            });
            console.log($scope.reservationsWithInvitations);
        }

        var currentReservation;

        $scope.getInvitable = function (res) {

            //OVDE SKINUTI currRes.start.ispis

            // delete res.ispisDatuma

            // var propertyToDelete = "ispisDatuma";
            // delete res[propertyToDelete];

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
