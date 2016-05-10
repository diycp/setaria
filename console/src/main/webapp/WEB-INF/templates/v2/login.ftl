<div class="row" style="padding-top:10%;" ng-controller="LoginCtrl">
    <div class="large-6 large-centered columns login_content">
        <h3 class="text-center" style="padding-bottom: 5px;">Login Form</h3>

        <input type="email" ng-model="email" placeholder="邮箱" required/>
        <input type="password" ng-model="password" placeholder="密码" required/>
        <a class="expanded button">登 录</a>
    </div>
</div>

<script>
    angular.module('App').controllerProvider.register('LoginCtrl', function ($scope, $state) {
        $scope.email = 'kevin@weghst.com';

        $scope.login = function () {
            $state.go('/');
        }
    });
</script>