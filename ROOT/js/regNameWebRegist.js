var unameResult;
function checkUname() {
	var spuname = "spuname";
	var uname = g("regUsername").value.trim();
	if (uname == "") {
		paintError(spuname, "个人账号不能为空！");
		unameResult = false;
		return unameResult;
	}
	var url = "/cmpt/userinfo/hasUsername.jspx?username=" + uname + "&t="
			+ new Date().getTime();
	var json = ajax(url);
	if (json.result == 1 || json.result == -1) {
		paintError(spuname, "对不起，该账号已经存在！");
		unameResult = false;
		return unameResult;
	}
	paintRight(spuname);
	unameResult = true;
	return unameResult;
}

var pwdResult;
function checkPwd() {
	var sppwd = "sppwd";
	var pwd = g("regPassword1").value.trim();
	if (pwd == "") {
		paintError(sppwd, "密码不能为空！");
		pwdResult = false;
		return pwdResult;
	}
	paintRight(sppwd);
	pwdResult = true;
	return pwdResult;
}

var regPwdResult;
function checkRegPwd() {
	var spregpwd = "spregpwd";
	var pwd = g("regPassword1").value.trim();
	var regpwd = g("regPassWord").value.trim();
	if (pwd != regpwd || regpwd == "") {
		paintError(spregpwd, "两次输入的密码不一致！");
		regPwdResult = false;
		return regPwdResult;
	}
	paintRight(spregpwd);
	regPwdResult = true;
	return regPwdResult;
}

var phoneResult;
function checkPhone() {
	var spphone = "spphone";
	var phone = g("phone").value.trim();
	if (phone == "") {
		paintError(spphone, "手机号不能为空！");
		phoneResult = false;
		return phoneResult;
	}
	if (!/\d{11}/.test(phone)) {
		paintError(spphone, "您输入的手机号不正确！");
		phoneResult = false;
		return phoneResult;
	}
	var url = "/cmpt/userinfo/hasPhone.jspx?phone=" + phone + "&t="
			+ new Date().getTime();
	var json = ajax(url);
	if (json.result != 0) {
		paintError(spphone, "对不起，手机号已经存在！");
		phoneResult = false;
		return phoneResult;
	}
	paintRight(spphone);
	phoneResult = true;
	return phoneResult;
}

var emailResult;
function checkEmail() {
	var spemail = "spemail";
	var email = g("email").value.trim();
	if (email == "") {
		paintError(spemail, "请输入您的邮箱！");
		emailResult = false;
		return emailResult;
	}
	if (!(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)
			.test(email)) {
		paintError(spemail, "您输入的邮箱格式不正确！");
		emailResult = false;
		return emailResult;
	}
	paintRight(spemail);
	emailResult = true;
	return emailResult;
}

var hourResult;
function checkHour() {
	var sphour = "sphour";
	var rcvtype = parseInt(g("rcvType").value);
	var sthour = parseInt(g("sthour").value), endhour = parseInt(g("endhour").value);
	if (rcvtype == 1 && sthour > endhour) {
		paintError(sphour, "接收的开始时间应小于结束时间！");
		hourResult = false;
		return hourResult;
	}
	paintRight(sphour);
	hourResult = true;
	return hourResult;
}

function checkRegForm() {
	unameResult = unameResult || checkUname();
	pwdResult = pwdResult || checkPwd();
	regPwdResult = regPwdResult || checkRegPwd();
	phoneResult = phoneResult || checkPhone();
	emailResult = emailResult || checkEmail();

	return (unameResult && pwdResult && regPwdResult && phoneResult
			&& emailResult && checkHour());
}

function ajax(url) {
	var json = {};
	var request = new XMLRequst(url, "GET", false).createXMLRequst();
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				json = eval('(' + request.responseText.trim() + ')');
			}
		}
	};
	request.send(null);
	return json;
}
function paintError(id, msg) {
	g(id).innerHTML = '<img src="/images/ferror.gif" />&nbsp;' + (msg || '');
}
function paintRight(id, msg) {
	g(id).innerHTML = '<img src="/images/fright.jpg" />&nbsp;' + (msg || '');
}