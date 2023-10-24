$('.btn-danger').click(function() {
    var fruitId = $(this).data('id');
    if (confirm("Вы уверены, что хотите удалить фрукт?")) {
        $.ajax({
            type: 'DELETE',
            url: '/admin/delete-fruit?fruitId=' + fruitId,
            success: function(data) {
                if (data.success) {
                    // Обновить список фруктов после удаления
                    location.reload();
                } else {
                    alert('Ошибка при удалении фрукта: ' + data.message);
                }
            },
            error: function(xhr, status, error) {
                alert('Ошибка при удалении фрукта: ' + error);
            }
        });
    }
});
