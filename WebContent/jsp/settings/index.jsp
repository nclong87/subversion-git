<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="ajChangePassword" namespace="/settings" id="ajChangePasswordURL"/>
<div id="box_form" style="margin: 0pt auto; width: 500px; height: auto;" class="box">
	<form method="post" action="" id="form">
	<input type="text" name="account.id" id="id" value="" style="display:none">
	<fieldset>
		<legend>CẬP NHẬT THÔNG TIN TÀI KHOẢN</legend>
		<table width="100%">
			<tr>
				<td></td>
				<td align="left" colspan="3">
				<span id="msg"></span>
				</td>
			</tr>
			<tr>
				<td style="width:150px;font-weight:bold;text-align:right">Mật khẩu mới <span class="require">*</span>:</td>
				<td>
				<input type="password" name="password" id="password" class="field size1">
				</td>
			</tr>
			<tr>
				<td style="width:150px;font-weight:bold;text-align:right">Nhập lại mật khẩu <span class="require">*</span>:</td>
				<td>
				<input type="password" name="confirm_password" id="confirm_password" class="field size1">
				</td>
			</tr>
			<tr style="height:50px">
				<td align="center" colspan="2">
				<input type="button" value="Lưu" onclick="doSaveSettings()">
				</td>
			</tr>	
		</table>
	</fieldset>
	</form>
</div>
<script>
function message(msg,type) {
	if(type==1) { //Thong diep thong bao
		byId("msg").innerHTML = '<div class="ui-state-highlight ui-corner-all" style=" padding: 0 .7em;"><p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span><strong>Success : </strong> '+msg+'</p></div>';
	} else if(type == 0) { //Thong diep bao loi
		byId("msg").innerHTML = '<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;"><p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span><strong>Error : </strong> '+msg+'</p></div>';
	}
}
function doSaveSettings(){
	checkValidate=true;
	validate(['require'],'password',["Vui lòng nhập mật khẩu mới!"]);
	validate(['pwdagain'],'confirm_password',["Mật khẩu nhập lại chưa chính xác!"]);
	if(checkValidate == false)
		return;
	block('#bg_wrapper');
	dataString = $("#form").serialize();
	$.ajax({
		type: "POST",
		cache: false,
		url : "${ajChangePasswordURL}",
		data: dataString,
		success: function(data){
			if(data == "END_SESSION") {
				location.href = LOGIN_PATH;
				return;
			}
			unblock('#bg_wrapper');
			if(data == "OK") {
				message("Lưu thành công!",1);
				return;
			}
			message('Lỗi! Lưu không thành công!',0);										
		},
		error: function(data){ alert (data);unblock('#bg_wrapper');}	
	});	
}
$(document).ready(function(){	
	$('input[type=button]').button({
		disabled: null,
	});
});
</script>