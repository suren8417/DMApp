/**
 * Created by suren on 9/07/16.
 */
angular.module('tchaApp').controller('recentItemController', function($scope, $http, $timeout, $sce) {

    $http.get("/TCHA/items/recentItem").success(function(data) {

           var myHTML1 = "";
           var  items = data.itemDtos;

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
                myHTML1 = myHTML1 + "</p> <hr style=\"margin-bottom: 20px;\"></p> ";

            });

            $scope.myHTML = $sce.trustAsHtml(myHTML1);


    });

});
