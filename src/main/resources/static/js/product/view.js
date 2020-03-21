/**
 * Created by aiwei on 2020-3-15.
 */

$(function () {
    var card = new ViewCard();
    //展开收起根分类
    card.expandRoot();
    //二级分类加载
    card.load(card);
});
