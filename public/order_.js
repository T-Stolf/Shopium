angular.module('demo', [])
    .controller('order_', function ($scope, $http) {

        const baseUrl = 'http://localhost:5000/orders/';

        //get the list of orders_
        $http.get(baseUrl).
            then(function (response) {
                console.log(response);
                $scope.orders_ = response.data._embedded?.order_List;
            });

        //filter deleted record
        $scope.deletedFilter = function (order_) {
            return !order_.deleted;
        };

        $scope.add = function () {
            var hasNewRecord = false;
            //check if another new record is still pending
            if( $scope.orders_ != null ){
            	angular.forEach($scope.orders_, function (order_) {
                	if (order_.orderID == null && !order_.deleted)
                	    hasNewRecord = true;
            	});
            }
            if (!hasNewRecord) {
                //add a new record
                if( $scope.orders_ == null ){
                	$scope.orders_ = [];
                }
                $scope.orders_.push({ orderID: null });
            }
        };

        $scope.delete = function (id) {
            angular.forEach($scope.orders_, function (order_) {
                if (order_.orderID == id) {
                    //mark the record as deleted
                 order_.deleted = true;
                    console.log("deleting: " + order_);
                }
            });
        };

		
		$scope.complete = function(id){
			angular.forEach($scope.orders_, function(order_){
				if(order_.orderID == id ){
					$http.put(baseUrl + id + "/complete").
						then(function(response) {} );
				}	
			});
		}
		$scope.cancel = function(id){
			angular.forEach($scope.orders_, function(order_){
				if(order_.orderID == id ){
					$http.put(baseUrl + id + "/cancel").
						then(function(response) {} );
				}	
			});
		}

        $scope.save = function () {
            angular.forEach($scope.orders_, function (order_) {
                if (order_.deleted) {
                    if (order_.orderID != null) {
                        //delete record
                        $http.delete(baseUrl + order_.orderID).
                            then(function (response) {
                                var index = $scope.orders_.indexOf(order_);
                                $scope.orders_.splice(index, 1);
                            });
                    }
                } else if (order_.orderID == null) {
                    //create new record
                    console.log(order_);
                    $http.post(baseUrl, order_).
                        then(function (response) {
                            console.log(order_);
                            order_.orderID = response.data.orderID;                          
                        });
                } else {
                    //edit existing record
                    console.log(order_);
                   // NO PUT METHOD FOR UPDATING AN ORDER
                   //$http.put(baseUrl + order_.orderID, order_).
                   //     then(function (response) { });
                }
            });
        };
    });