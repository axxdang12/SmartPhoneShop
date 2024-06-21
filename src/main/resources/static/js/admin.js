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
                $("#table").html("<tr th:each=\"item: ${data.text().cresultList}\">\n" +
                    "                            <td th:name=\"${item.userId}\" th:text=\"${item.userId}\"></td>\n" +
                    "                            <td th:text=\"${item.username}\"></td>\n" +
                    "                            <td><select th:value=\"${item.userId}\" class=\"role-select\">\n" +
                    "                                <option th:each=\"role: ${listRole}\" th:value=\"${role.roleName}\" th:selected=\"${role.roleName == item.roles[0].roleName}\" th:text=\"${role.roleName}\"></option>\n" +
                    "                            </select></td>\n" +
                    "                            <td><a class=\"btn btn-info\" th:href=\"@{/user_detail}\">Details</a></td>\n" +
                    "                            <td>\n" +
                    "                                <button class=\"btn btn-danger cancel-order\" data-order-id=\"1\">Delete</button>\n" +
                    "                            </td>\n" +
                    "                        </tr>");
            }
        })
    });
});