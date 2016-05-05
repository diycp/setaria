<div class="am-panel am-panel-default" ng-controller="AppEditController">
    <div class="am-panel-hd">修改应用</div>
    <div class="am-panel-bd">
        <form class="am-form" ng-submit="editApp()">
            <input type="hidden" ng-model="id"/>

            <div class="am-form-group">
                <label for="name">名称：</label>
                <input type="text" id="name" ng-model="app.name" placeholder="应用名称4至30字字符, 且只能为字母数字下划线及点"
                       required pattern="^([a-z]|[A-Z]|[0-9]|[_\.]){4,30}$"/>
            </div>
            <div class="am-form-group">
                <label for="env">环境：</label>
                <select id="env" ng-model="app.env" data-placeholder="应用环境">
                    <option></option>
                    <option value="developer">开发</option>
                    <option value="test">测试</option>
                    <option value="production">生产</option>
                </select>
            </div>
            <div class="am-form-group">
                <label for="description">描述：</label>
                <textarea name="description" ng-model="app.description" maxlength="256"></textarea>
            </div>
            <div class="am-form-group">
                <label for="userIds">用户：</label>
                <select id="userIds" ng-model="app.userIds" data-placeholder="应用所属用户" multiple>
                    <option></option>
                <#list users as user>
                    <option value="${user.id}">${user.email}</option>
                </#list>
                </select>
            </div>
            <input type="submit" value="保 存" class="am-btn am-btn-primary am-btn-block"/>
        </form>
    </div>
</div>
