/**
 * Created by aiwei on 2020-3-18.
 */

function AddCard(){
    Card.call(this);
    //添加商品分类标志
    var g_add_flag=false;
    //加载下一个card
    AddCard.prototype.loadNextCard = function(cardNo,productTypeId){
        Card.prototype.removeCardAfter(cardNo);
        var list = Card.prototype.loadCardData(productTypeId);
        if(list.length==0){
            return;
        }
        var card = AddCard.prototype.buildProductTypeList(list,cardNo+1);
        $("#main").append(card);
        card.on("click",".load",function(){
            var cardNo = $(this).parents('.card').attr('num');
            $("#main .card[num="+cardNo+"] .items .bgcolorgray").css('background-color','#fff');
            $(this).parent().parent().css('background-color','#eee');
            var productTypeId = $(this).children("input[name=id]").val();
            AddCard.prototype.loadNextCard(cardNo,productTypeId);
        });
        card.on("click",".saveorupdate>i",function(){
            AddCard.prototype.preInitSaveorupdate($(this));
        });
    };
    //功能按钮点击
    AddCard.prototype.saveorupdateClick = function(){
        $("#main .card .items .saveorupdate>i").each(function (i) {
            $(this).click(function () {
                AddCard.prototype.preInitSaveorupdate($(this));
            });
        });
    };
    //添加或者编辑商品类别信息的预处理
    AddCard.prototype.preInitSaveorupdate = function(obj){
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
    };
    //添加商品分类
    AddCard.prototype.addProductType = function(){
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
    };
    //关闭添加商品分类模态框
    AddCard.prototype.modalProductTypeOnHidden = function(){
        $("#modal_product_type").on("hidden.bs.modal", function(e){
            $("#productTypeId").val('');
            $("#name").val('');
            $("#parentId").val('');
            $("#msg").text('');
            if(g_add_flag==true){
                location.href="/product_type/view";
            }
        });
    };
    //生成商品分类列表
    AddCard.prototype.buildProductTypeList = function(list,cardNo){
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
            var saveorupdate = $('<div class="saveorupdate"></div>');
            subitemcontainer.append(saveorupdate);

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
                        var i_right = $('<i class="glyphicon glyphicon-chevron-right"></i>');
                        saveorupdate.append(i_right);
                    }
                }
            });
            var i_pencil = $('<i class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#modal_product_type"></i>');
            var i_plus = $('<i class="glyphicon glyphicon-plus" data-toggle="modal" data-target="#modal_product_type"></i>');
            saveorupdate.append(i_pencil);
            saveorupdate.append(i_plus);
        }
        return card;
    };
}(function(){
    // 创建一个没有实例方法的类
    var Super = function(){};
    Super.prototype = Card.prototype;
    //将实例作为子类的原型
    AddCard.prototype = new Super();
    // 需要修复下构造函数
    AddCard.prototype.constructor = AddCard;
})();


