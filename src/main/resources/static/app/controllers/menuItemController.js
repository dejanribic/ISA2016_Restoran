/**
 * Created by Nenad on 2/23/2017.
 * Controller for menu items
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('MenuItemController', MenuItemController);

    MenuItemController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function MenuItemController($cookies, $scope, $http, $location) {

        $http.get('/menu-item/all').success(function (response) {
            $scope.menuItems = response;
        });

        $scope.create = function () {
            $http.put('/menu-item/create', $scope.menuItem).success(reloadMenuItems);
        };

        $scope.remove = function (toRemove) {
            console.log(toRemove)
            $http.put('/menu-item/remove', toRemove).success(reloadMenuItems);
        };

        var reloadMenuItems = function(){
            $http.get('/menu-item/all').success(function (response) {
                $scope.menuItems = response;
            });

        }



    }



})();

