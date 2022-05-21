$('.container .square').on('click', function() {
    $(this).toggleClass('active');
    $(this).val($(this).attr('id'));
});