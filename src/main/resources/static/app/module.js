/**
 * Created by dan.geabunea on 3/29/2016.
 */
(function () {
    'use strict';

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

        $routeProvider.when('/register', {
            templateUrl: 'html/register.html',
            controller: 'LoginController'
        });

        $routeProvider.otherwise({});
        //$routeProvider.otherwise('/', {
        //  templateUrl: 'html/login.html',
        //  controller: 'LoginController'
        //});
    }]);
})();
