/**
 * Created by aiwei on 2020-3-15.
 */

$(function () {
    //添加商品分类标志
    var g_add_flag=false;

    $("#main .card .items>ul .root-product-type>.expand").click(function () {
        $(this).parent().next().toggleClass("unvisable");
        var i = $(this).children("i").first();
        if(i.hasClass("glyphicon-chevron-down")){
            i.removeClass("glyphicon-chevron-down");
            i.addClass("glyphicon-chevron-up");
        }else{
            i.removeClass("glyphicon-chevron-up");
            i.addClass("glyphicon-chevron-down");
        }
    });
    $("#main .card .items>ul .root-product-type>.add>i").each(function(i){
        $(this).click(function(){
            var id = $(this).parent().prev().children("input[name=id]").val();
            $("#parentId").val(id);
        });
    });
    $("#add_root_product_type").click(function(){
        $("#parentId").val(-1);
    });

    $("#add_product_type").click(function(){
        $.ajax({
            type: "POST",
            url: "/product_type/save",
            data: {
                name: $("#name").val(),
                parentId: $("#parentId").val()
            },
            dataType: "text",
            async: false,
            success: function (data, textStatus) {
                data = JSON.parse(data);
                $("#tip").text(data.msg);
                if(data.code==1){
                    $("#name").val("");
                    $("#name").focus();
                    g_add_flag = true;
                }
            }
        });
    });

    //关闭添加商品分类模态框
    $("#modal_product_type").on("hidden.bs.modal", function(e){
        $("#tip").text('');
        if(g_add_flag==true){
            location.href="/product_type/view";
        }
    });
});
