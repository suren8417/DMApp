angular.module('Authentication', []);

angular.module('tchaApp',[
    'Authentication',
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

        .when('/user', {
            controller: 'userController',
            templateUrl: '/TCHA/user',
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
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                $location.path('/login');
            }
        });
    }]);