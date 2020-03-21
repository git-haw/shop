/**
 * Created by aiwei on 2020-3-18.
 */

function Card(){
    //展开收起根分类
    Card.prototype.expandRoot = function(){
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
    };
    //二级分类加载
    Card.prototype.load = function(card){
        $("#main .card .items .load").each(function (i) {
            $(this).click(function () {
                var cardNo = $(this).parents('.card').attr('num');
                $("#main .card[num=" + cardNo + "] .items .bgcolorgray").each(function () {
                    $(this).css('background-color', '#fff');
                });
                $(this).parent().parent().css('background-color', '#eee');
                var productTypeId = $(this).children("input[name=id]").val();
                card.loadNextCard(Number(cardNo), productTypeId);
            });
        });
    };
    //移除当前card之后的card
    Card.prototype.removeCardAfter = function(cardNo){
        $("#main .card").each(function(i){
            var num = $(this).attr('num');
            num = Number(num);
            if(num>cardNo){
                $(this).remove();
            }
        });
    };
    //加载card数据
    Card.prototype.loadCardData = function(productTypeId){
        var list;
        $.ajax({
            type: "POST",
            url: "/product_type/load",
            data: {
                parentId: productTypeId
            },
            dataType: "text",
            async: false,
            success: function (data, textStatus) {
                list = JSON.parse(data);
            }
        });
        return list;
    };
};


