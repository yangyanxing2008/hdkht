//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
var docid = getUrlParam("id");

$(function(){
    getHospital();
    getDeprtment();
    getPositional();
    getSpecialize();
    getDoctor();
    $("#docSubmit").on("click",function(){
        updateDoctor();
    });
    var button = $('#uploadImg'), interval;
    new AjaxUpload(button, {
        action: '/cyyht/file/upLoadFile.action',
        name: 'file',
        onSubmit: function (file, ext) {
            button.val('上传中');
            interval = window.setInterval(function () {
                var text = button.text();
                if (text.length < 10) {
                    button.text(text + '.');
                } else {
                    button.text('上传中');
                }
            }, 200);
        },
        onComplete: function (file, response) {
            window.clearInterval(interval);
            $('#docHeadImg').empty();
            button.text('上传图片');
            $('#headImg').val(response);
            var img  = "<img src='/cyyht/"+response+"' width='149px' height='83px'>"
            $('#docHeadImg').append(img);
        }
    });

});

function getDoctor(){
    var doctor = {};
    doctor.id = docid;
    $.ajax({
        type: "post",
        url: "/cyyht/doctor/getDoctor.action",
        contentType: 'application/json',
        data: JSON.stringify(doctor),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                console.log(data)
                $('#headImg').val(data.data.headImg);
                $('#docHeadImg').empty();
                var img  = "<img src='/cyyht/"+data.data.headImg+"' width='149px' height='83px'>"
                $('#docHeadImg').append(img);
                 $('#doctorName').val(data.data.doctorName);
                $('#telephone').val(data.data.telephone);
                $('#age').val(data.data.age);
                $("#hospitals").val(data.data.hospitalId);
                $("#departments").val(data.data.departmentId);
                $("#positionals").val(data.data.positionalId);
                $("#sex").val(data.data.sex);
                $('#intervalTime').val(data.data.intervalTime);
                $('#registrationFee').val(data.data.registrationFee);
                $('#brief').val(data.data.brief);
                $('#workStartTime').val(data.data.workStartTime);
                $('#workEndTime').val(data.data.workEndTime);
            }else{
                alert(data.message);
            }
        }
    });
}


function  updateDoctor() {
    var doctorHeadImag =  $('#headImg').val();
    var doctorName = $('#doctorName').val();
    var telephone = $('#telephone').val();

    var age = $('#age').val();
    var sexOption = $("#sex option:selected");
    var hospitalsOption = $("#hospitals option:selected");
    var departmentsOption = $("#departments option:selected");
    var positionalsOption = $("#positionals option:selected");
    var sex = sexOption.val();
    var hospitals = hospitalsOption.val();
    var departments = departmentsOption.val();
    var positionals = positionalsOption.val();
    var intervalTime = $('#intervalTime').val();
    var registrationFee = $('#registrationFee').val();
    var brief = $('#brief').val();
    var accountNumber = $('#accountNumber').val();
    var accountPwd = $('#accountPwd').val();
    var workStartTime = $('#workStartTime').val();
    var workEndTime = $('#workEndTime').val();
    var obj = document.getElementsByName("habit");
    var specialize ="" ;
    for (k in obj) {
        if (obj[k].checked)
            specialize +=obj[k].value+",";
    }
    if(specialize.length>0);
       specialize = specialize.substr(0,specialize.length-1);

    var doctor={};
    doctor.id = docid;
    doctor.headImg = doctorHeadImag;
    doctor.doctorName = doctorName;
    doctor.telephone = telephone;
    doctor.sex = sex;
    doctor.age = age;
    doctor.hospitalId = hospitals;
    doctor.departmentId = departments;
    doctor.positionalId = positionals;
    doctor.intervalTime = intervalTime;
    doctor.registrationFee = registrationFee;
    doctor.brief = brief;
    doctor.specialize = specialize;
    doctor.workStartTime = workStartTime;
    doctor.workEndTime = workEndTime;
    doctor.accountNumber = accountNumber;
    doctor.accountPwd = accountPwd;
    console.log(doctor)
    $.ajax({
        type: "post",
        url: "/cyyht/doctor/update.action",
        contentType: 'application/json',
        data: JSON.stringify(doctor),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                alert("修改成功");
            }else{
                alert(data.message);
            }
        }
    });
}

   //查询所有医院
   function getHospital(){
       $.ajax({
           type:"post",
           url:"/cyyht/hospital/getList.action",
           contentType: 'application/json',
           dataType: "json",
           success:function(data){
               if(data.code=="1"){
                   var list = data.data;
                   for(var i=0;i<list.length;i++){
                       var option = $("<option></option>");
                       option.attr("value",list[i].id);
                       option.append(list[i].hospitalName);
                       $("#hospitals").append(option);
                   }
               }else{
                   alert(data.message);
               }

           }
       });
   }

//查询所有科室
function getDeprtment(){
    $.ajax({
        type:"post",
        url:"/cyyht/department/getList.action",
        contentType: 'application/json',
        dataType: "json",
        success:function(data){
            if(data.code=="1"){
                var list = data.data;
                for(var i=0;i<list.length;i++){
                    var option = $("<option></option>");
                    option.attr("value",list[i].id);
                    option.append(list[i].departmentName);
                    $("#departments").append(option);
                }

            }else{
                alert(data.message);
            }

        }
    });
}

//查询所有职称
function getPositional(){
    $.ajax({
        type:"post",
        url:"/cyyht/positional/getList.action",
        contentType: 'application/json',
        dataType: "json",
        success:function(data){
            if(data.code=="1"){
                var list = data.data;
                for(var i=0;i<list.length;i++){
                    var option = $("<option></option>");
                    option.attr("value",list[i].id);
                    option.append(list[i].positionalName);
                    $("#positionals").append(option);
                }

            }else{
                alert(data.message);
            }

        }
    });
}

//查询所有标签
function getSpecialize(){
    $.ajax({
        type:"post",
        url:"/cyyht/specialize/getList.action",
        contentType: 'application/json',
        dataType: "json",
        success:function(data){
            if(data.code=="1"){
                var list = data.data;
                for(var i=0;i<list.length;i++){
                    var input = $("<input />");
                    input.attr("value",list[i].specializeName);
                    input.attr("type","checkbox");
                    input.attr("name","habit");
                    $("#specializes").append(input).append(list[i].specializeName);
                }

            }else{
                alert(data.msg);
            }

        }
    });
}
