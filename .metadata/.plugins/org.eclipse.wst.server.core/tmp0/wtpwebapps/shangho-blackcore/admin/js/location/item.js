$(function () {	
	initial();	
});

function initial(){	
	setRouter();
}
function showMainContent(){
	$('#main-content').empty();
	buildTemplate('tpl_page_main',null,'main-content');
	
	showDataList();
	setBindMain();	
}
function showDataList(){
	var listOBJ = {
		content : {
			token : 'token'
		}
	};
	var arr = doListSubmit(listOBJ);

	var obj = {
		arr : arr
	};

	buildTemplate('tpl_mode_list',obj,'table_list');

	$("[data-toggle='tooltip']").tooltip();

	$('#data-table').DataTable({
		"paging": true,
		"lengthChange": false,
		
		"ordering": false,
		"info": false,
		"autoWidth": true,
		"displayLength": 10
    });

    $('input.column_filter').on( 'keyup click', function () {
        filterColumn( $(this).attr('count'),$(this).val());
    });

    $('#data-table_filter').attr('style','display:none;')

}
function filterColumn ( i,_value ) {
    $('#data-table').DataTable().column( i ).search(
        _value
    ).draw();
}
function setBindMain(){	
	$("#table_list").unbind("click.turnoff");
    $("#table_list").on("click.turnoff", ".turnoff", function(event){ 
    	var id = $(this).attr('value');
    	var OBJ = {
			content : {
				token : 'token',
				id : id,
				name : $('#name_' + id).text(),
				status :'0',
				categoryid : $('#category_id_' + id).val(),
				description : $('#description_' + id).text()
			}
		}
		doStatusSubmit(OBJ,id);
    });

    $("#table_list").unbind("click.turnon");
    $("#table_list").on("click.turnon", ".turnon", function(event){ 
    	var id = $(this).attr('value');
    	var OBJ = {
			content : {
				token : 'token',
				id : id,
				name : $('#name_' + id).text(),
				status :'1',
				categoryid : $('#category_id_' + id).val(),
				description : $('#description_' + id).text()
			}
		}
		doStatusSubmit(OBJ,id);
    });

    //修改
    $("#table_list").unbind("click.edit");
    $("#table_list").on("click.edit", ".edit", function(event){ 
       	console.log($(this));
    	var id = $(this).attr('value');
    	var name = $('#name_' + id).text();
    	var description = $('#description_' + id).text();
    	var categoryID = $('#category_id_' + id).val();
    	
    	$('#name_' + id).attr('style','display:none');
        $('#name_modify_' + id).html('<input name="input_name_'+ id +'" value="'+ name +'"/>');
        $('#description_' + id).attr('style','display:none');
        $('#description_modify_' + id).html('<input name="input_description_'+ id +'" value="'+ description +'"/>');
 		$('#category_name_modify_' + id).html(handleListCategoryOptionHTML(id,categoryID));

        $('#edit_' + id).attr('style','display:none');
        $('#check_' + id).attr('style','display');
        $('#delete_' + id).attr('style','display:none');
        $('#turnoff_' + id).attr('style','display:none');
        $('#turnon_' + id).attr('style','display:none');
        $('#category_name_' + id).attr('style','display:none');
    });

    //修改
    $("#table_list").unbind("click.check");
    $("#table_list").on("click.check", ".check", function(event){ 
    	var id = $(this).attr('value');
		var OBJ = {
			content : {
				token : 'token',
				id : id,
				name : $('input[name=input_name_'+ id +']').val(),
				status : $('#status_'+ id).val(),
				categoryid :$('#category_select_'+ id).val(),
				description : $('input[name=input_description_'+ id +']').val()
			}
		}
		doUpdateSubmit(OBJ,id);    	
    });

    //刪除
    $("#table_list").unbind("click.delete");
    $("#table_list").on("click.delete", ".delete", function(event){ 
    	var id = $(this).attr('value');
    	if(confirm("確認要刪除?")){
    		var OBJ = {
    			content : {
					token : 'token',
					id : $(this).attr('value')
				}
    		}
    		doDeleteSubmit(OBJ);
    	}
    });

    //查詢表單
    $("#main-content").unbind("click.search");
    $("#main-content").on("click.search", ".search", function(event){ 
    	var obj = $('#search_table');
    	if(obj.attr('style')=='display:none;'){
    		obj.attr('style','display');
    	}else{
    		obj.attr('style','display:none;');
    	}
    });
}
function setBindAdd(){
    //submit
	$("#main-content").unbind("click.add_submit");
	$("#main-content").on("click.add_submit", "#add_submit", function(event){ 
		if($("#myform").valid()){
			var OBJ = {
				content : {
					token : 'token',
					name : $('#name').val(),
					status :$('#status').val(),
					categoryid :$('#category_id').val(),
					description : $('#description').val()
				}
			}
			doInsertSubmit(OBJ);
		}else{
			var nameOBJ = $('#name');
			if(nameOBJ.val().length<=0){
				nameOBJ.parent('div').parent('div').addClass('has-error')
			}
		}
    });
}
function setRouter(){
	var routes = {		
		'':showMainContent(),
		'add': showAdd
	};

	var router = Router(routes)
	
	router.notfound = function() {
      	showMainContent();
  	};
    router.init('');
}
function buildTemplate(templateId,_obj,pageId){
	var tpl_code = $('#' + templateId).html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function buildTemplateMultipleHtml(tpl_code,_obj,pageId){
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function showAdd(){
	var OBJ = {
		content : {
			token : 'token'		
		}
	}

	var tplArr = {
		arr : doCategoryListSubmit(OBJ)
	};

	$('#main-content').empty();
	buildTemplate('tpl_page_add',tplArr,'main-content');
	setBindAdd();
	$("#myform").validate();
}
function doListSubmit(_data){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/location/item/list',
		data: JSON.stringify(_data),
		success: function(obj){			
			if(obj.status==0){
				data = obj.data;
				console.log(obj);
			}else{
				alert(obj.message)
				console.log(obj.message);	
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);			
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
	return data;
}
function doUpdateSubmit(_data,_ID){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/location/item/update',
		data: JSON.stringify(_data),
		success: function(obj){			
			if(obj.status==0){
				console.log(obj);
				handleUpdateSuccess(_ID,_data.content.status);
			}else{
				alert(obj.message)
				console.log(obj.message);
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);			
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function doDeleteSubmit(_data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/location/item/delete',
		data: JSON.stringify(_data),
		success: function(obj){			
			if(obj.status==0){
				console.log(obj);
				handleDeleteSuccess(_data.content.id);
			}else{
				alert(obj.message)
				console.log(obj.message);	
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);			
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function doStatusSubmit(_data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/location/item/update',
		data: JSON.stringify(_data),
		success: function(obj){			
			if(obj.status==0){
				handleUpdateStatusSuccess(_data.content.id,_data.content.status);
			}else{
				alert(obj.message)
				console.log(obj.message);
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);			
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function doInsertSubmit(_data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/location/item/insert',
		data: JSON.stringify(_data),
		success: function(obj){			
			if(obj.status==0){
				console.log(obj);
				window.history.replaceState(null,null,'#');
				showMainContent();
			}else{
				alert(obj.message)
				console.log(obj.message);
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);			
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function doCategoryListSubmit(_data){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/location/category/list',
		data: JSON.stringify(_data),
		success: function(obj){			
			if(obj.status==0){
				data = obj.data;
				console.log(obj);
			}else{
				alert(obj.message)
				console.log(obj.message);	
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);			
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
	return data;
}
function sortById(array){
	array.sort(function(objA, objB){
        if (objA.id > objB.id) {
	    	return 1;
	  	}
  		if (objA.id < objB.id) {
	    	return -1;
	  	}
	  	return 0;
    });
    return array;
}
function checkObj(_obj){
	if(_.isNull(_obj)){
		return false;
	}
	if(_.isUndefined(_obj)){
		return false;
	}
	if(_.isNaN(_obj)){
		return false;
	}
	if(_obj.length<=0){
		return false;
	}
	return true;
}
function handleUpdateSuccess(_ID,_status){
	var description = $('input[name=input_description_' + _ID +']').val();
	var name = $('input[name=input_name_' + _ID +']').val();
	var categoryname = $('#category_select_' + _ID +' option:selected').text();
	var categoryid = $('#category_select_' + _ID +'').val();

	$('#description_' + _ID).text(description);
	$('#description_' + _ID).attr('style','display');

	$('#name_' + _ID).text(name);
	$('#name_' + _ID).attr('style','display');
    $('#name_modify_' + _ID).html('');
    $('#description_modify_' + _ID).html('');

    $('#edit_' + _ID).attr('style','display');
    $('#check_' + _ID).attr('style','display:none');
    $('#delete_' + _ID).attr('style','display');

	$('#category_name_' + _ID).text(categoryname);
    $('#category_name_' + _ID).attr('style','display');
    $('#category_name_modify_' + _ID).html('');
    $('#category_id_' + _ID).val(categoryid);
    handleUpdateStatusSuccess(_ID,_status);
}
function handleUpdateStatusSuccess(_ID,_status){
	if(_status=="1"){
		$('#turnoff_'+_ID).attr('style','display');
		$('#turnon_'+_ID).attr('style','display:none');
	}else if(_status=="0"){
		$('#turnoff_'+_ID).attr('style','display:none');
		$('#turnon_'+_ID).attr('style','display');
	}
	$('#status_'+_ID).val(_status);
}
function handleDeleteSuccess(_ID){
	$('#delete_'+_ID).parent('td').parent('tr').empty()
}
function handleListCategoryOptionHTML(_ID,_categoryID){
	var OBJ = {
		content : {
			token : 'token'		
		}
	}

	var arr = doCategoryListSubmit(OBJ);
	var html = '<select class="form-control" id="category_select_' + _ID +'">';

	for(var i=0;i<arr.length;i++){
		if(_categoryID==arr[i].id){
			html += '<option value="'+arr[i].id+'" selected="true">'+arr[i].name+'</option>';
		}else{
			html += '<option value="'+arr[i].id+'">'+arr[i].name+'</option>';
		}
	}

	return html + '</select>';
}