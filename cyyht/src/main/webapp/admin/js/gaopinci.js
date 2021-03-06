var queryName = "";//保存搜索条件
var totalPage;
var pageNavObj;
var pageSizes=15;//每页数量
var prePageNum=10;
var startDate="";
var endDate="";
$(function(){
	getGpcData(1,startDate,endDate);
	$("#search").click(function() {
		startDate = $("#startDate").val();
		endDate = $("#endDate").val();
		getGpcData(1,startDate, endDate);
	});

	$("#export").click(function() {
		var options = {
			fileName : '高频词列表'
		};
		$.extend(true, options, {
			type : 'excel'
		});
		$("#tab").tableExport(options);
	});
});

function pageNavCallBack(clickPage){
    pageNavObj = new PageNavCreate("PageNavId",{
         pageCount:totalPage, 
         currentPage:clickPage, 
         perPageNum:prePageNum, 
    });
    pageNavObj.afterClick(pageNavCallBack);
    searchPage = clickPage;
    getGpcData(searchPage,startDate,endDate);
}
function getGpcData(pageNumber,startDate,endDate){
	 var queryString = "pageSize="+pageSizes+"&pageNumber="+pageNumber+"&searchName="+queryName+"&startDate="+startDate+"&endDate="+endDate+"";
	 $.ajax({
 	    type:"post",
 	    url : "/eprobot/hfw/getAllHighFre",
 	    dataType: "json",
 	    data:queryString,
 	    cache:false,
 	    async:false,
 	    success:function(data){
 	       $("#listShow").empty();
	 	   var list=data.list;
	       var total = data.total;
	       var pagecount = Math.ceil(total/pageSizes);
	       if(pagecount==1){
	            $("#PageNavId").html("");
	       }else{
	    	   pageNavObj = new PageNavCreate("PageNavId",{pageCount:pagecount,currentPage:pageNumber,perPageNum:prePageNum});
               pageNavObj.afterClick(pageNavCallBack);
	       }
 	       if(list!=null && list.length>0){
 	    	  for(var i=0; i<list.length; i++){
 	    		 var tr = $("<tr></tr>");
 	    		var td1 = $("<td height='50' align='center'></td>");
 	    		td1.append((pageNumber == 1) ? i+1: ((i == 0)?pageSizes*pageNumber-(pageSizes-1): pageSizes*pageNumber-(pageSizes-1)+i));
 	    		var td2 = $("<td height='50' align='center'></td>");
 	    		td2.append(list[i].words);
 	    		var td3 = $("<td height='50' align='center'></td>");
 	    		td3.append(list[i].num);
 	    		tr.append(td1).append(td2).append(td3);
 	    		$("#listShow").append(tr);
 	 	      }
 	       }
 	    }
 	});
}