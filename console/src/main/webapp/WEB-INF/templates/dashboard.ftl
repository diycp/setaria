<!DOCTYPE html>
<html ng-app="SetariaConsoleApp">
<head>
    <base href="${rc.contextPath}/">
    <meta charset="UTF-8">
    <title>Setaria 统一配置管理</title>

    <!-- 基础库 -->
    <script src="js/jquery.min.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/ocLazyLoad.min.js"></script>

    <!-- Amaze UI 基础 -->
    <link rel="stylesheet" href="amaze-ui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="css/admin.css">
    <script src="amaze-ui/js/amazeui.min.js"></script>

    <!-- Amaze UI DataTables plugins -->
    <link rel="stylesheet" href="amaze-ui/plugins/datatables/amazeui.datatables.min.css"/>
    <script src="amaze-ui/plugins/datatables/amazeui.datatables.min.js"></script>
    <script src="amaze-ui/plugins/datatables/dataTables.responsive.min.js"></script>

    <script>
        var app = angular.module('SetariaConsoleApp', ['oc.lazyLoad']);
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
                    <li><a href="#"><span class="am-icon-user"></span> 用户信息</a></li>
                    <li><a href="#"><span class="am-icon-cog"></span> 修改密码</a></li>
                </ul>
            </li>
            <li><a href="javascript:;"><i class="am-icon-sign-out"></i> 注销</a></li>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas">
        <ul class="am-list admin-sidebar-list">
            <li><a href="admin-index.html"><span class="am-icon-dashboard"></span> 首页</a></li>
            <li>
                <a><span class="am-icon-users"></span> 用户管理</a>
            </li>
            <li class="am-panel">
                <a data-am-collapse="{target: '#config-manager-nav'}">
                    <i class="am-icon-database"></i> 配置管理 <span
                        class="am-icon-angle-right am-fr am-margin-right"></span>
                </a>
                <ul class="am-list am-collapse admin-sidebar-sub" id="config-manager-nav">
                    <li><a href="admin-user.html" class="am-cf"> 统一登录</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> /
                    <small>一些常用模块</small>
                </div>
            </div>
        </div>
    </div>
    <!-- content end -->
</div>

</body>
</html>
