/**
 * Created by Administrator on 2021/7/31.
 */
var pageSize=20,pageNumber=1;//每页数量
var prePageNum=20;
var questionList;
var pageNavObj;
var totalPage;
var departments;
//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
var did = getUrlParam("id");

$(function(){
    getData();
    $(".closeX").on("click",function(){
        getData();
        $(".addE").hide();
        $(".updateE").hide();
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
function returnDepent() {
    window.location.href="departmentManager.html";
}

function add() {
    $(".addE").show();
}

function deleteDepartment(id) {
    if(window.confirm('你确定要删除吗？')) {
        var department = {};
        department.id = id;
        $.ajax({
            type: "post",
            url: "/cyyht/department/delete.action",
            contentType: 'application/json',
            data: JSON.stringify(department),
            dataType: "json",
            success: function (data) {
                if (data.code == 1) {
                    alert("删除科室成功");
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
function updateshow(id) {
    for(var i=0;i<departments.length;i++){
        if(id==departments[i].id){
            $("#departmentName").val(departments[i].departmentName);
            break;
        }
    }
    $(".updateE").attr("did",id);
    $(".updateE").show();
}

function  addDepart() {
    var department = {};
    $("#departmentContent").val();
    department.departmentName= $("#departmentContent").val();
    department.parentId=did;
    $.ajax({
        type: "post",
        url: "/cyyht/department/save.action",
        contentType: 'application/json',
        data: JSON.stringify(department),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                 alert("添加科室成功");
            }
        }
    });

}

function updateDepart() {
    var d = {};
    d.departmentName = $("#departmentName").val();
    d.parentId = did;
    d.id = $(".updateE").attr("did");
    $.ajax({
        type: "post",
        url: "/cyyht/department/update.action",
        contentType: 'application/json',
        data: JSON.stringify(d),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                alert("修改科室成功");
            }else{
                alert(data.message);
            }
        }
    });
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
//获取分页数据
function getData(){
    $("#PageNavId").empty();
    $(".totalNum").empty();
    $("#listShow").empty();

    var searchData = {};
    searchData.pageSize = pageSize;
    searchData.pageNumber = pageNumber;
    searchData.departmentName="";
    searchData.parentId=did;
    $.ajax({
        type:"post",
        url:"/cyyht/department/getPageList.action",
        contentType: 'application/json',
        data:JSON.stringify(searchData),
        dataType: "json",
        success:function(data){
            if(data.code ==1){

                var dataUl=$("#listShow");

                if(data.data.length >0){
                    var lists=data.data;
                    departments = lists;
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
                        td3.append(lists[i].departmentName);
                        var td8=$("<td height='44' align='center'><button onclick='updateshow("+lists[i].id+")' class='custom-btn btn-2' style='width: 100px;'>编辑</button>" +
                            "<button class='custom-btn btn-2' onclick='deleteDepartment("+lists[i].id+")'  style='width:100px;margin-left: 2px;background: linear-gradient( 0deg, rgba(96,9,240,1) 0%, rgb(240 5 16) 100%);'>删除</button>" +
                            "</td>");
                        tr.append(td2).append(td3).append(td8);
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
