/**
 * Created by aiwei on 2020-3-18.
 */

function Card(){
    //加载下一个card
    Card.prototype.loadNextCard = function(cardNo,productTypeId){
        Card.prototype.removeCardAfter(cardNo);
        var list = Card.prototype.loadCardData(productTypeId);
        if(list.length==0){
            return;
        }
        var card = Card.prototype.buildProductTypeList(list,cardNo+1);
        $("#main").append(card);
        card.on("click",".load",function(){
            var cardNo = $(this).parents('.card').attr('num');
            $("#main .card[num="+cardNo+"] .items .bgcolorgray").css('background-color','#fff');
            $(this).parent().parent().css('background-color','#eee');
            var productTypeId = $(this).children("input[name=id]").val();
            Card.prototype.loadNextCard(cardNo,productTypeId);
        });
    }
    //移除当前card之后的card
    Card.prototype.removeCardAfter = function(cardNo){
        $("#main .card").each(function(i){
            var num = $(this).attr('num');
            num = Number(num);
            if(num>cardNo){
                $(this).remove();
            }
        });
    }
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
    }
    //生成商品分类列表
    Card.prototype.buildProductTypeList = function(list,cardNo){
        var card = $('<div class="card" num="'+cardNo+'"></div>');
        var items = $('<div class="items"></div>');
        card.append(items);
        var ul = $('<ul></ul>');
        items.append(ul);
        for(var i= 0;i<list.length;i++){
            var item = list[i];
            var li = $('<li class="bgcolorgray"></li>');
            ul.append(li);
            var subitemcontainer = $('<div class="subitemcontainer"></div>');
            li.append(subitemcontainer);
            var load = $('<div class="load"></div>');
            subitemcontainer.append(load);
            var id = $('<input type="hidden" name="id" value="'+item.id+'"/>');
            var parentId = $('<input type="hidden" name="parentId" value="'+item.parentId+'"/>');
            var span = $('<span>'+item.name+'</span>');
            load.append(id);
            load.append(parentId);
            load.append(span);
            $.ajax({
                type: "POST",
                url: "/product_type/countChildren",
                data: {
                    parentId: item.id
                },
                dataType: "text",
                async: false,
                success: function (data, textStatus) {
                    var count = JSON.parse(data);
                    if(count!=0){
                        var i_right = $('<i class="glyphicon glyphicon-chevron-right fr"></i>');
                        load.append(i_right);
                    }
                }
            });
        }
        return card;
    }
};


