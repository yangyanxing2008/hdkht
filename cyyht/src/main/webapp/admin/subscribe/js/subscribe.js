var pageSize=20,pageNumber=1;//每页数量
var prePageNum=20;
var pageNavObj;
var totalPage;

$(function(){
    getData();
    $("#docSearch").on("click",function(){
        searchPage=1
    	getData();
    });
    $("#uploadImg").on("click",function(){
        alert("上传文件");
    });
	$(".icon-shanchu1").on("click",function(){
		$(".modelDel").hide();
	});
	$(".undel").on("click",function(){
		$(".modelDel").hide();
	});
	$("#search").on("click",function(){
		searchPage=1
		getData();
	});
});
function getSubscribe(id) {

}



function pageNavCallBack(clickPage){
    pageNavObj = new PageNavCreate("PageNavId",{
         pageCount:totalPage, 
         currentPage:clickPage, 
         perPageNum:prePageNum, 
    });
    pageNavObj.afterClick(pageNavCallBack);
    searchPage = clickPage;
    getData();
}
function updateDoctor(id) {
    var sub = {};
    sub.id = id;
    $.ajax({
        type: "post",
        url: "/cyyht/subscribe/updateHx.action",
        contentType: 'application/json',
        data: JSON.stringify(sub),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                getData();
                alert("已就诊");

            }else{
                alert(data.message);
            }
        }
    });
}
//获取分页数据
function getData(){
	$("#PageNavId").empty();
	$(".totalNum").empty();
	$("#listShow").empty();

    var searchData = {};
    searchData.pageSize = pageSize;
    searchData.pageNumber = pageNumber;

    $.ajax({
        type:"post",
        url:"/cyyht/subscribe/getPageList.action",
        contentType: 'application/json',
        data:JSON.stringify(searchData),
        dataType: "json",
        success:function(data){
        	if(data.code ==1) {
                var dataUl = $("#listShow");

                if (data.data.length > 0) {
                    var lists = data.data;
                    //questionList = lists;
                    var total = data.total;
                    $(".totalNum").text("共" + total + "条");
                    var pagecount = Math.ceil(total / pageSize);
                    totalPage = pagecount;
                    if (pagecount == 1) {
                        $("#PageNavId").html("");
                    } else {
                        pageNavObj = new PageNavCreate("PageNavId", {
                            pageCount: pagecount,
                            currentPage: pageNumber,
                            perPageNum: prePageNum
                        });
                        pageNavObj.afterClick(pageNavCallBack);
                    }

                    for (var i = 0; i < lists.length; i++) {
                        var tr = $("<tr></tr>");
                        var td2 = $("<td height='44' align='center' ></td>");
                        td2.append((pageNumber == 1) ? i + 1 : ((i == 0) ? pageSize * pageNumber - (pageSize - 1) : pageSize * pageNumber - (pageSize - 1) + i));
                        var td3 = $("<td height='44' align='center'></td>");
                        td3.append(lists[i].communityName);
                        var td4 = $("<td height='44' align='center'></td>");
                        td4.append(lists[i].departmentName);
                        var td5 = $("<td height='44' align='center' style='text-indent:14px;'></td>");
                        td5.append(lists[i].doctorName);
                        var td6 = $("<td height='44' align='center' style='text-indent:14px;'></td>");
                        td6.append(lists[i].appointmentTime);
                        var td7 = $("<td height='44' align='center'></td>");
                        td7.append(lists[i].visitUserSex);
                        var td71 = $("<td height='44' align='center'></td>");
                        td71.append(lists[i].visitUserName);
                        var td8 = $("<td height='44' align='center'></td>");
                        td8.append(lists[i].visitUserTelephone);
                        var td9 = $("<td height='44' align='center'></td>");
                        td9.append(lists[i].iCard);

                       var td10 = $("<td height='44' align='center'></td>");
                       if(lists[i].verification==1){
                           td10.append("已就诊");
                       }else{
                           td10.append("待就诊");
                       }


                        if (lists[i].verification == 1) {
                            var td11 = $("<td height='44' align='center'><button onclick='getSubscribe(" + lists[i].id + ")' class='custom-btn btn-2' style='width:70px;'>查看</button></td>");
                            tr.append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td71).append(td8).append(td9).append(td10).append(td11);
                        } else {
                            var td11 = $("<td height='44' align='center'><button onclick='updateDoctor(" + lists[i].id + ")' class='custom-btn btn-2' style='width:70px;margin-left: 5px;'>查看</button><button onclick='updateDoctor(" + lists[i].id + ")' class='custom-btn btn-2' style='width:75px;'>已就诊</button></td>");
                            tr.append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td71).append(td8).append(td9).append(td10).append(td11);

                        }
                        $("#listShow").append(tr);
                    }
                } else {
                    var tr = "<tr><td colspan='9'  height='44'>data.message</td></tr>"
                    $("#listShow").append(tr);
                }
            }
        }
    });
}


Date.prototype.format = function(fmt) {
    var o = {
      "M+" : this.getMonth()+1,                 //月份
      "d+" : this.getDate(),                    //日
      "h+" : this.getHours(),                   //小时
      "m+" : this.getMinutes(),                 //分
      "s+" : this.getSeconds(),                 //秒
      "q+" : Math.floor((this.getMonth()+3)/3), //季度
      "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
      fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
      if(new RegExp("("+ k +")").test(fmt)){
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
      }
    }
    return fmt;
  }
