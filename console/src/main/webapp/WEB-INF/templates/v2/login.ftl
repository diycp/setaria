<!DOCTYPE html>
<html ng-app="App">
<head>
    <meta charset="UTF-8">
    <title>Setaria 分布式统一配置管理</title>

    <link rel="stylesheet" href="${rc.contextPath}/vendor/foundation-6.2.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/vendor/font-awesome-4.6.2/css/font-awesome.min.css"/>

    <script src="${rc.contextPath}/vendor/jquery-2.2.3.min.js"></script>
    <script src="${rc.contextPath}/vendor/angular-1.5.5/angular.min.js"></script>
    <script src="${rc.contextPath}/vendor/ngStorage-0.3.1.min.js"></script>
    <script src="${rc.contextPath}/vendor/foundation-6.2.1/js/foundation.min.js"></script>

    <script>
        var app = angular.module('App', ['ngStorage'])
                .controller('LoginCtrl', function ($scope, $localStorage) {
                    $('#email').focus();

                    $scope.rememberMe = $localStorage['login.rememberMe'];
                    if ($scope.rememberMe) {
                        $scope.email = $localStorage['login.lastEmail'];
                        $('#password').focus();
                    }

                    $scope.login = function () {
                        if ($scope.rememberMe) {
                            $localStorage['login.lastEmail'] = $scope.email;
                            $localStorage['login.rememberMe'] = true;
                        } else {
                            $localStorage['login.lastEmail'] = null;
                            $localStorage['login.rememberMe'] = null;
                        }
                    }
                });

        $(document).foundation();
    </script>
</head>
<body style="background:#F7F7F7;">
<div>
    <div class="row" style="padding-top:10%;" ng-controller="LoginCtrl">
        <form ng-submit="login()">
            <div class="large-6 large-centered columns login_content">
                <h3 class="text-center" style="padding-bottom: 5px;">Login Form</h3>

                <input type="email" id="email" ng-model="email" placeholder="邮箱" required/>
                <input type="password" id="password" ng-model="password" placeholder="密码" required/>
                <input type="checkbox" id="rememberMe" ng-model="rememberMe"/>
                <label for="rememberMe">记住邮箱</label>
                <button type="submit" class="expanded button">登 录</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
