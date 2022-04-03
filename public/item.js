angular.module('demo', [])
    .controller('item', function ($scope, $http) {

        const baseUrl = 'http://localhost:5000/items/';

        //get the list of items
        $http.get(baseUrl).
            then(function (response) {
                console.log(response);
                $scope.items = response.data._embedded?.itemList;
            });

        //filter deleted record
        $scope.deletedFilter = function (item) {
            return !item.deleted;
        };

        $scope.add = function () {
            var hasNewRecord = false;
            //check if another new record is still pending
           if($scope.items != null){
            angular.forEach($scope.items, function (item) {
                if (item.itemID == null && !item.deleted)
                    hasNewRecord = true;
            });
            }
            if (!hasNewRecord) {
                //add a new record
                if( $scope.items == null ){
                	$scope.items = [];
                }
                $scope.items.push({ itemID: null });
            }
        };

        $scope.delete = function (id) {
            angular.forEach($scope.items, function (item) {
                if (item.itemID == id) {
                    //mark the record as deleted
                    item.deleted = true;
                    console.log("deleting: " + item.itemID);
                }
            });
        };

        $scope.save = function () {
            angular.forEach($scope.items, function (item) {
                if (item.deleted) {
                    if (item.itemID != null) {
                        //delete record
                        $http.delete(baseUrl + item.itemID).
                            then(function (response) {
                                var index = $scope.items.indexOf(item);
                                $scope.items.splice(index, 1);
                            });
                    }
                } else if (item.itemID == null) {
                    //create new record
                    console.log(item);
                    $http.post(baseUrl, item).
                        then(function (response) {
                            console.log(item);
                            item.itemID = response.data.itemID;                          
                        });
                } else {
                    //edit existing record
                    console.log(item);
                    $http.put(baseUrl + item.itemID, item).
                        then(function (response) { });
                }
            });
        };
    });