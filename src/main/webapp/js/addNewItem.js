angular.module('tchaApp').controller('newItemController', function($scope) {

  $scope.dateOfOrigin;
  $scope.dateOfAdd;
  $scope.dateOfValidate;
    $scope.UserName;
    $scope.selectedType;
    $scope.itemType = [{
        label: 'Image',
        value: 'Image'
    }, {
        label: 'Document',
        value: 'Document'
    }, {
        label: 'Audio',
        value: 'Audio'
    }, {
        label: 'Vedeo',
        value: 'Vedeo'
    }
    ];

 $scope.itemGridColumns = [{
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
            width: 400
        }, {
            field: 'Status',
            width: 100
        }

    ];

    $scope.itemGrid = {
        enableFullRowSelection: true,
        multiSelect: false,
        columnDefs: $scope.itemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {

            });
        }
    };



  $scope.itemGrid.data = [{
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



    $scope.clear = function() {
        $scope.dt = null;
    };

    $scope.toggleMin = function() {
        $scope.minDate = $scope.minDate ? null : new Date();
    };

    $scope.toggleMin();

    $scope.toggleOpenDatePicker = function($event, datePicker) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope[datePicker] = !$scope[datePicker];
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[1];


});