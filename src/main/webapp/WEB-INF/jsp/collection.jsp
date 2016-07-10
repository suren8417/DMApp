<div  style="margin:0px 100px 0px 100px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row" ng-controller="collectionController">
	<legend style="width:94%; margin-left:30px;"><h3><b style="color:#1D1F5A;">Manage Collection</b></h3></legend>
        <div style=" margin-left: 50px; margin-right: 50px;margin-top: 30px;margin-bottom: 30px;" >
            <div class="row" >
                <div class="col-md-4">
                        <div>
                            <p>Name</p>
                            <div class="controls">
                                <input type="text"  ng-model="collectionName" class="form-control" maxlength="100">
                                </p>
                                <p style="color:red" ng-show="collectionNameRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please enter name</p>
                            </div>
                            </p>
                        </div>
                        <div>
                            <p>Description</p>
                            <div class="controls">
                              <textarea rows="8" cols="50"  ng-model="collectionDescription" class="form-control" maxlength="1500"></textarea>
                              </p>
                              <p style="color:red" ng-show="collectionDescriptionRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please enter description</p>
                            </div>
                            </p>
                        </div>
                </div>
                <div class="col-md-8">
                 <p style=" margin-left: 25px;">Existing collections</p>
                    <div style=" width: 100%; height: 275px;margin-top: 10px;">
                        <div id="grid1" ui-grid="collectionGrid" ui-grid-selection class="grid" style="height: 250px;"></div>
                    </div>
                </div>
            </div>
            <div class="row" >
             <div class="col-md-12">
               <div>
                  <p>Please select items for collection</p>
                     <div style=" width: 100%; height: 300px;margin-top:5px;">
                     <div id="grid1" ui-grid="itemGrid" ui-grid-selection class="grid" style="height: 300px;"></div>
                     </div>
               </div>
                 <div>
                     <p style="color:green;font-size: 25px;" ng-show="successMessage"><span class="glyphicon glyphicon-ok"></span> Successfully save</p>
                     <p style="color:green;font-size: 25px;" ng-show="deleteMessage"><span class="glyphicon glyphicon-ok"></span> Successfully remove</p>
                 </div>
               <div class="controls">
                 <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="createAndUpdateCollection()">Save</button>
                 <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="removeCollection()">Remove</button>
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