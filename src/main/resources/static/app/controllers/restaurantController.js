(function () {
    'use strict';

    //noinspection JSUnresolvedFunction
    angular
        .module('app')
        .controller('RestaurantController', RestaurantController);

    RestaurantController.$inject = ['$cookies', '$http', '$scope'];
    function RestaurantController($cookies, $http, $scope) {

        if ($cookies.get("name") != null && $cookies.get("name") != "")
            $scope.profileName = $cookies.get("name");
        else {
            $scope.profileName = $cookies.get("email");
        }

        $http.get('/restaurants/all').success(function (response) {
            $scope.restaurants = response;
        });

        $http.get('/reservations/allInactive/' + $cookies.get('id')).success(function (response) {
            $scope.visited = response;
            $http.get('/invitations/pastVisits/' + $cookies.get('id')).success(function (response) {
                $scope.visited = $scope.visited.concat(response);
            });
        });

        $http.get('/guests/friends/' + $cookies.get('id')).success(function (response) {
            $scope.friends = response;
        });


        $scope.zapocniRezervaciju = function (res) {
            $scope.restoran = res;
            $scope.trajanje = null;
            $scope.datum = null;
            $scope.broj_stola = null;
        };


        var pozvaniPrijatelji = new Array();
        $scope.pozoviPrijatelja = function (id) {
            pozvaniPrijatelji.push(id);
            for (var i = 0; i < $scope.friends.length; i++) {
                if ($scope.friends[i].user_id == id) {
                    $scope.friends.splice(i, 1);
                }
            }
        }

        $scope.korak1enabled = function () {
            return true;
        }


        $scope.rezervisi = function (id) {
            $http.get('/users/getOne/' + $cookies.get('id')).success(function (response) {
                $scope.host = response;
                var rezervacija = {
                    restaurant: $scope.restoran,
                    host: $scope.host,
                    dateTime: $scope.datum,
                    duration: $scope.trajanje
                };
                $http.put('/reservations/add', rezervacija).success(function (response) {
                    for (var i = 0; i < pozvaniPrijatelji.length; i++) {
                        $http.put('/invitations/add/' + pozvaniPrijatelji[i], response).success(function (response) {
                        });
                    }
                });
            });
        };
    }
})();
