angular.module('demo', [])
    .controller('orderItem', function ($scope, $http) {

        const baseUrl = 'http://localhost:5000/orderItems/';

        //get the list of orderItems
        $http.get(baseUrl).
            then(function (response) {
                console.log(response);
                $scope.orderItems = response.data._embedded?.orderItemList;
            });

        //filter deleted record
        $scope.deletedFilter = function (orderItem) {
            return !orderItem.deleted;
        };

        $scope.add = function () {
            var hasNewRecord = false;
            //check if another new record is still pending
            if( $scope.orderItems != null ){
            	angular.forEach($scope.orderItems, function (orderItem) {
                	if (orderItem.orderItemID == null && !orderItem.deleted)
                	    hasNewRecord = true;
            	});
            }
            if (!hasNewRecord) {
                //add a new record
                if( $scope.orderItems == null ){
                	$scope.orderItems = [];
                }
                $scope.orderItems.push({ orderItemID: null });
            }
        };

        $scope.delete = function (id) {
            angular.forEach($scope.orderItems, function (orderItem) {
                if (orderItem.orderItemID == id) {
                    //mark the record as deleted
                 orderItem.deleted = true;
                    console.log("deleting: " + orderItem);
                }
            });
        };

        $scope.save = function () {
            angular.forEach($scope.orderItems, function (orderItem) {
                if (orderItem.deleted) {
                    if (orderItem.orderItemID != null) {
                        //delete record
                        $http.delete(baseUrl + orderItem.orderItemID).
                            then(function (response) {
                                var index = $scope.orderItems.indexOf(orderItem);
                                $scope.orderItems.splice(index, 1);
                            });
                    }
                } else if (orderItem.orderItemID == null) {
                    //create new record
                    console.log(orderItem);
                    $http.post(baseUrl, orderItem).
                        then(function (response) {
                            console.log(orderItem);
                            orderItem.orderItemID = response.data.orderItemID;
                        });
                } else {
                    //edit existing record
                    console.log(orderItem);
                    $http.put(baseUrl + orderItem.orderItemID, orderItem).
                        then(function (response) { });
                }
            });
        };
    });