angular.module('demo', [])
    .controller('item', function ($scope, $http) {

        const baseUrl = 'http://localhost:5000/items/';

        //get the list of items
        $http.get(baseUrl).
            then(function (response) {
                console.log(response);
                $scope.items = response.data._embedded.itemList;
            });

        //filter deleted record
        $scope.deletedFilter = function (item) {
            return !item.deleted;
        };

        $scope.add = function () {
            var hasNewRecord = false;
            //check if another new record is still pending
            angular.forEach($scope.items, function (item) {
                if (item.iid == null && !item.deleted)
                    hasNewRecord = true;
            });
            if (!hasNewRecord) {
                //add a new record
                $scope.items.push({ iid: null });
            }
        };

        $scope.delete = function (id) {
            angular.forEach($scope.items, function (item) {
                if (item.iid == id) {
                    //mark the record as deleted
                    item.deleted = true;
                    console.log("deleting: " + item.iid);
                }
            });
        };

        $scope.save = function () {
            angular.forEach($scope.items, function (item) {
                if (item.deleted) {
                    if (item.iid != null) {
                        //delete record
                        $http.delete(baseUrl + item.iid).
                            then(function (response) {
                                var index = $scope.items.indexOf(item);
                                $scope.items.splice(index, 1);
                            });
                    }
                } else if (item.iid == null) {
                    //create new record
                    console.log(item);
                    $http.post(baseUrl, item).
                        then(function (response) {
                            console.log(item);
                            item.iid = response.data.iid;
                            item.name = response.data.name;
                        });
                } else {
                    //edit existing record
                    console.log(item);
                    $http.put(baseUrl + item.iid, item).
                        then(function (response) { });
                }
            });
        };
    });