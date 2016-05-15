<!DOCTYPE html>
<html ng-app="App">
<head>
    <meta charset="UTF-8">
    <title>Setaria 配置管理 - 登录</title>

    <link rel="stylesheet" href="${rc.contextPath}/vendor/foundation-6.2.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/vendor/font-awesome-4.6.2/css/font-awesome.min.css"/>

    <script src="${rc.contextPath}/vendor/jquery-2.2.3.min.js"></script>
    <script src="${rc.contextPath}/vendor/jquery.storageapi.min.js"></script>
    <script src="${rc.contextPath}/vendor/angular-1.5.5/angular.min.js"></script>

    <script>
        var app = angular.module('App', []).controller('LoginCtrl', function ($scope, $http) {
            var ns = $.initNamespaceStorage('setaria.console');
            if (ns.localStorage.isSet('login.rememberMe')) {
                $scope.rememberMe = ns.localStorage.get('login.rememberMe');
            }

            if ($scope.rememberMe) {
                $scope.email = ns.localStorage.get('login.email');
                $('#password').focus();
            } else {
                $('#email').focus();
            }

            $scope.login = function () {
                if ($scope.rememberMe) {
                    ns.localStorage.set('login.email', $scope.email);
                    ns.localStorage.set('login.rememberMe', $scope.rememberMe);
                } else {
                    ns.localStorage.remove('login.email');
                    ns.localStorage.remove('login.rememberMe');
                }
            }
        });
    </script>
</head>
<body>
<div class="row" style="padding-top:10%;" ng-controller="LoginCtrl">
    <div class="medium-6 medium-centered columns">
        <h3 class="text-center" style="padding-bottom: 5px;">Login Form</h3>

        <form ng-submit="login()">
            <input type="email" id="email" ng-model="email" placeholder="邮箱" required/>
            <input type="password" id="password" ng-model="password" placeholder="密码" required/>
            <input type="checkbox" id="rememberMe" ng-model="rememberMe"/><label for="rememberMe">记住邮箱</label>
            <button type="submit" class="expanded button">登 录</button>
        </form>
    </div>
</div>
</body>
</html>
