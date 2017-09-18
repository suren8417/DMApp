
angular.module('tchaApp').controller('searchController', function($scope,$http,$sce) {

 $scope.searchData=null;
 $scope.searchDataInDetail=null;
 $scope.resultCount;
 $scope.searchSummary= false;
 $scope.searchInDetail= false;
 var items =null;

$scope.search = function(searchText, image, document, audio, video, collection, startDate, endDate, addedStartDate, addedEndDate) {

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
            collection:collection,
            startDate: startDate,
            endDate: endDate,
            addedStartDate: addedStartDate,
            addedEndDate: addedEndDate
        };

    $http.get("/TCHA/searches?searchQuery=" + JSON.stringify(dataObj))
        .success(function(data) {
            $scope.searchData = data.searchResultDtos;
            $scope.resultCount="About " + data.searchResultDtos.length +" results";
        });
    $scope.searchSummary = true;
    $scope.searchInDetail = false;

};

$scope.backToSearchSummary = function () {
    $scope.searchInDetail = false;
    $scope.searchSummary = true;

    angular.forEach(items, function(item) {
        if (item.itemsSelectedType === 'Video' | item.itemsSelectedType === 'Audio') {
        //item.id.pause();
        document.getElementById(item.id).pause();
        }
    });

};

$scope.showSearchInDetail = function(id) {
    $scope.searchInDetail = true;
    $scope.searchSummary = false;

    var myHTML1 = "";

    items = $scope.searchData[id].itemDtos;

  if ($scope.searchData[id].type === 'Collection') {
      myHTML1 += "<div style=\"box-shadow: 8px 8px 20px #aaa; border: 4px solid #1D1F5A; padding: 15px;\">";
   myHTML1 = myHTML1 + "<span class=\"glyphicon glyphicon-tags\" style=\"margin-right: 10px;\"></span>";
                myHTML1 = myHTML1 + "<p style=\" font-size:18px;margin:0px 0px 0px 0px;\"  >" + $scope.searchData[id].title + "</p>" +
                        "  <p style=\"margin:0px 0px 25px 0px;\">" + $scope.searchData[id].description + "</p></div><br/>";
        }


    angular.forEach(items, function(item) {
        myHTML1 += "<div style=\"box-shadow: 8px 8px 20px #aaa; border: 4px solid #1D1F5A; padding: 15px;\">";
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
            "  <p style=\"margin:0px 0px 25px 0px;\">Origin Date : " + item.itemStartDate + "</p>" +
            "  <p style=\"margin:0px 0px 25px 0px;\">Note : " + item.itemNote + "</p>";

        if (item.itemsSelectedType === 'Video') {

            if (item.itemName.indexOf(".mp4") > -1) {
                myHTML1 = myHTML1 + "<video id="+item.id+" width=\"800\" height=\"400\" controls>" +
                    "  <source src=\"docs/" + item.itemName + "\" type=\"video/mp4\">" +
                    "  Your browser does not support the video tag." +
                    "</video>";
            } else {
                myHTML1 = myHTML1 + "<a href=\"docs/" + item.itemName + "\" download=\""+item.itemName+"\" >Download the Video</a>";

            }
        }
        if (item.itemsSelectedType === 'Image') {
            myHTML1 = myHTML1 + "<img src=\"docs/" + item.itemName + "\" width=\"75%\" height=\"600\">";
        }
        if (item.itemsSelectedType === 'Document') {
            if (item.itemName.indexOf(".pdf") > -1) {
                myHTML1 = myHTML1 + "<object data=\"docs/" + item.itemName + "\" type=\"application/pdf\" width=\"100%\" height=\"900px\">" +
                    "<a href=\"docs/" + item.itemName + "\">test.pdf</a>" +
                    "</object>";
            } else {
                myHTML1 = myHTML1 + "<a href=\"docs/" + item.itemName + "\" download=\""+item.itemName+"\">Download the Document</a>";

            }
        }
        if (item.itemsSelectedType === 'Audio') {
            if (item.itemName.indexOf(".mp3") > -1) {
                myHTML1 = myHTML1 + "<audio controls id="+item.id+">" +
                    "  <source src=\"docs/" + item.itemName + "\" type=\"audio/mpeg\">" +
                    "Your browser does not support the audio element." +
                    "</audio>";
            } else {
                myHTML1 = myHTML1 + "<a href=\"docs/" + item.itemName + "\" download=\""+item.itemName+"\">Download the Audio</a>";

            }
        }
        myHTML1 = myHTML1 + "</div><br/>";

    });

/*         myHTML1 = myHTML1 + "</p></p> <div class=\"controls\">"+
                                                      "    <button class=\"btn\" style=\" margin-top:15px;width: 100px;\" ng-click=\"backToSearchSummary()\">Back</button>"+
                                                      "  </div>";
                                                      "  </div>";*/
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
    $scope.dtmax = new Date();

});

