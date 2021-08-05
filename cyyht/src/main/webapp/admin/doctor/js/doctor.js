var pageSize=20,pageNumber=1;//每页数量
var prePageNum=20;
var questionList;
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


function deleteDoctor(id) {
    if(window.confirm('你确定要删除吗？')) {
        var doctor = {};
        doctor.id = id;
        $.ajax({
            type: "post",
            url: "/cyyht/doctor/delete.action",
            contentType: 'application/json',
            data: JSON.stringify(doctor),
            dataType: "json",
            success: function (data) {
                if (data.code == 1) {
                    alert("删除成功");
                    getData();
                }else{
                    alert(data.message);
                }
            }
        });
        return true;
    }else{
        return false;
    }

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
    window.location.href = "updateDoctor.html?id="+id;
}
//获取分页数据
function getData(){
	$("#PageNavId").empty();
	$(".totalNum").empty();
	$("#listShow").empty();

    var searchData = {};
    searchData.pageSize = pageSize;
    searchData.pageNumber = pageNumber;
    searchData.doctorName=$("#searchText").val();
    searchData.specialize="";
    searchData.hospitalName="";
    searchData.positionalName="";
    //searchData.sex=null;
    //searchData.age="";
    $.ajax({
        type:"post",
        url:"/cyyht/doctor/getPageList.action",
        contentType: 'application/json',
        data:JSON.stringify(searchData),
        dataType: "json",
        success:function(data){
        	if(data.code ==1){
            var dataUl=$("#listShow");

            if(data.data.length >0){
            	var lists=data.data;
				//questionList = lists;
                var total = data.total;
                $(".totalNum").text("共"+total+"条");
                var pagecount = Math.ceil(total/pageSize);
                totalPage = pagecount;
               if(pagecount==1){
                   $("#PageNavId").html("");
               }else{
            	   pageNavObj = new PageNavCreate("PageNavId",{pageCount:pagecount,currentPage: pageNumber,perPageNum:prePageNum});
                   pageNavObj.afterClick(pageNavCallBack);
               }

                for ( var i = 0; i < lists.length; i++) {
                	var tr = $("<tr></tr>");
                	/*var td1 = $("<td height='44' align='center' ></td>");
                	td1.append("<input type=\"checkbox\" name=\"checkOne\" value='"+lists[i].id+"'>");*/
                	var td2=$("<td height='44' align='center' class='newList'></td>");
					td2.append((pageNumber == 1) ? i+1: ((i == 0)?pageSize*pageNumber-(pageSize-1): pageSize*pageNumber-(pageSize-1)+i));
                	var td3=$("<td height='44' align='center'></td>");
                	td3.append(lists[i].doctorName);
                	var td4=$("<td height='44' align='center'></td>");
                    var img  = "<img src='/cyyht/"+lists[i].headImg+"' width='40px' height='35px'>"
                	td4.append(img);
                	//关键字
                	var td5=$("<td height='44' align='center' style='text-indent:14px;'></td>");
                	td5.append(lists[i].sex);
                	var td51=$("<td height='44' align='center' style='text-indent:14px;'></td>");
                	td51.append(lists[i].age);
                	var td6=$("<td height='44' align='center'></td>");
                	td6.append(lists[i].hospitalName);
                	var td7=$("<td height='44' align='center'></td>");
                    td7.append(lists[i].departmentName);
                    var td8=$("<td height='44' align='center'></td>");
                    td8.append(lists[i].positionalName);
                    var td9=$("<td height='44' align='center'></td>");
                    td9.append(lists[i].specialize);
                    var td10=$("<td height='44' align='center'></td>");
                    td10.append(lists[i].telephone);
                   /* var td11=$("<td height='44' align='center'></td>");
                    td11.append(lists[i].qr);*/
                    var td12=$("<td height='44' align='center'><button onclick='updateDoctor("+lists[i].id+")' class='custom-btn btn-2'>编辑</button>" +
						"<button  onclick='deleteDoctor("+lists[i].id+")' class='custom-btn btn-2' style='margin-left: 2px;background: linear-gradient( 0deg, rgba(96,9,240,1) 0%, rgb(240 5 16) 100%);'>删除</button></td>");
                                    	tr.append(td2).append(td3).append(td4).append(td5).append(td51).append(td6).append(td7).append(td8).append(td9).append(td10).append(td12);
                	$("#listShow").append(tr);
                	
				}
            }else{
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
