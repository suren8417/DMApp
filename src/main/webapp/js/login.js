
angular.module('tchaApp').controller('LoginController',
    ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function ($scope, $rootScope, $location, AuthenticationService) {
    $rootScope.userTask= false;
    $rootScope.loginError = false;

        // reset login status
        AuthenticationService.ClearCredentials();

        $scope.login = function () {
            $scope.dataLoading = true;
            AuthenticationService.Login($scope.username, $scope.password, function(response) {
                if(response.privilegeTasks.length > 0) {
                    $rootScope.userType=response.userType;
                    $rootScope.subjects = response.privilegeTasks;
                    $rootScope.userType = response.userType;
                    $rootScope.userTask= true;
                    AuthenticationService.SetCredentials($scope.username, $scope.password,response);
                    $location.path('/search');
                } else {
                    $rootScope.loginError = true;
                    $scope.dataLoading = false;
                }
            });
        };
    }]);
