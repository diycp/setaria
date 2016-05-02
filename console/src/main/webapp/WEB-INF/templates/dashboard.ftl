<!DOCTYPE html>
<html ng-app="App">
<head>
    <base href="${rc.contextPath}/">
    <meta charset="UTF-8">
    <title>Setaria 统一配置管理</title>

    <!-- 基础库 -->
    <script src="js/jquery.min.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/angular-ui-router.min.js"></script>
    <script src="js/dateFormat.js"></script>
    <script src="js/ocLazyLoad.min.js"></script>
    <script src="js/jquery.ellipsis.js"></script>

    <!-- Amaze UI 基础 -->
    <link rel="stylesheet" href="amaze-ui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="css/admin.css">
    <script src="amaze-ui/js/amazeui.min.js"></script>

    <!-- Amaze UI DataTables plugins -->
    <link rel="stylesheet" href="amaze-ui/plugins/datatables/amazeui.datatables.min.css"/>
    <script src="amaze-ui/plugins/datatables/amazeui.datatables.min.js"></script>
    <script src="amaze-ui/plugins/datatables/dataTables.responsive.min.js"></script>

    <!-- Amaze UI Chosen plugins -->
    <link rel="stylesheet" href="amaze-ui/plugins/chosen/amazeui.chosen.css"/>
    <script src="amaze-ui/plugins/chosen/amazeui.chosen.min.js"></script>

    <!-- Amaze UI Confirm plugins -->
    <link rel="stylesheet" href="amaze-ui/plugins/jquery-confirm/jquery.confirm.css"/>
    <script src="amaze-ui/plugins/jquery-confirm/jquery.confirm.js"></script>

    <script>

        function noCacheTemplateUrl(url) {
            return function () {
                return url + "?_=" + Date.now();
            };
        }

        var app = angular.module('App', ['ui.router']).config(function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');
            $stateProvider
                    .state('home', {url: '/', templateUrl: 'p/view?v=summary'})
                    .state('users', {url: '/users', templateUrl: noCacheTemplateUrl('p/users/list.v')})
                    .state('user-add', {url: '/user-add', templateUrl: noCacheTemplateUrl('p/users/add.v')})
                    .state('user-edit', {url: '/user-edit/:id', templateUrl: noCacheTemplateUrl('p/users/edit.v')})
                    .state('user-update-password', {
                        url: '/user-update-password', templateUrl: 'p/view?v=user-update-password'
                    })
                    .state('apps', {url: '/apps', templateUrl: noCacheTemplateUrl('p/apps/list.v')})
                    .state('app-add', {url: '/app-add', templateUrl: noCacheTemplateUrl('p/apps/add.v')})
                    .state('app-edit', {url: '/app-edit/:id', templateUrl: noCacheTemplateUrl('p/apps/edit.v')})
                    .state('config-app', {
                        url: '/configs/:appId', templateUrl: function ($stateParams) {
                            return 'p/configs/list.v?appId=' + $stateParams.appId + "&_=" + Date.now();
                        }
                    })
                    .state('config-add', {
                        url: '/config-add/:appId?appName',
                        templateUrl: noCacheTemplateUrl('p/configs/add.v')
                    })
                    .state('config-edit', {
                        url: '/config-edit/:id?appName&appEnv',
                        templateUrl: function ($stateParams) {
                            return 'p/configs/edit.v?id=' + $stateParams.id + "&_=" + Date.now();
                        }
                    })
            ;
        });
    </script>
</head>
<body>
<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong>Setaria</strong>
        <small>统一配置管理</small>
    </div>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-user"></span> 用户 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                <#--<li><a href="#"><span class="am-icon-user"></span> 用户信息</a></li>-->
                    <li><a ui-sref="user-update-password"><span class="am-icon-cog"></span> 修改密码</a></li>
                </ul>
            </li>
            <li><a href="p/logout"><i class="am-icon-sign-out"></i> 注销</a></li>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas">
        <ul class="am-list admin-sidebar-list">
            <li><a ui-sref="home"><span class="am-icon-dashboard"></span> 首页</a></li>
            <li>
                <a ui-sref="users"><span class="am-icon-users"></span> 用户管理</a>
            </li>
            <li class="am-panel">
                <a ui-sref="apps" data-am-collapse="{target: '#app-manager-nav'}">
                    <i class="am-icon-database"></i> 应用管理<span
                        class="am-icon-angle-right am-fr am-margin-right"></span>
                </a>
                <ul class="am-list am-collapse admin-sidebar-sub" id="app-manager-nav">
                <#list apps as app>
                    <li><a ui-sref="configs-app({appId:555})" class="am-cf"> <span>${app.name}</span>
                        <#if app.env.code==3>
                            <span class="am-badge">开发</span>
                        <#elseif app.env.code=2>
                            <span class="am-badge am-badge-secondary">测试</span>
                        <#elseif app.env.code=1>
                            <span class="am-badge am-badge-success">生产</span>
                        </#if>
                    </a></li>
                </#list>
                </ul>
            </li>
        </ul>
    </div>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding" ui-view>
            </div>
        </div>
    </div>
    <!-- content end -->
</div>

</body>
</html>

<script src="controller/UserControllers.js"></script>
<script src="controller/AppControllers.js"></script>
<script src="controller/ConfigControllers.js"></script>
