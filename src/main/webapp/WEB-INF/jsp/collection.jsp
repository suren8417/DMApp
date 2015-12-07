<div style="margin:65px 0px 0px 170px;">
    <p>Manage Collection</p>
</div>
<div  style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row">
        <div style=" margin-left: 50px; margin-right: 50px;margin-top: 30px;margin-bottom: 30px;">
            <div class="row" ng-controller="manageCollection">
                <div class="col-md-4">
                        <div>
                            <p>Name</p>
                            <div class="controls">
                                <input type="text" id="collectionName" name="collectionName" placeholder="" ng-model="collectionName" class="form-control">
                            </div>
                            </p>
                        </div>
                        <div>
                            <p>Description</p>
                            <div class="controls">
                              <textarea rows="8" cols="50" id="collectionDescription" name="collectionDescription" ng-model="collectionDescription" class="form-control"></textarea>
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
            <div class="row" ng-controller="manageCollection">
             <div class="col-md-12">
               <div>
                  <p>Please select items for collection</p>
                     <div style=" width: 100%; height: 500px;margin-top:5px;">
                     <div id="grid1" ui-grid="itemGrid" ui-grid-selection class="grid" style="height: 500px;"></div>
                     </div>
               </div>
               <div class="controls">
                 <button class="btn" style=" margin-top:15px;">Create</button>
                 <button class="btn" style=" margin-top:15px;">Remove</button>
                 <button class="btn" style=" margin-top:15px;">Clear</button>
               </div>
              </div>
             </div>
        </div>
    </div>
 </div>