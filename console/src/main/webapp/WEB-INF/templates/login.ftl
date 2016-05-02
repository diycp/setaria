<!DOCTYPE html>
<html ng-app="LoginApp">
<head>
    <base href="${rc.contextPath}/">
    <meta charset="UTF-8">
    <title>Setaria 统一配置 - 登录</title>
    <link rel="stylesheet" href="amaze-ui/css/amazeui.min.css"/>

    <script src="js/jquery.min.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="amaze-ui/js/amazeui.min.js"></script>
    <script>
        var app = angular.module('LoginApp', []).controller('LoginController', function ($scope, $http, $compile, $templateCache) {
            var emailInput = $('input[type="email"');
            emailInput.focus();

            var store = $.AMUI.store;
            if (store.enabled) {
                var v = store.get('login.email');
                if (v) {
                    $scope.email = v;
                    $scope.rememberMe = true;

                    $('input[type="password"]').focus();
                }
            }

            $scope.login = function () {
                if (store.enabled) {
                    if ($scope.rememberMe) {
                        store.set('login.email', $scope.email);
                    } else {
                        store.remove('login.email');
                    }
                }

                $http.post('p/login', {email: $scope.email, password: $scope.password})
                        .success(function (data) {
                            location.href = 'p/dashboard';
                        }).error(function (data) {
                    if (!data) {
                        return;
                    }

                    alert(data.errorMessage);
                    if (data.errorCode == 10001) {
                        $scope.email = '';
                        $('input[type="email"]').focus();
                    } else if (data.errorCode == 10002) {
                        $('input[type="password"]').focus();
                    }
                    $scope.password = '';
                });
            }
        });
    </script>

</head>
<body>

<div>
    <div class="am-u-md-4 am-u-sm-centered" style="margin-top: 10%">
        <h2>Setaria 统一配置管理 - 登录</h2>

        <form ng-controller="LoginController" class="am-form" ng-submit="login()">
            <div class="am-form-group am-form-icon">
                <i class="am-icon-user"></i>
                <input type="email" ng-model="email" class="am-form-field" placeholder="邮箱" required/>
            </div>

            <div class="am-form-group am-form-icon">
                <i class="am-icon-key"></i>
                <input type="password" ng-model="password" class="am-form-field" placeholder="密码" required/>
            </div>

            <div class="am-form-group">
                <label for="rememberMe" class="am-checkbox-inline">
                    <input ng-model="rememberMe" id="rememberMe" type="checkbox"/>
                    记住用户名
                </label>
            </div>
            <input type="submit" value="登 录" class="am-btn am-btn-primary am-btn-block"/>
        </form>
    </div>
</div>
</body>
</html>
