<div class="am-panel am-panel-default" ng-controller="UserUpdatePasswordController">
<div class="am-panel-hd">修改密码</div>
<div class="am-panel-bd">
    <form class="am-form" ng-submit="updatePassword()">
        <input type="hidden" ng-model="id"/>

        <div class="am-form-group">
            <label for="oldPassword">旧密码：</label>
            <input type="password" id="oldPassword" ng-model="oldPassword" required/>
        </div>
        <div class="am-form-group">
            <label for="newPassword">新密码：</label>
            <input type="password" id="newPassword" ng-model="newPassword" required minlength="6"/>
        </div>
        <div class="am-form-group">
            <label for="confirmNewPassword">确认密码：</label>
            <input type="password" id="confirmNewPassword" ng-model="confirmNewPassword"/>
        </div>
        <input type="submit" value="保 存" class="am-btn am-btn-primary am-btn-block"/>
    </form>
</div>
</div>
