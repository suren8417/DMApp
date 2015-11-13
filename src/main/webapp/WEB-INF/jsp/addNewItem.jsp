<div ng-show="addNewItemUI" style="margin:65px 0px 0px 170px;">
    <p>Add New Item</p>
</div>
<div ng-show="addNewItemUI" style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row">
        <div class="container" style=" margin-left: 50px;margin-top: 30px;margin-bottom: 30px;">
            <div class="row" ng-controller="newItemGrid">
                <div class="col-md-5">
                    <form class="form-horizontal" action="" method="POST">
                        <div>
                            <p>Item Type</p>
                            <div class="controls">
                                <select ng-model="selectedType" class="form-control" style="width: 30%;">
                                    <option ng-repeat="item in itemType" value="{{item.value}}">{{item.label}}</option>
                                </select>
                            </div>
                            </p>
                        </div>
                        <div>
                            <p>Title</p>
                            <div class="controls">
                                <input type="title" id="title" name="title" placeholder="" class="form-control">
                            </div>
                            </p>
                        </div>
                             <div>
                                                    <p>Donor</p>
                                                    <div class="controls">
                                                        <input type="donor" id="donor" name="donor" placeholder="" class="form-control">
                                                    </div>
                                                    </p>
                                                </div>
                        <div>
                            <p>Description</p>
                            <div class="controls">
                                <textarea rows="6" cols="50" id="collectionDescription" name="collectionDescription" ng-model="collectionDescription" class="form-control"></textarea>
                            </div>
                            </p>
                        </div>

                        <div>
                            <p>Select Item</p>
                            <div class="controls">
                                <input type="file" />
                            </div>
                            </p>
                        </div>
                        <div>
                            <p>Date Of Origin</p>
                            <div class="controls">
                               <p class="input-group">
                                                 <input id="23224" show-button-bar="false" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="startDate" is-open="opened3"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                                                 <span class="input-group-btn">
                                                   <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened3')">  <i class="glyphicon glyphicon-calendar"></i></button>
                                                 </span>
                                               </p>
                            </div>
                            </p>
                        </div>
                        <div>
                            <p>Date Of Add</p>
                            <div class="controls" >
                               <p class="input-group">
                                                <input id="23dd224" show-button-bar="false" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="endDate" is-open="opened13"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                                                 <span class="input-group-btn">
                                                   <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened13')">  <i class="glyphicon glyphicon-calendar"></i></button>
                                                 </span>
                                               </p>
                            </div>
                            </p>
                        </div>
                        <div class="controls">
                            <button class="btn" style=" margin-top:15px;">Add</button>
                            <button class="btn" style=" margin-top:15px;">Remove</button>
                            <button class="btn" style=" margin-top:15px;">Clear</button>
                        </div>

                    </form>
                </div>
                <div class="col-md-6">
                    <div style=" width: 950px; height:625px;margin-left: 25px;margin-top: 25px;">
                        <div id="grid1" ui-grid="itemGrid" ui-grid-selection class="grid" style=" width: 950px; height: 625px;" ></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>