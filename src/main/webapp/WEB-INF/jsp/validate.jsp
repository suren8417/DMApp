<div style="margin:0px 100px 0px 100px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row" ng-controller="validateItemController">
	<legend style="width:94%; margin-left:30px;"><h3><b style="color:#1D1F5A;">Validate</b></h3></legend>
        <div style=" margin-left: 50px;margin-right: 50px;margin-top: 30px;margin-bottom: 30px;">
            <div class="row" >
                <div class="col-md-12">
                      <div>
                           <p style="color:green" ng-show="successMessage"><span class="glyphicon glyphicon-ok"></span> Successfully validate</p>
                           <p style="color:green" ng-show="rejectMessage"><span class="glyphicon glyphicon-ok"></span> Successfully rejected</p>
                           <p style="color:red" ng-show="itemRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please select items</p>
                       </div>
                        <div>
                            <p>Please select items for validation</p>
                            <div style=" width: 100%; height: 560px;margin-top:5px;">
                              <div id="grid1" ui-grid="validateItemGrid" ui-grid-selection class="grid" style="height: 550px;"></div>
                            </div>
                         </div>
                        <div class="controls">
                            <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="validateItem()">Validate</button>
                             <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="rejectItem()">Reject</button>
                            <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="formClear()">Clear</button>
                        </div>

                </div>
            </div>

            <div ng-bind-html="myHTML" style=" margin-top: 20px;">
            </div>
        </div>
    </div>
</div>

<img src="images/Badge.png" width="10%" style="margin-left:100px;"/>