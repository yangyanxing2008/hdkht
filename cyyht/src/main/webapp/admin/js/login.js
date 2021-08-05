$(function(){

	$("#btnLogin").on("click",function(){
        logins();
    });
});

function logins(){
	var name=$.trim($("#userName").val());
	var pwd =$.trim( $("#pwd").val());
	 var validateCode = $.trim($("#validateCode").val());
	if(name =="" || name ==null){
		alert("请输入用户名");
		return;
	}
	if(pwd =="" || pwd ==null){
		alert("请输入密码");
		return;
	}
    var user = {};
	user.accountPwd = pwd;
	user.accountNumber = name;
	$.ajax({
		type:"post",
		url:"/cyyht/user/login.action",
        contentType: 'application/json',
		dataType:"json",
		data:JSON.stringify(user),
		success:function(data){
			console.log(data);
			if(data.code="1"){
                var tempUrl = "admin/gk.html";
                window.location.href="admin/index.html";
			}else{
				alert("用户名或密码错误");
			}



		}
	});
}

//修改验证码
function changeCode(){
    document.getElementById('codeImg').src='/eprobot/codeMsg.jsp?' + Math.random();
    return false;
}
