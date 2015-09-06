angular.module('tchaApp', ['ui.bootstrap','ui.grid','ui.grid.selection']);

angular.module('tchaApp').controller('dashBordController', function ($scope) {

    $scope.subjects = ['Search', 'Recent Additions', 'Add New Item', 'Validate', 'Users'];

    $scope.selectedItem;
    $scope.searchUI = false;
    $scope.recentAdditionsUI = false;
    $scope.addNewItemUI = false;
    $scope.validateUI = false;
    $scope.userUI = false;

    $scope.dropboxitemselected = function (item) {
         $scope.selectedItem = item;
         if(item == "Search"){
             $scope.searchUI = true;
             $scope.recentAdditionsUI = false;
             $scope.addNewItemUI = false;
             $scope.validateUI = false;
             $scope.userUI = false;}
         if(item == "Recent Additions"){
          $scope.recentAdditionsUI = true;
             $scope.searchUI = false;
             $scope.addNewItemUI = false;
             $scope.validateUI = false;
             $scope.userUI = false;}
         if(item == "Add New Item"){
         $scope.addNewItemUI = true;
             $scope.searchUI = false;
             $scope.recentAdditionsUI = false;
             $scope.validateUI = false;
             $scope.userUI = false;}
         if(item == "Validate"){
          $scope.validateUI = true;
             $scope.searchUI = false;
             $scope.recentAdditionsUI = false;
             $scope.addNewItemUI = false;
             $scope.userUI = false;}
         if(item == "Users"){
             $scope.userUI = true;
             $scope.searchUI = false;
             $scope.recentAdditionsUI = false;
             $scope.addNewItemUI = false;
             $scope.validateUI = false;
            }

    };

  $scope.clear = function () {
    $scope.dt = null;
  };

  $scope.toggleMin = function() {
    $scope.minDate = $scope.minDate ? null : new Date();
  };
  $scope.toggleMin();


  $scope.toggleOpenDatePicker = function($event,datePicker) {
       $event.preventDefault();
       $event.stopPropagation();
       $scope[datePicker] = !$scope[datePicker];
  };

  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  $scope.format = $scope.formats[0];


});

