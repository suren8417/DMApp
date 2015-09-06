
angular.module('tchaApp').controller('userGrid', function ($scope) {

    $scope.columns = [{ field: 'name' ,width: 200 }, { field: 'Designation',width: 150 },{ field: 'Username',width: 200 },{ field: 'Role',width: 100 }];
      $scope.gridOptions = {
        enableFullRowSelection : true,
        columnDefs: $scope.columns,
        onRegisterApi: function( gridApi ) {
              $scope.gridApi = gridApi;
              var cellTemplate = 'ui-grid/selectionRowHeader';
              $scope.gridApi.core.addRowHeaderColumn( { name: 'rowHeaderCol', displayName: '', width: 25, cellTemplate: cellTemplate} );
        }
    };


    $scope.gridOptions.data=[{"name": "Cox","Designation": "Carney","Username": "Carney","Role": "Carney"},{"name": "Cox","Designation": "Carney","Username": "Carney","Role": "Carney"},{"name": "Cox","Designation": "Carney","Username": "Carney","Role": "Carney"}]

});

