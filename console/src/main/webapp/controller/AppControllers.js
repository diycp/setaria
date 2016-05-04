(function (module) {

    module.controller('AppListController', function ($scope, $http, $state) {

        $scope.deleteApp = function (id, name) {
            $.confirm({
                content: '确认删除应用[' + name + "]吗",
                confirm: function () {
                    $http.delete('p/apps/' + id).success(function () {
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

        $scope.showClientInfos = function (id) {
            $http.get('p/apps/' + id + "/clientInfos").success(function (data) {
                var html = '';
                $.each(data, function (i, v) {
                    html += '<tr>';
                    html += '<td>' + v.host + '</td>';
                    html += '<td>' + (new Date(v.lastPullTime * 1000)).format(dateFormat.masks.chinaDateTime) + '</td>';
                    html += '</tr>';
                });

                $('#client-infos-modal tbody').html(html);

                $('#client-infos-modal').modal({
                    width: 700,
                    height: 550
                });
            }).error(function (data) {
                alert(data);
            });
        }
    });

    module.controller('AppAddController', function ($scope, $http, $state) {
        $('#name').focus();
        $('#env').chosen({disable_search_threshold: 10});
        $('#userIds').chosen();
        $scope.addApp = function () {
            if ($scope.app.env) {
                submit();
            } else {
                $.alert({content: '请选择应用环境'});
            }
        }

        function submit() {
            $http.post('p/apps', $scope.app)
                .success(function (data) {
                    $.alert({
                        content: '保存成功',
                        confirm: function () {
                            $state.go('apps');
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

    module.controller('AppEditController', function ($scope, $http, $state, $stateParams) {
        var envElem = $('#env');
        envElem.chosen({disable_search_threshold: 10});

        var userIdsElem = $('#userIds');
        userIdsElem.chosen();

        $http.get('p/apps/' + $stateParams.id).success(function (data) {
            $scope.id = data.id;
            $scope.app = data;

            envElem.val(data.env);
            envElem.trigger("chosen:updated");

            userIdsElem.val(data.userIds);
            userIdsElem.trigger("chosen:updated");
        });

        $scope.editApp = function () {
            // var app = $.extend({}, $scope.app);
            $http.put('p/apps/' + $scope.id, $scope.app)
                .success(function (data) {
                    $.alert({
                        content: '保存成功',
                        confirm: function () {
                            $state.go('apps');
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