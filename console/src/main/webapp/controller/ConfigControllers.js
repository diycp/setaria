(function (module) {

    module.controller('ConfigListController', function ($scope, $http, $state, $stateParams) {
        $scope.deleteConfig = function (id, key) {
            $.confirm({
                content: '确认删除配置[' + key + "]吗",
                confirm: function () {
                    $http.delete('p/configs/' + id).success(function () {
                        $state.go($state.current, {}, {reload: true});
                    }).error(function (data) {
                        var errorMessage;
                        if (!data) {
                            errorMessage = '删除失败, 未知错误';
                        } else {
                            errorMessage = data.errorMessage;
                        }

                        $.alert({content: errorMessage});
                    });
                }
            });
        }

        $scope.export = function (appId) {
            $.fileDownload('p/configs/export/' + appId).fail(function () {
                alert("导出失败");
            });
        }

        $scope.refresh = function () {
            $state.go($state.current, {}, {reload: true});
        }
    });

    module.controller('ConfigAddController', function ($scope, $http, $state, $stateParams) {
        $scope.appName = $stateParams.appName;
        $scope.config = {appId: $stateParams.appId};

        $scope.addConfig = function () {
            $http.post('p/configs', $scope.config)
                .success(function (data) {
                    $.alert({
                        content: '保存成功',
                        confirm: function () {
                            $state.go('config-app', {appId: $scope.config.appId});
                        }
                    });
                })
                .error(function (data) {
                    var errorMessage;
                    if (!data) {
                        errorMessage = '保存失败, 未知错误';
                    } else {
                        errorMessage = data.errorMessage;
                    }

                    $.alert({content: errorMessage});
                });
        }
    });

    module.controller('ConfigEditController', function ($scope, $http, $state, $stateParams) {
        $scope.appName = $stateParams.appName;

        $http.get('p/configs/' + $stateParams.id).success(function (data) {
            $scope.config = data;
        });

        $scope.editConfig = function () {
            var config = $scope.config;
            $http.put('p/configs/' + $stateParams.id, {
                    key: config.key,
                    value: config.value,
                    description: config.description
                })
                .success(function (data) {
                    $.alert({
                        content: '保存成功',
                        confirm: function () {
                            $state.go('config-app', {appId: $scope.config.appId});
                        }
                    });
                })
                .error(function (data) {
                    var errorMessage;
                    if (!data) {
                        errorMessage = '修改失败, 未知错误';
                    } else {
                        errorMessage = data.errorMessage;
                    }

                    $.alert({content: errorMessage});
                });
        }
    });
})(app);