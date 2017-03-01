(function () {
    'use strict';

    //noinspection JSUnresolvedFunction
    angular
        .module('app')
        .controller('RestaurantController', RestaurantController);

    RestaurantController.$inject = ['$cookies', '$http', '$scope', '$location'];
    function RestaurantController($cookies, $http, $scope, $location) {

        $scope.etype = $cookies.get('etype');
        if ($cookies.get("name") != null && $cookies.get("name") != "")
            $scope.profileName = $cookies.get("name");
        else {
            $scope.profileName = $cookies.get("email");
        }

        var pozvaniPrijatelji = new Array();

        $http.get('/restaurants/all').success(function (response) {
            $scope.restaurants = response;
        });

        $http.get('/guests/friends/' + $cookies.get("email")).success(function (response) {
            $scope.friends = response;
        });

        $http.get('/reservations/allInactive/' + $cookies.get('email')).success(function (response) {
            $scope.visited = response;
            console.log(response);
            /*
             $http.get('/invitations/pastVisits/' + $cookies.get('email')).success(function (response) {
             $scope.visited = $scope.visited.concat(response);
             });
             */
        });

        $scope.zapocniRezervaciju = function (res) {
            $scope.restoran = res;
            $scope.trajanje = '1';
            $scope.broj_stola = '1';

            $scope.datum = new Date();
            console.log($scope.datum);
        }

        $scope.pozoviPrijatelja = function (email) {
            pozvaniPrijatelji.push(email);
            for (var i = 0; i < $scope.friends.length; i++) {
                if ($scope.friends[i].email == email) {
                    $scope.friends.splice(i, 1);
                }
            }
        }

        $scope.rezervisi = function () {
            $http.get('/users/getOne/' + $cookies.get('email')).success(function (response) {
                $scope.host = response;

                var rezervacija = {
                    restaurantName: $scope.restoran.name,
                    guestEmail: $scope.host.email,
                    start: $scope.datum,
                    forh: $scope.trajanje,
                    id: null
                };

                $http.put('/reservations/add', rezervacija).success(function (response) {
                    for (var i = 0; i < pozvaniPrijatelji.length; i++) {
                        $http.put('/invitations/add/' + pozvaniPrijatelji[i], response).success(function (response) {
                            pozvaniPrijatelji = [];
                            $http.get('/guests/friends/' + $cookies.get("email")).success(function (response) {
                                $scope.friends = response;
                            });
                        });
                    }
                });
            });
        };

        // JQUERY SEATING

        var firstSeatLabel = 1;

        $(document).ready(function () {
            var $cart = $('#selected-seats'),
                $counter = $('#counter'),
                $total = $('#total'),
                sc = $('#seat-map').seatCharts({
                    map: [
                        'aaaaaa',
                        'aaaaaa',
                        'aaaaaa',
                        'aaaaaa',
                    ],
                    seats: {
                        a: {
                            price: 100,
                            classes: 'first-class', //your custom CSS class
                            category: 'First Class'
                        },
                    },
                    naming: {
                        top: false,
                        getLabel: function (character, row, column) {
                            return firstSeatLabel++;
                        },
                    },
                    legend: {
                        node: $('#legend'),
                        items: [
                            ['a', 'available', 'Open'],
                            ['f', 'unavailable', 'Reserved']
                        ]
                    },
                    click: function () {
                        if (this.status() == 'available') {
                            //let's create a new <li> which we'll add to the cart items
                            $('<li>' + this.data().category + ' Seat # ' + this.settings.label + ': <b>$' + this.data().price + '</b> <a href="#" class="cancel-cart-item">[cancel]</a></li>')
                                .attr('id', 'cart-item-' + this.settings.id)
                                .data('seatId', this.settings.id)
                                .appendTo($cart);

                            /*
                             * Lets update the counter and total
                             *
                             * .find function will not find the current seat, because it will change its stauts only after return
                             * 'selected'. This is why we have to add 1 to the length and the current seat price to the total.
                             */
                            //$counter.text(sc.find('selected').length + 1);
                            //$total.text(recalculateTotal(sc) + this.data().price);

                            return 'selected';
                        } else if (this.status() == 'selected') {
                            //update the counter
                            $counter.text(sc.find('selected').length - 1);
                            //and total
                            $total.text(recalculateTotal(sc) - this.data().price);

                            //remove the item from our cart
                            $('#cart-item-' + this.settings.id).remove();

                            //seat has been vacated
                            return 'available';
                        } else if (this.status() == 'unavailable') {
                            //seat has been already booked
                            return 'unavailable';
                        } else {
                            return this.style();
                        }
                    }
                });

            //this will handle "[cancel]" link clicks
            $('#selected-seats').on('click', '.cancel-cart-item', function () {
                //let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
                sc.get($(this).parents('li:first').data('seatId')).click();
            });

            //let's pretend some seats have already been booked
            sc.get(['1_2', '2_3', '4_5']).status('unavailable');
            //sc.get(['1_2', '4_1', '7_1', '7_2']).status('unavailable');
        });

        function recalculateTotal(sc) {
            var total = 0;

            //basically find every selected seat and sum its price
            sc.find('selected').each(function () {
                total += this.data().price;
            });

            return total;
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
