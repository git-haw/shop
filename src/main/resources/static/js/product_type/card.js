/**
 * Created by aiwei on 2020-3-18.
 */

//加载下一个card
function loadNextCard(obj){
    var card_num = obj.parents('.card').attr('num');
    card_num = Number(card_num);
    removeCardAfter(card_num);
    var id = obj.children("input[name=id]").val();
    var data = loadCardData(id);
    var card = buildProductTypeList(data,card_num+1);
    $("#main").append(card);
    card.on("click",".load",function(){
        loadNextCard($(this));
    });
    card.on("click",".add>i",function(){
        var id = $(this).parent().prev().children("input[name=id]").val();
        $("#parentId").val(id);
    });
}

//生成商品分类列表
function buildProductTypeList(list,card_num){
    var card = $('<div class="card" num="'+card_num+'"></div>');
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

//移除当前card之后的card
function removeCardAfter(card_num){
    $("#main .card").each(function(i){
        var num = $(this).attr('num');
        num = Number(num);
        if(num>card_num){
            $(this).remove();
        }
    });
}

//加载card数据
function loadCardData(parentId){
    var list;
    $.ajax({
        type: "POST",
        url: "/product_type/load",
        data: {
            parentId: parentId
        },
        dataType: "text",
        async: false,
        success: function (data, textStatus) {
            list = JSON.parse(data);
        }
    });
    return list;
}

