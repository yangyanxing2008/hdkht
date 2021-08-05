//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
var cid = getUrlParam("id");

$(function(){
    getCommunity();
    $("#communitySubmit").on("click",function(){
        updateCommunity();
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

function getCommunity(){
    var community = {};
    community.id = cid;
    $.ajax({
        type: "post",
        url: "/cyyht/community/findById.action",
        contentType: 'application/json',
        data: JSON.stringify(community),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                $('#headImg').val(data.data.logo);
                $('#docHeadImg').empty();
                var img  = "<img src='/cyyht/"+data.data.headImg+"' width='149px' height='83px'>"
                $('#docHeadImg').append(img);
                 $('#communityName').val(data.data.communityName);
                $('#telephone').val(data.data.telephone);
                $('#contactName').val(data.data.contactName);
                $("#address").val(data.data.address);
                $("#brief").val(data.data.brief);
                 $("#ccuid").val(data.data.cuid);
                $("#userId").val(data.data.userId);
            }else{
                alert(data.message);
            }
        }
    });
}


function  updateCommunity() {
    var community={};
    community.id = cid;
    community.logo =  $('#headImg').val();
    community.communityName = $('#communityName').val();
    community.telephone = $('#telephone').val();;
    community.contactName = $('#contactName').val();
    community.address = $('#address').val();
    community.brief = $('#brief').val();
    community.cuid = $('#ccuid').val();
    community.userId =  $('#userId').val();
    $.ajax({
        type: "post",
        url: "/cyyht/community/update.action",
        contentType: 'application/json',
        data: JSON.stringify(community),
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

