var ip = "http://192.168.43.210:8080/Construtec.asmx/Parsear?frase="; 
angular.module('starter', ['ionic','starter.itemscontrol','starter.myproducts','starter.userscontrol', 'starter.categoriescontrol', 'starter.orderscontrol', 'starter.stadisticcontrol']) /*ionic le permite iniciar*/


.config(function($stateProvider, $urlRouterProvider){
    $stateProvider
    .state('login', {
                url:'/login',
                controller: 'LoginCtrl',
                templateUrl:'templates/login.html'
            })
    .state('menu', {
                url:'/menu',       
                controller: 'MenuCtrl',
                templateUrl:'templates/menu.html'
            })
    .state('home', {
                url:'/home',
                templateUrl:'templates/home.html',
                controller: 'HomeCtrl'
             
            })
    .state('cart', {
                url:'/cart',
                controller: 'CartCtrl',
                templateUrl:'templates/cart.html'
            })
    
    .state('about', {
                url:'/about',
                controller: 'AboutCtrl',
                templateUrl:'templates/about.html'
                })
    
    .state('stadistic', {
                url:'/stadistic',
                controller: 'StadisticCtrl',
                templateUrl:'templates/stadistic.html'
            })
    .state('myProducts', {
                url:'/myProducts',
                controller: 'MyProductsCtrl',
                templateUrl:'templates/products.html'
            })
    
    .state('orders', {
                url:'/orders',
                controller: 'OrdersCtrl',
                templateUrl:'templates/orders.html'
            })
    .state('SignUp', {
                url:'/signUp',
                controller: 'SignUpCtrl',
                templateUrl:'templates/signUp.html'
            })
    
        .state('maintenance', {
                url:'/maintenance',
                controller: 'MaintenanceCtrl',
                templateUrl:'templates/maintenance.html'
            })
    
    .state('epa', {
                url:'/epa',
                controller: 'EpaCtrl',
                templateUrl:'templates/epa.html'
            })
    
        $urlRouterProvider.otherwise('/login');
})


.controller('LoginCtrl', function($scope, $state, loginData,$http){
    $scope.login = {username:'', password:'',name:'',id:'', office:'', menutype:'' };
        var form = document.getElementById("myForm");  
        form.onsubmit = function(){
        form.reset();
      }
    $scope.verificar =  function(login){
                    
                    var peticion = "listar/clientes/_office/_name/_id/where/_identityNumber/"
                    var request = "";
                    request = request.concat(ip, peticion, $scope.login.username,"/_password/",$scope.login.password);
                    console.log("Request es:", request);
                                $http.get(request)
                        .then(function (response) {
                        console.log('Get Post', response);
                        console.log("Get Post status", response.data);
                        var data = response.data;
                        var result = data.substring(76, data.length - 9);
                        console.log("Get Post status", result);
    
                        if (result==="[]"){
                            alert("login erorr")
                            }
                        else if (result=="no se pudo conectar"){
                            alert("database conection error");
                        }
                        else{
                            var result2 = angular.fromJson(result);
                            console.log("Get Post status 2", result2);

                            updateRoles(login,result2[0]._id,result2[0]._name,result2[0]._office,loginData)

                        }

                    });   
    };
    
    function updateRoles(login,id,name,office,loginData){
        
    var rol = []; 
    
    var peticion = "listar/listarrolporid/"
    var request = "";
        
    request = request.concat(ip,peticion,id);
     console.log(request);
        
    $http.get(request)
            .then(function (response) {
            console.log('Get Post', response);
            console.log("Get Post status", response.data);
            var data = response.data;
            var result = data.substring(76, data.length - 9);
            console.log("Get Post status", result);
        
        
            var result4 = angular.fromJson(result);

    for(i=0; i<result4.length;i++){
            rol.push(result4[i].rol_id);
                                    }

            if (rol[0]==0){  
            loginData.updateLogin(login,id,name,office,rol);
                
            $state.go('home');
            }

            else if (rol[0]==1){    
            loginData.updateLogin(login,id,name,office,rol);
            $state.go('myProducts');
            }

            else if (rol[0]==2){    
            loginData.updateLogin(login,id,name,office,rol);
             $state.go('orders');
            }

            else if (rol[0]==3){    
            log.updateLogin(login,id,name,office,rol);
             $state.go('orders');
            }
            else if (rol[0]==4){    
            loginData.updateLogin(login,id,name,office,rol);
            $state.go('stadistic');
            }
         }); 

};
})

