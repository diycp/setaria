<script>
    $(function () {
        $('#appList').DataTable({
            bSort: false,
            columns: [{},
                {},
                {
                    width: 50
                },
                {
                    width: 250
                },
                {
                    searchable: false,
                    width: 180
                },
                {
                    searchable: false,
                    width: 180
                },
                {
                    searchable: false,
                    width: 75
                }]
        });

        $('.am-modal-bd table').DataTable({
            bSort: false,
            bLengthChange: false
        });
    });
</script>

<div class="am-modal am-modal-no-btn" tabindex="-1" id="client-infos-modal">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">客户端信息
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd">
            <table class="am-table am-table-striped am-table-bordered am-table-compact">
                <thead>
                <tr>
                    <td>主机</td>
                    <td>配置拉取时间</td>
                </tr>
                </thead>
                <tbody>
                <!-- 动态添加客户端信息 -->
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="am-panel am-panel-default" ng-controller="AppListController">
    <div class="am-panel-hd">
        <div class="am-fl">应用列表</div>
        <div class="am-cf">
            <a ui-sref="app-add" class="am-btn am-btn-xs am-btn-primary am-fr"><i class="am-icon-plus"></i>添加</a>
        </div>
    </div>
    <div class="am-panel-bd">
        <table class="am-table am-table-striped am-table-bordered am-table-compact" id="appList">
            <thead>
            <tr>
                <td>ID</td>
                <td>名称</td>
                <td>环境</td>
                <td>描述</td>
                <td>创建时间</td>
                <td>最后修改时间</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <#list apps as app>
            <tr>
                <td>${app.id}</td>
                <td><a ui-sref="config-app({appId:${app.id}})">${app.name}</a></td>
                <td>
                    <#if app.env.code==3>
                        <span class="am-badge">开发</span>
                    <#elseif app.env.code=2>
                        <span class="am-badge am-badge-secondary">测试</span>
                    <#elseif app.env.code=1>
                        <span class="am-badge am-badge-success">生产</span>
                    </#if>
                </td>
                <td>${app.description!""}</td>
                <td>${app.createdTime?number_to_datetime?string("yyyy-MM-dd HH:mm")}</td>
                <td><#if app.lastUpdatedTime!=0>${app.lastUpdatedTime?number_to_datetime?string("yyyy-MM-dd HH:mm")}</#if></td>
                <td>
                    <a ui-sref="app-edit({id:${app.id}})" title="编辑"><i class="am-icon-edit"></i></a>
                    <a href ng-click="deleteApp(${app.id},'${app.name}')" title="删除"><i class="am-icon-remove"></i></a>
                    <a href ng-click="showClientInfos(${app.id})" title="查看客户端信息"><i class="am-icon-eye"></i></a>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
