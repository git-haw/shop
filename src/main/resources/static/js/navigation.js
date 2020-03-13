/**
 * Created by haw on 18-3-7.
 */
function showProductsAsideCategory(cid) {
    $("#category li[cid="+cid+"]").css("background-color","white");
    $("#category li[cid="+cid+"] a").css("color","#87CEFA");
    $("div.productsAsideCategory[cid="+cid+"]").show();
}
function hideProductsAsideCategory(cid) {
    $("#category li[cid="+cid+"]").css("background-color","#e2e2e3");
    $("#category li[cid="+cid+"] a").css("color","#000");
    $("div.productsAsideCategory[cid="+cid+"]").hide();
}
$(function () {
    $("#category li").mouseenter(function () {
        var cid = $(this).attr("cid");
        showProductsAsideCategory(cid);
    });
    $("#category li").mouseleave(function () {
        var cid = $(this).attr("cid");
        hideProductsAsideCategory(cid);
    });
    $("div.productsAsideCategory").mouseenter(function () {
        var cid = $(this).attr("cid");
        showProductsAsideCategory(cid);
    });
    $("div.productsAsideCategory").mouseleave(function () {
        var cid = $(this).attr("cid");
        hideProductsAsideCategory(cid);
    });
    
    $("div.rightMenu span").mouseenter(function () {
        var left = $(this).position().left;
        var top = $(this).position().top;
        var width_span = $(this).outerWidth(true);
        var width_img = $("img#catear").outerWidth(true);
        var destLeft = parseInt(left) + (parseInt(width_span)-parseInt(width_img))/2;
        $("img#catear").css("left",destLeft);
        $("img#catear").css("top",parseInt(top)-20);
        $("img#catear").fadeIn(500);
    })
    $("div.rightMenu span").mouseleave(function () {
        $("img#catear").hide();
    });
});