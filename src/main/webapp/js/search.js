
angular.module('tchaApp').controller('searchController', function($scope,$http,$sce) {

 $scope.searchData=null;
 $scope.searchDataInDetail=null;
 $scope.resultCount;
 $scope.searchSummary= false;
 $scope.searchInDetail= false;

$scope.search = function(searchText, image, document, audio, video, collection, startDate, endDate) {

    $scope.myHTML = $sce.trustAsHtml("");

    if (searchText === undefined) {
        searchText = "";
    }
    if (image === undefined) {
        image = "NO";
    }
    if (document === undefined) {
        document = "NO";
    }
    if (audio === undefined) {
        audio = "NO";
    }
    if (video === undefined) {
        video = "NO";
    }
    if (collection === undefined) {
        collection = "NO";
    }

        var dataObj = {

            searchText: searchText,
            image: image,
            document: document,
            audio: audio,
            video: video,
            startDate: startDate,
            endDate: endDate
        };

    $http.get("/TCHA/searches?searchQuery=" + JSON.stringify(dataObj))
        .success(function(data) {
            $scope.searchData = data.searchResultDtos;
        });
    $scope.searchSummary = true;
    $scope.searchInDetail = false;

};

$scope.showSearchInDetail = function(id) {
    $scope.searchInDetail = true;
    $scope.searchSummary = false;

    var myHTML1 = "";

    var items = $scope.searchData[id].itemDtos;

    angular.forEach(items, function(item) {
        if (item.itemsSelectedType === 'Video') {
            myHTML1 = myHTML1 + "<span class=\"glyphicon glyphicon-film\" style=\"margin-right: 10px;\"></span>";
        }
        if (item.itemsSelectedType === 'Image') {
            myHTML1 = myHTML1 + "<span class=\"glyphicon glyphicon-picture\" style=\"margin-right: 10px;\"></span>";
        }
        if (item.itemsSelectedType === 'Document') {
            myHTML1 = myHTML1 + "<span class=\"glyphicon glyphicon-list-alt\" style=\"margin-right: 10px;\"></span>";
        }
        if (item.itemsSelectedType === 'Audio') {
            myHTML1 = myHTML1 + "<span class=\"glyphicon glyphicon-music\" style=\"margin-right: 10px;\"></span>";
        }


        myHTML1 = myHTML1 + "<p style=\" font-size:18px;margin:0px 0px 0px 0px;\"  >" + item.itemTitle + "</p>" +
            "  <p style=\"margin:0px 0px 25px 0px;\">" + item.itemDescription + "</p>" +
            "  <p style=\"margin:0px 0px 5px 0px;\"> Donor : " + item.itemDonor + "</p>" +
            "  <p style=\"margin:0px 0px 5px 0px;\">KeyWords : " + item.itemKeyWords + "</p>" +
            "  <p style=\"margin:0px 0px 25px 0px;\">Origin Date : " + item.itemStartDate + "</p>";

        if (item.itemsSelectedType === 'Video') {
            myHTML1 = myHTML1 + "<video width=\"800\" height=\"400\" controls>" +
                "  <source src=\"docs/"+item.itemName+"\" type=\"video/mp4\">" +
                "  Your browser does not support the video tag." +
                "</video>";
        }
        if (item.itemsSelectedType === 'Image') {
            myHTML1 = myHTML1 + "<img src=\"docs/"+item.itemName+"\" width=\"75%\" height=\"600\">";
        }
        if (item.itemsSelectedType === 'Document') {
            myHTML1 = myHTML1 + "<object data=\"docs/"+item.itemName+"\" type=\"application/pdf\" width=\"100%\" height=\"900px\">" +
                "<a href=\"docs/"+item.itemName+"\">test.pdf</a>" +
                "</object>";
        }
        if (item.itemsSelectedType === 'Audio') {
            myHTML1 = myHTML1 + "<audio controls>" +
                "  <source src=\"docs/"+item.itemName+"\" type=\"audio/mpeg\">" +
                "Your browser does not support the audio element." +
                "</audio>";
        }
        myHTML1 = myHTML1 + "</p></p>"

    });
    $scope.myHTML = $sce.trustAsHtml(myHTML1);

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

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[1];


});

