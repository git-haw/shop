/**
 * Created by aiwei on 2020-3-15.
 */

$(function () {
    var card = new ViewCard();
    //展开收起根分类
    card.expandRoot();
    //二级分类加载
    card.load();
    $(".next-btn").click(function () {
        var ids='';
        for(i=0;i<card.productTypeIds.length;i++){
            var id = card.productTypeIds[i];
            ids=ids+id+',';
        }
        ids=ids.substr(0,ids.length-1);
        location.href="/sell/publish?productTypeIds="+ids;

    });
});
