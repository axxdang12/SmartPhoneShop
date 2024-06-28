//
//
//    // Activate tooltip
// $('[data-toggle="tooltip"]').tooltip();

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
                btn.style.color = 'gray';
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
///////////////////////////////////////////////////////////////////////////////////





/////////////////////////////////////////////
  $(document).ready(function(){

     $('#statusSelect').change(function() {
         const selectedValue = $(this).val(); // Lấy giá trị của select option đã chọn
         console.log('Selected value:', selectedValue); // In ra giá trị đã chọn để kiểm tra
        $('#search-form input[name="keyword"]').val('');
         if (selectedValue == -1) {
             loadPage(1); // Gọi hàm loadPage với trang đầu tiên khi chọn "All"
         } else {
             loadPageStatus(1, selectedValue); // Gọi hàm loadPageStatus với trang đầu tiên và giá trị đã chọn
         }
     });

          $(document).on('click', '#page a', function(e) {
             e.preventDefault();
                 var page = $(this).data('page');
                 var selectedValue = $('#statusSelect').val();
                console.log("selectedValue: "+selectedValue );
                 var keyword = $('#search-form input[name="keyword"]').val();

                 if (keyword != ''&& keyword != null ) { // Kiểm tra nếu keyword không trống và selectedValue là "All"
                             loadPage(page, keyword);
                         } else {
                             loadPageStatus(page, selectedValue);
                         }
          });

          $('#search-button').click(function(e) {
                      e.preventDefault();
                      var keyword = $('#search-form input[name="keyword"]').val();
                      loadPage(1, keyword);
                        $('#statusSelect').val('');
                  });

           function loadPageStatus(page, selectedValue) {
                                      $.ajax({
                                          url: '/searchStatus/json',
                                          type: 'GET',
                                          data: {
                                              pageNumber: page,
                                              selectedValue: selectedValue
                                          },
                                          success: function(response) {
                                              $('#tableee').html(response.htmlContent);
                                              updatePaginationLinks(page, response.totalPages);

                                          },
                                          error: function() {
                                              console.error("Error loading page status");
                                          }
                                      });
                                  }
                            function updatePaginationLinks(currentPage, totalPages) {

                                $('#page li').remove();

                                if (currentPage > 1) {
                                    $('#page').append('<li><a href="#" id="page-link-prev" data-page="' + (currentPage - 1) + '">Prev</a></li>');

                                }
                                for (var i = 1; i <= totalPages; i++) {
                                    var activeClass = (i === currentPage) ? 'active' : '';
                                    $('#page').append('<li><a href="#" id="page-link-'
                                     + i + '" data-page="' + i + '" class="' + activeClass + '">'
                                      + i + '</a></li>');
                                }


                                if (currentPage < totalPages) {
                                    $('#page').append('<li><a href="#" id="page-link-next" data-page="'
                                     + (currentPage + 1) +
                                     '">Next</a></li>');
                                }
                        }

                         function loadPage(page, keyword = '') {
                                      $.ajax({
                                          url: '/manageProduct/json',
                                          type: 'GET',
                                          data: {
                                              pageNumber: page,
                                              keyword: keyword
                                          },
                                          success: function(response) {
                                              $('#tableee').html(response.htmlContent);
                                              updatePaginationLinks(page, response.totalPages);
                                          },
                                          error: function() {
                                              console.error("Error loading page");
                                          }
                                      });
                                  }


          var initialPage = 1;
          loadPage(initialPage);

      });

//////////////////////////////////////////////////////////////////////////////////////







//        function updatePaginationLinksStatus(currentPage, totalPages) {
//            $('#page a').removeClass('active');
//            $('#page-link-' + currentPage).addClass('active');
//
//            var currentTotalPages = $('#page li').length - 2; // Exclude "Prev" and "Next" links
//            if (totalPages !== currentTotalPages) {
//                $('#page li').slice(1, -1).remove(); // Remove current pagination links
//                for (var i = 1; i <= totalPages; i++) {
//                    var activeClass = (i === currentPage) ? 'active' : '';
//                    $('#page-link-next').before('<li><a href="#" id="page-link-' + i + '" data-page="' + i + '" class="' + activeClass + '">' + i + '</a></li>');
//
//                }
//            }
//
//            if (currentPage <= 1) {
//                $('#page-link-prev').hide();
//            } else {
//                $('#page-link-prev').show().data('page', currentPage - 1);
//            }
//            if (currentPage >= totalPages) {
//                $('#page-link-next').hide();
//            } else {
//                $('#page-link-next').show().data('page', currentPage + 1);
//            }
//        }
//
//        $(document).on('click', '#page a', function(e) {
//            e.preventDefault();
//            var page = $(this).data('page');
//            var selectedValue = $('input[name="radio"]:checked').val(); // Get the currently selected radio value
//            loadPageStatus(page, selectedValue);
//        });










