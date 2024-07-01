$(document).ready(function () {
    $('.role-select').change(function () {
        var userId = $(this).attr("data-id");
        var roleName = $(this).val();
        var params = new window.URLSearchParams(window.location.search);
        var currentPage;
        if (params.get("page") == null){
            currentPage = 1;
        }else {
            currentPage = params.get("page");
        }
        var requestSaveUserRoleDto = {
            userId: userId,
            roleName: roleName,
            page: currentPage
        };
        $.ajax({
            url: "/save-role/",
            type: 'POST',
            data: JSON.stringify(requestSaveUserRoleDto),
            contentType: 'application/json; charset=utf-8',
            error: function (error) {
                console.error(error.data)
            },
            success: function (data) {
                alert(data.resultList);
                $('#table').empty();
                $.each(data.resultList, function (key, value){
                    $('#table').append(`
                        <tr>
                           <td>${value.userId}</td>
                           <td>${value.username}</td>
                           <td><select class="role-select" data-id="${value.userId}">
                                <option value="${data.roles[0].roleName}" selected="${data.roles[0].roleName === value.roles[0].roleName}">${data.roles[0].roleName}</option>
                                <option value="${data.roles[1].roleName}" selected="${data.roles[1].roleName === value.roles[0].roleName}">${data.roles[1].roleName}</option>
                                <option value="${data.roles[2].roleName}" selected="${data.roles[2].roleName === value.roles[0].roleName}">${data.roles[2].roleName}</option>
                           </select></td>
                           <td><a class="btn btn-info" href="/user_detail/${item.userId}">Details</a></td>
                           <td>
                                <button class="btn btn-danger cancel-order" data-order-id="1">Active</button>
                           </td>
                        </tr>                
                    `);
                });
            }
        })
    });
});