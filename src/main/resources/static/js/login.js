/**
 * 登录页面的用户名，密码，验证码的验证
 */
var code;// 定义全局 定义验证码
function createCode() {
    code = "";
    var codeLength = 6;// 验证码的长度
    var checkCode = document.getElementById("checkCode");
    var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
        'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');// 所有候选组成验证码的字符.
    for (var i = 0; i < codeLength; i++) {
        var charIndex = Math.floor(Math.random() * 36);
        code += selectChar[charIndex];
    }
    document.getElementById("checkCode").innerHTML = code;

}

function checkCode() {
    var input_code = document.getElementById("input_code").value;
    code = code.toLowerCase();
    if (input_code.length == 0) {
        $("#msg").text("请输入验证码");
        return false;

    }
    if (input_code != code) {
        $("#msg").text("验证码有误");
        return false;
    }
    if (input_code == code) {
        $("#msg").text("");
        return true;
    }

}

function checkName() {
    var name = document.getElementById("name").value;
    if (name.length == 0) {
        $("#msg").text("请输入账户名");
        return false;
    } else {
        $("#msg").text("");
        return true;
    }

}

function checkPassword() {
    var pwd = document.getElementById("pwd").value;
    if (pwd.length == 0) {
        $("#msg").text("请输入密码");
        return false;
    } else {
        $("#msg").text("");
        return true;
    }

}
//从地址栏获取登录成功后跳转地址
function getRedirectUrl(){
    var url = location.href;
    var sub = url.indexOf('?');
    if(sub==-1){
        return "/index";
    }
    var p = url.substring(sub+1);
    if(p.length===0){
        return "/index";
    }
    var sub1 = p.indexOf('=');
    var rst = p.substring(sub1+1);
    if(rst.length==0){
        return "/index";
    }
    return rst;
}


$(function () {
    $(window).load(function () {
        var login_error = 0;
        $("#checkCode").click(function () {
            createCode();
        });
        $("#name").blur(function () {
            checkName();
        });
        $("#pwd").blur(function () {
            checkPassword();
        });
        $("#input_code").blur(function () {
            checkCode();
        });
        $("#login").click(function () {
            if (checkName() == false) {
                return false;
            }
            if (checkPassword() == false) {
                return false;
            }
            if (login_error > 5) {
                if (checkCode() == false) {
                    return false;
                }
            }
            $.ajax({
                type: "POST",
                url: "/user/login",
                data: {
                    name: document.getElementById("name").value,
                    password: document.getElementById("pwd").value
                },
                dataType: "text",
                async: false,
                success: function (data, textStatus) {
                    data = JSON.parse(data);
                    if (data.flag == 1) {
                        var url = getRedirectUrl();
                        location.href = url;
                    } else {
                        login_error = login_error+1;
                        $("#msg").text("用户名或者密码错误");
                        if (login_error > 5) {
                            $("#checkCodeDiv").show();
                            createCode();
                        }
                    }
                }
            });
            return false;
        });
    });
});