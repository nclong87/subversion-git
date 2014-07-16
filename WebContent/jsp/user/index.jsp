<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="ajLoadAccounts" namespace="/user" id="ajLoadAccountsURL"/>
<s:url action="ajSaveAccount" namespace="/user" id="ajSaveAccountURL"/>
<s:url action="ajLockAccount" namespace="/user" id="ajLockAccountURL"/>
<s:url action="index" namespace="/search" id="searchIndexURL"/>
<s:url action="index" namespace="/permission" id="permissionIndexURL"/>
<div id="box_form" style="margin: 0pt auto; width: 900px; height: auto;" class="box">
	<form method="post" action="" id="form">
	<input type="text" name="account.id" id="id" value="" style="display:none">
	<fieldset>
		<legend>QUẢN LÝ USER</legend>
		<table width="100%">
			<tr>
				<td></td>
				<td align="left" colspan="3">
				<span id="msg"></span>
				</td>
			</tr>
			<tr>
				<td style="width:150px;font-weight:bold;text-align:right">Username <span class="require">*</span>:</td>
				<td>
				<input type="text" name="account.username" id="username" class="field size1">
				</td>
				<td style="width:150px;font-weight:bold;text-align:right">Password <span class="require">*</span>:</td>
				<td>
				<input type="password" name="account.password" id="password" class="field size1">
				</td>
			</tr>
			<tr>
				<td style="font-weight:bold;text-align:right">Nhân viên <span class="require">*</span>:</td>
				<td>
					<div style="position:relative">
					<input type="text" name="account.msnhanvien" id="msnhanvien" style="display:none">
					<input type="text" readonly="true" id="hotennhanvien" class="field size1" style="width: 168px;">
					<button id="btSearchNhanVien">Search</button>
					</div>
				</td>
				<td style="font-weight:bold;text-align:right">Tình trạng <span class="require">*</span>:</td>
				<td>
					<select class="field size1" name="account.active" id="active">
						<option value="">---SELECT---</option>
						<option value="1">Hoạt động</option>
						<option value="0">Khóa</option>
					</select>
				</td>
			</tr>
			<tr style="height:50px">
				<td align="center" colspan="4">
				<input type="button" value="Lưu" onclick="doSaveAccount()">
				<input type="button" value="Reset" onclick="doReset()">
				<input type="button" value="Tìm kiếm" onclick="doSearch()">
				</td>
			</tr>	
		</table>
	</fieldset>
	</form>
</div>
<div style="float: right; padding: 5px;">
<input type="button" value="Khóa" onclick="doLock()">
</div>
<table width="100%" id="dataTable" class="display">
<thead>
	<tr>
		<th width="5%">#</th>
		<th width="5%">Id</th>
		<th>Username</th>
		<th>msnhanvien</th>
		<th width="30%">Họ tên</th>
		<th width="5%">Active</th>
		<th width="5px">Phân quyền</th>
		<th width="5px">Edit</th>
		<th width="5px" align="center"><input type="checkbox" onclick="selectAll(this)"/></th>
	</tr>
</thead>
<tbody>
	<tr>
		<td colspan="7" class="dataTables_empty">Loading data from server</td>
	</tr>