.controller('MenuCtrl', function($scope, $state, loginData){
    $scope.login = loginData.getLogin();
    $scope.buyer=false;
    $scope.seller=false;
    $scope.depen=false;
    $scope.admi=false;
    $scope.manage=false;
    console.log($scope.login.menutype);
    
    
    var type=$scope.login.menutype;
for ( i= 0; i< type.length; i++ )  { 
     if (type[i]==0){
         $scope.buyer=true;
     }
      else if (type[i]==1){
         $scope.seller=true;
     }
      else if (type[i]==2){
         $scope.depen=true;
     }
     else if (type[i]==3){
         $scope.admi=true;
     }
     else if (type[i]==4){
         $scope.manage=true;
     }
}


    $scope.logOut = function(){        
        $state.go('login');
        window.location.reload()    
    ;
    };
 })


.controller('HomeCtrl', function($scope, ItemsControl,$http) {

                    var peticion = "listar,productos"
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
                                $scope.items.push(result2[i]);
                            }
                            var result3 = $scope.items
                            console.log("Result3 ", result3);
                    });
    
   $scope.update = function() {
       addDelay();
       var itemsFinal = $scope.itemsFinal = [];
       ItemsControl.update($http);
   }
   
 
    var letters = $scope.letters = [];
    var itemsFinal = $scope.itemsFinal = [];
    var currentCharCode = ' '.charCodeAt(0) - 1;
    var letterHasMatch = {};  
    

    function addDelay() {
        $scope.items
        .sort(function(a, b) {
      return a._description.toUpperCase().charCodeAt(0) > b._description.toUpperCase().charCodeAt(0) ? 1 : -1;
    })
    .forEach(function(item) {
      //Get the first letter of the last name, and if the last name changes
      //put the letter in the array
        
      var itemCharCode = item._description.toUpperCase().charCodeAt(0);
        
        
        
      if (itemCharCode < 65) {
         itemCharCode = 35; 
      }
   
      //We may jump two letters, be sure to put both in
      //(eg if we jump from Adam Bradley to Bob Doe, add both C and D)
      var difference = itemCharCode - currentCharCode;
      for (var i = 1; i <= difference; i++) {
        /*console.log(String.fromCharCode(currentCharCode));*/
        addLetter(currentCharCode + i);;
        
      }
      currentCharCode = itemCharCode;
        console.log(item);
      itemsFinal.push(item);
    });
    }
    
    
    $scope.getItems = function(search) {
    $scope.items = ItemsControl.list();
    $scope.search = search;
    letterHasMatch = {};
        
    //Filter contacts by $scope.search.
    //Additionally, filter letters so that they only show if there
    //is one or more matching contact
    return itemsFinal.filter(function(item) {
      var itemDoesMatch = !$scope.search || item.isLetter ||
        item._description.toLowerCase().indexOf($scope.search.toLowerCase()) > -1
      //console.log(item.last_name.toString().charAt(0));
      
      //Mark this person's last name letter as 'has a match'
      if (!item.isLetter && itemDoesMatch) {

        var letter = item._description.charAt(0).toUpperCase();
        if ( item._description.charCodeAt(0) < 65 ){
          letter = "#";
        }
        letterHasMatch[letter] = true;
      }

      return itemDoesMatch;
    }).filter(function(item) {
      //Finally, re-filter all of the letters and take out ones that don't
      //have a match
      if (item.isLetter && !letterHasMatch[item.letter]) {
        return false;
      }
      return true;
    });
  };
    
    
    function addLetter(code) {
    var letter = String.fromCharCode(code);

    itemsFinal.push({
      isLetter: true,
      letter: letter
    });
   
    letters.push(letter);
  }
    
  $scope.addItemCart = function(id) {
      var item = ItemsControl.get(id);
      ItemsControl.addCart(item);
      
  };  
    
})
 
.controller('AboutCtrl', function($scope, $state){

})


