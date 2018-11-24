$(function(){
    var URL="http://localhost:8080/acc/ver";

    $.ajax({
        url:URL,
        type:"get",
        dataType:"text",
        statusCode:{
            200:function(data){
               // alert(data.toString().split(":")[1]);
                 $("#loginname").html(data.toString().split(":")[1]);
                //window.location="../shop/main.html";
            },
            404:function(data){
                $("#loginname").html("");
               //window.location="../shop/login.html";
            }
        }

    });

});