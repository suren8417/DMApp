
angular.module('tchaApp',[
    'ngRoute',
    'ngCookies',
    'ui.bootstrap',
    'ui.grid',
    'ui.grid.selection'
]).config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/login', {
            controller: 'LoginController',
            templateUrl: '/TCHA/login',
        })

        .when('/users', {
            controller: 'userController',
            templateUrl: '/TCHA/user',
        })

        .when('/newItem', {
            controller: 'userController',
            templateUrl: '/TCHA/addNewItem',
        })

        .when('/manageCollection', {
            controller: 'collectionController',
            templateUrl: '/TCHA/manageCollection',
        })

        .when('/validateItem', {
             controller: 'validateItemController',
             templateUrl: '/TCHA/validate',
        })

        .when('/deleteItem', {
            controller: 'deleteItemController',
            templateUrl: '/TCHA/deleteItem',
        })


        .when('/recentItem', {
            controller: 'recentItemController',
            templateUrl: '/TCHA/recentItem',
        })

        .when('/search', {
            controller: 'searchController',
            templateUrl: '/TCHA/search',
        })
 
        .otherwise({ redirectTo: '/login' });
}]).run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $rootScope.subjects=$rootScope.globals.currentUser.response.privilegeTasks;
            $rootScope.userType=$rootScope.globals.currentUser.response.userType;
            $rootScope.userTask= true;
            $rootScope.loginError = false;
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                $location.path('/login');
            }
        });
    }]);


