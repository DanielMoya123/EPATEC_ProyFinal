var ip = "http://192.168.43.210:8080/Construtec.asmx/Parsear?frase="; 
(function(){
    var app = angular.module('starter.userscontrol', [])
        app.factory('UsersControl', function(){
        var items = [];
            return {
                update: function($http) {
                    items = []
                    var peticion = "listar,clientes"
                    var request = "";
                    request = request.concat(ip, peticion);
                    console.log("Request es:", request);
                $http.get(request)
                            .then(function (response) {
                            console.log('Get Post', response);
                            console.log("Get Post status", response.data);
                            var data = response.data;
                            var result = data.substring(76, data.length - 9);
                            console.log("Get Post status", result);
                            var result2 = angular.fromJson(result);
                            console.log("Get Post status 2", result2);                            
                            for(var i in result2) {
                                items.push(result2[i]);
                            }
                    });
                },
                list: function($http, $scope){
                    return items;
                    
                },
                get: function(id){
                    return items.filter(function(item){
                        return item._id === id;
                    })[0];
                },
            };
        });
}());