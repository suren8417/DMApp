<div style="margin:65px 0px 0px 170px;" >
   <p>Validate</p>
</div>
<div style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row">
        <div style=" margin-left: 50px;margin-right: 50px;margin-top: 30px;margin-bottom: 30px;">
            <div class="row" ng-controller="validateItemController">
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
        </div>
    </div>
</div>