(function () {
    'use strict';

    angular
        .module('app')
        .controller('InvitationsController', InvitationsController);

    InvitationsController.$inject = ['$scope', '$http', '$cookies'];

    function InvitationsController($scope, $http, $cookies) {

        var refresh = function () {
            $http.get('/invitations/activeNotConfirmed/' + $cookies.get('id')).success(function (response) {
                $scope.notConfirmed = response;
            });

            $http.get('/invitations/activeConfirmed/' + $cookies.get('id')).success(function (response) {
                $scope.confirmed = response;
            });

        }

        refresh();

        $scope.potvrdi = function (pozivnica) {
            $http.put('/invitations/confirmInvitation', pozivnica).success(function (response) {
                refresh();
            });
        };
    }
})();
