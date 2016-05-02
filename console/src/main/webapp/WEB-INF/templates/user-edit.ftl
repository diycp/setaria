<div class="am-panel am-panel-default" ng-controller="UserEditController">
    <div class="am-panel-hd">修改用户</div>
    <div class="am-panel-bd">
        <form class="am-form" ng-submit="editUser()">
            <input type="hidden" ng-model="id"/>

            <div class="am-form-group">
                <label for="email">邮箱：</label>
                <input type="email" id="email" ng-model="email" required/>
            </div>
            <div class="am-form-group">
                <label for="password">密码：</label>
                <input type="password" id="password" ng-model="password" required minlength="6"/>
            </div>
            <div class="am-form-group">
                <label for="appIds">应用：</label>
                <select id="appIds" multiple>
                <#list apps as app>
                    <option value="${app.id}">${app.name} - ${app.env}</option>
                </#list>
                </select>
            </div>
            <input type="submit" value="保 存" class="am-btn am-btn-primary am-btn-block"/>
        </form>
    </div>
</div>
