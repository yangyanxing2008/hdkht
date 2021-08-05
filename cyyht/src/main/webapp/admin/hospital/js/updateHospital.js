//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
var docid = getUrlParam("id");

$(function(){
    getHospital();
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

function getHospital(){

    var hospital = {};
    hospital.id = docid;
    $.ajax({
        type: "post",
        url: "/cyyht/hospital/getHospital.action",
        contentType: 'application/json',
        data: JSON.stringify(hospital),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                $('#headImg').val(data.data.logo);
                $('#docHeadImg').empty();
                var img  = "<img src='/cyyht/"+data.data.logo+"' width='149px' height='83px'>"
                $('#docHeadImg').append(img);
                 $('#hospitalName').val(data.data.hospitalName);
                $('#telephone').val(data.data.telephone);
                $('#userName').val(data.data.userName);
                $("#address").val(data.data.address);
                $("#brief").val(data.data.brief);
            }else{
                alert(data.message);
            }
        }
    });
}


function  updateDoctor() {
    var logo =  $('#headImg').val();
    var hospitalName = $('#hospitalName').val();
    var telephone = $('#telephone').val();
    var userName = $('#userName').val();
    var address = $('#address').val();
    var brief = $('#brief').val();

    var hospital={};
    hospital.id = docid;
    hospital.logo = logo;
    hospital.hospitalName = hospitalName;
    hospital.telephone = telephone;
    hospital.userName = userName;
    hospital.address = address;
    hospital.brief = brief;

    $.ajax({
        type: "post",
        url: "/cyyht/hospital/update.action",
        contentType: 'application/json',
        data: JSON.stringify(hospital),
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
