$(document).ready(function () {
    $('.role-select').change(function () {
        var userId = $(this).parent("td").val();
        var roleName = $(this).val();
        var requestSaveUserRoleDto = {
            userId: userId,
            roleName: roleName
        };
        $.ajax({
            url: "/save-role/" + 0 + "/" + 2,
            type: 'POST',
            data: JSON.stringify(requestSaveUserRoleDto),
            contentType: 'application/json; charset=utf-8',
            error: function (error) {
                alert(error)
            },
            success: function (data) {
                $('#table').empty();
                alert(data);
            }
        })
    });
});