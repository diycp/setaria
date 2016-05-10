<style>
    .site_title {
        text-overflow: ellipsis;
        overflow: hidden;
        font-weight: 400;
        font-size: 22px;
        width: 100%;
        color: #ECF0F1 !important;
        margin-left: 0 !important;
        line-height: 59px;
        display: block;
        height: 55px;
        margin: 0;
        padding-left: 10px;
    }

    .site_title i {
        border: 1px solid #EAEAEA;
        padding: 5px 6px;
        border-radius: 50%;
    }

    .profile {
    }

    .profile_pic {
        width: 35%;
        float: left;
    }

    .img-circle {
        height: 70px;
        width: 70px;
        -moz-border-radius: 35px;
        border-radius: 35px;
    }

    .img-circle.profile_img {
        background: #fff;
        margin-left: 15%;
        position: inherit;
        margin-top: 20px;
        border: 1px solid rgba(52, 73, 94, 0.44);
        padding: 4px;
    }

    .profile_info {
        padding: 25px 10px 10px;
        width: 65%;
        float: left;
    }

    .profile_info span {
        font-size: 13px;
        line-height: 30px;
        color: #BAB8B8;
    }

    .profile_info h2 {
        font-size: 14px;
        color: #ECF0F1;
        margin: 0;
        font-weight: 300;
    }

    .side-menu {
        margin-top: 7px;
        color: white !important;
    }

    .side-menu li.active-m {
        border-right: 5px solid #1ABB9C;
    }

    .side-menu li.active-m > a {
        text-shadow: rgba(0, 0, 0, 0.25) 0 -1px 0;
        background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #5b6479), color-stop(100%, #4c5566)), #686e78;
        background: -webkit-linear-gradient(#334556, #2C4257), #2A3F54;
        background: -moz-linear-gradient(#334556, #2C4257), #2A3F54;
        background: -o-linear-gradient(#334556, #2C4257), #2A3F54;
        background: linear-gradient(#334556, #2C4257), #2A3F54;
        -webkit-box-shadow: rgba(0, 0, 0, 0.25) 0 1px 0, inset rgba(255, 255, 255, 0.16) 0 1px 0;
        -moz-box-shadow: rgba(0, 0, 0, 0.25) 0 1px 0, inset rgba(255, 255, 255, 0.16) 0 1px 0;
        box-shadow: rgba(0, 0, 0, 0.25) 0 1px 0, inset rgba(255, 255, 255, 0.16) 0 1px 0;
    }

</style>

<script>
    $(function () {
        $('.side-menu a').click(function (e) {
            var className = $(this).parent().parent().attr('class');
            if (className.indexOf('side-menu') >= 0) {
                $('.side-menu .active-m').removeClass('active-m');
                $(this).parent().addClass('active-m');
            } else if (className.indexOf('nested') >= 0) {
                $('.side-menu .nested .active').removeClass('active');
                $(this).parent().addClass('active');
            }
        });
    });
</script>

<div class="row" style="min-width:100vw;min-height:100vh;">
    <div class="row" style="background-color: #EDEDED">
        <div class="top-bar">
            <div class="top-bar-left">
                <ul class="dropdown menu" data-dropdown-menu>
                    <li class="menu-text">Site Title</li>
                    <li>
                        <a href="#">One</a>
                        <ul class="menu vertical">
                            <li><a href="#">One</a></li>
                            <li><a href="#">Two</a></li>
                            <li><a href="#">Three</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Two</a></li>
                    <li><a href="#">Three</a></li>
                </ul>
            </div>
            <div class="top-bar-right">
                <ul class="menu">
                    <li><input type="search" placeholder="Search"></li>
                    <li>
                        <button type="button" class="button">Search</button>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="small-3 columns"
         style="min-height: 100vh; padding-left: 0;padding-right: 0;">

        <div class="site_title">
            <i class="fa fa-paw"></i><span> Setaria 统一配置管理</span>
        </div>
        <div class="clearfix"></div>

        <div class="profile">
            <div class="profile_pic">
                <img src="${rc.contextPath}/images/img.jpg" class="img-circle profile_img">
            </div>
            <div class="profile_info">
                <span>Welcome,</span>

                <h2>John Doe</h2>
            </div>
        </div>
        <div class="clearfix"></div>

        <ul class="vertical menu side-menu" data-accordion-menu role="reflow">
            <li><a href="#"><i class="fa fa-home"></i> Home </a>
            </li>
            <li><a><i class="fa fa-edit"></i> Forms</a>
                <ul class="menu vertical nested">
                    <li><a href="#">General Form</a></li>
                    <li><a href="#">Advanced Components</a></li>
                    <li><a href="#">Form Validation</a></li>
                    <li><a href="#">Form Wizard</a></li>
                    <li><a href="#">Form Upload</a></li>
                    <li><a href="#">Form Buttons</a></li>
                </ul>
            </li>
            <li><a><i class="fa fa-sitemap"></i> Multilevel Menu </a>
                <ul class="menu vertical nested">
                    <li><a href="#">Level One</a>
                    <li><a>Level One</a>
                        <ul class="menu vertical nested">
                            <li><a href="#">Level Two</a></li>
                            <li><a href="#">Level Two</a></li>
                            <li><a href="#">Level Two</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Level One</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="small-9 columns" style="border:1px solid red;">
        Right
    </div>

</div>
<script>
    $(document).foundation();
</script>
