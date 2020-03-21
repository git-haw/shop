/**
 * Created by aiwei on 2020-3-15.
 */

$(function () {
    //添加商品分类标志
    var g_add_flag=false;
    var card = new Card();
    //展开收起根分类
    $("#main .card .items>ul .itemcontainer>.expand").click(function () {
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
    //二级分类加载
    $("#main .card .items .load").each(function(i){
        $(this).click(function(){
            var cardNo = $(this).parents('.card').attr('num');
            $("#main .card[num="+cardNo+"] .items .bgcolorgray").each(function(){
                $(this).css('background-color','#fff');
            });
            $(this).parent().parent().css('background-color','#eee');
            var productTypeId = $(this).children("input[name=id]").val();
            card.loadNextCard(Number(cardNo), productTypeId);
        });
    });
    //增加根商品分类
    $("#add_root_product_type").click(function(){
        $("#parentId").val(-1);
    });
    //添加商品分类
    $("#add_product_type").click(function(){
        $.ajax({
            type: "POST",
            url: "/product_type/save",
            data: {
                id: $("#productTypeId").val(),
                name: $("#name").val(),
                parentId: $("#parentId").val()
            },
            dataType: "text",
            async: false,
            success: function (data, textStatus) {
                data = JSON.parse(data);
                $("#msg").text(data.msg);
                if(data.code==1){
                    $("#name").val("");
                    $("#name").focus();
                    g_add_flag = true;
                    if($("#productTypeId").val()!=''){
                        $('#modal_product_type').modal('hide')
                    }
                }
            }
        });
    });
    //关闭添加商品分类模态框
    $("#modal_product_type").on("hidden.bs.modal", function(e){
        $("#productTypeId").val('');
        $("#name").val('');
        $("#parentId").val('');
        $("#msg").text('');
        if(g_add_flag==true){
            location.href="/product_type/view";
        }
    });

});
