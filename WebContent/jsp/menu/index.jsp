<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="ajSaveMenu" namespace="/menu" id="ajSaveMenuURL"></s:url>
<s:url action="ajLoadMenu" namespace="/menu" id="ajLoadMenuURL"></s:url>
<%@ page language="java" contentType="text/html;"%>
<div id="box_form" style="margin: 0pt auto; width: 800px; height: auto;" class="box">
	<div class="tab">  
		<div id="tab_0" class="tl fl"></div>
		<div id="tab_1" class="tc fl"><div style="padding: 2px 5px 0;">QUẢN LÝ MENU</div></div>
		<div id="tab_2" class="fl tr"></div>
	 </div>
	<div class="l clb">
		<div class="r">
			<div class="c">
			<div class="tboxhder"></div>
			<div id="msg" style="padding-left: 17px;"></div>
			</div>
		</div>
	</div>
	<div class="box_leftside">
	<div class="box_rightside">
		<div style="width:98%">
			<form method="post" action="" id="form">
				<!-- Form -->
				<div class="form">
					<input type="hidden" name="menu.id" id="id"/>
					<div class="div_input_left">
						<label>Menu name <span>(Required Field)</span></label>
						<input type="text" name="menu.namemenu" id="namemenu" class="field size1">
					</div>	
					<div class="div_input_right">
						<label>Action <span>(Required Field)</span></label>
						<input type="text" name="menu.action" id="action" class="field size1">
					</div>
					<div class="div_input_left">
						<label>Root Menu <span>(Required Field)</span></label>
						<s:select 
							cssClass = "field size1"
							name="menu.rootmenu.id"
							id="rootmenu"
							list="lstRootMenu"
							listKey="id"
							listValue="name"
							headerKey="0"
							headerValue="---SELECT---"
							cssStyle="width:362px"
						/>
					</div>	
					<div class="div_input_right">
						<div style="display: block;height:20px"></div>
						<input type="radio" name="menu.active" id="active" checked="checked" value="1"/><label for="active" style="display: inline">Hoạt động</label>
						<input type="radio" name="menu.active" id="inactive" value="0"/><label for="inactive" style="display: inline">Không hoạt động</label>
					</div>
					<div class="div_input_inline">
						<input type="button" value="Lưu" class="button" onclick="doSaveMenu()">
						<input type="button" value="Reset" class="button" onclick="doReset()">
						<input type="button" value="Tìm kiếm" class="button" onclick="doSearch()">
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	<div class="foot_l"></div><div class="foot_c"></div><div class="foot_r"></div>
</div>
<table width="100%" id="dataTable" class="display">
	<thead>
		<tr>
			<th width="5px">STT</th>
			<th>ID</th>
			<th width="35%">Menu name</th>
			<th width="40%">Action</th>
			<th width="15%">ID Root Menu</th>
			<th width="15%">Root Menu</th>
			<th width="5px">Status</th>
			<th width="5px">Edit</th>
			<th width="5px"><input type="checkbox" onclick="selectAll(this)"/></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="8" class="dataTables_empty">Loading data from server</td>
		</tr>
	</tbody>
</table>
<script>
function selectAll(_this) {
	$('#dataTable input[type=checkbox]').each(function(){
		this.checked=_this.checked;
	});
}
function doSearch() {
	var frm = $('#form');
	var dat = "{'array':"+stringify(frm.serializeArray())+"}";
	//alert(dat);
	oTable.fnFilter(dat);
}
function doSaveMenu(){
	if(byId("id").value!='') {
		if(!confirm('Bạn muốn cập nhật dữ liệu này?'))
			return;
	}
	block('#bg_wrapper');
	dataString = $("#form").serialize();
	$.ajax({
		type: "POST",
		cache: false,
		url : "${ajSaveMenuURL}",
		data: dataString,
		success: function(data){
			unblock('#bg_wrapper');
			if(data == "OK") {
				if(byId("id").value=='') {//Them moi
					doReset();
				} 	
				message("Lưu thành công!",1);
				oTable.fnDraw(false);
			}
			else{
				alert("Dữ liệu không hợp lệ. Vui lòng kiểm tra lại")
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
		byId("namemenu").value = aDataSelected.namemenu;
		byId("action").value = aDataSelected.action;
		byId("rootmenu").value = aDataSelected.idrootmenu;
		if(aDataSelected.active==1){
			byId("active").checked=true;
		}
		else{
			byId("inactive").checked=true;
		}
	}
}
$(document).ready(function(){	
	oTable = $('#dataTable').dataTable({
		"bJQueryUI": true,
		"bProcessing": true,
		"bServerSide": true,
		"bAutoWidth": false,
		"sAjaxSource": "${ajLoadMenuURL}",
		"aoColumns": [
					{ "mDataProp": "stt","bSortable": false,"bSearchable": false,"sClass":'td_center' },
					{ "mDataProp": "id","bSortable": false,"bSearchable": false,"bVisible": false},
					{ "mDataProp": "namemenu","bSortable": false,"bSearchable": false},
					{ "mDataProp": "action","bSortable": false,"bSearchable": false},
					{ "mDataProp": "idrootmenu","bSortable": false,"bSearchable": false,"sClass":'td_center',"bVisible": false},
					{ "mDataProp": "namerootmenu","bSortable": false,"bSearchable": false,"sClass":'td_center'},
					{ "mDataProp": null,"bSortable": false,"bSearchable": false,
						"fnRender": function( oObj ) {
							if(oObj.aData.active==1) 
								return '<center><div title="DeActive it!"  class="active"></div></center>'; 
							return '<center><div title="Active it!"  class="inactive"></div></center>';
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
	
});
</script>