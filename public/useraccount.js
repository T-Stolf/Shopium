angular.module('demo', [])
    .controller('userAccount', function ($scope, $http) {

        const baseUrl = 'http://localhost:5000/userAccounts/';

        //get the list of userAccounts
        $http.get(baseUrl).
            then(function (response) {
                console.log(response);
                $scope.userAccounts = response.data._embedded?.userAccountList;
            });

        //filter deleted record
        $scope.deletedFilter = function (userAccount) {
            return !userAccount.deleted;
        };

        $scope.add = function () {
            var hasNewRecord = false;
            //check if another new record is still pending
            if( $scope.userAccounts != null ){
            	angular.forEach($scope.userAccounts, function (userAccount) {
                	if (userAccount.userID == null && !userAccount.deleted)
                	    hasNewRecord = true;
            	});
            }
            if (!hasNewRecord) {
                //add a new record
                if( $scope.userAccounts == null ){
                	$scope.userAccounts = [];
                }
                $scope.userAccounts.push({ userID: null });
            }
        };

        $scope.delete = function (id) {
            angular.forEach($scope.userAccounts, function (userAccount) {
                if (userAccount.userID == id) {
                    //mark the record as deleted
                 userAccount.deleted = true;
                    console.log("deleting: " + userAccount);
                }
            });
        };

        $scope.save = function () {
            angular.forEach($scope.userAccounts, function (userAccount) {
                if (userAccount.deleted) {
                    if (userAccount.userID != null) {
                        //delete record
                        $http.delete(baseUrl + userAccount.userID).
                            then(function (response) {
                                var index = $scope.userAccounts.indexOf(userAccount);
                                $scope.userAccounts.splice(index, 1);
                            });
                    }
                } else if (userAccount.userID == null) {
                    //create new record
                    console.log(userAccount);
                    $http.post(baseUrl, userAccount).
                        then(function (response) {
                            console.log(userAccount);
                            userAccount.userID = response.data.userID;
                        });
                } else {
                    //edit existing record
                    console.log(userAccount);
                    $http.put(baseUrl + userAccount.userID, userAccount).
                        then(function (response) { });
                }
            });
        };
    });