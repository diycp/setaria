<form class="am-form am-form-horizontal">
    <div class="am-form-group">
        <label class="am-u-sm-2 am-form-label">键:</label>

        <div class="am-u-sm-10">
            <input type="text" id="key" value="${config.key}" disabled/>
        </div>
    </div>
    <div class="am-form-group">
        <label class="am-u-sm-2 am-form-label">值:</label>

        <div class="am-u-sm-10">
            <textarea id="value" rows="8" disabled>${config.value}</textarea>
        </div>
    </div>
    <div class="am-form-group">
        <label class="am-u-sm-2 am-form-label">描述:</label>

        <div class="am-u-sm-10">
            <textarea name="description" rows="8" disabled>${config.description}</textarea>
        </div>
    </div>
</form>
