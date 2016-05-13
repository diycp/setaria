<div class="row full-body">
    <div class="small-3 columns left">

        <div class="site_title">
            <span> Setaria 分布式统一配置管理</span>
        </div>
        <div class="clearfix"></div>

        <div>
            <div class="profile_pic">
                <img src="${rc.contextPath}/images/img.jpg" class="img-circle profile_img">
            </div>
            <div class="profile_info">
                <span>欢迎您,</span>

                <h2>John Doe</h2>
            </div>
        </div>
        <div class="clearfix"></div>

        <ul class="vertical menu side-menu" data-accordion-menu>
            <li class="hide"><a></a></li>
            <li><a href="#"><i class="fa fa-home"></i> 首页 </a>
            </li>
            <li><a><i class="fa fa-users"></i> 用户管理</a>
                <ul class="menu vertical nested">
                    <li><a>用户列表</a></li>
                    <li><a href="#">添加用户</a></li>
                </ul>
            </li>
            <li><a><i class="fa fa-database"></i> 应用管理</a>
                <ul class="menu vertical nested">
                    <li><a ui-sref="appList">应用列表</a>
                </ul>
            </li>
            <li><a><i class="fa fa-history"></i> 历史记录</a>
                <ul class="menu vertical nested">
                    <li><a href="#">配置修改历史</a>
                </ul>
            </li>
        </ul>
    </div>

    <div class="small-9 columns">
        <div class="row top">
            <div class="secondary button-group float-right" style="margin-bottom: 0px;">
                <a class="button">修改密码</a>
                <a class="button">注销</a>
            </div>
        </div>

        <div ui-view>
            <div ng-include src="'user/user-list.ftl'"></div>
        </div>
    </div>

</div>
<script>
    $(document).foundation();
</script>
