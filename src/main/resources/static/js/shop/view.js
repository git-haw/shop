/**
 * Created by aiwei on 2020-3-15.
 */

function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) {
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}


$(function () {
    $(window).load(function () {
        $("#file").change(function () {
            var file = this.files[0];
            var objUrl = getObjectURL(file);//获取文件信息
            alert("objUrl = " + objUrl);
            if (objUrl) {
                $("#logo_img").attr("src", objUrl);
                var userid = $.cookie('userid');
                $("#logo").val(userid + "_" + file.name);

                var formData = new FormData();
                formData.append("file",file);
                $.ajax({
                    type: "POST",
                    url: "/shop/uploadLogo",
                    data: formData,
                    dataType: "json",
                    async: false,
                    processData: false, // 使数据不做处理
                    contentType: false, // 不要设置Content-Type请求头
                    success: function (data, textStatus) {
                        data = JSON.parse(data);
                    }
                });
            }
        });
    });
});
