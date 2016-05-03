<script>
    $(function () {
        $('#key').focus();
    });
</script>
<div class="am-panel am-panel-default" ng-controller="ConfigAddController">
    <div class="am-panel-hd">配置添加</div>
    <div class="am-panel-bd">
        <form class="am-form" ng-submit="addConfig()">
            <input type="hidden" ng-model="config.appId"/>

            <div class="am-form-group">
                <label for="key">键：</label>
                <input type="text" id="key" ng-model="config.key" placeholder="配置键值, 最长50个字符"
                       required maxlength="50"/>
            </div>
            <div class="am-form-group">
                <label for="value">值：</label>
                <textarea id="value" ng-model="config.value" required maxlength="1024"></textarea>
            </div>
            <div class="am-form-group">
                <label for="description">描述：</label>
                <textarea name="description" ng-model="config.description" maxlength="512"></textarea>
            </div>
            <input type="submit" value="保 存" class="am-btn am-btn-primary am-btn-block"/>
        </form>
    </div>
</div>
