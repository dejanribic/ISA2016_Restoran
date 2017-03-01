/**
 * Created by Nenad on 2/28/2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('EmployeeManagementController', EmployeeManagementController);

    EmployeeManagementController.$inject = ['$cookies', '$scope', '$http', '$location'];

    function EmployeeManagementController($cookies, $scope, $http, $location) {
        $http.get('empman/allWithEarningAndRating').success(function (response) {
            $scope.employees = response;
            $http.get('empman/getSchedules').success(function (response) {
                $scope.schedules = response;
                $scope.dp = new DayPilot.Scheduler("dp");
                $scope.dp.init();
                $scope.dp.treeEnabled = true;
                $scope.dp.resources = makeResources();
                $scope.dp.events.list = makeEvents();
                $scope.dp.onTimeRangeSelected = addSchEvent;
                $scope.schDate.setHours(1,0,0,0);
                $scope.dp.eventMoveHandling = 'Disabled';
                $scope.dp.startDate = new DayPilot.Date($scope.schDate);
                $scope.dp.days = $scope.dp.startDate.daysInMonth();
                $scope.dp.update();

            });
        });

        var addSchEvent = function (args) {
            $scope.dp.events.list.push({
                "id": $scope.dp.events.list.length + 1,
                "text": "Region",
                "start": args.start.value,
                "end": args.end.value,
                "resource": args.resource,
                "bubbleHtml": "Event details: <br\/>Task 1"

            });
            $scope.dp.update();

        };

        $scope.saveSchChanges = function () {
            var i = 0;
            var saveList = [];
            for (i = 0; i < $scope.dp.events.list.length; i++) {
                saveList.push({
                    "num": $scope.dp.events.list[i].id,
                    "start": new Date($scope.dp.events.list[i].start).getTime(),
                    "end": new Date($scope.dp.events.list[i].end).getTime(),
                    "email": $scope.dp.events.list[i].resource,
                    "id": undefined
                });
            }
            $http.put("/empman/addSchedules", saveList).success(function (response) {
                    alert(response.message);
                    $http.get('empman/allWithEarningAndRating').success(function (response) {
                        $scope.employees = response;
                        $http.get('empman/getSchedules').success(function (response) {
                            $scope.schedules = response;
                            $scope.dp = new DayPilot.Scheduler("dp");
                            $scope.dp.init();
                            $scope.dp.treeEnabled = true;
                            $scope.dp.resources = makeResources();
                            $scope.dp.events.list = makeEvents();
                            $scope.dp.onTimeRangeSelected = addSchEvent;
                            $scope.schDate.setHours(1,0,0,0);
                            $scope.dp.eventMoveHandling = 'Disabled';
                            $scope.dp.startDate = new DayPilot.Date($scope.schDate);
                            $scope.dp.days = $scope.dp.startDate.daysInMonth();
                            $scope.dp.update();

                        });
                    });
            });


        };

        $scope.schedulerConfig = {
            scale: "Day",
            days: new DayPilot.Date().daysInMonth(),
            startDate: new DayPilot.Date().firstDayOfMonth(),
            timeHeaders: [
                {groupBy: "Month"},
                {groupBy: "Day", format: "d"}
            ]
        };

        $scope.createEmployee = function () {
            $http.put('/empman/create', $scope.employee).success(function (response) {
                reloadEmployees();
            })
        };

        $scope.schDate = new Date();



        var makeResources = function () {
            var ret = [];
            $scope.employees.forEach(function (emp, ind) {
                ret.push({'id': emp.employee.email, 'name': emp.employee.email});
            });
            return ret;

        }

        var makeEvents = function () {
            var ret = [];
            $scope.schedules.forEach(function (sch, ind) {
                ret.push({
                    "id": sch.num,
                    "text": "Region" + sch.id,
                    "start": new DayPilot.Date(new Date(sch.start)),
                    "end": new DayPilot.Date(new Date(sch.end)),
                    "resource": sch.email,
                });
            });
            return ret;

        }

        var reloadEmployees = function () {

        }

    }


})();