$(function(){
    var URL="http://localhost:8080/acc/ver";

    $.ajax({
        url:URL,
        type:"get",
        dataType:"text",
        statusCode:{
            200:function(data){
                 $("#loginname").html(data);
                //window.location="../shop/main.html";
            },
            404:function(data){
                $("#loginname").html("сн©м");
               //window.location="../shop/login.html";
            }
        }

    });

});