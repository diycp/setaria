<script>
    angular.module('App').controllerProvider.register('UserListCtrl', function ($scope, $state, $resource, $timeout) {
        $scope.users = [];
        $resource('/p/users').query(function (users) {
            $scope.users = users;

            $timeout(function () {
                $('#userList').DataTable({});
            });
        });

        $scope.addUser = function () {
            var elem = new Foundation.Reveal($("<div class='reveal'>HELLO</div>"));
            console.dir(elem);
            elem.open();
        }
    });

    $(document).foundation();
</script>
<div ng-controller="UserListCtrl" class="callout">
    <p><a data-toggle="animatedModal10">Click me for a modal</a></p>

    <div class="clearfix">
        <div class="float-left">
            <h4>用户管理</h4>
        </div>
        <a ng-click="addUser()" class="button float-right">添加</a>
    </div>
    <div class="row">
        <table id="userList" class="hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>邮箱</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="u in users">
                <td>{{u.id}}</td>
                <td>{{u.email}}</td>
                <td>{{u.createdTime | date: 'yyyy-MM-dd HH:mm:ss'}}</td>
                <td>
                    <a ui-sref="userEdit" title="编辑"><i class="fa fa-edit"></i></a>
                    <a title="删除"><i class="fa fa-remove"></i></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
