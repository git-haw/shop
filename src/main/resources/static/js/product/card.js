/**
 * Created by aiwei on 2020-3-18.
 */

function ViewCard() {
    Card.call(this);
    //二级分类加载
    ViewCard.prototype.load = function () {
        $("#main .card .items .load").click(function () {
            //点击加载
            ViewCard.prototype.clickLoad($(this));
        });
    };
    //加载下一个card
    ViewCard.prototype.loadNextCard = function (cardNo, productTypeId) {
        Card.prototype.removeCardAfter(cardNo);
        var list = Card.prototype.loadCardData(productTypeId);
        if (list.length == 0) {
            //显示输入card
            var inputCard = ViewCard.prototype.buildInputCard(cardNo + 1);
            $("#cards").append(inputCard);
            $(".next-btn").attr('disabled',false);
            return;
        }else{
            $(".next-btn").attr('disabled',true);
        }
        //生成商品分类列表
        var card = ViewCard.prototype.buildProductTypeList(list, cardNo + 1);
        $("#cards").append(card);
        card.on("click", ".load", function () {
            //点击加载
            ViewCard.prototype.clickLoad($(this));
        });
    };
    //点击加载
    ViewCard.prototype.clickLoad = function (obj) {
        var cardNo = obj.parents('.card').attr('num');
        cardNo = Number(cardNo);
        $("#main .card[num=" + cardNo + "] .items .category-item").removeClass("selected");
        obj.parent().parent().addClass("selected");
        var productTypeId = obj.children("input[name=id]").val();
        var name = obj.children("span").text();
        //类目信息维护
        ViewCard.prototype.categoryPath(name, cardNo);
        //加载下一个card
        ViewCard.prototype.loadNextCard(cardNo, productTypeId);
    };
    //类目信息维护
    ViewCard.prototype.categoryPath = function (name, cardNo) {
        $(".path-content>span").each(function (i) {
            if (i >= cardNo) {
                $(this).remove();
            }
        });
        if (cardNo == 0) {
            var namespan = $('<span>' + name + '</span>');
            $(".path-content").append(namespan);
        }else{
            var namespan = $('<span> > ' + name + '</span>');
            $(".path-content").append(namespan);
        }
    };
    //生成商品分类列表
    ViewCard.prototype.buildProductTypeList = function (list, cardNo) {
        var card = $('<div class="card" num="' + cardNo + '"></div>');
        var items = $('<div class="items"></div>');
        card.append(items);
        var ul = $('<ul></ul>');
        items.append(ul);
        for (var i = 0; i < list.length; i++) {
            var item = list[i];
            var li = $('<li class="category-item"></li>');
            ul.append(li);
            var subitemcontainer = $('<div class="subitemcontainer"></div>');
            li.append(subitemcontainer);
            var load = $('<div class="load"></div>');
            subitemcontainer.append(load);
            var id = $('<input type="hidden" name="id" value="' + item.id + '"/>');
            var parentId = $('<input type="hidden" name="parentId" value="' + item.parentId + '"/>');
            var span = $('<span>' + item.name + '</span>');
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
                    if (count != 0) {
                        var i_right = $('<i class="glyphicon glyphicon-chevron-right fr"></i>');
                        load.append(i_right);
                    }
                }
            });
        }
        return card;
    };
    //生成inputcard
    ViewCard.prototype.buildInputCard = function (cardNo) {
        var card = $('<div class="card" num="' + cardNo + '"></div>');
        var inputcard = $('<div class="input-card"></div>');
        card.append(inputcard);
        var p = $('<p>货号：</p>');
        var input = $('<input type="text" class="form-control"/>');
        inputcard.append(p);
        inputcard.append(input);
        return card;
    };
}
(function () {
    // 创建一个没有实例方法的类
    var Super = function () {
    };
    Super.prototype = Card.prototype;
    //将实例作为子类的原型
    ViewCard.prototype = new Super();
    // 需要修复下构造函数
    ViewCard.prototype.constructor = ViewCard;
})();


