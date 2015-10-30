angular.module('tchaApp').controller('validateItem', function($scope) {

    $scope.validateItemGridColumns = [{
            field: 'Type',
            width: 100
        }, {
            field: 'Title',
            width: 200
        }, {
            field: 'DateOfOrigin',
            width: 150
        }, {
            field: 'DateOfAdd',
            width: 150
        }, {
            field: 'DateOfValidate',
            width: 150
        }, {
            field: 'Donor',
            width: 150
        }, {
            field: 'Description',
            width: 500
        }, {
            field: 'Status',
            width: 100
        }

    ];

    $scope.validateItemGrid = {
        enableFullRowSelection: true,
        multiSelect: true,
        columnDefs: $scope.itemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {

            });
        }
    };



  $scope.validateItemGrid.data = [{
         "Type": "Carney",
         "Title": "DataEntry",
         "DateOfOrigin": "DataEntry",
         "DateOfAdd": "DataEntry",
         "DateOfValidate": "DataEntry",
         "Donor": "DataEntry",
         "Description": "DataEntry",
         "Status": "DataEntry"
     }, {
         "Type": "Carney",
         "Title": "DataEntry",
         "DateOfOrigin": "DataEntry",
         "DateOfAdd": "DataEntry",
         "DateOfValidate": "DataEntry",
         "Donor": "DataEntry",
         "Description": "DataEntry",
         "Status": "DataEntry"
     }, {
         "Type": "Carney",
         "Title": "DataEntry",
         "DateOfOrigin": "DataEntry",
         "DateOfAdd": "DataEntry",
         "DateOfValidate": "DataEntry",
         "Donor": "DataEntry",
         "Description": "DataEntry",
         "Status": "DataEntry"
     }];


});