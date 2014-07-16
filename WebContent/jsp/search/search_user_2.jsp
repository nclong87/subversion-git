<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="ajListBoPhan" namespace="/search" id="ajListBoPhanURL"/>
<s:url action="ajListNhanvienByBophan" namespace="/search" id="ajListNhanvienByBophanURL"/>
<style>
tr {
height:30px;
}
select.multiple {
float: left;
height: 300px;
width: 265px;
}
#buttons button {
display: block;
margin: 5px;
}
</style>
<table width="99%" id="table_search_user_2">
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
		<td align="center" colspan="2">
			<select class="multiple" multiple id="select1">  
			
			</select>
			<div id="buttons" style="display: block; float: left; text-align: center; width: 40px;">
				<button id="btadd">Select</button>
				<button id="btremove">Un Select</button>
				<button id="btaddall">Select All</button>
				<button id="btremoveall">Un Select All</button>
			</div>
			<select class="multiple" multiple name="users" id="select2">  
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
$(document).ready(function(){	
	search_user_2.init({
		ajListBoPhanURL : "${ajListBoPhanURL}",
		ajListNhanvienByBophanURL : "${ajListNhanvienByBophanURL}"
	});
});
</script>