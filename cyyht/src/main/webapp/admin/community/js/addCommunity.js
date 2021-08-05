var pageSize=20,pageNumber=1;//每页数量
var prePageNum=20;
var questionList;
var pageNavObj;
var totalPage;

$(function(){

    $("#docSubmit").on("click",function(){
        addCommunity();
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

function  addCommunity() {
    var doctorHeadImag =  $('#headImg').val();
    var communityName = $('#communityName').val();
    var telephone = $('#telephone').val();
    var contactName = $('#contactName').val();
    var address = $('#address').val();
    var brief = $('#brief').val();
    var accountNumber = $('#accountNumber').val();
    var accountPwd = $('#accountPwd').val();
    var community={};
    community.logo = doctorHeadImag;
    community.communityName = communityName;
    community.telephone = telephone;
    community.contactName = contactName;
    community.address = address;
    community.brief = brief;
    community.accountNumber = accountNumber;
    community.accountPwd = accountPwd;

    $.ajax({
        type: "post",
        url: "/cyyht/community/save.action",
        contentType: 'application/json',
        data: JSON.stringify(community),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                alert("添加成功");
                window.location.href="communityManager.html";
            }else{
                alert(data.message);
            }
        }
    });
}
