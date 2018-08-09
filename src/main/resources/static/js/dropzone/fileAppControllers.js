/**
 * Created by a.lam.tuan on 8. 8. 2018.
 */
fileAppControllers.controller('FileCtrl', ['scope',
    function ($scope) {

        $scope.partialDownloadLink = 'http://localhost:8080/download?filename=';
        $scope.filename = '';

        $scope.uploadFile = function() {
            $scope.processQueue();
        };

        $scope.reset = function() {
            $scope.resetDropzone();
        };
    }

]);
