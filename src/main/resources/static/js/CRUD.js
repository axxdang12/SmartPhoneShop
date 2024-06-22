

    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Khi nút "Edit" được nhấn
    $('.edit').on('click', function() {
        var phoneId = $(this).data('id');

        // Gửi yêu cầu Ajax tới server để lấy dữ liệu
        $.ajax({
            url: '/api/edit-product',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify( phoneId ),
            success: function(response) {
            alert(response.listBrand[0]);
             var phone = response.phone;
              var listBrand = response.listBrand;
                // Điền dữ liệu vào các trường trong popup
                 $('#editProductId').val(phone.phoneId);
                $('#editProductName').val(phone.productName);
                $('#editProductPrice').val(phone.price);
                $('#editProductCPU').val(phone.cpu);
                $('#editProductOrigin').val(phone.origin);
                $('#editProductSim').val(phone.sim);
                $('#editProductDate').val(phone.releaseDate);
                $('#editProductCamera').val(phone.camera);
                $('#editProductDisplay').val(phone.display);

                $('#editProductRam').val(phone.ram);
                $('#editProductMemory').val(phone.memory);

                    var selectbrand = $('#editProductBrand');


                $('#editProductBrand').empty();

                   // Duyệt qua danh sách listBrand và thêm các tùy chọn vào select box
                $.each(listBrand, function(index, brand) {
                    $('#editProductBrand').append('<option  th:value="${' + brand.brandId + '}" th:text="${' + brand.brandName + '}"></option>');
                   });


                // Hiển thị popup
                $('#editEmployeeModal').modal('show');
            },
            error: function(error) {
                console.log("Error fetching product data: ", error);
            }
        });
    });


// Khi form được submit
$('#editProductForm').on('submit', function(event) {
event.preventDefault();

// Thu thập dữ liệu từ các trường input
var productData = {
   phoneId: $('#editProductId').val(),
   productName: $('#editProductName').val(),
   price: $('#editProductPrice').val(),
   cpu: $('#editProductCPU').val(),
   origin: $('#editProductOrigin').val(),
   sim: $('#editProductSim').val(),
   releaseDate: $('#editProductDate').val(),
   camera: $('#editProductCamera').val(),
   display: $('#editProductDisplay').val(),
   ram: $('#editProductRam').val(),
   memory: $('#editProductMemory').val(),
   brandId: $('#editProductBrand').val()
};

// Gửi yêu cầu Ajax tới server để cập nhật sản phẩm
$.ajax({
   url: '/api/update-product', // URL endpoint để cập nhật sản phẩm
   type: 'POST',
   contentType: 'application/json',
   data: JSON.stringify(productData),
   success: function(response) {
       // Xử lý khi cập nhật thành công
       console.log("Product updated successfully:", response);

       // Ẩn modal sau khi cập nhật thành công
       $('#editEmployeeModal').modal('hide');

       // Cập nhật danh sách sản phẩm (có thể tải lại trang hoặc cập nhật DOM)
       // location.reload(); // Hoặc cập nhật DOM tùy thuộc vào yêu cầu cụ thể
   },
   error: function(error) {
       console.log("Error updating product: ", error);
   }
});
});




//  $(document).on('click', '.delete', function() {
//      // Lấy ID của sản phẩm từ thuộc tính data-id
//      var phoneId = $(this).data('id');
//
//      $.ajax({
//          url: '/api/delete-product',
//          type: 'POST',
//          contentType: 'application/json',
//          data: JSON.stringify({ id: phoneId }), // Đóng gói ID thành đối tượng JSON
//          success: function(response) {
//              // Hiển thị thông báo xóa thành công (nếu cần)
//              alert('chỉnh sửa trạng thái sản phẩm thành công!');
//              loadPhoneData();
//          },
//          error: function(error) {
//              console.log("Error deleting product: ", error);
//              alert('Có lỗi xảy ra khi xóa sản phẩm.');
//          }
//      });
//  });
function changeStatus(btn) {
   var isActive = btn.classList.contains('active'); // Kiểm tra xem nút có class 'active' hay không

    var phoneId = btn.getAttribute('data-id'); // Lấy ID của sản phẩm từ thuộc tính data-id của thẻ <a>

    $.ajax({
        url: '/api/change-status',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ id: phoneId }), // Đóng gói ID thành đối tượng JSON
        success: function(response) {
            // Hiển thị thông báo chỉnh sửa trạng thái sản phẩm thành công (nếu cần)
            alert('Chỉnh sửa trạng thái sản phẩm thành công!');
            if (isActive) {
                            btn.classList.remove('active');
                            btn.style.color = 'gray';
                            btn.querySelector('i').setAttribute('title', 'Inactive');
                            btn.querySelector('i').textContent = 'remove_circle';
                        } else {
                            btn.classList.add('active');
                            btn.style.color = 'green';
                            btn.querySelector('i').setAttribute('title', 'Active');
                            btn.querySelector('i').textContent = 'check_circle';
                        }


        },
        error: function(error) {
            console.log("Lỗi khi chỉnh sửa trạng thái sản phẩm: ", error);
            alert('Có lỗi xảy ra khi chỉnh sửa trạng thái sản phẩm.');
        }
    });
}




        // Gửi yêu cầu Ajax tới server để xóa sản phẩm









