<div style="margin:0px 100px 0px 100px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row" ng-controller="deleteItemController">
        <legend style="width:94%; margin-left:30px;"><h3><b style="color:#1D1F5A;">Manage Items</b></h3></legend>   <div style=" margin-left: 50px;margin-right: 50px;margin-top: 30px;margin-bottom: 30px;">
        <div class="row" >
            <div class="col-md-12">
                <div>
                    <p>Please select items to delete</p>
                    <div style=" width: 100%; height: 560px;margin-top:5px;">
                        <div id="grid1" ui-grid="deleteItemGrid" ui-grid-selection class="grid" style="height: 550px;"></div>
                    </div>
                </div>
                <div>
                    <p style="color:green;font-size: 25px;" ng-show="deleteMessage"><span class="glyphicon glyphicon-ok"></span> Successfully Deleted</p>
                    <p style="color:green;font-size: 25px;" ng-show="rectifyMessage"><span class="glyphicon glyphicon-ok"></span> Successfully send for Rectification</p>
                    <p style="color:red;font-size: 25px;" ng-show="itemRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please select items</p>
                </div>
                <div class="controls">
                    <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="deleteItem()">Delete</button>
                    <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="rectifyItem()">Rectify</button>
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