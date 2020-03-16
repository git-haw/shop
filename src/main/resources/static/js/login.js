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
	for ( var i = 0; i < codeLength; i++) {
		var charIndex = Math.floor(Math.random() * 36);
		code += selectChar[charIndex];
	}
	document.getElementById("checkCode").innerHTML = code;
}

function checkCode() {
	var input_code = document.getElementById("input_code").value;
	code = code.toLowerCase();
	if (input_code.length == 0) {
		document.getElementById("Code").innerHTML = "验证码不能为空";
		return false;

	}
	if (input_code != code) {
		document.getElementById("Code").innerHTML = "验证码不正确";
		return false;
	}
	if (input_code == code) {
		document.getElementById("Code").innerHTML = "";
		return true;
	}

}

function checkName() {
	var name = document.getElementById("name").value;
	if (name.length == 0) {
		document.getElementById("checkName").innerHTML = "邮箱不能为空";
		return false;
	} else {
		document.getElementById("checkName").innerHTML = "";
		return true;
	}

}

function checkPassword() {
	var pwd = document.getElementById("pwd").value;
	if (pwd.length == 0) {
		document.getElementById("checkpw").innerHTML = "密码不能为空";
		return false;
	}
	if (pwd.length < 6 || pwd.length > 20) {
		document.getElementById("checkpw").innerHTML = "密码长度在6~20位";
		return false;
	}
	if (pwd.length >= 6 && pwd.length <= 20) {
		document.getElementById("checkpw").innerHTML = "";
		return true;
	}

}


$(function () {
	$(window).load(function () {

		var checkResult = false;
		var checkPasswordResult = false;
		var checkCodeResult = false;
		var checkNameResult = false;

		createCode();
		$("#checkCode").click(function () {
			createCode();
		});

		$("#name").blur(function () {
			checkNameResult = checkName();
		});
		$("#pwd").blur(function () {
			checkPasswordResult = checkPassword();
		});
		$("#input_code").blur(function () {
			checkCodeResult = checkCode();
		});
		$("#login").click(function () {
			checkResult = true;//checkNameResult && checkPasswordResult && checkCodeResult;
//                    	alert(checkNameResult);
//                    	alert(checkPasswordResult);
//                    	alert(checkCodeResult);
//                    	alert(checkResult);
			if (checkResult) {
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
							$.cookie('userid', data.userid, {expires: 1, path: '/'});
							$.cookie('token', data.token, {expires: 1, path: '/'});
							location.href = "/index";
						} else {
							$("#checkName").text("用户名或者密码错误！");
						}
					}
				});
			}
		});
	});
});