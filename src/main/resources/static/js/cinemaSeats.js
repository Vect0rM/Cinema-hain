$('.container .square').on('click', function() {
    var clickCount = +$(this).data("clickCount");
    if (!clickCount)
        clickCount = 0;
    clickCount++;
    $(this).data("clickCount", clickCount);
    if (clickCount % 2 === 1) {
        $(this).addClass('active');
        $(this).val($(this).attr('id'));
    } else {
        $(this).removeClass('active');
        $(this).val("");
    }
});