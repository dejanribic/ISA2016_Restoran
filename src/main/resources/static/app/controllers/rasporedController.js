/**
 * Created by Viktor on 2/28/2017.
 */


(function () {
    'use strict';

    angular
        .module('app')
        .controller('RasporedController', RasporedController);

    RasporedController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function RasporedController($cookies, $scope, $http, $location) {

        $scope.etype = $cookies.get('etype');
        $scope.rname = $cookies.get('rname');

        $http.get('/raspored/getEmployee/'+$cookies.get('email')).success(function (emp) {
            $http.get('/raspored/smene/'+emp.email+'/'+emp.restaurantName).success(function (response) {
                var smena;
                var i;
                $scope.smene = [];
                for (i = 0; i < response.length; i++) {
                    smena = {
                        color: "#64e6ff",
                        title: "Raspored",
                        start: new Date(response[i].start),
                        end: new Date(response[i].end)
                    };

                    $scope.smene.push(smena);
                }
                $('#timetable').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month, agendaWeek, agendaDay'
                    },
                    editable: false,
                    events: $scope.smene
                });

            });
        });
    }
})();
