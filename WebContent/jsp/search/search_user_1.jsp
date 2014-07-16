<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="ajListBoPhan" namespace="/search" id="ajListBoPhanURL"/>
<s:url action="ajListNhanvienByBophan" namespace="/search" id="ajListNhanvienByBophanURL"/>
<style>
tr {
height:30px;
}
</style>
<table width="99%" id="table_search_user_1">
	<tr>
		<td style="width:150px;font-weight:bold;text-align:right">Chọn phòng ban</td>
		<td>
		<select id="msphongban" class="field size1">	
			<option value="" selected=true>--Chọn phòng ban--</option>
			<s:iterator value="phongbans">
				<option value='<s:property value="msphongban" />'><s:property value="tenphongban" /></option>									
			</s:iterator>
		</select>
		</td>
	</tr>
	<tr>
		<td style="width:150px;font-weight:bold;text-align:right">Chọn bộ phận</td>
		<td>
		<select id="msbophan" class="field size1">	
		</select>
		</td>
	</tr>
	<tr>
		<td style="width:150px;font-weight:bold;text-align:right">Chọn nhân viên</td>
		<td>
		<select id="msnhanvien" class="field size1">	
		</select>
		</td>
	</tr>
	<tr>
		<td align="right" colspan="2">
			<button id="btChon">Chọn</button>
		</td>
	</tr>
</table>
<script>
var dialog = $("#table_search_user_1").parents(".ui-dialog-content");
$(document).ready(function(){	
	dialog.dialog("option", "position", "center");
	$('button#btChon',dialog).button({
		disabled: true
	});
	$("#msphongban",dialog).change(function(){
		if(this.value == '') return;
		$.get("${ajListBoPhanURL}",{msphongban : this.value},function(data){
			var msbophan = $("#msbophan",dialog);
			msbophan.empty();
			msbophan.append('<option value="" selected=true>--Chọn bộ phận--</option>');
			$.each(data.data,function(){
				msbophan.append('<option value="'+this.msbophan+'">'+this.tenbophan+'</option>');
			});
		});
	});
	
	$("#msbophan",dialog).change(function(){
		if(this.value == '') return;
		$.get("${ajListNhanvienByBophanURL}",{msbophan : this.value},function(data){
			var msnhanvien = $("#msnhanvien",dialog);
			msnhanvien.empty();
			msnhanvien.append('<option value="" selected=true>--Chọn nhân viên--</option>');
			$.each(data.data,function(){
				var str = '<option value="'+this.msnhanvien+'">'+this.honhanvien +' ' + this.tennhanvien+'</option>';
				msnhanvien.append(str);
			});
		});
	});
	$("#msnhanvien",dialog).change(function(){
		if(this.value == '') {
			$('button#btChon',dialog).button({
				disabled: true
			});
		} else {
			$('button#btChon',dialog).button({
				disabled: null
			});
		}
	});
	$("#btChon",dialog).click(function(){
		$("#form #msnhanvien").val($("#msnhanvien option:selected",dialog).val());
		$("#form #hotennhanvien").val($("#msnhanvien option:selected",dialog).text());
		dialog.dialog("close");
		//jsdebug($("#dialog #msnhanvien option:selected").text());
		return false;
		//alert($("#dialog #msnhanvien option[selected]").
		
	});
});
</script>