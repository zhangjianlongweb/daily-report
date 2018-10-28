$(function(){

    $("#add").click(function(){
        var URL="http://localhost:8080/acc/ver";
        $.ajax({
            url:URL,
            type:"get",
            dataType:"text",
            statusCode:{
                200:function(data){

                    add(data.toString().split(":")[0]);
                },
                404:function(data){
                    $("#loginname").html("");
                    window.location="../shop/login.html";
                }
            }
        });
    });
});
function add(userid){
    var URL="http://localhost:8080/cart/add";
    var cart={
        "userid":userid,
        "itemid":$("#itemid").val() ,
        "quantity":$("#quantity").val() ,
    }



    var s=JSON.stringify(cart);
    $.ajax({
        contentType:"application/json",
        url:URL,
        data:s,
        type:"post",
        dataType:"text json",
        statusCode:{
            200:function(data){


                window.location="../shop/cart.html";

            },
            404:function(data){
                $("#loginname").html("");
                window.location="../shop/login.html";
            }
        }
    });
}