.controller('CartCtrl', function($scope, $state, ItemsControl,$http,loginData) {
    $scope.login = loginData.getLogin();
    $scope.items = ItemsControl.listCart();;
    $scope.goBack = function(){ 
        $state.go('home');
    };
    var letters = $scope.letters = [];
    var itemsFinal = $scope.itemsFinal = [];
    var currentCharCode = ' '.charCodeAt(0) - 1;
    var letterHasMatch = {};  
    $scope.items
        .sort(function(a, b) {
      return a._description.toUpperCase().charCodeAt(0) > b._description.toUpperCase().charCodeAt(0) ? 1 : -1;
    })
    .forEach(function(item) {
      //Get the first letter of the last name, and if the last name changes
      //put the letter in the array
      var itemCharCode = item._description.toUpperCase().charCodeAt(0);
      if (itemCharCode < 65) {
         itemCharCode = 35; 
      }
   
      //We may jump two letters, be sure to put both in
      //(eg if we jump from Adam Bradley to Bob Doe, add both C and D)
      var difference = itemCharCode - currentCharCode;

      for (var i = 1; i <= difference; i++) {
        /*console.log(String.fromCharCode(currentCharCode));*/
        addLetter(currentCharCode + i);
      }
      currentCharCode = itemCharCode;
      itemsFinal.push(item);
    });
    
    $scope.getItems = function(search) {
    $scope.search = search;
    letterHasMatch = {};
    //Filter contacts by $scope.search.
    //Additionally, filter letters so that they only show if there
    //is one or more matching contact
    return itemsFinal.filter(function(item) {
      var itemDoesMatch = !$scope.search || item.isLetter ||
        item._description.toLowerCase().indexOf($scope.search.toLowerCase()) > -1

      //console.log(item.last_name.toString().charAt(0));
      
      //Mark this person's last name letter as 'has a match'
      if (!item.isLetter && itemDoesMatch) {

        var letter = item._description.charAt(0).toUpperCase();
        if ( item._description.charCodeAt(0) < 65 ){
          letter = "#";
        }
        letterHasMatch[letter] = true;
      }

      return itemDoesMatch;
    }).filter(function(item) {
      //Finally, re-filter all of the letters and take out ones that don't
      //have a match
      if (item.isLetter && !letterHasMatch[item.letter]) {
        return false;
      }
      
      return true;
    });
  };
    
    for (var i = currentCharCode + 1; i <= 'Z'.charCodeAt(0); i++) {
    addLetter(i);
  }
    
    function addLetter(code) {
    var letter = String.fromCharCode(code);

    itemsFinal.push({
      isLetter: true,
      letter: letter
    });
   
    letters.push(letter);
  }
    
   

    $scope.eliminateItemCart = function(id) {
      var item = ItemsControl.getCart(id);
      ItemsControl.elimCart(item); 
      
  };
     
     
     
     $scope.checkout = function(office) {
        var newid = new Date().getTime().toString().slice(2,12);
        var newid2 = new Date().getTime().toString().slice(3,13);
        var hour = new Date();
        var time="";
         time=time.concat(hour.getHours().toString(),":",hour.getMinutes().toString(),":",hour.getSeconds().toString());
         
            
                    var peticion = "crear/pedido/"
                    var request = "";
                    request = request.concat(ip, peticion,$scope.login.id,"/",newid,"/",office.replace(" ","%20"),"/",time,"/",0);
                    console.log("Request es:", request);
                    $http.get(request)
                            .then(function (response) {
                            console.log('Get Post', response);
                            console.log("Get Post status", response.data);
                            var data = response.data;
                            var result = data.substring(76, data.length - 9);
                            console.log("Get Post status", result);                            
                            if (result==="[]"){
                                updaterelation(newid);
                            }
                            else{
                                alert("check the data");
                            }
                    });
     
         };
     function updaterelation(newid){
        var peticion = "actualizarproductopedido/"
        for (var i in $scope.items) {
             var id = $scope.items[i]._id;
             var cant = $scope.items[i]._amount;
             var request = "";
             request = request.concat(ip, peticion,id,"/",newid,"/",cant);
             console.log("Request es:", request);
             $http.get(request)
                .then(function (response) {
                    console.log('Get Post', response);
                            console.log("Get Post status", response.data);
                            var data = response.data;
                            var result = data.substring(76, data.length - 9);
                            console.log("Get Post status", result);                          
                            if (result==="[]"){
                                return;
                            }
                            else{
                                alert("error");
                            }
             }  
     );
  }
    alert("order created");     
    var itemsFinal = $scope.itemsFinal = [];
     }
 
 })



