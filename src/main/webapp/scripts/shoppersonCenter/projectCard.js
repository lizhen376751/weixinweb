(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize =Math.floor(100*(clientWidth / 1080))+ 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document,window);
$(function(){
	 var galleryThumbs = new Swiper('.gallery-thumbs', {
	 	pagination: '.swiper-pagination',
        spaceBetween: 10,
        centeredSlides: true,
        slidesPerView: 1.5,
        touchRatio: 0.2,
//      slideToClickedSlide: true,
        loop:true,
    });
})
