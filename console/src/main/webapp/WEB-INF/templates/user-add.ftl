<div class="am-panel am-panel-default" ng-controller="UserAddController">
    <div class="am-panel-hd">添加用户</div>
    <div class="am-panel-bd">
        <form class="am-form" ng-submit="addUser()">
            <div class="am-form-group">
                <label for="email">邮箱：</label>
                <input type="email" id="email" ng-model="email" required/>
            </div>
            <div class="am-form-group">
                <label for="password">密码：</label>
                <input type="password" id="password" ng-model="password" required minlength="6"/>
            </div>
            <div class="am-form-group">
                <label for="manager">权限：</label>

                <div>
                    <label class="am-radio-inline">
                        <input type="radio" ng-value="true" ng-model="manager"> 管理员
                    </label>
                    <label class="am-radio-inline">
                        <input type="radio" ng-value="false" ng-model="manager"> 普通用户
                    </label>
                </div>
            </div>
            <div class="am-form-group" ng-show="!manager">
                <label for="appIds">应用：</label>
                <select id="appIds" ng-model="appIds" multiple>
                <#list apps as app>
                    <option value="${app.id}">${app.name} - ${app.env}</option>
                </#list>
                </select>
            </div>
            <input type="submit" value="保 存" class="am-btn am-btn-primary am-btn-block"/>
        </form>
    </div>
</div>