.controller('MyProductsCtrl', function($scope, $state,ProductsControl,$http,ProductsControl,loginData) {
$scope.login = loginData.getLogin();
     
     
     
$scope.items = ProductsControl.list();
    
   $scope.update = function() {
       
       var ip = "http://webserviceepatec.azurewebsites.net/EPATEC.asmx/Parsear?frase=";
                   var peticion = "pedirpedidoporproveedor/"
                    var request = "";
                    request = request.concat(ip, peticion,$scope.login.id);
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
                                $scope.items.push(result2[i]);
                            }
                            var result3 = $scope.items
                            console.log("Result3 ", result3);
                    });
        itemsFinal=$scope.itemsFinal=[];
        
        addDelay();
       ProductsControl.update($http,$scope.login.id);
    
       
   };
     
    var letters = $scope.letters = [];
    var itemsFinal = $scope.itemsFinal = [];
    var currentCharCode = ' '.charCodeAt(0) - 1;
    var letterHasMatch = {};  
    $scope.items = [];
    function addDelay() {
        $scope.items
        .sort(function(a, b) {
      return a._description.toUpperCase().charCodeAt(0) > b._description.toUpperCase().charCodeAt(0) ? 1 : -1;
    })
    .forEach(function(item) {
      //Get the first letter of the last name, and if the last name changes
      //put the letter in the array
        
      var itemCharCode = item._description.toUpperCase().charCodeAt(0);
        
        
        
      if (itemCharCode < 65) {
         itemCharCode = 35; 
      }
   
      //We may jump two letters, be sure to put both in
      //(eg if we jump from Adam Bradley to Bob Doe, add both C and D)
      var difference = itemCharCode - currentCharCode;
      for (var i = 1; i <= difference; i++) {
        /*console.log(String.fromCharCode(currentCharCode));*/
        addLetter(currentCharCode + i);;
        
      }
      currentCharCode = itemCharCode;
        console.log(item);
      itemsFinal.push(item);
    });
    }
    
    
    $scope.getItems = function(search) {
    $scope.items = ProductsControl.list();
    $scope.search = search;
    letterHasMatch = {};
        
    //Filter contacts by $scope.search.
    //Additionally, filter letters so that they only show if there
    //is one or more matching contact
    return itemsFinal.filter(function(item) {
      var itemDoesMatch = !$scope.search || item.isLetter ||
        item._description.toLowerCase().indexOf($scope.search.toLowerCase()) > -1
      //console.log(item.last_name.toString().charAt(0));
      
      //Mark this person's last name letter as 'has a match'
      if (!item.isLetter && itemDoesMatch) {

        var letter = item._description.charAt(0).toUpperCase();
        if ( item._description.charCodeAt(0) < 65 ){
          letter = "#";
        }
        letterHasMatch[letter] = true;
      }

      return itemDoesMatch;
    }).filter(function(item) {
      //Finally, re-filter all of the letters and take out ones that don't
      //have a match
      if (item.isLetter && !letterHasMatch[item.letter]) {
        return false;
      }
      return true;
    });
  };
    

    
    function addLetter(code) {
    var letter = String.fromCharCode(code);

    itemsFinal.push({
      isLetter: true,
      letter: letter
    });
   
    letters.push(letter);
  }
    
    $scope.addProducts=function(name,cant,tax,office){
        $scope.login = loginData.getLogin();
            console.log("en añadir");
            var form2 = document.getElementById("myForm2");
            var peticion = "crear/producto/"
            var request = "";
            var newid = new Date().getTime().toString().slice(4,14);
        
                   request = request.concat(ip, peticion,0,"/",$scope.login.id,"/",newid,"/",tax,"/",cant,"/",office.replace(" ","%20"),"/",name.replace(" ","%20"));
                    console.log("Request es:", request);
                    $http.get(request)
                            .then(function (response) {
                            console.log('Get Post', response);
                            console.log("Get Post status", response.data);
                            var data = response.data;
                            var result = data.substring(76, data.length - 9);
                            console.log("Get Post status", result);
                            if (result==="[]"){
                                alert("Product Created");
                                form2.reset();
                            }
                    });
    };
})
 
 


.controller('StadisticCtrl', function($scope, $state, $http, StadisticControl,loginData){
     var login = loginData.getLogin();
     var office=login.office;
     
     
     StadisticControl.updateBS($http);
     StadisticControl.updateBSO($http, office);
     StadisticControl.updateSPO($http, office);
     $scope.getItemsBS = function() {
         return StadisticControl.listBS();
     };
     $scope.getItemsBS = function() {
         return StadisticControl.listBSO();
     };
     $scope.getItemsSPO = function() {
         return StadisticControl.listSPO();
     };
})


