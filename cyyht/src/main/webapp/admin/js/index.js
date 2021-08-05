var KnowAllList =[];
//欄目總條數
var allChnl;
var knowlegeid;
var flType;//知识点分类 类型
var rcId;
	$(function(){

		$(".ul1 li").on("click",function(){
			$(".ul1 li").removeClass("on");
			$(this).addClass("on");
			if($(".ul1 li:eq(0)").hasClass("on")){
				$("iframe").attr("src","gk.html");
			}
            if($(".ul1 li:eq(1)").hasClass("on")){
            	 $("iframe").attr("src","community/communityManager.html");
            }
           /* if($(".ul1 li:eq(2)").hasClass("on")){
                $("iframe").attr("src","hospital/hospitalManager.html");
            }*/
            if($(".ul1 li:eq(2)").hasClass("on")){
                $("iframe").attr("src","department/departmentManager.html");
            }
            if($(".ul1 li:eq(3)").hasClass("on")){
                $("iframe").attr("src","doctor/doctorManager.html");
            }
            if($(".ul1 li:eq(4)").hasClass("on")){
                $("iframe").attr("src","positional/positionalManager.html");
            }
            if($(".ul1 li:eq(5)").hasClass("on")){
                $("iframe").attr("src","specialize/specializeManager.html");
            }
            if($(".ul1 li:eq(6)").hasClass("on")){
                $("iframe").attr("src","userManager.html");
            }
		});

	});





