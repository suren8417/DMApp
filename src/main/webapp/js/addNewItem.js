angular.module('tchaApp').controller('newItemController', function($scope, $http, $sce) {

    $scope.itemsSelectedType;
    $scope.itemTitle;
    $scope.itemDonor;
    $scope.itemDescription;
    $scope.itemKeyWords;
    $scope.itemStartDate;
    $scope.uploadItem;
    $scope.id;

    $scope.successMessage=false;
    $scope.deleteMessage=false;
    $scope.itemTypeRequired=false;
    $scope.itemTitleRequired=false;
    $scope.itemDonorRequired=false;
    $scope.itemDescriptionRequired=false;
    $scope.itemKeyWordsRequired=false;
    $scope.itemOriginDateRequired=false;
    $scope.itemFileRequired=false;

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
    }];

    $scope.collectionGridColumns = [{
        field: 'name',
        displayName: 'Collection Name',
        width: 260
    }, {
        field: 'description',
        displayName: 'Collection Description',
        width: 400
    }, {
        field: 'id',
        displayName: 'Id',
        visible: false
    }
    ];

    $scope.collectionGrid = {
        enableFullRowSelection: true,
        multiSelect: true,
        enableFiltering: true,
        enableSelectAll: false,
        enableColumnMenus: false,
        columnDefs: $scope.collectionGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.collectionGridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {
            });
        }
    };

    $scope.itemGridColumns = [{
            field: 'itemsSelectedType',
            displayName: 'Type',
            width: 100
        }, {
            field: 'status',
            displayName: 'Status',
            width: 100
        }, {
            field: 'itemTitle',
            displayName: 'Title',
            width: 200
        }, {
            field: 'itemDonor',
            displayName: 'Donor',
            width: 300
        }, {
            field: 'itemDescription',
            displayName: 'Description',
            width: 800
        }, {
            field: 'itemKeyWords',
            displayName: 'KeyWords',
            width: 500
        }, {
            field: 'itemStartDate',
            displayName: 'DateOfOrigin',
            width: 150
        }, {
            field: 'id',
            displayName: 'Id',
            visible: false
        }, {
            field: 'itemName',
            displayName: 'ItemName',
            visible: false,
            width: 200
        }

    ];

    $http.get("/TCHA/items").success(function(data) {
        $scope.itemGrid.data = data.itemDtos;
        $scope.collectionGrid.data = data.collectionDtos;

    });

    $scope.itemGrid = {
        enableFullRowSelection: true,
        multiSelect: false,
        enableFiltering: true,
        enableSelectAll: false,
        enableColumnMenus: false,
        columnDefs: $scope.itemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {
                $scope.itemsSelectedType = row.entity.itemsSelectedType;
                $scope.itemTitle = row.entity.itemTitle;
                $scope.itemDonor = row.entity.itemDonor;
                $scope.itemDescription = row.entity.itemDescription;
                $scope.itemKeyWords = row.entity.itemKeyWords;
                $scope.itemStartDate = row.entity.itemStartDate;
                $scope.id = row.entity.id;
                $scope.uploadItem = null;

                var selectedCollections = row.entity.selectedCollection;
                var index = 0;
                angular.forEach(selectedCollections, function(selectedCollectionId) {
                    angular.forEach($scope.collectionGrid.data, function(collection) {
                        if (collection.id === selectedCollectionId) {
                            $scope.collectionGridApi.selection.selectRow($scope.collectionGrid.data[index]);
                        }
                    });
                    index++;
                });

        var myHTML1 = "";
        if (row.entity.itemsSelectedType === 'Video') {

            if (row.entity.itemName.indexOf(".mp4") > -1) {
                myHTML1 = myHTML1 + "<video id="+row.entity.id+" width=\"600\" height=\"340\" controls>" +
                    "  <source src=\"docs/" + row.entity.itemName + "\" type=\"video/mp4\">" +
                    "  Your browser does not support the video tag." +
                    "</video>";
            } else {
                myHTML1 = myHTML1 + "<a href=\"docs/" + row.entity.itemName + "\" download=\""+row.entity.itemName+"\" >Download the Video</a>";

            }
        }
        if (row.entity.itemsSelectedType === 'Image') {
            myHTML1 = myHTML1 + "<img src=\"docs/" + row.entity.itemName + "\" width=\"75%\" height=\"600\">";
        }
        if (row.entity.itemsSelectedType === 'Document') {
            if (row.entity.itemName.indexOf(".pdf") > -1) {
                myHTML1 = myHTML1 + "<object data=\"docs/" + row.entity.itemName + "\" type=\"application/pdf\" width=\"100%\" height=\"900px\">" +
                    "<a href=\"docs/" + row.entity.itemName + "\">test.pdf</a>" +
                    "</object>";
            } else {
                myHTML1 = myHTML1 + "<a href=\"docs/" + row.entity.itemName + "\" download=\""+row.entity.itemName+"\">Download the Document</a>";

            }
        }
        if (row.entity.itemsSelectedType === 'Audio') {
            if (row.entity.itemName.indexOf(".mp3") > -1) {
                myHTML1 = myHTML1 + "<audio controls id="+row.entity.id+">" +
                    "  <source src=\"docs/" + row.entity.itemName + "\" type=\"audio/mpeg\">" +
                    "Your browser does not support the audio element." +
                    "</audio>";
            } else {
                myHTML1 = myHTML1 + "<a href=\"docs/" + row.entity.itemName + "\" download=\""+row.entity.itemName+"\">Download the Audio</a>";

            }
        }

    $scope.myHTML = $sce.trustAsHtml(myHTML1);


            });
        }
    };

    $scope.formClear = function() {
         $scope.clearItem();
         $scope.successMessage=false;
         $scope.deleteMessage=false;
    };

    $scope.clearItem = function() {

     $scope.myHTML = $sce.trustAsHtml("");
        $scope.itemsSelectedType = null;
        $scope.itemTitle = null;
        $scope.itemDonor = null;
        $scope.itemDescription = null;
        $scope.itemKeyWords = null;
        $scope.itemStartDate = null;
        $scope.uploadItem = null;
        $scope.id = null;

        $scope.gridApi.selection.clearSelectedRows();
        $scope.collectionGridApi.selection.clearSelectedRows();

        $scope.itemTypeRequired=false;
        $scope.itemTitleRequired=false;
        $scope.itemDonorRequired=false;
        $scope.itemDescriptionRequired=false;
        $scope.itemKeyWordsRequired=false;
        $scope.itemOriginDateRequired=false;
        $scope.itemFileRequired=false;
    };


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

    $scope.formats = ['DD-MM-YYYY', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[1];
    $scope.dtmax = new Date();

   function validateForm() {

    if($scope.itemsSelectedType == null){
         $scope.itemTypeRequired=true;
         return false;
    }
    if($scope.itemTitle == null){
         $scope.itemTitleRequired=true;
         return false;
    }
    if($scope.itemDonor == null){
         $scope.itemDonorRequired=true;
         return false;
    }
    if($scope.itemDescription == null){
        $scope.itemDescriptionRequired=true;
        return false;
    }
    if($scope.itemKeyWords == null){
        $scope.itemKeyWordsRequired=true;
        return false;
    }
    if($scope.itemStartDate == null){
        $scope.itemOriginDateRequired=true;
        return false;
    }

    if($scope.id == null && $scope.uploadItem == null){
        $scope.itemFileRequired=true;
        return false;
    }
     return true;
    }

    $scope.createItem = function() {
     $scope.deleteMessage=false;
      if(validateForm()){

          var selectedCollection = [];
          var selectedAddRows = $scope.collectionGridApi.selection.getSelectedRows();
          if (selectedAddRows != null) {
              angular.forEach(selectedAddRows, function (selectedAddRow) {
                  selectedCollection.push(selectedAddRow.id)
              });
          }

        var dataObj = {
            id: $scope.id,
            itemsSelectedType: $scope.itemsSelectedType,
            itemTitle: $scope.itemTitle,
            itemDonor: $scope.itemDonor,
            itemDescription: $scope.itemDescription,
            itemKeyWords: $scope.itemKeyWords,
            itemStartDate: $scope.itemStartDate,
            selectedCollection:selectedCollection
        };
        var fd = new FormData();
        fd.append('itemDto', JSON.stringify(dataObj));
        if ($scope.uploadItem !== null) {
            fd.append('file', $scope.uploadItem);
            var res = $http.post("/TCHA/items/item", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.itemGrid.data = data.itemDtos;
                $scope.collectionGrid.data = data.collectionDtos;
                $scope.successMessage=true;
                $scope.clearItem();
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        } else {
            var res = $http.post("/TCHA/items/itemDetail", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.itemGrid.data = data.itemDtos;
                $scope.collectionGrid.data = data.collectionDtos;
                $scope.successMessage=true;
                $scope.clearItem();
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
      }
    };


    $scope.removeItem = function() {
    $scope.successMessage=false;
        if ($scope.id !== null) {
            var res = $http.delete("/TCHA/items/item?deleteItem=" + $scope.id);
            res.success(function(data, status, headers, config) {
                $scope.itemGrid.data = data.itemDtos;
                $scope.collectionGrid.data = data.collectionDtos;
                 $scope.deleteMessage=true;
                 $scope.clearItem();
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };

});

angular.module('tchaApp').directive('fileModel', ['$parse', function($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);