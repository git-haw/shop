/**
 * Created by aiwei on 2020-3-18.
 */

function ViewCard(){
    Card.call(this);
    //加载下一个card
    ViewCard.prototype.loadNextCard = function(cardNo,productTypeId){
        Card.prototype.removeCardAfter(cardNo);
        var list = Card.prototype.loadCardData(productTypeId);
        if(list.length==0){
            return;
        }
        var card = ViewCard.prototype.buildProductTypeList(list,cardNo+1);
        $("#main").append(card);
        card.on("click",".load",function(){
            var cardNo = $(this).parents('.card').attr('num');
            $("#main .card[num="+cardNo+"] .items .category-item").removeClass("selected");
            $(this).parent().parent().addClass("selected");
            var productTypeId = $(this).children("input[name=id]").val();
            ViewCard.prototype.loadNextCard(cardNo,productTypeId);
        });
    };
    //生成商品分类列表
    ViewCard.prototype.buildProductTypeList = function(list,cardNo){
        var card = $('<div class="card" num="'+cardNo+'"></div>');
        var items = $('<div class="items"></div>');
        card.append(items);
        var ul = $('<ul></ul>');
        items.append(ul);
        for(var i= 0;i<list.length;i++){
            var item = list[i];
            var li = $('<li class="category-item"></li>');
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
    };
}(function(){
    // 创建一个没有实例方法的类
    var Super = function(){};
    Super.prototype = Card.prototype;
    //将实例作为子类的原型
    ViewCard.prototype = new Super();
    // 需要修复下构造函数
    ViewCard.prototype.constructor = ViewCard;
})();


