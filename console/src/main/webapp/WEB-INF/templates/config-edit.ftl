<div class="am-panel am-panel-default" ng-controller="ConfigEditController">
    <div class="am-panel-hd">配置修改</div>
    <div class="am-panel-bd">
        <form class="am-form" ng-submit="editConfig()">
            <input type="hidden" ng-model="id"/>

            <div class="am-form-group">
                <label for="key">键：</label>
                <input type="text" id="key" ng-model="config.key" placeholder="配置键值, 最长50个字符" required maxlength="50"/>
            </div>
            <div class="am-form-group">
                <label for="value">值：</label>
                <textarea id="value" ng-model="config.value" required maxlength="1024"></textarea>
            </div>
            <div class="am-form-group">
                <label for="description">描述：</label>
                <textarea name="description" ng-model="config.description" maxlength="512"></textarea>
            </div>
            <div class="am-form-group">
                <label>应用：</label>
                <input type="text" ng-model="appName" disabled/>
            </div>
            <input type="submit" value="保 存" class="am-btn am-btn-primary am-btn-block"/>
        </form>
    </div>
</div>
