<div ng-show="collectionUI" style="margin:65px 0px 0px 170px;">
    <p>Manage Collection</p>
</div>
<div ng-show="collectionUI" style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row">
        <div class="container" style=" margin-left: 50px;margin-top: 30px;margin-bottom: 30px;">
            <div class="row" ng-controller="manageCollection">
                <div class="col-md-5">
                    <form class="form-horizontal" action="" method="POST">
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
                              <textarea rows="9" cols="50" id="collectionDescription" name="collectionDescription" ng-model="collectionDescription" class="form-control"></textarea>
                            </div>
                            </p>
                        </div>
                        <div>
                            <p>Please select items for collection</p>
                            <div style=" width: 1460px; height: 500px;margin-top:5px;">
                              <div id="grid1" ui-grid="itemGrid" ui-grid-selection class="grid" style="height: 500px;"></div>
                            </div>
                         </div>
                        <div class="controls">
                            <button class="btn" style=" margin-top:15px;">Create</button>
                            <button class="btn" style=" margin-top:15px;">Remove</button>
                            <button class="btn" style=" margin-top:15px;">Clear</button>
                        </div>

                    </form>
                </div>
                <div class="col-md-6">
                 <p style=" margin-left: 25px;">Existing collections</p>
                    <div style=" width: 950px; height: 300px;margin-left: 25px;margin-top: 25px;">
                        <div id="grid1" ui-grid="collectionGrid" ui-grid-selection class="grid" style="height: 250px;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 </div>