angular.module('tchaApp', ['ui.bootstrap', 'ui.grid', 'ui.grid.selection']);

angular.module('tchaApp').controller('dashBordController', function($scope) {

    $scope.loginError;
    $scope.showModal = false;
    $scope.loginUI = true;
    $scope.searchUI = false;
    $scope.recentAdditionsUI = false;
    $scope.addNewItemUI = false;
    $scope.validateUI = false;
    $scope.userUI = false;
    $scope.collectionUI = false;
    $scope.userTask = false;
    $scope.userType = "";
    $scope.subjects ;

    $scope.login = function (username, password) {

        if ( username === 'admin' && password === '1234') {
         $scope.subjects = ['Search', 'New Item', 'Manage Collection','Validate Item', 'Users'];
         $scope.searchUI = true;
         $scope.userTask = true;
         $scope.loginUI = false;
         $scope.userType=" Admin";
        } else if(username === 'dataentry' && password === '1234'){
         $scope.subjects = ['Search', 'New Item','Manage Collection'];
                 $scope.searchUI = true;
                 $scope.userTask = true;
                 $scope.loginUI = false;
                 $scope.userType=" DataEntry";
        }

        else {
            $scope.loginError = "Invalid username/password combination";
        }
    };


    $scope.dropboxitemselected = function(item) {

        if (item == "Search") {
            $scope.searchUI = true;
            $scope.recentAdditionsUI = false;
            $scope.addNewItemUI = false;
            $scope.validateUI = false;
            $scope.userUI = false;
            $scope.loginUI = false;
            $scope.collectionUI = false;
        }
        if (item == "Recent Additions") {
            $scope.recentAdditionsUI = true;
            $scope.searchUI = false;
            $scope.addNewItemUI = false;
            $scope.validateUI = false;
            $scope.userUI = false;
            $scope.loginUI = false;
            $scope.collectionUI = false;
        }
        if (item == "New Item") {
            $scope.addNewItemUI = true;
            $scope.searchUI = false;
            $scope.recentAdditionsUI = false;
            $scope.validateUI = false;
            $scope.userUI = false;
            $scope.loginUI = false;
            $scope.collectionUI = false;
        }
        if (item == "Validate Item") {
            $scope.validateUI = true;
            $scope.searchUI = false;
            $scope.recentAdditionsUI = false;
            $scope.addNewItemUI = false;
            $scope.userUI = false;
            $scope.loginUI = false;
            $scope.collectionUI = false;
        }
        if (item == "Users") {
            $scope.userUI = true;
            $scope.searchUI = false;
            $scope.recentAdditionsUI = false;
            $scope.addNewItemUI = false;
            $scope.validateUI = false;
            $scope.loginUI = false;
            $scope.collectionUI = false;
        }
        if (item == "Manage Collection") {
            $scope.collectionUI = true;
            $scope.userUI = false;
            $scope.searchUI = false;
            $scope.recentAdditionsUI = false;
            $scope.addNewItemUI = false;
            $scope.validateUI = false;
            $scope.loginUI = false;
        }

    };

});

