angular.module('tchaApp').controller('validateItemController', function($scope, $http, $timeout, $sce) {

    $scope.successMessage = false;
    $scope.rejectMessage = false;
    $scope.selectItems = [];
    $scope.itemRequired = false;
    $scope.validateItemGridColumns = [{
            field: 'itemsSelectedType',
            displayName: 'Type',
         headerCellClass: $scope.highlightFilteredHeader,
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
            field: 'itemCode',
            displayName: 'Item Code',
            width: 200
        }, {
            field: 'addedBy',
            displayName: 'Added By',
            width: 100
        },{
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

    $scope.formClear = function() {

        $scope.myHTML = $sce.trustAsHtml("");
        $scope.successMessage = false;
        $scope.rejectMessage = false;
        $scope.itemRequired = false
        $scope.gridApi.selection.clearSelectedRows();
    }

    $scope.clearValidate = function() {
        $scope.selectItems = [];
    };

    $scope.validateItemGrid = {
        enableFullRowSelection: true,
        multiSelect: true,
        enableSelectAll: false,
        enableColumnMenus: false,
        enableFiltering: true,
        columnDefs: $scope.validateItemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {

                if ($scope.selectItems.indexOf(row.entity.id) == -1) {
                    $scope.selectItems.push(row.entity.id);
                } else {
                    $scope.selectItems.splice($scope.selectItems.indexOf(row.entity.id), 1);
                }
                      $scope.myHTML = $sce.trustAsHtml("");
                      var myHTML1 = "";
                       if (row.entity.itemsSelectedType === 'Video') {

                           if (row.entity.itemName.indexOf(".mp4") > -1) {
                               myHTML1 = myHTML1 + "<video id="+row.entity.id+" width=\"600\" height=\"400\" controls>" +
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


    $http.get("/TCHA/validates").success(function(data) {
        $scope.validateItemGrid.data = data.itemDtos;
    });


    function validateForm() {
        if ($scope.selectItems.length === 0) {
            $scope.itemRequired = true
            return false;
        }
        return true;
    }
    $scope.validateItem = function() {
        $scope.formClear();
        if (validateForm()) {

            var fd = new FormData();
            fd.append('itemArray', JSON.stringify($scope.selectItems));
            var res = $http.post("/TCHA/validates/validateItem", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.validateItemGrid.data = data.itemDtos;;
                $scope.successMessage = true;;
                $scope.selectItems = [];
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };

    $scope.rejectItem = function() {
        $scope.formClear();
        if (validateForm()) {

            var fd = new FormData();
            fd.append('itemArray', JSON.stringify($scope.selectItems));
            var res = $http.post("/TCHA/validates/rejectItem", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.validateItemGrid.data = data.itemDtos;;
                $scope.rejectMessage = true;;
                $scope.selectItems = [];
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };
});