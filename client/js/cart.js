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
   // alert($("#productid").val());
    var cart={
        "userid":userid,
        "itemid":$("#itemid").val() ,
        "quantity":$("#quantity").val() ,
        "item":{
            "productid":$("#productid").val()
        }
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
               // $("#msg>tbody").empty();
                //showCart(data);
                window.location="../shop/cart.html";

            },
            404:function(data){
                $("#loginname").html("");
                window.location="../shop/login.html";
            }
        }
    });
}
function showCart(userid){
    var URL="http://localhost:8080/cart/show"+"/"+userid;
    $.ajax({
        url:URL,
        type:"get",
        dataType:"text json",
        statusCode:{
            200:function(data){
                $("#msg>tbody").empty();
                var d=$("#msg>thead>tr:last").attr("id");
                var str="";
                //alert(data);
                var total=0;
                $(data).each(function(index,value){
                    total+=value.quantity* value.item.listprice;
                    str+="<tr id=''"+(++d)+"bgcolor=\"#FFFFFF\">" +
                        "<td>" +
                        "<b><a href=\"item.html?iid="+value.itemid+"&pid="+value.item.product.productid+"\">" +value.item.itemno+
                        "</a>" +
                        "</b>" +
                        "</td>" +
                        "<td>" +
                        "<b>" +value.item.product.productno+
                        "</b>" +
                        "</td>" +
                        "<td>" +
                        "<b>" +value.item.product.descn+
                        "</b>" +
                        "</td>" +

                        "<td>" +
                        "<b>" +value.item.attr1+
                        "</b>" +
                        "</td>" +
                        "<td>" +
                        "<img src='../images/"+value.item.product.pic+"'/>" +

                        "</td>" +

                        "<td>" +value.item.listprice+
                        "</td>" +

                        "<td>" +
                        "<b> <input type='number' maxlength='2' onchange='update("+this+","+value.itemid+","+value.orderid+")'  id='qty'+"+value.itemid+" value='"+value.quantity+"'/>"+

                        "</b>" +
                        "</td>" +

                        "<td>" +value.quantity* value.item.listprice+

                        "</td>" +

                        "<td>" +
                        "<b>" +"<input onclick='del("+value.orderid+","+value.itemid+")' type='image' src=\"../images/button_remove.gif\"/>"+
                        "</b>" +
                        "</td>" +
                        "</tr>"

                });

                $("#msg>tbody").append(str);
                $("#total").html("<b>TOTAL:"+total+"</b>");

            },
            404:function(data){
                $("#loginname").html("");
                window.location="../shop/login.html";
            }
        }
    });


}
function update(self,itemid,orderid){
    //alert(self.value)


    // var URL="http://localhost:8080/acc/ver";
    // $.ajax({
    //     url:URL,
    //     type:"get",
    //     dataType:"text",
    //     statusCode:{
    //         200:function(data){
    //
    //             post1(data.toString().split(":")[0],orderid,itemid);
    //         },
    //         404:function(data){
    //             $("#loginname").html("");
    //             window.location="../shop/login.html";
    //         }
    //     }
    // });
}
function del(orderid,itemid){
    var URL="http://localhost:8080/acc/ver";
    $.ajax({
        url:URL,
        type:"get",
        dataType:"text",
        statusCode:{
            200:function(data){

                post(data.toString().split(":")[0],orderid,itemid);
            },
            404:function(data){
                $("#loginname").html("");
                window.location="../shop/login.html";
            }
        }
    });


}

function post1(quantity,userid,orderid,itemid) {//真正修改
    var URL="http://localhost:8080/cart/up";

    var cart={
        "userid":userid,
        "itemid": itemid,
        "orderid":orderid,
        "quantity":quantity

    }
    var d=JSON.stringify(cart);
    $.ajax({
        contentType:"application/json",
        url:URL,
        data:d,
        type:"post",
        statusCode:{
            200:function(data){
                window.location="../shop/cart.html";
            },
            404:function(data){


            }
        }
    });

}


function post(userid,orderid,itemid){//真正删除
    var URL="http://localhost:8080/cart/del";

    var cart={
        "userid":userid,
        "itemid": itemid,
        "orderid":orderid
    }
    var d=JSON.stringify(cart);
    $.ajax({
        contentType:"application/json",
        url:URL,
        data:d,
        type:"post",
        statusCode:{
            200:function(data){
                window.location="../shop/cart.html";
            },
            404:function(data){


            }
        }
    });
}