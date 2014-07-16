<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="doLogout" namespace="/login" id="doLogoutURL"/>
<s:url action="index" namespace="/index" id="indexURL"/>
<s:url action="index" namespace="/settings" id="settingsIndexURL"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="description" content="Reflect Template" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <title>VMS Supporter</title>
        <link rel="stylesheet" href="../css/style_all.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="../css/style1.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="../css/cupertino/jquery-ui.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="../css/jquery.wysiwyg.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="../css/superfish.css" media="screen">
		<link rel="stylesheet" type="text/css" href="../css/demo_table_jui.css" />
        <!--Internet Explorer Trancparency fix-->
        <!--[if IE 6]>
        <script src="/js/ie6pngfix.js"></script>
        <script>
          DD_belatedPNG.fix('#head, a, a span, img, .message p, .click_to_close, .ie6fix');
        </script>
        <![endif]--> 
        
        <script type='text/javascript' src='../js/jquery.js'></script>
        <script type='text/javascript' src='../js/jquery-ui.js'></script>
		<script type="text/javascript" src="../js/jquery_blockUI.js"></script>
        <script type='text/javascript' src='../js/jquery.wysiwyg.js'></script>
		<script type="text/javascript" src="../js/hoverIntent.js"></script>
		<script type="text/javascript" src="../js/superfish.js"></script>
		<script type="text/javascript" src="../js/validator.js"></script>
		<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="../js/jquery.hint.js"></script>
		<script type="text/javascript" src="../js/utils.js"></script>
		<script type="text/javascript" src="../js/templates.js"></script>
		<script type="text/javascript" src="../js/date.js"></script>

		<script>
		var contextPath = '<%= request.getContextPath() %>';
		var baseUrl = contextPath;
		function byId(id) { //Viet tat cua ham document.getElementById
			return document.getElementById(id);
		}
		function jsdebug(data) { //Ho tro debug voi ajax
			alert(data);
		}
		function block(id) {
			overlay = $(id).block({ 
				message: '<span style="color:white">Loading...</span>', 
				css: { 
					border: 'none', 
					padding: '15px', 
					backgroundColor: '#000', 
					'-webkit-border-radius': '10px', 
					'-moz-border-radius': '10px', 
					opacity: .5, 
					color: '#fff'
				} 
			}); 
			$('.blockOverlay', overlay).css('cursor', 'auto');
		}
		function unblock(id) {
			$(id).unblock(); 
		}
		function loadContent(action) {
			url = contextPath+action;
			block('#bg_wrapper');
			$.get(url, function(data) {
				if(data == "END_SESSION") {
					location.href = "${indexURL}";
				} else {
					$('#bg_wrapper').html(data);
				}
				unblock('#bg_wrapper');
			}); 
		}
		</script>
    </head>
    
    <body>
        <div id="top">
			<div id="dialog" title="Welcome to VMS"><center>Loading...</center></div>
			<div id="dialog1" title="Welcome to VMS"></div>
            <div id="head">
            	<h1 class="logo">
                	<a href="#">flexy - adjustable admin skin</a>
                </h1>
                
                <div class="head_memberinfo">
                    <span class='memberinfo_span'>
                   		 Welcome <strong><s:property value="#session.SESS_USERLOGIN.username"/></strong>
                    </span>
                    
                    <span class='memberinfo_span'>
                    	<a href="#" onclick="loadContent('/settings/index.action')">Your account</a>
                    </span>
                    <span>
                    	<a href="${doLogoutURL}">Logout</a>
                    </span>
                </div><!--end head_memberinfo-->
            </div><!--end head-->
			<% 
				String sMenu = (String)session.getAttribute(com.vms.utils.Constances.SESS_MENU);
				if(sMenu == null){
					sMenu = "";
				} 
			%>
			<%=sMenu%>
			<div id="bg_wrapper">
				
			</div>
        </div>
        <div id="footer">
			
			</div>
    </body>
</html>
<script>
function showDialog() {
	$("#dialog1").html(replaceText(templates.loading,{baseUrl:baseUrl}));
	$("#dialog1").dialog({ 
		modal: true,
        resizable: false,
		open : function(){
		},
		close : function() {
		}
	});
}
var LOGIN_PATH = "${indexURL}";
function showDialogUrl(url,title_,width_,dialog) {
	if(dialog == null) dialog = $("#dialog");
	if(width_ == null)
		width_= 300;
	if(title_ == null)
		title_ = 'Welcome to Top7';
	dialog.html('');
	dialog.html(replaceText(templates.loading,{baseUrl:baseUrl}));
	dialog.load(url);
	dialog.dialog({ 
		modal: true,
        resizable: false,
		title : title_,
		width: width_,
		open : function(){
			//$("body").css("overflow", "hidden");
			
		},
		close : function() {
			//$("body").css("overflow", "auto");
		}
	});
}
jQuery.fn.dataTableExt.oApi.fnSetFilteringPressEnter = function (oSettings) {
    var _that = this;
    this.each(function (i) {
        $.fn.dataTableExt.iApiIndex = i;
        var $this = this;
        var anControl = $('input', _that.fnSettings().aanFeatures.f);
        anControl.unbind('keyup').bind('keypress', function (e) {
            if (e.which == 13) {
                $.fn.dataTableExt.iApiIndex = i;
                _that.fnFilter(anControl.val());
            }
        });
        return this;
    });
    return this;
}

$(document).ready(function(){	 
	//kriesi_tab('#content','.jquery_tab_title','.jquery_tab'); /*remove this if you dont want to have jquery tabs*/
	//kriesi_navigation(".nav"); /*remove this if you dont want a jquery sidebar menu*/
	//kriesi_closeable_divs(".closeable"); /*remove this if you dont want message box to be closeable*/
	//jQuery(".flexy_datepicker, .flexy_datepicker_input").datepicker(); //datepicker input field and box
	//jQuery("#dialog").dialog(); //pop up dialog window on pageopen.
	//jQuery('.richtext').wysiwyg(); //rich text editor for textareas
	$('ul.sf-menu').superfish();
	});
</script>