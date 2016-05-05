<script>
    $(function () {
        $('#configList').DataTable({
            bSort: false,
            columns: [{}, {}, {}, {},
                {
                    width: 190
                }, {
                    width: 55
                }
            ]
        });

        $('#configs-import-file').AjaxFileUpload({
            action: 'p/configs/import/${app.id}',
            onComplete: function (filename, response) {
                if (response.errorCode) {
                    $.alert({content: response.errorMessage});
                } else {
                    angular.element('#configList').scope().refresh();
                }
            }
        });
    });

    function showConfigDetails(id) {
        $.get('p/configs/details.v?id=' + id, function (data) {
            $('#config-details-modal .am-modal-bd').html(data);
            $('#config-details-modal').modal({
                width: 600
            });
        });
    }
</script>

<!-- 配置详情模式窗口 -->
<div class="am-modal am-modal-no-btn" tabindex="-1" id="config-details-modal">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">配置详细
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd">
        </div>
    </div>
</div>

<!-- 配置列表 -->
<div class="am-panel am-panel-default" ng-controller="ConfigListController">
    <div class="am-panel-hd">
        <div class="am-fl">(<strong>${app.name}</strong>
        <#if app.env.code==3>
            <span class="am-badge">开发</span>
        <#elseif app.env.code=2>
            <span class="am-badge am-badge-secondary">测试</span>
        <#elseif app.env.code=1>
            <span class="am-badge am-badge-success">生产</span>
        </#if>) 配置列表
        </div>
        <div class="am-cf">
            <a ui-sref="config-add({appId:${app.id}, appName:${app.name}})"
               class="am-btn am-btn-xs am-btn-primary am-fr">
                <i class="am-icon-plus"></i>添加</a>
            <a href ng-click="export(${app.id})" class="am-btn am-btn-xs am-btn-primary am-fr">
                <i class="am-icon-download"></i>导出</a>

            <div class="am-form-group am-form-file">
                <a href class="am-btn am-btn-xs am-btn-primary am-fr">
                    <i class="am-icon-upload"></i>导入</a>
                <input id="configs-import-file" name="file" type="file"/>
            </div>
        </div>
    </div>
    <div class="am-panel-bd">
        <table class="am-table am-table-striped am-table-bordered am-table-compact" id="configList">
            <thead>
            <tr>
                <td>ID</td>
                <td>键</td>
                <td>值</td>
                <td>描述</td>
                <td>最后修改时间</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <#list configs as config>
            <tr>
                <td>${config.id}</td>
                <td><a href="javascript:showConfigDetails(${config.id})">${config.key}</a></td>
                <td>${config.value}</td>
                <td>${config.description}</td>
                <td><#if config.lastUpdatedTime != 0>${config.lastUpdatedTime?number_to_datetime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                <td>
                    <a ui-sref="config-edit({id:${config.id}, appName:'${app.name}', appEnv:'${app.env.code}'})"
                       title="编辑"><i class="am-icon-edit"></i></a>
                    <a href ng-click="deleteConfig(${config.id}, '${config.key}')" title="删除">
                        <i class="am-icon-remove"></i></a>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