.controller('OrdersCtrl', function($scope, $state, $http, loginData, OrdersControl){
     
     $scope.items = OrdersControl.list();
     
     $scope.login = loginData.getLogin();
     var office = $scope.login.office;
    
   $scope.update = function() {
       
                    //Agregar peticion para este tipo
                    var peticion = "pedidosporoficina/"
                    
                    //var officeFinal = office;
                    
                    var request = "";
                    request = request.concat(ip, peticion, office);
                    console.log("Request es 9:", request);
                    $http.get(request)
                            .then(function (response) {
                            console.log('Get Post', response);
                            console.log("Get Post status", response.data);
                            var data = response.data;
                            var result = data.substring(76, data.length - 9);
                            console.log("Get Post status", result);
                            var result2 = angular.fromJson(result);
                            console.log("Get Post status 9", result2);                            
                            for(var i in result2) {
                                $scope.items.push(result2[i]);
                            }
                            var result3 = $scope.items
                            console.log("Result3 ", result3);
                    });
        var itemsFinal= $scope.itemsFinal = [];
       addDelay();
       OrdersControl.update($http, office);
   }
   
 
    var letters = $scope.letters = [];
    var itemsFinal = $scope.itemsFinal = [];
    var currentCharCode = ' '.charCodeAt(0) - 1;
    var letterHasMatch = {};  
    

    function addDelay() {
        $scope.items
        .sort(function(a, b) {
      return a._office.toUpperCase().charCodeAt(0) > b._office.toUpperCase().charCodeAt(0) ? 1 : -1;
    })
    .forEach(function(item) {
      //Get the first letter of the last name, and if the last name changes
      //put the letter in the array
        
      var itemCharCode = item._office.toUpperCase().charCodeAt(0);
        
        
        
      if (itemCharCode < 65) {
         itemCharCode = 35; 
      }
   
      //We may jump two letters, be sure to put both in
      //(eg if we jump from Adam Bradley to Bob Doe, add both C and D)
      var difference = itemCharCode - currentCharCode;
      for (var i = 1; i <= difference; i++) {
        /*console.log(String.fromCharCode(currentCharCode));*/
        addLetter(currentCharCode + i);;
        
      }
      currentCharCode = itemCharCode;
        console.log(item);
      itemsFinal.push(item);
    });
    }
    
    
    $scope.getItems = function(search) {
    $scope.items = OrdersControl.list();
    $scope.search = search;
    letterHasMatch = {};
        
    //Filter contacts by $scope.search.
    //Additionally, filter letters so that they only show if there
    //is one or more matching contact
    return itemsFinal.filter(function(item) {
      var itemDoesMatch = !$scope.search || item.isLetter ||
        item._office.toLowerCase().indexOf($scope.search.toLowerCase()) > -1
      //console.log(item.last_name.toString().charAt(0));
      
      //Mark this person's last name letter as 'has a match'
      if (!item.isLetter && itemDoesMatch) {

        var letter = item._office.charAt(0).toUpperCase();
        if ( item._office.charCodeAt(0) < 65 ){
          letter = "#";
        }
        letterHasMatch[letter] = true;
      }

      return itemDoesMatch;
    }).filter(function(item) {
      //Finally, re-filter all of the letters and take out ones that don't
      //have a match
      if (item.isLetter && !letterHasMatch[item.letter]) {
        return false;
      }
      return true;
    });
  };
    
    
    function addLetter(code) {
    var letter = String.fromCharCode(code);

    itemsFinal.push({
      isLetter: true,
      letter: letter
    });
   
    letters.push(letter);
  }
     
})
 
 
 
 
.controller('SignUpCtrl', function($scope, $state, $http ){
    var form = document.getElementById("myForm");
    var request = "";
     
    $scope.createUser = function(type){ 
        /* verificar que usuario no exista*/

                var peticion = "listar/clientes/_id/where/_identityNumber/"

                request = request.concat(ip, peticion,$scope.signUp.inum);
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
                    
                            if (result2.length===0){
                                registry(type);
                            }else{
                                alert("Identity number already in use")
                            }                         
             
                    })}
                                  
        function registry(type){
                            var request2 = "";
                            var peticion2 = "crear/cliente/_id/"
                            var newid = new Date().getTime().toString().slice(4,14);
                            peticion2 = peticion2.concat(newid,"/_name/",$scope.signUp.Uname.replace(" ","%20"),"/_lastName1/", $scope.signUp.lname1,"/_lastName2/",$scope.signUp.lname2,"/_cellPhone/",$scope.signUp.phone,"/_identityNumber/",$scope.signUp.inum,"/_residenceAddress/",$scope.signUp.address.replace(" ","%20"),"/_birthDate/",$scope.signUp.bdate,"/_type/",type,"/_password/",$scope.signUp.password);
                                
                            request2 = request2.concat(ip, peticion2);
                            console.log("Request es:", request2);
                                
                            $http.get(request2)
                                .then(function (response) {
                                console.log("enty");
                                console.log('Get Post', response);
                                console.log("Get Post status", response.data);
                                var data = response.data;
                                var result = data.substring(76, data.length - 9);
                                console.log("Get Post status", result);
                                if (result==="no se pudo conectar"){
                                    alert("check the data");
                                    
                                }else{
                                    form.reset();
                                    $state.go('login');
                                }
                            });
                                 };
          
})
 
