<!DOCTYPE html>
<html ng-app="App">
<head>
    <meta charset="UTF-8">
    <title>Setaria 分布式统一配置管理</title>

    <link rel="stylesheet" href="${rc.contextPath}/vendor/foundation-6.2.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/vendor/font-awesome-4.6.2/css/font-awesome.min.css"/>
    <<<<<<< HEAD
    <link rel="stylesheet" href="${rc.contextPath}/css/dashboard.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/css/theme.css"/>
    =======
    <link rel="stylesheet" href="${rc.contextPath}/css/custom.css"/>
    >>>>>>> origin/master

    <script src="${rc.contextPath}/vendor/jquery-2.2.3.min.js"></script>
    <script src="${rc.contextPath}/vendor/angular-1.5.5/angular.min.js"></script>
    <script src="${rc.contextPath}/vendor/ui-router-0.2.18/angular-ui-router.min.js"></script>
    <script src="${rc.contextPath}/vendor/ocLazyLoad-1.0.9/ocLazyLoad.min.js"></script>
    <script src="${rc.contextPath}/vendor/foundation-6.2.1/js/foundation.min.js"></script>
    <script src="${rc.contextPath}/vendor/what-input.js"></script>

    <script src="${rc.contextPath}/vendor/jquery.slicknav-1.0.7/jquery.slicknav.min.js"></script>
    <script src="${rc.contextPath}/js/sliding-menu.js"></script>

    <script>
        var app = angular.module('App', ['ui.router', 'oc.lazyLoad'])
                .config(function ($controllerProvider, $stateProvider, $urlRouterProvider) {
                    app.controllerProvider = $controllerProvider;
//                    $urlRouterProvider.otherwise('/');
                    $stateProvider.state('default', {
                                url: '/',
                                templateUrl: 'default.ftl'
                            })
                            .state('login', {
                                url: '/login',
                                templateUrl: 'login.ftl'
                            })
                            .state('appList', {
                                url: '/apps',
                                views: {
                                    "": {
                                        templateUrl: 'app/app-list.ftl'
                                    }
                                }
                            });
                });
    </script>
</head>
<body>

<
<div id="skin-select" style="width: 260px;">
    <!--      Toggle sidemenu icon button -->
    <a id="toggle" class="">
        <span class="fa icon-menu"></span>
    </a>
    <!--      End of Toggle sidemenu icon button -->

    <div class="skin-part" style="visibility: visible;">
        <div id="tree-wrap">
            <!-- Profile -->
            <div class="profile" style="top: -13px;">
                <img alt="" class="" src="${rc.contextPath}/images/logo.png"
                     style="width: 45px; height: 45px; top: 15px; left: 4px;">
                <h3 style="display: block;">EDUMIX
                    <small>1.2</small>
                </h3>

            </div>
            <!-- End of Profile -->

            <!-- Menu sidebar begin-->
            <div class="side-bar">
                <ul id="menu-showhide" class="topnav slicknav" style="height: auto; margin: -13px 0px 0px;">
                    <li style="text-align: left;">
                        <a id="menu-select" class="tooltip-tip tooltipster-disable" href="index.html" title="Dashboard">
                            <i class="icon-monitor" style="top: 0px; left: -15px; padding: 8px 0px;"></i>
                            <span style="display: inline-block; float: none;">Dashboard</span>

                        </a>

                    </li>
                    <li style="text-align: left;">
                        <a class="tooltip-tip tooltipster-disable" href="#">
                            <i class=" icon-window" style="top: 0px; left: -15px; padding: 8px 0px;"></i>
                            <span style="display: inline-block; float: none;">Layout<small class="side-menu-noft">New
                            </small></span>

                            <h4 style="display: inline-block; float: none;"><b class="fa fa-circle"></b></h4></a>
                        <ul style="border-radius: 0px; padding: 15px 25px;">
                            <li style="text-align: left;">
                                <a href="sidebar-fixed.html">Sidebar Fixed</a>
                            </li>
                            <li style="text-align: left;">
                                <a href="all-fixed.html">All Fixed</a>
                            </li>

                        </ul>
                    </li>

                    <li style="text-align: left;">
                        <a class="tooltip-tip tooltipster-disable" href="#" title="Mail">
                            <i class=" icon-preview" style="top: 0px; left: -15px; padding: 8px 0px;"></i>
                            <span style="display: inline-block; float: none;">Skin</span>

                            <h4 style="display: inline-block; float: none;"><b class="fa fa-circle"></b></h4></a>
                        <ul style="border-radius: 0px; padding: 15px 25px;">

                            <li style="text-align: left;">
                                <a href="blue-skin.html" title="Black Skin">Blue Skin</a>
                            </li>
                            <li style="text-align: left;">

                                <a href="white-skin.html" title="White Skin">White Skin</a>
                            </li>
                            <li style="text-align: left;">

                                <a href="green-skin.html" title="Blue Skin">Green Skin</a>
                            </li>
                        </ul>
                    </li>

                    <li style="text-align: left;">
                        <a class="tooltip-tip tooltipster-disable" href="#" title="Mail">
                            <i class=" icon-mail" style="top: 0px; left: -15px; padding: 8px 0px;"></i>
                            <span style="display: inline-block; float: none;">mail</span>

                            <h4 style="display: inline-block; float: none;"><b class="fa fa-circle"></b></h4></a>
                        <ul style="border-radius: 0px; padding: 15px 25px; display: none;">
                            <li style="text-align: left;">
                                <a href="mail.html" title="Inbox">Inbox
                                    <div class="noft-blue bg-red" style="display: inline-block; float: none;">256</div>
                                </a>
                            </li>
                            <li style="text-align: left;">

                                <a href="compose.html" title="Compose">Compose</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- end of Menu sidebar  -->
            <ul class="bottom-list-menu">
                <li style="text-align: left; display: block;">Settings <span class="icon-gear"
                                                                             style="display: inline-block; float: none;"></span>
                </li>
                <li style="text-align: left; display: block;">Help <span class="icon-phone"
                                                                         style="display: inline-block; float: none;"></span>
                </li>
                <li style="text-align: left; display: block;">About Kecambah <span class="icon-music"
                                                                                   style="display: inline-block; float: none;"></span>
                </li>

            </ul>
        </div>
    </div>
