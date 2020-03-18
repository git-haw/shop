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
            $("#main .card[num="+cardNo+"] .items .bgcolorgray").css('background-color','#fff');
            $(this).parent().css('background-color','#eee');
            var productTypeId = $(this).children("input[name=id]").val();
            Card.prototype.loadNextCard(cardNo,productTypeId);
        });
        card.on("click",".saveorupdate>i",function(){
            Card.prototype.preInitSaveorupdate($(this));
        });
    }
    //添加或者编辑商品类别信息的预处理
    Card.prototype.preInitSaveorupdate = function(obj){
        var id = obj.parent().prev().children("input[name=id]").val();
        if(obj.hasClass('glyphicon-plus')){
            $("#parentId").val(id);
            $("#myModalLabel").text('添加商品分类');
        }else{
            $("#productTypeId").val(id);
            var name = obj.parent().prev().children("span").text();
            $("#name").val(name);
            $("#myModalLabel").text('编辑商品分类');
        }
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
            var itemcontainer = $('<div class="itemcontainer bgcolorgray"></div>');
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
            var saveorupdate = $('<div class="saveorupdate"></div>');
            itemcontainer.append(saveorupdate);
            var i_plus = $('<i class="glyphicon glyphicon-plus" data-toggle="modal" data-target="#modal_product_type"></i>');
            var i_pencil = $('<i class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#modal_product_type"></i>');
            saveorupdate.append(i_plus);
            saveorupdate.append(i_pencil);
        }
        return card;
    }
};

