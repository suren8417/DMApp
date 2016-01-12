angular.module('tchaApp').controller('collectionController', function($scope, $http, $timeout, $sce) {

    $scope.collectionName;
    $scope.collectionDescription;
    $scope.collectionId = null;
    $scope.selectItems = [];
    $scope.successMessage = false;
    $scope.deleteMessage = false;

    $scope.collectionNameRequired = false;
    $scope.collectionDescriptionRequired = false;

    $scope.columns = [{
            field: 'name',
            displayName: 'CollectionName',
            width: 250
        }, {
            field: 'description',
            displayName: 'CollectionDescription',
            width: 700
        }, {
            field: 'id',
            displayName: 'Id',
            visible: false
        }

    ];

    $scope.itemGridColumns = [{
            field: 'itemsSelectedType',
            displayName: 'Type',
            width: 100
        }, {
            field: 'itemTitle',
            displayName: 'Title',
            width: 300
        }, {
            field: 'itemDonor',
            displayName: 'Donor',
            width: 300
        }, {
            field: 'itemDescription',
            displayName: 'Description',
            width: 500
        }, {
            field: 'itemKeyWords',
            displayName: 'KeyWords',
            width: 250
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
            width: 250
        }

    ];


    $scope.collectionGrid = {
        enableFullRowSelection: true,
        multiSelect: false,
        columnDefs: $scope.columns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {

                $scope.gridApi.selection.clearSelectedRows();
                $scope.selectItems = [];
                $scope.collectionId = row.entity.id;
                $scope.collectionName = row.entity.name;
                $scope.collectionDescription = row.entity.description;
                var items = null;
                angular.forEach($scope.collectionGrid.data, function(collection) {
                    if (collection.id === $scope.collectionId) {
                        items = collection.itemDtos;
                    }
                });

                var index = 0;
                var displayItems = $scope.itemGrid.data;
                angular.forEach(displayItems, function(displayItem) {
                    angular.forEach(items, function(item) {
                        if (item.id === displayItem.id) {

                            $scope.gridApi.selection.selectRow($scope.itemGrid.data[index]);
                        }
                    });
                    index++;
                });
            });
        }
    };




    $scope.itemGrid = {

        enableFullRowSelection: true,
        multiSelect: true,
        columnDefs: $scope.itemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {
                if ($scope.selectItems.indexOf(row.entity.id) == -1) {
                    $scope.selectItems.push(row.entity.id);
                } else {
                    $scope.selectItems.splice($scope.selectItems.indexOf(row.entity.id), 1);
                }

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

    $http.get("/TCHA/collections").success(function(data) {
        $scope.itemGrid.data = data.itemDtos;
        /*           $timeout(function() {
                         if($scope.gridApi.selection.selectRow){

                              $scope.gridApi.selection.selectRow($scope.itemGrid.data[1]);
                         }
                       });*/
        $scope.collectionGrid.data = data.collectionDto;
    });


    $scope.formClear = function() {

        $scope.clearItem();
        $scope.successMessage = false;
        $scope.deleteMessage = false;

        $http.get("/TCHA/collections").success(function(data) {
            $scope.itemGrid.data = data.itemDtos;
            $scope.collectionGrid.data = data.collectionDto;
        });

    };

    $scope.clearItem = function() {

        $scope.myHTML = $sce.trustAsHtml("");
        $scope.collectionName = null;
        $scope.collectionDescription = null;
        $scope.collectionId = null;
        $scope.selectItems = [];

        $scope.collectionNameRequired = false;
        $scope.collectionDescriptionRequired = false;

        $scope.gridApi.selection.clearSelectedRows();

    };


    function validateForm() {

        if ($scope.collectionName == null) {
            $scope.collectionNameRequired = true;
            return false;
        }
        if ($scope.collectionDescription == null) {
            $scope.collectionDescriptionRequired = true;
            return false;
        }

        return true;
    }

    $scope.createAndUpdateCollection = function() {
        $scope.deleteMessage = false;
        if (validateForm()) {

            var dataObj = {
                id: $scope.collectionId,
                name: $scope.collectionName,
                description: $scope.collectionDescription
            };
            var fd = new FormData();
            fd.append('itemArray', JSON.stringify($scope.selectItems));
            fd.append('collection', JSON.stringify(dataObj));
            var res = $http.post("/TCHA/collections/collection", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.collectionGrid.data = data.collectionDto;
                $scope.successMessage = true;;
                $scope.clearItem();
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };

    $scope.removeCollection = function() {
        $scope.successMessage = false;
        if ($scope.id !== null) {
            var res = $http.delete("/TCHA/collections/collection?deleteCollection=" + $scope.collectionId);
            res.success(function(data, status, headers, config) {
                $scope.collectionGrid.data = data.collectionDto;
                $scope.clearItem();
                $scope.deleteMessage = true;
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };


});