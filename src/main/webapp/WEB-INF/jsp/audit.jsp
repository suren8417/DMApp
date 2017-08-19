<div style="margin:0px 100px 0px 100px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row" ng-controller="auditController">
        <legend style="width:94%; margin-left:30px;"><h3><b style="color:#1D1F5A;">Audit</b></h3></legend>
        <div style=" margin-left: 50px;margin-right: 50px;margin-top: 30px;margin-bottom: 30px;">

            <div class="row" style="margin-bottom:20px;">
                <div class="col-md-2"><input type="radio" ng-model="searchBy" value="user"
                                             style="width: 20px;height: 20px;">Search By User
                </div>
                <div class="col-md-2"><input type="radio" ng-model="searchBy" value="item"
                                             style="width: 20px;height: 20px;">Search By Item
                </div>
                <div class="col-md-2"><input type="radio" ng-model="searchBy" value="collection"
                                             style="width: 20px;height: 20px;">Search by Collection
                </div>
            </div>


            <div ng-switch="searchBy">
                <div class="row" style="margin-bottom:20px">
                    <div class="col-md-1"><p style="margin-top: 10px;text-align: left;">User</p></div>
                    <div class="col-md-3">
                        <div class="controls">
                            <select ng-model="$parent.selectedUser" class="form-control">
                                <option ng-repeat="user in users">{{user.label}}</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div ng-switch-when="item">
                    <div class="row" style="margin-bottom:20px">
                        <div class="col-md-1"><p style="margin-top: 10px;text-align: left;">Item</p></div>
                        <div class="col-md-7">
                            <select ng-model="$parent.itemName" class="form-control">
                                <option ng-repeat="item in items" ng-value={{item.id}}>{{item.label}}</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div ng-switch-when="collection">
                    <div class="row" style="margin-bottom:20px">
                        <div class="col-md-1"><p style="margin-top: 10px;text-align: left;">Collection</p></div>
                        <div class="col-md-7">
                            <select ng-model="$parent.collectionName" class="form-control">
                                <option ng-repeat="collection in collections" ng-value={{collection.id}}>
                                    {{collection.label}}
                                </option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row" style="margin-bottom:20px">
                    <div class="col-md-1"><p style="margin-top: 10px;text-align: left;">Dates</p></div>
                    <div class="col-md-3">
                        <p class="input-group">
                            <input id="2325" onkeydown="return false" max-date="dtmax" show-button-bar="false"
                                   type="text"
                                   class="form-control" datepicker-popup="{{format}}" ng-model="startDate"
                                   is-open="opened2"
                                   datepicker-options="dateOptions" date-disabled="disabled(date, mode)"
                                   ng-required="true"
                                   close-text="Close"/>
                                <span class="input-group-btn">
                                  <button type="button" class="btn btn-default"
                                          ng-click="toggleOpenDatePicker($event,'opened2')"> From <i
                                          class="glyphicon glyphicon-calendar"></i></button>
                                </span>
                        </p>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-3">
                        <p class="input-group">
                            <input id="23dd28" onkeydown="return false" max-date="dtmax" show-button-bar="false"
                                   type="text"
                                   class="form-control" datepicker-popup="{{format}}" ng-model="endDate"
                                   is-open="opened3"
                                   datepicker-options="dateOptions" date-disabled="disabled(date, mode)"
                                   ng-required="true"
                                   close-text="Close"/>
                                <span class="input-group-btn">
                                  <button type="button" class="btn btn-default"
                                          ng-click="toggleOpenDatePicker($event,'opened3')"> To <i
                                          class="glyphicon glyphicon-calendar"></i></button>
                                </span>
                        </p>
                    </div>
                    <div class="col-md-2">
                        <div class="controls">
                            <button class="btn" style="width: 100px;"
                                    ng-click="search(startDate,endDate)">Search
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div  ng-switch="searchBy== 'item' || searchBy== 'user'">
                <div ng-switch-when="true">
                    <div class="row">
                        <div class="col-md-12">
                            <div>
                                <p>Search changes of item</p>

                                <div style=" width: 100%; height: 400px;margin-top:5px;">
                                    <div id="grid1" ui-grid="auditItemGrid" ui-grid-selection class="grid"
                                         style="height: 390px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div ng-switch="searchBy== 'collection' || searchBy== 'user'">
                    <div ng-switch-when="true">
                    <div class="row">
                        <div class="col-md-12">
                            <div>
                                <p>Search changes of Collection</p>
                                <div style=" width: 100%; height: 400px;margin-top:5px;">
                                    <div id="grid2" ui-grid="auditCollectionGrid" ui-grid-selection class="grid"
                                         style="height: 390px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                   </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<img src="images/Badge.png" width="10%" style="margin-left:100px;"/>