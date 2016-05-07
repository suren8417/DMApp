<div ng-controller="searchController"  style="margin:0px 170px 20px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
            <div class="row">
			<legend style="width:94%; margin-left:30px;"><h3><b style="color:#1D1F5A;">Search</b></h3></legend>
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
            <div class="row" style="margin-bottom:10px">
               <div class="col-md-2"></div>
               <div class="col-md-1"></div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="image" ng-true-value="'YES'" ng-false-value="'NO'" style="width: 20px;height: 20px;"> <span>Image</span></label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="document" ng-true-value="'YES'" ng-false-value="'NO'" style="width: 20px;height: 20px;"><span> Document</span></label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="audio" ng-true-value="'YES'" ng-false-value="'NO'" style="width: 20px;height: 20px;"><span> Audio</span></label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="video" ng-true-value="'YES'" ng-false-value="'NO'" style="width: 20px;height: 20px;"><span> Video</span> </label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox" ng-model="collection" ng-true-value="'YES'" ng-false-value="'NO'" style="width: 20px;height: 20px;"><span> Collection</span></label>
                  </div>
               </div>
               <div class="col-md-2"></div>
            </div>
            <div class="row" style="margin-bottom:20px">
               <div class="col-md-2"></div>
               <div class="col-md-1"></div>
               <div class="col-md-2">
                      <p class="input-group">
                        <input id="2322" onkeydown="return false" max-date="dtmax" show-button-bar="false" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="startDate" is-open="opened"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                        <span class="input-group-btn">
                          <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened')"> From <i class="glyphicon glyphicon-calendar"></i></button>
                        </span>
                      </p>
               </div>
               <div class="col-md-1"></div>
                  <div class="col-md-2">
                      <p class="input-group">
                       <input id="23dd22" onkeydown="return false" max-date="dtmax" show-button-bar="false" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="endDate" is-open="opened1"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                        <span class="input-group-btn">
                          <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened1')"> To <i class="glyphicon glyphicon-calendar"></i></button>
                        </span>
                      </p>
                  </div>
               <div class="col-md-2"></div>
            </div>

            <div style="margin:10px 80px 10px 80px; border:1px solid #e7e7e7;" ng-show="searchSummary" id="searchSummaryId">
                <p style=" font-size:14px;margin:10px 10px 10px 10px;" ><span ng-bind="resultCount"></span></p>
                <table ng-repeat="item in searchData" style="margin:10px 10px 10px 10px;">
                <tr style="margin:0px 0px 5px 0px;">
                    <td>
                        <span ng-if="item.type ==='Video'"><span class="glyphicon glyphicon-film" style="margin-right: 10px;"></span></span>
                        <span ng-if="item.type ==='Image'"><span class="glyphicon glyphicon-picture" style="margin-right: 10px;"></span></span>
                        <span ng-if="item.type ==='Document'"><span class="glyphicon glyphicon-list-alt" style="margin-right: 10px;"></span></span>
                        <span ng-if="item.type ==='Audio'"><span class="glyphicon glyphicon-music" style="margin-right: 10px;" ></span></span>
                        <span ng-if="item.type ==='Collection'"><span class="glyphicon glyphicon-tags" style="margin-right: 10px;"></span></span>
                        <a href="" style=" font-size:18px;margin:0px 0px 0px 0px;" ng-click="showSearchInDetail(item.id)" >{{ item.title }}</a>
                        <p style="margin:0px 0px 0px 0px;">{{ item.description }}</p>
                        <p>More .....</p>
                    </td>
                </tr>
                </table>
            </div>


            <div style="margin:20px 80px 10px 80px; border:1px solid #e7e7e7;" ng-show="searchInDetail" ng-bind-html="myHTML" >

            </div>

              <div class="controls" ng-show="searchInDetail" style="margin-left: 75px;margin-bottom: 25px;">
                <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="backToSearchSummary()">Back</button>
              </div>
			  
			  
			  
 </div>
 
 <img src="images/Badge.png" width="10%" style="margin-left:170px;"/>