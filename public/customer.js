angular.module('demo', [])
    .controller('customer', function ($scope, $http) {

        const baseUrl = 'http://localhost:5000/customers/';

        //get the list of customers
        $http.get(baseUrl).
            then(function (response) {
                console.log(response);
                $scope.customers = response.data._embedded.customerList;
            });

        //filter deleted record
        $scope.deletedFilter = function (customer) {
            return !customer.deleted;
        };

        $scope.add = function () {
            var hasNewRecord = false;
            //check if another new record is still pending
            angular.forEach($scope.customers, function (customer) {
                if (customer.uid == null && !customer.deleted)
                    hasNewRecord = true;
            });
            if (!hasNewRecord) {
                //add a new record
                $scope.customers.push({ uid: null });
            }
        };

        $scope.delete = function (id) {
            angular.forEach($scope.customers, function (customer) {
                if (customer.uid == id) {
                    //mark the record as deleted
                    customer.deleted = true;
                    console.log("deleting: " + customer.uid);
                }
            });
        };

        $scope.save = function () {
            angular.forEach($scope.customers, function (customer) {
                if (customer.deleted) {
                    if (customer.uid != null) {
                        //delete record
                        $http.delete(baseUrl + customer.uid).
                            then(function (response) {
                                var index = $scope.customers.indexOf(customer);
                                $scope.customers.splice(index, 1);
                            });
                    }
                } else if (customer.uid == null) {
                    //create new record
                    console.log(customer);
                    $http.post(baseUrl, customer).
                        then(function (response) {
                            console.log(customer);
                            customer.uid = response.data.iid;
                        });
                } else {
                    //edit existing record
                    console.log(customer);
                    $http.put(baseUrl + customer.uid, customer).
                        then(function (response) { });
                }
            });
        };
    });