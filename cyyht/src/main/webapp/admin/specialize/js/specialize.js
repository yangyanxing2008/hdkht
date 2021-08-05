/**
 * Created by Administrator on 2021/7/31.
 */
var pageSize=20,pageNumber=1;//每页数量
var prePageNum=20;
var pageNavObj;
var totalPage;
var specializes;
$(function(){
    getData();

    $(".btn").on("click",function(){
        getData();
    });
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
function updateshow(id) {
    for(var i=0;i<specializes.length;i++){
        if(id==specializes[i].id){
            $("#specializeName").val(specializes[i].specializeName);
            break;
        }
    }
    $(".updateE").attr("did",id);
    $(".updateE").show();
}

function add() {
    $(".addE").show();
}

function updateSpecialize() {
    var d = {};
    d.specializeName = $("#specializeName").val();
    d.state = 0;
    d.id = $(".updateE").attr("did");
    $.ajax({
        type: "post",
        url: "/cyyht/specialize/update.action",
        contentType: 'application/json',
        data: JSON.stringify(d),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                alert("修改标签成功");
            }else{
                alert(data.message);
            }
        }
    });
}



function  addSpecialize() {
    var specialize = {};
    $("#positionalContent").val();
    specialize.specializeName= $("#specializeContent").val();
    $.ajax({
        type: "post",
        url: "/cyyht/specialize/save.action",
        contentType: 'application/json',
        data: JSON.stringify(specialize),
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                 alert("添加标签成功");
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
/*function checkd(id) {
    window.location.href="departmentManager2.html?id="+id;
}*/
//禁用
function jyPositional(id) {
    if(window.confirm('你确定要禁用该标签吗？')){
        var d = {};
        d.id = id;
        for(var i=0;i<specializes.length;i++){
            if(id==specializes[i].id){
                d.specializeName =specializes[i].specializeName ;
                d.state = 1;
                break;
            }
        }

        $.ajax({
            type: "post",
            url: "/cyyht/specialize/update.action",
            contentType: 'application/json',
            data: JSON.stringify(d),
            dataType: "json",
            success: function (data) {
                if (data.code == 1) {
                    getData();
                    alert("标签已禁用");
                }else{
                    alert(data.message);
                }
            }
        });
        return true;
    }else{
        //alert("取消");
        return false;
    }

}

function deletep(id){
    if(window.confirm('你确定要删除吗？')){
        var d = {};
        d.id = id;
        $.ajax({
            type: "post",
            url: "/cyyht/specialize/delete.action",
            contentType: 'application/json',
            data: JSON.stringify(d),
            dataType: "json",
            success: function (data) {
                if (data.code == 1) {
                    getData();
                    alert("删除成功");
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

//获取分页数据
function getData(){
    $("#PageNavId").empty();
    $(".totalNum").empty();
    $("#listShow").empty();

    var searchData = {};
    searchData.pageSize = pageSize;
    searchData.pageNumber = pageNumber;
    searchData.specializeName="";
    searchData.state="0";
    $.ajax({
        type:"post",
        url:"/cyyht/specialize/getPageList.action",
        contentType: 'application/json',
        data:JSON.stringify(searchData),
        dataType: "json",
        success:function(data){
            if(data.code ==1){
                var dataUl=$("#listShow");

                if(data.data.length >0){
                    var lists=data.data;
                    specializes = lists;
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
                        var td2=$("<td height='44' align='center' class='newList'></td>");
                        td2.append((pageNumber == 1) ? i+1: ((i == 0)?pageSize*pageNumber-(pageSize-1): pageSize*pageNumber-(pageSize-1)+i));
                        var td3=$("<td height='44' align='center'></td>");
                        td3.append(lists[i].specializeName);
                        var td8=$("<td height='44' align='center'><button onclick='updateshow("+lists[i].id+")' class='custom-btn btn-2' style='width: 100px;'>编辑</button>" +
                            "<button class='custom-btn btn-2' onclick='deletep("+lists[i].id+")' style='width:100px;margin-left: 2px;background: linear-gradient( 0deg, rgba(96,9,240,1) 0%, rgb(240 5 16) 100%);'>删除</button>" +
                            "<button class='custom-btn btn-2'  onclick='jyPositional("+lists[i].id+")' style='width: 100px;'>禁用</button></td>");
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
