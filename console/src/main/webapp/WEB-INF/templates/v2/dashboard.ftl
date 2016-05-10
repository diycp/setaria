<!DOCTYPE html>
<html ng-app="App">
<head>
    <meta charset="UTF-8">
    <title>Setaria 分布式统一配置管理</title>

    <link rel="stylesheet" href="${rc.contextPath}/vendor/foundation-6.2.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/vendor/font-awesome-4.6.2/css/font-awesome.min.css"/>

    <script src="${rc.contextPath}/vendor/jquery-2.2.3.min.js"></script>
    <script src="${rc.contextPath}/vendor/angular-1.5.5/angular.min.js"></script>
    <script src="${rc.contextPath}/vendor/ui-router-0.2.18/angular-ui-router.min.js"></script>
    <script src="${rc.contextPath}/vendor/ocLazyLoad-1.0.9/ocLazyLoad.min.js"></script>
    <script src="${rc.contextPath}/vendor/foundation-6.2.1/js/foundation.min.js"></script>

    <script>
        var app = angular.module('App', ['ui.router', 'oc.lazyLoad'])
                .config(function ($controllerProvider, $stateProvider, $urlRouterProvider) {
                    app.controllerProvider = $controllerProvider;


                    $urlRouterProvider.otherwise('/');
                    $stateProvider.state('/', {
                                url: '/',
                                templateUrl: 'summary.ftl'
                            })
                            .state('login', {
                                url: '/login',
                                templateUrl: 'login.ftl'
                            });
                });

        $(document).foundation();
    </script>
</head>
<body style="background:#F7F7F7;">
<div ui-view></div>
</body>
</html>
