$('.container .square').on('click', function() {
    $(this).toggleClass('active');
    $(this).text($(this).attr('id'));
});