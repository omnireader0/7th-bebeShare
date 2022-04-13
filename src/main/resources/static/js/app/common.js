function fnAjaxCall( param ){
    var ajaxParam = {
        url: param.url,
        data: param.data,
        dataType: param.dataType ? param.dataType : 'json',
        type: param.type ? param.type : "POST",
        async: param.async === false ? param.async : true,
        contentType: param.contentType ? param.contentType : "application/text;charset=utf-8",
        success: function(data) {

        },
        error: param.error,
        complete: function(data) {
            if(param.loadingBar){
                cfHideBlock();
            }
        }
    };

    $.ajax(ajaxParam);
}