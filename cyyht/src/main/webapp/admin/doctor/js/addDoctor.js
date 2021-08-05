var pageSize=20,pageNumber=1;//每页数量
var prePageNum=20;
var questionList;
var pageNavObj;
var totalPage;

$(function(){
    getHospital();
    getDeprtment();
    getPositional();
    getSpecialize();


    $("#docSubmit").on("click",function(){
        addDoctor();
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

function  addDoctor() {
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
        url: "/cyyht/doctor/save.action",
        contentType: 'application/json',
        data: JSON.stringify(doctor),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                alert("添加成功");
                window.location.href="doctorManager.html";
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
