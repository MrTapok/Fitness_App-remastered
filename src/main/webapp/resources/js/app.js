var app = angular.module('app', []);
var config = {headers: {'Content-Type': 'application/json;charset=utf-8;'}};

app.controller('getController', function ($scope, $http, $location, $window) {

    $scope.getUsername = function () {
        var url = "getUsername";
        $http.get(url, config).then(function (response) {
            $scope.username = response.data;
        });
    };

    $scope.getCurrentUser = function () {
        var url = "getCurrentUser";
        $http.get(url, config).then(function (response) {
            $scope.user = response.data;
        });
    };

    $scope.getUsers = function () {
        var url = "getAllUsers";
        $http.get(url, config).then(function (response) {
            $scope.users = response.data;
        });
    };

    $scope.getTrainers = function () {
        var url = "getAllTrainers";
        $http.get(url, config).then(function (response) {
            $scope.trainers = response.data;
        });
    };

    $scope.getWorkoutTypes = function () {
        var url = "getAllTypes";
        $http.get(url, config).then(function (response) {
            $scope.types = response.data;
        });
    };

    $scope.getPasses = function () {
        var url = "getAllPasses";
        $http.get(url, config).then(function (response) {
            $scope.passes = response.data;
        });
    };

    $scope.getWorkouts = function () {
        var url = "getAllWorkouts";
        $http.get(url, config).then(function (response) {
            $scope.workouts = response.data;
        });
    };
});

app.controller('postController', function ($scope, $http, $location, $window) {

    $scope.submitRegistration = function () {
        var url = "submitRegistration";
        var data = {
            name: $scope.name,
            password: $scope.password
        };
        if ($scope.name == null || $scope.password == null)
            $scope.postResultMessage = "Введите имя пользователя и пароль";
        $http.post(url, data, config).then(function (response) {
            $scope.answer = response.data;
            if ($scope.answer === "true") {
                $window.location.href = '/login';
            } else {
                $scope.postResultMessage = "Такой пользователь уже существует";
            }
        });
    };

    var id;
    $scope.setId = function (Id) {
        id = Id;
    };
    $scope.addWorkoutToUser = function () {
        var url = "addWorkoutToUser";
        var data = {
            id: id
        };
        $http.post(url, data, config).then(function (response) {
            $window.location.href = '/home';
        });
    };

    var id;
    $scope.setId = function (Id) {
        id = Id;
    };
    $scope.addPassToUser = function () {
        var url = "addPassToUser";
        var data = {
            id: id
        };
        $http.post(url, data, config).then(function (response) {
            $window.location.href = '/home';
        });
    };

    $scope.deleteUser = function () {
        var url = "deleteUser";
        var data = {
            id: id
        };
        $http.post(url, data, config).then(function (response) {
            $window.location.href = '/admin';
        });
    };

    $scope.goToLogin = function () {
        $window.location.href = '/login';
    };

    $scope.goToInformation = function () {
        $window.location.href = '/information';
    };


    $scope.addTrainer = function () {
        var url = "addTrainer";
        var data = {
            name: $scope.name,
            phone: $scope.phone
        };
        $http.post(url, data, config).then(function (response) {
            $window.location.href = '/admin';
        });
    };

    $scope.addManager = function () {
        var url = "addManager";
        var data = {
            name: $scope.name,
            password: $scope.password
        };
        $http.post(url, data, config).then(function (response) {
            $window.location.href = '/admin';
        });
    };

});
