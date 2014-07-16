<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="ajMyEvent" namespace="/event" id="ajMyEventURL"/>
<style>
.td_label {
width:100px;font-weight:bold;text-align:right
}
</style>
<div id="box_form" style="margin: 0pt auto; width: 550px; height: auto;" class="box">
	<form method="post" action="" id="form">
	<fieldset>
		<legend>DANH SÁCH SỰ KIỆN ĐÃ TẠO</legend>
		<table width="100%">
			<tr>
				<td class="td_label">Từ ngày :</td>
				<td>
				<input type="text" name="date_from" id="date_from" class="field date" style="width:150px">
				</td>
				<td class="td_label">Đến ngày :</td>
				<td>
				<input type="text" name="date_end" id="date_end" class="field date" style="width:150px">
				</td>
			</tr>
			<tr style="height:50px">
				<td align="center" colspan="4">
				<input type="button" id="btSearch" value="Tìm kiếm" onclick="doSearch()">
				<input type="button" id="btReset" value="Reset" onclick="doReset()">
				</td>
			</tr>	
		</table>
	</fieldset>
	</form>
</div>
<div style="float: right; padding: 5px;">
</div>
<table width="100%" id="dataTable" class="display">
<thead>
	<tr>
		<th width="5%">#</th>
		<th>ID</th>
		<th width="200px">Tên sự kiện</th>
		<th width="150px">Ngày sự kiện</th>
		<th width="250px">Thông điệp</th>
		<th>Loại thông báo</th>
		<th>Trạng thái</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td colspan="7" class="dataTables_empty">Loading data from server</td>
	</tr>
</tbody>
</table>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-ui/jquery.ui.datepicker-vi.js"></script>
<script>
function doReset() {
	$("#form")[0].reset(); //Reset form cua jquery, giu lai gia tri mac dinh cua cac field
	byId("msg").innerHTML="";
	$("#username").attr('readonly',false);
}
function doSearch() {
	var frm = $('#form');
	var dat = "{'array':"+stringify(frm.serializeArray())+"}";
	oTable.fnFilter(dat);
}
$(document).ready(function(){	
	$('input[type=button]').button();
	$( "input.date" ).datepicker({
		showButtonPanel: true,
		dateFormat : "dd-mm-yy"
	});
	$("#date_from").val( $.datepicker.formatDate('dd-mm-yy',new Date()));
	oTable = $('#dataTable').dataTable({
		"bJQueryUI": true,
		"bProcessing": true,
		"bServerSide": true,
		"bAutoWidth": false,
		"sAjaxSource": "${ajMyEventURL}",
		"aoColumns": [
					{ "mDataProp": "stt","bSortable": false,"bSearchable": false },
					{ "mDataProp": "id","bSortable": false,"bSearchable": false,"bVisible": false,"sClass":'td_center'},
					{ "mDataProp": "tensukien","bSortable": false,"bSearchable": false},
					{ 	"mDataProp": null,"bSortable": false,"bSearchable": false,"sClass":'td_center',
						"fnRender": function( oObj ) {
							//return $.datepicker.formatDate('dd/mm/yy',getDateFromFormat(oObj.aData.ngaysukien,"yyyy-MM-dd 00:00:00.0"));
							return oObj.aData.ngaysukien;
						}
					},
					{ "mDataProp": "noidung","bSortable": false,"bSearchable": false},
					{ 	"mDataProp": null,"bSortable": false,"bSearchable": false,"sClass":'td_center',
						"fnRender": function( oObj ) {
							if(oObj.aData.loaithongbao == 0) {
								return "Một lần";
							} else if(oObj.aData.loaithongbao == 1) {
								return "Hàng tuần";
							} else if(oObj.aData.loaithongbao == 2) {
								return "Hàng tháng";
							} else if(oObj.aData.loaithongbao == 3) {
								return "Hàng năm";
							}
						}
					},
					{ 	"mDataProp": null,"bSortable": false,"bSearchable": false,"sClass":'td_center',
						"fnRender": function( oObj ) {
							if(oObj.aData.state == 0) {
								return "Đã xóa";
							} else if(oObj.aData.state == 1) {
								return "Chưa thông báo";
							} else if(oObj.aData.state == 2) {
								return "Đã thông báo";
							} 
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