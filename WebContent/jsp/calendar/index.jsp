<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="addEvent" namespace="/event" id="addEventURL"/>
<s:url action="editEvent" namespace="/event" id="editEventURL"/>
<s:url action="getEvents" namespace="/event" id="getEventsURL"/>
<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() %>/plugins/calendar/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() %>/css/events.css' />
<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() %>/plugins/calendar/fullcalendar.print.css' media='print' />
<script type='text/javascript' src='<%= request.getContextPath() %>/plugins/calendar/fullcalendar.min.js'></script>
<script src="<%= request.getContextPath() %>/js/view.js" type="text/javascript" ></script>
<script src="<%= request.getContextPath() %>/js/ejs_production.js" type="text/javascript" ></script>
<script src="<%= request.getContextPath() %>/js/mylibs/search_user_2.js" type="text/javascript" ></script>
<div id='calendar' ></div>
<script>
function message(msg,type) {
	if(type==1) { //Thong diep thong bao
		byId("msg").innerHTML = '<div class="ui-state-highlight ui-corner-all" style=" padding: 0 .7em;"><p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span><strong>Success : </strong> '+msg+'</p></div>';
	} else if(type == 0) { //Thong diep bao loi
		byId("msg").innerHTML = '<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;"><p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span><strong>Error : </strong> '+msg+'</p></div>';
	}
}
var date_selected = "";
function refetchEvents(){
	$('#calendar').fullCalendar( 'refetchEvents' )
}
$(document).ready(function(){	
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	$('#calendar').fullCalendar({
		monthNames: ['Tháng 1','Tháng 2','Tháng 3','Tháng 4','Tháng 5','Tháng 6','Tháng 7','Tháng 8','Tháng 9','Tháng 10','Tháng 11','Tháng 12'],
		monthNamesShort: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
		dayNames: ['Chủ Nhật','Thứ Hai','Thứ Ba','Thứ Tư','Thứ Năm','Thứ Sáu','Thứ Bảy'],
		dayNamesShort: ['Chủ Nhật','Thứ Hai','Thứ Ba','Thứ Tư','Thứ Năm','Thứ Sáu','Thứ Bảy'],
		buttonText: {
			prev: '&nbsp;&#9668;&nbsp;',
			next: '&nbsp;&#9658;&nbsp;',
			prevYear: '&nbsp;&lt;&lt;&nbsp;',
			nextYear: '&nbsp;&gt;&gt;&nbsp;',
			today: 'Hôm nay',
			month: 'Tháng',
			week: 'Tuần',
			day: 'Ngày'
		},
		theme: true,
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		editable: true,
		selectable: true,
		selectHelper: true,
		select: function(start, end, allDay) {
			date_selected = $.datepicker.formatDate('dd/mm/yy',start);
			showDialogUrl("${addEventURL}",'Tạo sự kiện cho ngày : '+ date_selected ,650);
			//showDialog();
			//$('#calendar').fullCalendar('render');
		},
		eventClick: function(event) {
			/* if (event.url) {
				window.open(event.url);
				return false;
			} */
			date_selected = $.datepicker.formatDate('dd/mm/yy',event.start);
			showDialogUrl("${editEventURL}?id="+event.id,'Sửa sự kiện ngày : '+ date_selected ,650);
		},
		events: function(start, end, callback) {
			$.ajax({
				url: "${getEventsURL}",
				dataType: 'json',
				data: {
					// our hypothetical feed requires UNIX timestamps
					start: Math.round(start.getTime()),
					end: Math.round(end.getTime())
				},
				success: function(data) {
					var events = [];
					$.each(data.data, function() {
						events.push({
							color: this.state==1?"blue":'#CCC',
							id: this.id,
							title: this.tensukien,
							start: this.ngaysukien // will be parsed
						});
					});
					callback(events);
				}
			});
		},
		timeFormat: 'H:mm'
	});
});
</script>