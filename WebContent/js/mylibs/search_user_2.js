var search_user_2 = {	afterSelected : null,	dialog : null,	ajListBoPhanURL : '',	ajListNhanvienByBophanURL : '',	init : function(params) {		search_user_2.ajListBoPhanURL = params.ajListBoPhanURL;		search_user_2.ajListNhanvienByBophanURL = params.ajListNhanvienByBophanURL;		dialog = $("#table_search_user_2").parents(".ui-dialog-content");		dialog.dialog("option", "position", "center");		MultiSelect("btadd","btremove","btaddall","btremoveall","select1","select2");		$('button#btChon',dialog).button();		$( "#buttons button:first",dialog).button({			icons: {				primary: "ui-icon-seek-end"			},			text: false		}).next().button({			icons: {				primary: "ui-icon-seek-first"			},			text: false		}).next().button({			icons: {				primary: "ui-icon-seek-next"			},			text: false		}).next().button({			icons: {				primary: "ui-icon-seek-prev"			},			text: false		});		$("#msphongban",dialog).change(function(){			if(this.value == '') return;			$.get(search_user_2.ajListBoPhanURL,{msphongban : this.value},function(data){				var msbophan = $("#msbophan",dialog);				msbophan.empty();				msbophan.append('<option value="" selected=true>--Chọn bộ phận--</option>');				$.each(data.data,function(){					msbophan.append('<option value="'+this.msbophan+'">'+this.tenbophan+'</option>');				});			});		});				$("#msbophan",dialog).change(function(){			if(this.value == '') return;			$.get(search_user_2.ajListNhanvienByBophanURL,{msbophan : this.value},function(data){				var select1 = $("#select1",dialog);				select1.empty();				$.each(data.data,function(){					var str = '<option value="'+this.msnhanvien+'">'+this.honhanvien +' ' + this.tennhanvien+'</option>';					select1.append(str);				});			});		});		$("#btChon",dialog).click(function(){			//$("#form #msnhanvien").val($("#msnhanvien option:selected",dialog).val());			//$("#form #hotennhanvien").val($("#msnhanvien option:selected",dialog).text());			var data = [];			$("#select2 option").each(function()			{				data.push({					id : $(this).val(),					ten_nhan_vien : $(this).text()				});			});			search_user_2.afterSelected(data);			dialog.dialog("close");			//jsdebug($("#dialog #msnhanvien option:selected").text());			return false;			//alert($("#dialog #msnhanvien option[selected]").					});	}}