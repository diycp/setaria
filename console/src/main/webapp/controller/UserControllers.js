(function (module) {

    module.controller('UserListController', function ($scope, $http, $state) {

        $scope.deleteUser = function (id, email) {
            $.confirm({
                content: '确认删除用户[' + email + "]吗",
                confirm: function () {
                    $http.delete('p/users/' + id).success(function () {
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
    });

    module.controller('UserAddController', function ($scope, $http, $state) {
        $('input[type="email"]').focus();
        $('select').chosen();

        $scope.addUser = function () {
            $http.post('p/users', {
                    email: $scope.email,
                    password: $scope.password,
                    manager: $scope.manager,
                    appIds: $scope.appIds
                })
                .success(function (data) {
                    $.alert({
                        content: '保存成功',
                        confirm: function () {
                            $state.go('users');
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

    module.controller('UserEditController', function ($scope, $http, $state, $stateParams) {
        $('input[type="email"]').focus();
        $('select').chosen();

        $http.get('p/users/' + $stateParams.id).success(function (data) {
            $.extend($scope, data);
            $('select').val(data.appIds);
            $('select').trigger("chosen:updated");
        });

        $scope.editUser = function () {
            $http.put('p/users/' + $scope.id, {
                    email: $scope.email,
                    password: $scope.password,
                    manager: $scope.manager,
                    appIds: $('select').val()
                })
                .success(function (data) {
                    $.alert({
                        content: '保存成功',
                        confirm: function () {
                            $state.go('users', {}, {reload: true, inherit: false});
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

    module.controller('UserUpdatePasswordController', function ($scope, $http) {

        $('#oldPassword').focus();

        $scope.updatePassword = function () {
            if ($scope.newPassword != $scope.confirmNewPassword) {
                $.alert({content: '新密码与确认密码不匹配'});
                return;
            }

            $http.patch('p/users/update-password', {
                oldPassword: $scope.oldPassword,
                newPassword: $scope.newPassword
            }).success(function () {
                $.alert({content: '密码修改成功'});
            }).error(function () {
                var errorMessage;
                if (!data) {
                    errorMessage = '修改密码失败, 未知错误';
                } else {
                    errorMessage = data.errorMessage;
                }

                $.alert({content: errorMessage});
            });
        }
    });

})(app);