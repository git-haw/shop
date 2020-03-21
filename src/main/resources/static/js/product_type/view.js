/**
 * Created by aiwei on 2020-3-15.
 */

$(function () {
    var card = new AddCard();
    //展开收起根分类
    card.expandRoot();
    ////功能按钮点击
    card.saveorupdateClick();
    //二级分类加载
    card.load(card);
    //增加根商品分类
    $("#add_root_product_type").click(function(){
        $("#parentId").val(-1);
    });
    //添加商品分类
    card.addProductType();
    //关闭添加商品分类模态框
    card.modalProductTypeOnHidden();
});
