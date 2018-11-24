$(function(){


    //具体是哪个页
    var url= window.location.href;
    var str = url.substring(url.lastIndexOf('/') + 1);
    var p=str.split("?")[0];
    if(p.indexOf("product")!=-1){//你操作的是产品页

        var str1 = str.substring(str.lastIndexOf('?') + 1);
        var category=str1.split("=")[1];
        queryProByCate(category);
    }
    if(p.indexOf("items")!=-1) {//你操作的是项目S页

        var str1 = str.substring(str.lastIndexOf('?') + 1);
        var pro=str1.split("=")[1];
        queryItemsByPro(pro);

    }

    if(p.indexOf("item")!=-1) {//你操作的是项目页

        var str1 = str.substring(str.lastIndexOf('?') + 1);
        var item=str1.split("&")[0];


        var is=item.split("=")[1];

        var pro=str1.split("&")[1];

        var pid=pro.split("=")[1];

        queryItemByPro(is,pid);

    }
});

function queryItemByPro(item,pid){

    var URL="http://localhost:8080/pet/qi/"+item+"/"+pid;
    $.ajax({
        url:URL,
        type:"get",
        statusCode:{
            200:function(data){
                showItem(data);
            },
            404:function(){

            }
        }

    });

}
function queryItemsByPro(pro){
    var URL="http://localhost:8080/pet/qis/"+pro;
    $.ajax({
        url:URL,
        type:"get",
        statusCode:{
            200:function(data){
                showItems(data);
            },
            404:function(){

            }
        }

    });
}
function queryProByCate(cate){
    var URL="http://localhost:8080/pet/qp/"+cate;
    $.ajax({
        url:URL,
        type:"get",
        statusCode:{
            200:function(data){
                 showProduct(data);

            },
            404:function(){

            }
        }

    });
}
function showItem(data){
    $(data).each(function(index,value){

        $("#pic").attr("src","../images/"+value.product.pic);

        $("#productno").html(value.product.productno);
        $("#itemno").html(value.itemno);
        $("#itemid").val(value.itemid);
        //alert(value.product.productid+" "+value.itemid)
       // alert(value.product.productid);
        $("#productid").val(value.product.productid);
        $("#price").html(value.listprice);
        $("#descn").html(value.product.descn);

    });

}
function showItems(data){
    $("#msg>tbody").empty();
    var d=$("#msg>thead>tr:last").attr("id");
    var str="";
    $(data).each(function(index,value){
        str+="<tr id=''"+(++d)+"bgcolor=\"#FFFFFF\">" +
            "<td>" +
            "<b><a href=\"item.html?iid="+value.itemid+"&pid="+value.product.productid+"\">" +value.itemno+
            "</a>" +
            "</b>" +
            "</td>" +

            "<td>" +
            "<b>" +value.product.productno+
            "</b>" +
            "</td>" +

            "<td>" +
            "<b>" +value.product.name+
            "</b>" +
            "</td>" +

            "<td>" +
            "<b>" +value.listprice+
            "</b>" +
            "</td>" +

            "<td>" +
            "<img src='../images/"+value.product.pic+"'/>" +

            "</td>" +

            "<td>" +
            "<b>" +value.product.descn+"-"+value.attr1+
            "</b>" +
            "</td>" +

            "<td>" +
            "<b>" +"<a href=\"cart.html\"><img border=\"0\"\n" +
            "\t\t\t\t\t\tsrc=\"../images/button_add_to_cart.gif\" />\n" +
            "\t\t\t\t</a>"+
            "</b>" +
            "</td>" +

            "</tr>"



        // console.log(value.productid);
    });
    // alert(str)
    $("#msg>tbody").append(str);



}
function showProduct(data){
    $("#msg>tbody").empty();
    var d=$("#msg>thead>tr:last").attr("id");
    var str="";
    $(data).each(function(index,value){
        str+="<tr id=''"+(++d)+"bgcolor=\"#FFFFFF\">" +
            "<td>" +
            "<b><a href=\"items.html?pid="+value.productid+"\">" +value.productno+
            "</a>" +
            "</b>" +
            "</td>" +

            "<td>" +
            "<b>" +value.name+
            "</b>" +
            "</td>" +

            "<td>" +
            "<b>" +value.descn+
            "</b>" +
            "</td>" +

            "<td>" +
            "<img src='../images/"+value.pic+"'/>" +

            "</td>" +
            "</tr>"



       // console.log(value.productid);
    });
   // alert(str)
    $("#msg>tbody").append(str);

}