.controller('MaintenanceCtrl', function($scope, $state,ItemsControl, UsersControl, CategoriesControl, $http){
        $scope.users=false;
        $scope.category=false;
        $scope.products=false
        $scope.items = ItemsControl.list();
        $scope.allUsers = UsersControl.list();
        $scope.allCategory = CategoriesControl.list();
    
   $scope.updateUsers=function(){
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
                                $scope.allUsers.push(result2[i]);
                            }
                            var result3 = $scope.allUsers;
                    
                            var arr = {};

                            for ( var i=0, len=$scope.allUsers.length; i < len; i++ )
                                arr[$scope.allUsers[i]['_id']] = $scope.allUsers[i];

                            $scope.allUsers = new Array();
                            for ( var key in arr )
                                $scope.allUsers.push(arr[key]);
                    
                            console.log("Result3 ", result3);
                    });
        itemsFinalU=$scope.itemsFinalU=[]
        addDelayU();
       UsersControl.update($http);  
   };
     
     
    $scope.updateCategories=function(){
        
       var peticion = "listar/categorias"
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
                                $scope.allCategory.push(result2[i]);
                            }
                            var result3 = $scope.allCategory;
                    
                            var arr = {};

                            for ( var i=0, len=$scope.allCategory.length; i < len; i++ )
                                arr[$scope.allCategory[i]['_id']] = $scope.allCategory[i];

                            $scope.allCategory = new Array();
                            for ( var key in arr )
                                $scope.allCategory.push(arr[key]);
                    
                    
                            console.log("Result3 ", result3);
                    });
        
               itemsFinalC=$scope.itemsFinalC=[]
        
        addDelayC();
       CategoriesControl.update($http); 
   };
     

    $scope.updateProducts=function(){
        
        var peticion = "listar/productos"
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
                                $scope.items.push(result2[i]);
                            }
                            $scope.items = ItemsControl.list();
                            var result3 = $scope.items
                            var arr = {};

                            for ( var i=0, len=$scope.items.length; i < len; i++ )
                                arr[$scope.items[i]['_id']] = $scope.items[i];

                            $scope.items = new Array();
                            for ( var key in arr )
                                $scope.items.push(arr[key]);
                            console.log("Result3 ", result3);
                    });
        itemsFinal=$scope.itemsFinal=[];
        
        addDelay();
       ItemsControl.update($http);  
   };
     
     
     $scope.updateview=function(num){
    if (num===1){
        $scope.users=true;
        $scope.category=false;
        $scope.products=false;
    }
    else if (num===2){

        $scope.category=true;
        $scope.users=false;
        $scope.products=false;
    }
    else {

        $scope.products=true;
        $scope.users=false;
        $scope.category=false;
    }
};
     
     
    var letters = $scope.letters = [];
    var itemsFinal = $scope.itemsFinal = [];
    var currentCharCode = ' '.charCodeAt(0) - 1;
    var letterHasMatch = {};  
    

    function addDelay() {
        $scope.items
        .sort(function(a, b) {
      return a._description.toUpperCase().charCodeAt(0) > b._description.toUpperCase().charCodeAt(0) ? 1 : -.1;
    })
    .forEach(function(item) {
      //Get the first letter of the last name, and if the last name changes
      //put the letter in the array
        
      var itemCharCode = item._description.toUpperCase().charCodeAt(0);
        
        
        
      if (itemCharCode < 65) {
         itemCharCode = 35; 
      }
   
      //We may jump two letters, be sure to put both in
      //(eg if we jump from Adam Bradley to Bob Doe, add both C and D)
      var difference = itemCharCode - currentCharCode;
      for (var i = 1; i <= difference; i++) {
        /*console.log(String.fromCharCode(currentCharCode));*/
        addLetter(currentCharCode + i);;
        
      }
      currentCharCode = itemCharCode;
        console.log(item);
      itemsFinal.push(item);
    });
    }
    
    
    $scope.getItems = function(search) {
    
    $scope.search = search;
    letterHasMatch = {};
        
    //Filter contacts by $scope.search.
    //Additionally, filter letters so that they only show if there
    //is one or more matching contact
    return itemsFinal.filter(function(item) {
      var itemDoesMatch = !$scope.search || item.isLetter ||
        item._description.toLowerCase().indexOf($scope.search.toLowerCase()) > -1
      //console.log(item.last_name.toString().charAt(0));
      
      //Mark this person's last name letter as 'has a match'
      if (!item.isLetter && itemDoesMatch) {

        var letter = item._description.charAt(0).toUpperCase();
        if ( item._description.charCodeAt(0) < 65 ){
          letter = "#";
        }
        letterHasMatch[letter] = true;
      }

      return itemDoesMatch;
    }).filter(function(item) {
      //Finally, re-filter all of the letters and take out ones that don't
      //have a match
      if (item.isLetter && !letterHasMatch[item.letter]) {
        return false;
      }
      return true;
    });
  };
    
    
    function addLetter(code) {
    var letter = String.fromCharCode(code);

    itemsFinal.push({
      isLetter: true,
      letter: letter
    });
   
    letters.push(letter);
  }
   
 $scope.deleteItem=function(id){
     var item = ItemsControl.get(id);
      ItemsControl.elimItem(item);
     var peticion = "eliminar/producto/_id/"
        var request = "";
                   request = request.concat(ip, peticion,id);
                    console.log("Request es:", request);
                $http.get(request)
                            .then(function (response) {
                            console.log('Get Post', response);
                            console.log("Get Post status", response.data);
                    });
     
     
 };
     
     var lettersU = $scope.lettersU = [];
    var itemsFinalU = $scope.itemsFinalU = [];
    var currentCharCodeU = ' '.charCodeAt(0) - 1;
    var letterHasMatchU = {};  
    

    function addDelayU() {
        $scope.allUsers
        .sort(function(a, b) {
      return a._name.toUpperCase().charCodeAt(0) > b._name.toUpperCase().charCodeAt(0) ? 1 : -.1;
    })
    .forEach(function(user) {
      //Get the first letter of the last name, and if the last name changes
      //put the letter in the array
        
      var userCharCode = user._name.toUpperCase().charCodeAt(0);
        
        
        
      if (userCharCode < 65) {
         userCharCode = 35; 
      }
   
      //We may jump two letters, be sure to put both in
      //(eg if we jump from Adam Bradley to Bob Doe, add both C and D)
      var differenceU = userCharCode - currentCharCodeU;
      for (var i = 1; i <= differenceU; i++) {
        /*console.log(String.fromCharCode(currentCharCode));*/
        addLetterU(currentCharCodeU + i);;
        
      }
      currentCharCodeU = userCharCode;
      itemsFinalU.push(user);
    });
    }
    
    
    $scope.getUsers = function(search) {
    
    $scope.search = search;
    letterHasMatchU = {};
        
    //Filter contacts by $scope.search.
    //Additionally, filter letters so that they only show if there
    //is one or more matching contact
    return itemsFinalU.filter(function(user) {
      var itemDoesMatch = !$scope.search || user.isLetter ||
        user._name.toLowerCase().indexOf($scope.search.toLowerCase()) > -1
      //console.log(item.last_name.toString().charAt(0));
      
      //Mark this person's last name letter as 'has a match'
      if (!user.isLetter && itemDoesMatch) {

        var letter = user._name.charAt(0).toUpperCase();
        if ( user._name.charCodeAt(0) < 65 ){
          letter = "#";
        }
        letterHasMatchU[letter] = true;
      }

      return itemDoesMatch;
    }).filter(function(user) {
      //Finally, re-filter all of the letters and take out ones that don't
      //have a match
      if (user.isLetter && !letterHasMatchU[user.letter]) {
        return false;
      }
      return true;
    });
  };
    
    
    function addLetterU(code) {
    var letter = String.fromCharCode(code);

    itemsFinalU.push({
      isLetter: true,
      letter: letter
    });
   
    lettersU.push(letter);
  }
     
     $scope.deleteUser=function(id){
     
     var peticion = "eliminar/cliente/_id/"
        var request = "";
                   request = request.concat(ip, peticion,id);
                    console.log("Request es:", request);
                $http.get(request)
                            .then(function (response) {
                            console.log('Get Post', response);
                            console.log("Get Post status", response.data);
                    }); 
 };
    
     
     var lettersC = $scope.lettersC = [];
    var itemsFinalC = $scope.itemsFinalC = [];
    var currentCharCodeC = ' '.charCodeAt(0) - 1;
    var letterHasMatchC = {};  
    

    function addDelayC() {
        $scope.allCategory
        .sort(function(a, b) {
      return a._description.toUpperCase().charCodeAt(0) > b._description.toUpperCase().charCodeAt(0) ? 1 : -.1;
    })
    .forEach(function(category) {
      //Get the first letter of the last name, and if the last name changes
      //put the letter in the array
        
      var categoryCharCode = category._description.toUpperCase().charCodeAt(0);
        
        
        
      if (categoryCharCode < 65) {
         categoryCharCode = 35; 
      }
   
      //We may jump two letters, be sure to put both in
      //(eg if we jump from Adam Bradley to Bob Doe, add both C and D)
      var differenceC = categoryCharCode - currentCharCodeC;
      for (var i = 1; i <= differenceC; i++) {
        /*console.log(String.fromCharCode(currentCharCode));*/
        addLetterC(currentCharCodeC + i);;
        
      }
      currentCharCodeC = categoryCharCode;
      itemsFinalC.push(category);
    });
    }
    
    
    $scope.getCategories = function(search) {
    
    $scope.search = search;
    letterHasMatchC = {};
        
    //Filter contacts by $scope.search.
    //Additionally, filter letters so that they only show if there
    //is one or more matching contact
    return itemsFinalC.filter(function(category) {
      var itemDoesMatch = !$scope.search || category.isLetter ||
        category._lastName1.toLowerCase().indexOf($scope.search.toLowerCase()) > -1
      //console.log(item.last_name.toString().charAt(0));
      
      //Mark this person's last name letter as 'has a match'
      if (!category.isLetter && itemDoesMatch) {

        var letter = category._description.charAt(0).toUpperCase();
        if ( category._description.charCodeAt(0) < 65 ){
          letter = "#";
        }
        letterHasMatchC[letter] = true;
      }

      return itemDoesMatch;
    }).filter(function(category) {
      //Finally, re-filter all of the letters and take out ones that don't
      //have a match
      if (category.isLetter && !letterHasMatchC[category.letter]) {
        return false;
      }
      return true;
    });
  };
    
    
    function addLetterC(code) {
    var letter = String.fromCharCode(code);

    itemsFinalC.push({
      isLetter: true,
      letter: letter
    });
   
    lettersC.push(letter);
  }
     
     $scope.deleteCategory=function(id){
     
     var peticion = "eliminar/categoria/"
        var request = "";
                   request = request.concat(ip, peticion,id);
                    console.log("Request es:", request);
                $http.get(request)
                            .then(function (response) {
                            console.log('Get Post', response);
                            console.log("Get Post status", response.data);
                    });
     
     
 };

