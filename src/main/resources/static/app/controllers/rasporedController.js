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


        $('#timetable').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month, agendaWeek, agendaDay'
            },
            editable: false/*,
             events: $scope.shifts*/
        });


    }
})();
