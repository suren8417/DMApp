 <div style="margin:65px 0px 0px 170px;" >
   <p>Search</p>
 </div>
 <div ng-controller="searchController"  style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
            <div class="row" style="margin-top:40px">
               <div class="col-md-2"></div>
               <div class="col-md-8">
                  <div class="input-group">
                     <input type="text" class="form-control" ng-model="searchText" placeholder="Search for...">
                     <span class="input-group-btn">
                     <button class="btn btn-default" type="button" ng-click="search(searchText,image,document,audio,video,collection,startDate,endDate)">search <span class="glyphicon glyphicon-search"></span></button>
                     </span>
                  </div>
               </div>
               <div class="col-md-2"></div>
            </div>
            <div class="row">
               <div class="col-md-2"></div>
               <div class="col-md-1"></div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="image" ng-true-value="'YES'" ng-false-value="'NO'"> Image</label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="document" ng-true-value="'YES'" ng-false-value="'NO'" > Document</label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="audio" ng-true-value="'YES'" ng-false-value="'NO'"> Audio</label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="video" ng-true-value="'YES'" ng-false-value="'NO'"> Video </label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="collection" ng-true-value="'YES'" ng-false-value="'NO'"> Collection</label>
                  </div>
               </div>
               <div class="col-md-2"></div>
            </div>
            <div class="row" style="margin-bottom:20px">
               <div class="col-md-2"></div>
               <div class="col-md-1"></div>
               <div class="col-md-2">
                      <p class="input-group">
                        <input id="2322" show-button-bar="false" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="startDate" is-open="opened"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                        <span class="input-group-btn">
                          <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened')"> From <i class="glyphicon glyphicon-calendar"></i></button>
                        </span>
                      </p>
               </div>
               <div class="col-md-1"></div>
                  <div class="col-md-2">
                      <p class="input-group">
                       <input id="23dd22" show-button-bar="false" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="endDate" is-open="opened1"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                        <span class="input-group-btn">
                          <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened1')"> To <i class="glyphicon glyphicon-calendar"></i></button>
                        </span>
                      </p>
                  </div>
               <div class="col-md-2"></div>
            </div>

            <div style="margin:10px 80px 0px 80px;" ng-show="searchSummary" id="searchSummaryId">
                <p style=" font-size:14px;margin:0px 0px 10px 0px;" ><span ng-bind="resultCount"></span></p>
                <table ng-repeat="item in searchData.items">
                <tr style="margin:0px 0px 5px 0px;">
                    <td>
                        <a style=" font-size:18px;margin:0px 0px 0px 0px;" ng-click="showSearchInDetail()" >{{ item.title }}</a>
                        <p style="margin:0px 0px 0px 0px;">{{ item.description }}</p>
                        <p>More .....</p>
                    </td>
                </tr>
                </table>
            </div>

            <div style="margin:20px 80px 10px 80px;" ng-show="searchInDetail" id="searchInDetailId">
             <p style=" font-size:18px;margin:0px 0px 0px 0px;"  >{{ searchDataInDetail.title }}</p>
             <p style="margin:0px 0px 25px 0px;">{{ searchDataInDetail.description }}</p>

             <div class="bg"  ng-show="searchInDetail" >

             <!--    Layout the large images horizontally using viewwindow
                     to hide the overflow (all but the current image). -->
                     <div class="mainimageshell">
                         <div class="viewwindow">
                     <!--    fullscroller is referenced from the Controller js
                             to control scrolling -->
                             <ul id="fullscroller" class="fullsizelist" ng-style="listposition" >
                         <!--    Iterate through all images and display them end to end -->
                                 <li ng-repeat="image in galleryData">
                                     <img id="fullsize" class="large" ng-src="{{IMAGE_LOCATION+image.image}}" />
                                 </li>
                             </ul>
                         </div>
                     </div>

                     <!-- Display the description of the current image -->
                     <div class="captionshell">
                         <p class="caption" style="margin-top: 10px;">{{selected.desc}}</p>
                     </div>

                     <div style="clear:both;"></div>

                  <!--    Layout the large images horizontally using viewwindow
                     to hide the overflow (all but the current image). -->
                     <div class="thumbsshell">
                         <div class="thumbswrapper">
                             <ul>
                         <!--    Iterate through all images and display them end to end as thumbs -->
                                 <li ng-repeat="image in galleryData">
                                     <div class="thumbwrapper">
                                 <!--    Note ng-click here calls the scrollTo function found in the
                                         controller and passes it its index. This is used to scroll
                                         the large image to the correct image -->
                                         <a ng-href="" ng-click="scrollTo(image,$index)">
                                             <img class="thumbnail" ng-src="{{IMAGE_LOCATION+image.image}}" />
                                         </a>
                                     </div>
                                 </li>
                             </ul>
                         </div>
                     </div>

                 </div>
            </div>
 </div>