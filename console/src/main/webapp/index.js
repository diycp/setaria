// DataTables 默认设置
$.extend($.fn.dataTable.defaults, {
    autoWidth: false,
    columnDefs: [{
        ordering: false
    }],
    aaSorting: [],
    dom: '<"datatable-header"fl><"datatable-scroll"t><"datatable-footer"ip>',
    language: {
        search: '<span>过滤:</span> _INPUT_',
        lengthMenu: '<span>显示:</span> _MENU_',
        paginate: {'first': '首页', 'last': '尾页', 'next': '&rarr;', 'previous': '&larr;'}
    }
});
$.fn.dataTable.ext.errMode = function () {
};

// Block UI 默认设置
$.extend($.blockUI.defaults, {
    message: '<i class="icon-spinner4 spinner"></i>',
    overlayCSS: {
        backgroundColor: '#1b2024',
        opacity: 0.8,
        cursor: 'wait'
    },
    css: {
        padding: 0,
        margin: 0,
        width: '30%',
        top: '40%',
        left: '35%',
        textAlign: 'center',
        color: '#fff',
        border: '0',
        backgroundColor: 'transparent',
        cursor: 'wait'
    }
});

// Highlighting rows and columns on mouseover
function dataTableHighlightColumns() {
    var lastIdx = null;
    var table = $('.datatable-highlight').DataTable();
    $('.datatable-highlight tbody').on('mouseover', 'td', function () {
        var colIdx = table.cell(this).index().column;
        if (colIdx !== lastIdx) {
            $(table.cells().nodes()).removeClass('active');
            $(table.column(colIdx).nodes()).addClass('active');
        }
    }).on('mouseleave', function () {
        $(table.cells().nodes()).removeClass('active');
    });
}

// 模式窗口对象设置
$(function () {
    window.winModal = $('#modal');
    winModal.open = function () {
        winModal.modal();
    }
    winModal.close = function () {
        winModal.modal('hide');
    }
});

var app = angular.module('App', ['ui.router'])
    .config(function ($controllerProvider, $stateProvider, $urlRouterProvider, $httpProvider) {
        $urlRouterProvider.otherwise('/');
        $stateProvider.state('index', {url: '/', templateUrl: 'pages/summary.html'})
        $stateProvider.state('user-list', {url: '/user-list', templateUrl: 'pages/user-list.html'})
        $stateProvider.state('user-add', {url: '/user-add', templateUrl: 'pages/user-add.html'})
        $stateProvider.state('user-edit', {url: '/user-edit/:id', templateUrl: 'pages/user-edit.html'})
        $stateProvider.state('user-update-password', {
            url: '/user-update-password',
            templateUrl: 'pages/user-update-password.html'
        })
        $stateProvider.state('app-list', {url: '/app-list', templateUrl: 'pages/app-list.html'})
        $stateProvider.state('app-add', {url: '/app-add', templateUrl: 'pages/app-add.html'})
        $stateProvider.state('app-edit', {url: '/app-edit/:id', templateUrl: 'pages/app-edit.html'})
        $stateProvider.state('config-list', {
            url: '/config-list/:appId?appName&appEnv',
            templateUrl: 'pages/config-list.html'
        })
        ;

        // controller register
        app.controller = function (name, callback) {
            $controllerProvider.register(name, callback);
        }

        $httpProvider.interceptors.push(function ($q) {
            return {
                request: function (config) {
                    $.blockUI();
                    return config;
                },
                requestError: function (rejection) {
                    $.unblockUI();
                    return $q.reject(rejection);
                },
                response: function (response) {
                    $.unblockUI();
                    return response;
                },
                responseError: function (response) {
                    $.unblockUI();

                    if (response.status === 401) {
                        location.href = 'login.html';
                    }
                    return $q.reject(response);
                }
            };
        });

    }).run(function ($rootScope, $stateParams) {
        $rootScope.$on('$stateChangeStart',
            function (event, toState, toParams, fromState, fromParams, options) {
                $('#navbar-second-toggle li.active').removeClass('active');
                var a = $('#navbar-second-toggle a[ui-sref="' + toState.name + '"');
                a.parents('li').addClass('active');
            });
    });

// 初始化
$(function () {
    $(document).ajaxError(function (event, xhr, settings) {
        if (xhr.status === 401) {
            location.href = 'login.html';
        }
    });


    var ns = $.initNamespaceStorage('setaria.console');
    if (!ns.localStorage.isSet('signed_user')) {
        //location.href = 'login.html';
    }

    var signedUser = ns.localStorage.get('signed_user');
    $('#signed_email').text(signedUser.email);
    if (signedUser.type === 'simple') {
        $('#navbar-second-toggle .user-manager').remove();
    }
});
