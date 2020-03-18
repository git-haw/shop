/**
 * Created by aiwei on 2020-3-18.
 */

function Card(){
    //加载下一个card
    Card.prototype.loadNextCard = function(cardNo,productTypeId){
        Card.prototype.removeCardAfter(cardNo);
        var data = Card.prototype.loadCardData(productTypeId);
        var card = Card.prototype.buildProductTypeList(data,cardNo+1);
        $("#main").append(card);
        card.on("click",".load",function(){
            var cardNo = $(this).parents('.card').attr('num');
            var productTypeId = $(this).children("input[name=id]").val();
            Card.prototype.loadNextCard(cardNo,productTypeId);
        });
        card.on("click",".add>i",function(){
            var id = $(this).parent().prev().children("input[name=id]").val();
            $("#parentId").val(id);
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
            var li = $('<li></li>');
            ul.append(li);
            var itemcontainer = $('<div class="itemcontainer"></div>');
            li.append(itemcontainer);
            var load = $('<div class="load"></div>');
            itemcontainer.append(load);
            var id = $('<input type="hidden" name="id" value="'+item.id+'"/>');
            var parentId = $('<input type="hidden" name="parentId" value="'+item.parentId+'"/>');
            var span = $('<span>'+item.name+'</span>');
            var i_down = $('<i class="glyphicon glyphicon-chevron-down"></i>');
            load.append(id);
            load.append(parentId);
            load.append(span);
            load.append(i_down);
            var add = $('<div class="add"></div>');
            itemcontainer.append(add);
            var i_plus = $('<i class="glyphicon glyphicon glyphicon-plus" data-toggle="modal" data-target="#modal_product_type"></i>');
            add.append(i_plus);
        }
        return card;
    }
};


