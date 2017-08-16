<div style="margin:0px 100px 0px 100px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row" ng-controller="auditController">
	<legend style="width:94%; margin-left:30px;"><h3><b style="color:#1D1F5A;">Audit</b></h3></legend>
        <div style=" margin-left: 50px;margin-right: 50px;margin-top: 30px;margin-bottom: 30px;">
            <div ng-show="showAuditby">
            <div class="row" style="margin-bottom:20px">
                <div class="col-md-1"><p style="margin-top: 10px;text-align: left;">User</p></div>
                <div class="col-md-3">
                    <div class="controls">
                        <select ng-model="selectedUser" class="form-control">
                            <option ng-repeat="user in users" >{{user.label}}</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="controls">
                        <button class="btn" style="width: 100px;" ng-click="search(itemName,collectionName,selectedUser,startDate,endDate)">Search</button>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="checkbox">
                        <label><input type="checkbox" ng-click="showByItem()" style="width: 20px;height: 20px;" ng-checked="showAuditby"> <span>By User</span></label>
                    </div>
                </div>
            </div>
            </div>
            <div ng-show="!showAuditby">
            <div class="row" style="margin-bottom:20px">
                <div ng-show="!showCollectinby">
                    <div class="col-md-1"><p style="margin-top: 10px;text-align: left;">Item</p></div>
                    <div class="col-md-7">
                        <input type="text" id="itemName" name="itemName" placeholder="" ng-model="itemName"
                               class="form-control" maxlength="250">
                    </div>
                    <div class="col-md-2">
                        <div class="checkbox">
                            <label><input type="checkbox" style="width: 20px;height: 20px;" ng-click="showByCollection()" ng-checked="!showCollectinby"> <span>By Item</span></label>
                        </div>
                    </div>
                </div>
                <div ng-show="showCollectinby">
                    <div class="col-md-1"><p style="margin-top: 10px;text-align: left;">Collection</p></div>
                    <div class="col-md-7">
                        <input type="text"  placeholder="" ng-model="collectionName" class="form-control" maxlength="250">
                    </div>
                    <div class="col-md-2">
                        <div class="checkbox">
                            <label><input type="checkbox" style="width: 20px;height: 20px;" ng-click="showByCollection()" ng-checked="showCollectinby"> <span>By Collection</span></label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-bottom:20px">
                <div class="col-md-1"><p style="margin-top: 10px;text-align: left;">Dates</p></div>
                <div class="col-md-3">
                    <p class="input-group">
                        <input id="2325" onkeydown="return false" max-date="dtmax" show-button-bar="false" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="startDate" is-open="opened2"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                                <span class="input-group-btn">
                                  <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened2')"> From <i class="glyphicon glyphicon-calendar"></i></button>
                                </span>
                    </p>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <p class="input-group">
                        <input id="23dd28" onkeydown="return false" max-date="dtmax" show-button-bar="false" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="endDate" is-open="opened3"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                                <span class="input-group-btn">
                                  <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened3')"> To <i class="glyphicon glyphicon-calendar"></i></button>
                                </span>
                    </p>
                </div>
                <div class="col-md-2">
                    <div class="controls">
                        <button class="btn" style="width: 100px;" ng-click="search(itemName,collectionName,selectedUser,startDate,endDate)">Search</button>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="checkbox">
                        <label><input type="checkbox" style="width: 20px;height: 20px;" ng-click="showByUser()" ng-checked="!showAuditby"> <span>By Item/Collection</span></label>
                    </div>
                </div>
            </div>
            </div>
            <div class="row" >
                <div class="col-md-12">
                        <div>
                            <p>Search changes of item</p>

                            <div style=" width: 100%; height: 560px;margin-top:5px;">
                              <div id="grid1" ui-grid="auditItemGrid" ui-grid-selection class="grid" style="height: 550px;"></div>
                            </div>
                         </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>

<img src="images/Badge.png" width="10%" style="margin-left:100px;"/>