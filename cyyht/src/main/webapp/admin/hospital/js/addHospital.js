
$(function(){
    $("#docSubmit").on("click",function(){
        addHospital();
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

function  addHospital() {
    var doctorHeadImag =  $('#headImg').val();
    var hospitalName = $('#hospitalName').val();
    var telephone = $('#telephone').val();
    var userName = $('#userName').val();
    var address = $('#address').val();
    var brief = $('#brief').val();
    var accountNumber = $('#accountNumber').val();
    var accountPwd = $('#accountPwd').val();


    var hospital={};
    hospital.headImg = doctorHeadImag;
    hospital.hospitalName = hospitalName;
    hospital.telephone = telephone;
    hospital.userName = userName;
    hospital.address = address;
    hospital.brief = brief;
    hospital.accountNumber = accountNumber;
    hospital.accountPwd = accountPwd;
    $.ajax({
        type: "post",
        url: "/cyyht/hospital/save.action",
        contentType: 'application/json',
        data: JSON.stringify(hospital),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                alert("添加成功");
                window.location.href="hospitalManager.html";
            }else{
                alert(data.message);
            }
        }
    });
}

