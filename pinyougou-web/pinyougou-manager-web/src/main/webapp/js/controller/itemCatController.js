/**
 * Created by kim on 2019/5/27.
 */

app.controller("itemCatController",function($scope,itemCatService){


    $scope.mbxList=[];

    $scope.parentId=0; //上级ID


    $scope.findByParentId= function (obj) {

        $scope.parentId=$scope.id;//记住上级ID

        console.log(obj.name);
        $scope.mbxList.push(obj);
        $scope.dataList=[];

        itemCatService.findByParentId(obj.id).success(
         function(response){
             $scope.list=response;
             for (var i=0;i<$scope.dataList.length;i++){
                    $scope.dataList.push($scope.list[i]);
             }

         }


        )
    }












})