</div>

<div class="wrap-fluid" id="paper-bg" style="width: auto; margin-left: 260px;">
    <!-- top nav -->
    <div class="top-bar-nest">
        <nav class="top-bar" data-topbar role="navigation" data-options="is_hover: false">
            <div class="responsive-menu">
                <div class="top-bar-left">
                    <!-- Right Nav Section -->
                    <ul class="dropdown menu" data-dropdown-menu data-disable-hover="false">
                        <li>
                            <a class="bg-white" href="#"><i class="text-green fa fa-envelope"></i>&nbsp;<span
                                    class="label edumix-msg-noft">84</span></a>
                            <ul class="menu">
                                <li class="title back js-generated"><h5><a href="javascript:void(0)">Back</a></h5></li>
                                <li class="parent-link show-for-small"><a class="parent-link js-generated" href="#"><i
                                        class="text-green fa fa-envelope"></i>&nbsp;<span
                                        class="label edumix-msg-noft">84</span></a></li>
                                <li class="top-dropdown-nest"><span class="label round bg-green">MESSAGE</span>
                                </li>
                                <li class="bg-blue">
                                    <a href="#">
                                        <h3 class=" text-black"> Big Boss<b class="text-red fontello-record"></b><span>Just Now<small></small></span>
                                        </h3>
                                        <p class=" text-black">Important task!</p>
                                    </a>
                                </li>

                                <li>
                                    <div class="slimScrollDiv"
                                         style="position: relative; overflow: hidden; width: auto; height: 180px;">
                                        <div class="slim-scroll" style="overflow: hidden; width: auto; height: 180px;">

                                            <div>
                                                <a href="#">
                                                    <h3>Noel A. Riley<b class="text-green fontello-record"></b><span>12:23<small>
                                                        PM
                                                    </small></span>
                                                    </h3>
                                                    <p>Dua dua sayang adik kakak</p>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="#">
                                                    <h3>Shirley J. Carneal<b
                                                            class="text-gray fontello-record"></b><span>10:11<small>
                                                        PM
                                                    </small></span>
                                                    </h3>
                                                    <p>Tiga tiga sayang kekasihku</p>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="#">
                                                    <h3>Paul L. Williamsr<b class="text-gray fontello-record"></b><span>Yesterday</span>
                                                    </h3>
                                                    <p>Empat empat sayang semuanya</p>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="#">
                                                    <h3>William L. Wilcox<b class="text-gray fontello-record"></b><span>2 Days Ago</span>
                                                    </h3>
                                                    <p>Yang jomblo kasian deh lu</p>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="slimScrollBar"
                                             style="width: 3px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 135px; background: rgb(0, 0, 0);"></div>
                                        <div class="slimScrollRail"
                                             style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div>
                                    </div>
                                </li>

                                <li class="active right">
                                    <a href="#">
                                        <div class="label bg-white">View All</div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a class="bg-white" href="#"><i class="text-blue fa fa-bell"></i> <span
                                    class="label edumix-noft">45</span></a>
                            <ul class="menu">
                                <li class="title back js-generated"><h5><a href="javascript:void(0)">Back</a></h5></li>
                                <li class="parent-link show-for-small"><a class="parent-link js-generated" href="#"><i
                                        class="text-blue fa fa-bell"></i> <span class="label edumix-noft">45</span></a>
                                </li>
                                <li class="top-dropdown-nest"><span class="label round bg-blue">ALERT</span>
                                </li>
                                <li class="bg-black text-black">
                                    <i class="icon-warning"></i>
                                    <a href="#">
                                        <h3 class="text-black">Sticky Very Important<span
                                                class="text-red fontello-record"></span></h3>
                                        <p class="text-black">1 minute ago</p>
                                    </a>
                                </li>
                                <li>
                                    <div class="slimScrollDiv"
                                         style="position: relative; overflow: hidden; width: auto; height: 180px;">
                                        <div class="slim-scroll" style="overflow: hidden; width: auto; height: 180px;">
                                            <div>
                                                <i class="fontello-megaphone"></i>
                                                <a href="#">
                                                    <h3>Announcements <span class="text-green fontello-record"></span>
                                                    </h3>
                                                    <p>Just Now !</p>
                                                </a>
                                            </div>
                                            <div>
                                                <i class="  fontello-attach-1"></i>
                                                <a href="#">
                                                    <h3>Complete your profile<span
                                                            class="text-yellow fontello-record"></span>
                                                    </h3>
                                                    <p>2 Minute Ago</p>
                                                </a>
                                            </div>
                                            <div>
                                                <i class="  fontello-calendar-1"></i>
                                                <a href="#">
                                                    <h3>New Schedule Realease<span
                                                            class="text-yellow fontello-record"></span>
                                                    </h3>
                                                    <p>30 Minute ago</p>
                                                </a>
                                            </div>
                                            <div>
                                                <i class="fontello-database-1"></i>
                                                <a href="#">
                                                    <h3>New Student Data<span
                                                            class="text-orange fontello-record"></span>
                                                    </h3>
                                                    <p>1 Hour ago</p>
                                                </a>
                                            </div>
                                            <div>
                                                <i class="fontello-graduation-cap"></i>
                                                <a href="#">
                                                    <h3>Graduate Student List<span class="fontello-record"></span>
                                                    </h3>
                                                    <p>2 Days ago</p>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="slimScrollBar"
                                             style="width: 3px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 108px; background: rgb(0, 0, 0);"></div>
                                        <div class="slimScrollRail"
                                             style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div>
                                    </div>
                                </li>
                                <li class="active right">
                                    <a href="#">
                                        <div class="label bg-white">View All</div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- Left Nav Section -->

                <div class="top-bar-right">
                    <ul class="dropdown menu" data-dropdown-menu>
                        <li class="bg-white">
                            <a class="bg-white" href="#">
                                <img alt="" class="admin-pic img-circle"
                                     src="http://api.randomuser.me/portraits/thumb/men/28.jpg"
                                     style="margin: 20px 0px 0px 20px;">
                                <span class="admin-pic-text text-gray">Hi, Dave Mattew </span>
                            </a>

                            <ul class="menu">
                                <li class="title back js-generated"><h5><a href="javascript:void(0)">Back</a></h5></li>
                                <li class="parent-link show-for-small"><a class="parent-link js-generated" href="#"><img
                                        alt="" class="admin-pic img-circle"
                                        src="http://api.randomuser.me/portraits/thumb/men/28.jpg"
                                        style="margin: 20px 0px 0px 20px;"><span class="admin-pic-text text-gray">Hi, Dave Mattew </span>
                                </a></li>

                                <li>
                                    <i class="icon-user"></i>
                                    <a href="#">
                                        <h4>Profile<span class="text-aqua fontello-record"></span></h4>
                                    </a>
                                </li>
                                <li>
                                    <i class="icon-folder-open"></i>
                                    <a href="#">
                                        <h4>Account<span class="text-blue fontello-record"></span></h4>
                                    </a>
                                </li>
                                <li>
                                    <i class="icon-upload"></i>
                                    <a href="login.html">
                                        <h4>Logout<span class="text-dark-blue fontello-record"></span></h4>
                                    </a>
                                </li>

                                <li class="active right">
                                    <a href="#">
                                        <div class="label bg-white">More</div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="bg-white">
                            <a class="right-off-canvas-toggle bg-white text-gray" href="#">
                                <span style="font-size:13px" class="icon-view-list"></span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <!-- end of top nav -->
</div>

<script>
    $(document).foundation();
</script>

</body>
</html>
