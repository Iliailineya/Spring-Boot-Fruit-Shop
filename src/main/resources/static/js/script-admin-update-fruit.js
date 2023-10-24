$(function() {

    $('#updateModalBtnSave').click(function(e) {
        e.preventDefault();
        $("#addModalBtnClear").prop("disabled", true);
        $("#addModalBtnSave").prop("disabled", true);
        $('#addModalSpinner').show();
        var fruitId = $('#updateFruitId').val();
        var fruitName = $('#updateFruitName').val();
        var fruitArt = $('#updateFruitArt').val();
        var fruitDescr = $('#updateFruitDescr').val();
        var fruitPrice = $('#updateFruitPrice').val();

        var formData = new FormData();
        formData.append('fruitId', fruitId);
        formData.append('fruitName', fruitName);
        formData.append('fruitArt', fruitArt);
        formData.append('fruitDescr', fruitDescr);
        formData.append('fruitPrice', fruitPrice);

        // Отправляем AJAX-запрос на сервер для обновления фрукта
        $.ajax({
            type: 'PUT',
            enctype: 'multipart/form-data',
            dataType: 'json',
            contentType: false,
            processData: false,
            url: '/admin/update-fruit',
            data: formData
        })
        .done(function(response) {
            if(response.success == false) {
                            output = "<span style='color: #f02d1f; font-size: 16px;'>" + response.message + "</span>";
                        }else {
                            output = "<span style='color: #22a131; font-size: 16px;'>" + response.message + "</span>";
                        }
                        $('#addModalSpinner').hide();
                        $('#addModalResponse').html(output);
                        $("#addModalBtnClear").prop("disabled", false);
                        $("#addModalBtnSave").prop("disabled", false);
        })
            .fail (function(e) {
                $('#addModalSpinner').hide();
                $("#addModalResponse").html(e.responseText);
                $("#addModalBtnClear").prop("disabled", false);
                $("#addModalBtnSave").prop("disabled", false);
            });
});
}