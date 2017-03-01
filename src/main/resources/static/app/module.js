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
            controller: 'RestaurantController'
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

        $routeProvider.when('/employeeManagement', {
            templateUrl: 'html/employee-management.html',
            controller: 'EmployeeManagementController'
        });

        $routeProvider.when('/demand', {
            templateUrl: 'html/demand.html',
            controller: 'DemandController'
        });
        $routeProvider.when('/sysman', {
            templateUrl: 'html/sys-manager-page.html',
            controller: 'RestaurantCreateController'
        });
        $routeProvider.when('/manager', {
            templateUrl: 'html/manager-page.html',
            controller: 'DemandController'
        });
        $routeProvider.when('/resadm', {
            templateUrl: 'html/restaurant-administration.html',
            controller: 'MenuItemController'
        });
        $routeProvider.when('/supplier', {
            templateUrl: 'html/supplier.html',
            controller: 'SupplierController'
        });

        $routeProvider.when('/regionz', {
            templateUrl: 'html/regionz.html',
            controller: 'RegionsController'
        });
        $routeProvider.when('/order', {
            templateUrl: 'html/order.html',
            controller: 'OrderController'
        });

        $routeProvider.when('/racuni', {
            templateUrl: 'html/racuni.html',
            controller: 'RacuniController'
        });

        $routeProvider.when('/raspored', {
            templateUrl: 'html/raspored.html',
            controller: 'RasporedController'
        });

        $routeProvider.when('/first', {
            templateUrl: 'html/first.html',
            controller: 'FirstController'
        });

        //noinspection JSUnresolvedFunction
        $routeProvider.otherwise('/', {
            templateUrl: 'html/login.html',
            controller: 'LoginController'
        });


    }]);
})();