</tbody>
</table>
<script>
function message(msg,type) {
	if(type==1) { //Thong diep thong bao
		byId("msg").innerHTML = '<div class="ui-state-highlight ui-corner-all" style=" padding: 0 .7em;"><p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span><strong>Success : </strong> '+msg+'</p></div>';
	} else if(type == 0) { //Thong diep bao loi
		byId("msg").innerHTML = '<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;"><p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span><strong>Error : </strong> '+msg+'</p></div>';
	}
}
function doReset() {
	$("#form")[0].reset(); //Reset form cua jquery, giu lai gia tri mac dinh cua cac field
	byId("msg").innerHTML="";
	$("#username").attr('readonly',false);
}
function selectAll(_this) {
	$('#dataTable input[type=checkbox]').each(function(){
		this.checked=_this.checked;
	});
}
function doSaveAccount() {
	checkValidate=true;
	validate(['require'],'username',["Username không được để trống!"]);
	validate(['requireselect'],'active',["Chọn tình trạng hoạt động!"]);
	if(checkValidate == false)
		return;
	if(byId("id").value!='') {
		if(!confirm('Bạn muốn cập nhật dữ liệu này?'))
			return;
	}
	block('#bg_wrapper');
	dataString = $("#form").serialize();
	$.ajax({
		type: "POST",
		cache: false,
		url : "${ajSaveAccountURL}",
		data: dataString,
		success: function(data){
			if(data == "END_SESSION") {
				location.href = LOGIN_PATH;
				return;
			}
			unblock('#bg_wrapper');
			if(data == "OK") {
				if(byId("id").value=='') {//Them moi
					doReset();
						
				} 
				oTable.fnDraw(false);
				message("Lưu thành công!",1);
			} else if(data == 'ACCOUNT_EXIST') {
				message('Lỗi! Username này đã tồn tại!',0);	
				$('#username').css('border-color','red');
				$('#username').focus();
			} else {
				message('Lỗi! Lưu không thành công!',0);										
			}
		},
		error: function(data){ alert (data);unblock('#bg_wrapper');}	
	});	
}
function doSelectRow(nRow) {
	aDataSelected = oTable.fnGetData(nRow); // get datarow
	if (null != aDataSelected)  // null if we clicked on title row
	{
		byId("id").value = aDataSelected.id;
		byId("username").value = aDataSelected.username;
		byId("msnhanvien").value = aDataSelected.msnhanvien;
		byId("hotennhanvien").value = aDataSelected.honhanvien + " " +aDataSelected.tennhanvien;
		byId("active").value = aDataSelected.active;
		$("#username").attr('readonly',true);
	}
}
function doSearch() {
	var frm = $('#form');
	var dat = "{'array':"+stringify(frm.serializeArray())+"}";
	oTable.fnFilter(dat);
}
function doLock() {
	dataString = '';
	$('#dataTable input[type=checkbox]').each(function(){
		if(this.checked==true) {
			if(this.value!='on')
				dataString+='&ids='+this.value;
		}
	});
	if(dataString=='') {
		alert('Bạn chưa chọn dòng để khóa!');
		return;
	}
	$.ajax({
		type: "POST",
		cache: false,
		url : "${ajLockAccountURL}",
		data: dataString,
		success: function(data){
			if(data == "END_SESSION") {
				location.href = LOGIN_PATH;
				return;
			}
			unblock('#bg_wrapper');
			oTable.fnDraw(false);
			message("Thao tác thành công!",1);
		},
		error: function(data){ alert (data);unblock('#bg_wrapper');}	
	});	
}
function openPermissionWindow(uid) {
	showDialogUrl("${permissionIndexURL}?id="+uid,'',610);
}
$(document).ready(function(){	
	$('#btSearchNhanVien').button({
		disabled: null,
		icons: {
			primary: "ui-icon-search"
		}
	});
	$("#btSearchNhanVien").click(function(){
		showDialogUrl("${searchIndexURL}",'Tìm kiếm nhân viên',500);
		return false;
	});
	$('input[type=button]').button({
		disabled: null,
	});
	oTable = $('#dataTable').dataTable({
		"bJQueryUI": true,
		"bProcessing": true,
		"bServerSide": true,
		"bAutoWidth": false,
		"sAjaxSource": "${ajLoadAccountsURL}",
		"aoColumns": [
					{ "mDataProp": "stt","bSortable": false,"bSearchable": false },
					{ "mDataProp": "id","bSortable": false,"bSearchable": false,"sClass":'td_center'},
					{ "mDataProp": "username","bSortable": false,"bSearchable": false},
					{ "mDataProp": "msnhanvien","bSortable": false,"bSearchable": false,"bVisible": false},
					{ 	"mDataProp": null,"bSortable": false,"bSearchable": false,
						"fnRender": function( oObj ) {
							return oObj.aData.honhanvien + " " + oObj.aData.tennhanvien;
						}
					},
					{ 	"mDataProp": null,"bSortable": false,"bSearchable": false,
						"fnRender": function( oObj ) {
							if(oObj.aData.active==1) 
								return '<center><div title="DeActive it!"  class="active"></div></center>'; 
							return '<center><div title="Active it!"  class="inactive"></div></center>';
						}
					},
					{ 	"mDataProp": null,"bSortable": false,"bSearchable": false,
						"fnRender": function( oObj ) {
							return '<center><img title="permission" src="'+contextPath+'/images/icons/permission.png" onclick="openPermissionWindow('+oObj.aData.id+')" style="cursor:pointer"></center>'; 
						}
					},
					{ 	"mDataProp": null,"bSortable": false,"bSearchable": false,
						"fnRender": function( oObj ) {
							return '<center><img title="Edit" src="'+contextPath+'/images/icons/edit.png" onclick="doSelectRow('+oObj.iDataRow+')" style="cursor:pointer"></center>'; 
						}
					},
					{ 	"mDataProp": null,"bSortable": false,"bSearchable": false,
						"fnRender": function( oObj ) {
							return '<center><input type="checkbox" value="'+oObj.aData.id+'"/></center>'; 
						}
					}
				],
		"fnServerData": function ( sSource, aoData, fnCallback ) {
			$.ajax( {
				"dataType": 'json', 
				"type": "POST", 
				"url": sSource, 
				"data": aoData, 
				"success": fnCallback
			} );
		},
		"sPaginationType": "two_button"
	}).fnSetFilteringPressEnter();
	$('#dataTable_filter input').attr('title','username');
	$('#dataTable_filter input[title!=""]').hint();
});
</script>