angular.module('tchaApp').controller('newItemController', function($scope,$http) {

  $scope.itemsSelectedType;
  $scope.itemTitle;
  $scope.itemDonor;
  $scope.itemDescription;
  $scope.itemKeyWords;
  $scope.itemStartDate;
  $scope.uploadItem;


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
        label: 'Video',
        value: 'Video'
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



   $scope.createItem = function() {
   //itemStartDate:$scope.itemStartDate,uploadItem:$scope.uploadItem
            var dataObj = {id : 123, itemsSelectedType :  $scope.itemsSelectedType, itemTitle : $scope.itemTitle, itemDonor:$scope.itemDonor,itemDescription:$scope.itemDescription,itemKeyWords:$scope.itemKeyWords,itemStartDate:$scope.itemStartDate};
            var fd = new FormData();
            fd.append('file', $scope.uploadItem);
            fd.append('itemDto', JSON.stringify(dataObj));
            var res = $http.post("/TCHA/items/item", fd, {transformRequest: angular.identity, headers: {'Content-Type': undefined}});
          	res.success(function(data, status, headers, config) {
              alert( "okkkkkkkkkkkk" );
          	});
          	res.error(function(data, status, headers, config) {
          			alert( "failure message: " + JSON.stringify({data: data}));
          	});
    };


});

angular.module('tchaApp').directive('fileModel', ['$parse', function ($parse) {
    return {
                                  restrict: 'A',
                                  link: function(scope, element, attrs) {
                                      var model = $parse(attrs.fileModel);
                                      var modelSetter = model.assign;

                                      element.bind('change', function(){
                                          scope.$apply(function(){
                                              modelSetter(scope, element[0].files[0]);
                                          });
                                      });
                                  }
                              };
  }]);