<!DOCTYPE html>
<html ng-app="App">
<head>
    <meta charset="UTF-8">
    <title>Setaria 配置管理</title>

    <link rel="stylesheet" href="${rc.contextPath}/vendor/foundation-6.2.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/vendor/font-awesome-4.6.2/css/font-awesome.min.css"/>

    <link rel="stylesheet" href="${rc.contextPath}/vendor/DataTables-1.10.11/datatables.min.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/vendor/chosen-1.5.1/chosen.foundation.min.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/vendor/sweetalert-1.1.1/sweetalert.css"/>

    <script src="${rc.contextPath}/vendor/jquery-2.2.3.min.js"></script>
    <script src="${rc.contextPath}/vendor/angular-1.5.5/angular.min.js"></script>
    <script src="${rc.contextPath}/vendor/angular-1.5.5/angular-resource.min.js"></script>
    <script src="${rc.contextPath}/vendor/ui-router-0.2.18/angular-ui-router.min.js"></script>
    <script src="${rc.contextPath}/vendor/what-input.js"></script>
    <script src="${rc.contextPath}/vendor/foundation-6.2.1/js/foundation.min.js"></script>
    <script src="${rc.contextPath}/vendor/angular-foundation-6-0.9.32/angular-foundation.min.js"></script>

    <script src="${rc.contextPath}/vendor/DataTables-1.10.11/datatables.min.js"></script>
    <script src="${rc.contextPath}/vendor/chosen-1.5.1/chosen.jquery.min.js"></script>
    <script src="${rc.contextPath}/vendor/sweetalert-1.1.1/sweetalert.min.js"></script>
    <script src="${rc.contextPath}/vendor/ng-file-upload-12.0.4/ng-file-upload.min.js"></script>

    <script>
        var app = angular.module('App', ['ui.router', 'ngResource', 'mm.foundation', 'ngFileUpload'])
                .config(function ($controllerProvider, $stateProvider, $urlRouterProvider) {
                    app.controllerProvider = $controllerProvider;

                    $urlRouterProvider.otherwise('/');

                    $stateProvider.state('default', {
                                url: '/',
                                templateUrl: 'default.ftl'
                            })
                            .state('userList', {
                                url: '/users',
                                templateUrl: 'user/user-list.ftl'
                            })
                            .state('userEdit', {
                                url: '/users/:id',
                                templateUrl: 'user/user-list.ftl'
                            })
                            .state('appList', {
                                url: '/apps',
                                templateUrl: 'app/app-list.ftl'
                            });
                })
                ;
    </script>
</head>
<body>
<div class="medium-3 columns">
    <div id="">
        <div>
            <h3>Setaria 配置管理</h3>
        </div>

        <ul class="vertical menu" data-accordion-menu>
            <li><a href="#"><i class="fa fa-home"></i> 首页 </a></li>
            <li><a ui-sref="userList"><i class="fa fa-users"></i> 用户管理</a></li>
            <li><a ui-sref="appList"><i class="fa fa-database"></i> 应用管理</a></li>
            <li><a><i class="fa fa-history"></i> 配置修改历史</a></li>
        </ul>
    </div>
</div>

<div class="medium-9 columns">
    <div class="row">
        <nav class="top-bar" data-topbar role="navigation" data-options="is_hover: false">
            <div class="responsive-menu">
                <div class="top-bar-right">
                    <ul class="dropdown menu" data-dropdown-menu>
                        <li><a href="http://www.baidu.com">修改密码</a></li>
                        <li><a href="#" class="bg-white">注销</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div style="padding-top: 30px;" ui-view></div>
</div>

<script>
    $(document).foundation();
</script>

</body>
</html>