$scope.addUser=function(Uname,lname1,lname2,phone,address,inum,bdate,type,password,office){
    var form = document.getElementById("myForm3");
    var request = "";
    var peticion = "listar/clientes/_id/where/_identityNumber/"

                request = request.concat(ip, peticion,inum);
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
                    
                            if (result2.length===0){
                                registry(Uname,lname1,lname2,phone,address,inum,bdate,type,"123",office);
                                
                            }else{
                                alert("Identity number already in use")
                            }
                    });
};
function registry(Uname,lname1,lname2,phone,address,inum,bdate,type,password,office){
							var form = document.getElementById("myForm3");
                            var request2 = "";
                            var peticion2 = "crear/cliente/"
                            var newid = new Date().getTime().toString().slice(2,12);
                            //crear/cliente/id/nombre/ap1/ap2/cell/cedul/residencia/fechaNa/rolid/12-12-12/juanProducto
                            peticion2 = peticion2.concat(newid,"/",Uname.replace(" ","%20"),"/", lname1,"/",lname2,"/",phone,"/",inum,"/",address.replace(" ","%20"),"/",bdate,"/",type,"/",password,"/",Uname.replace(" ","%20"),123);
                                
                            request2 = request2.concat(ip, peticion2);
                 
                            console.log("Request es:", request2);
                                
                            $http.get(request2)
                                .then(function (response) {
                                console.log("enty");
                                console.log('Get Post', response);
                                console.log("Get Post status", response.data);
                                var data = response.data;
                                var result = data.substring(76, data.length - 9);
                                console.log("Get Post status", result);
                                if (result==="no se pudo conectar"){
                                    alert("check the data");
                                    
                                }else{
                                form.reset();
                                }
                            });
                                 
                                 };
             
                    
$scope.addCategory=function(cname){

    var form2 = document.getElementById("myForm2");
    var peticion = "crear/categoria/"
    var request = "";
    var newid = new Date().getTime().toString().slice(3,13);
                   request = request.concat(ip, peticion,newid,"/",cname);
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
                            if (result2.length===0){
                                alert("Category Created");
                                form2.reset();
                            }
                    });
};
})
 
 
.controller('EpaCtrl', function($scope, $state){
})

.service('loginData', function() {
return {
login: {},
getLogin: function() {// para recuperar los datos 
 return this.login;
},
updateLogin: function(login,id,name,office,menutype) {// para actualizar los datos del login, y definir el tipo de menu segun tipo de usuario
   this.login = login;// username, password
   this.login.id=id;
   this.login.name=name;
   this.login.office=office;
   this.login.menutype=menutype;// tipo de menu segun permisos de del tipo de usuarios
}
}
}) 
 
.directive('menu', function() {
  return {
     templateUrl: 'templates/menu.html',
      controller:"MenuCtrl"
  };});
