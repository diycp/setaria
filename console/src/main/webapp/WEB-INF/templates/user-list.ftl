<script>
    $(function () {
        $('#userList').DataTable({
            bSort: false,
            columns: [{}, {},
                {
                    searchable: false,
                    width: 80
                },
                {
                    searchable: false,
                    width: 190
                },
                {
                    searchable: false,
                    width: 55
                }
            ]
        });
    });
</script>


<div class="am-panel am-panel-default" ng-controller="UserListController">
    <div class="am-panel-hd">
        <div class="am-fl">用户列表</div>
        <div class="am-cf">
            <a ui-sref="user-add" class="am-btn am-btn-xs am-btn-primary am-fr"><i class="am-icon-plus"></i>添加</a>
        </div>
    </div>
    <div class="am-panel-bd">
        <table class="am-table am-table-striped am-table-bordered am-table-compact" id="userList">
            <thead>
            <tr>
                <td>ID</td>
                <td>邮箱</td>
                <td>类型</td>
                <td>创建时间</td>
                <td>操作</td>
            </tr>
            </thead>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>
                    <#if user.manager>
                        <span class="am-badge am-badge-success">管理员</span>
                    <#else>
                        <span class="am-badge">普通用户</span>
                    </#if>
                </td>
                <td>${user.createdTime?number_to_date?string("yyyy-MM-dd HH:mm:ss")}</td>
                <td>
                    <#if user.email != rootUser>
                        <a ui-sref="user-edit({id:${user.id}})" title="编辑"><i class="am-icon-edit"></i></a>
                        <a href ng-click="deleteUser(${user.id}, '${user.email}')" title="删除"><i
                                class="am-icon-remove"></i></a>
                    </#if>
                </td>
            </tr>
        </#list>
        </table>
    </div>
</div>
