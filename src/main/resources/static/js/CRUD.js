

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

            if (isActive) {
                btn.classList.remove('active');
                btn.style.color = 'red';
                btn.querySelector('i').setAttribute('title', 'Out of Stock');
                btn.querySelector('i').classList.remove('fa-check-circle');
                btn.querySelector('i').classList.add('fa-times-circle');
            } else {
                btn.classList.add('active');
                btn.style.color = 'green';
                btn.querySelector('i').setAttribute('title', 'In Stock');
                btn.querySelector('i').classList.remove('fa-times-circle');
                btn.querySelector('i').classList.add('fa-check-circle');
            }

            alert('Chỉnh sửa trạng thái sản phẩm thành công!');
        },
        error: function(error) {
            console.log("Lỗi khi chỉnh sửa trạng thái sản phẩm: ", error);
            alert('Có lỗi xảy ra khi chỉnh sửa trạng thái sản phẩm.');
        }
    });
}


//     $(document).ready(function() {
//         // Hàm xử lý khi người dùng click vào nút phân trang
//         $('a[id^="page-link"]').click(function(event) {
//             event.preventDefault();
//             var page = $(this).attr('data-page');
//
//             // Thực hiện AJAX để lấy dữ liệu từ server
//             $.ajax({
//                 type: 'GET',
//                 url: '/phones',
//                 data: {
//                     page: page
//                 },
//                 success: function(data) {
//                     renderPhones(data.content);
//                     updatePagination(data);
//                 },
//                 error: function() {
//                     alert('Error fetching data.');
//                 }
//             });
//         });
//
//         // Hàm render dữ liệu vào bảng
//         function renderPhones(phones) {
//             var tableBody = $('#tableee');
//             tableBody.empty();
//             phones.forEach(function(phone) {
//                 var statusClass = phone.status ? 'active' : 'inactive';
//                 var statusColor = phone.status ? 'green' : 'red';
//                 var statusTitle = phone.status ? 'In Stock' : 'Out of Stock';
//
//                 tableBody.append(
//                     `<tr>
//                         <td class="tm-product-name">${phone.phoneId}</td>
//                         <td>${phone.productName}</td>
//                         <td>${phone.price} $</td>
//                         <td>
//                             <form action="/edit-product" method="post">
//                                 <input type="hidden" name="id" value="${phone.phoneId}">
//                                 <button type="submit" class="btn btn-link" style="color: white;">Edit</button>
//                             </form>
//                         </td>
//                         <td class="action-links">
//                             <a onclick="changeStatus(this)" class="stock ${statusClass}" style="color: ${statusColor};" data-id="${phone.phoneId}">
//                                 <i class="fas fa-${phone.status ? 'check' : 'times'}-circle" data-toggle="tooltip" title="${statusTitle}"></i>
//                             </a>
//                         </td>
//                     </tr>`
//                 );
//             });
//         }
//
//         // Hàm cập nhật phân trang
//         function updatePagination(data) {
//             var pagination = $('#page');
//             pagination.empty();
//
//             // Nút Prev
//             pagination.append(
//                 `<li>
//                     <a href="#" id="page-link-prev" data-page="prev">Prev</a>
//                 </li>`
//             );
//
//             // Các nút trang
//             for (var i = 0; i < data.totalPages; i++) {
//                 var pageNumber = i + 1;
//                 var activeClass = data.number === i ? 'active' : '';
//                 pagination.append(
//                     `<li>
//                         <a href="#" id="page-link-${pageNumber}" class="${activeClass}" data-page="${pageNumber}">${pageNumber}</a>
//                     </li>`
//                 );
//             }
//
//             // Nút Next
//             pagination.append(
//                 `<li>
//                     <a href="#" id="page-link-next" data-page="next">Next</a>
//                 </li>`
//             );
//
//             // Xử lý sự kiện click trên các nút phân trang mới
//             $('a[id^="page-link"]').click(function(event) {
//                 event.preventDefault();
//                 var page = $(this).attr('data-page');
//
//                 // Thực hiện AJAX để lấy dữ liệu từ server
//                 $.ajax({
//                     type: 'GET',
//                     url: '/phones',
//                     data: {
//                         page: page
//                     },
//                     success: function(data) {
//                         renderPhones(data.content);
//                         updatePagination(data);
//                     },
//                     error: function() {
//                         alert('Error fetching data.');
//                     }
//                 });
//             });
//         }
//     });










