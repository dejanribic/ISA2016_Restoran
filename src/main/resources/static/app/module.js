/**
 * Created by Dejan on 3/29/2016.
 * Angular routing
 */
(function () {
    'use strict';

    //noinspection JSUnresolvedFunction
    var App = angular.module('app', ['ngRoute', 'ngCookies']);

    App.config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/restorani', {
            templateUrl: 'html/restorani.html',
            controller: 'RestorauntsController'
        });

        $routeProvider.when('/rezervacije', {
            templateUrl: 'html/rezervacije.html',
            controller: 'ReservationController'
        });

        $routeProvider.when('/pozivnice', {
            templateUrl: 'html/pozivnice.html',
            controller: 'InvitationsController'
        });

        $routeProvider.when('/nalog', {
            templateUrl: 'html/nalog.html',
            controller: 'AccountController'
        });

        $routeProvider.when('/', {
            templateUrl: 'html/login.html',
            controller: 'LoginController'
        });

        $routeProvider.when('/menu-item', {
            templateUrl: 'html/restaurant-administration.html',
            controller: 'MenuItemController'
        });

        //noinspection JSUnresolvedFunction
        $routeProvider.otherwise('/', {
            templateUrl: 'html/login.html',
            controller: 'LoginController'
        });
    }]);
})();
