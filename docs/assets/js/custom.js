$ = jQuery
jQuery(document).ready(function () {

  /* search toggle */
  $('body').click(function (evt) {
    if (!($(evt.target).closest('.search-section').length || $(evt.target).hasClass('search-toggle'))) {
      if ($(".search-toggle").hasClass("search-active")) {
        $(".search-toggle").removeClass("search-active");
        $(".search-box").slideUp("slow");
      }
    }
  });
  
  $(".search-toggle").click(function () {
    $(".search-box").toggle("slow");
    if (!$(".search-toggle").hasClass("search-active")) {
      $(".search-toggle").addClass("search-active");

    } else {
      $(".search-toggle").removeClass("search-active");
    }

  });
  /* mobile menu */
  /* mobile menu */
  jQuery('.menu-content-wrapper').meanmenu({
    meanMenuContainer: '.hgroup-wrap',
    meanScreenWidth: "767",
    meanRevealPosition: "right",
  });
	
	//keyboard navigation for mean menu
		var myEvents = {
		click: function(e) {
		  if ( jQuery(this).hasClass('menu-item-has-children') ) {
			jQuery(this).find('.mean-expand').addClass('mean-clicked').text('-');
		  }
		  jQuery(this).siblings('li').find('.mean-expand').removeClass('mean-clicked').text('+');
		  jQuery(this).children('.sub-menu').show().end().siblings('li').find('ul').hide();

		},

		keydown: function(e) {
		  e.stopPropagation();

		  if (e.keyCode == 9) {


			if (!e.shiftKey &&
			  ( jQuery('.mean-bar li').index( jQuery(this) ) == ( jQuery('.mean-bar li').length-1 ) ) ){
				jQuery('.meanclose').trigger('click');
			}  else if( jQuery('.mean-bar li').index( jQuery(this) ) == 0 ) {
			  $('.meanclose').removeClass('onfocus');
			}
			else if (e.shiftKey && jQuery('.mean-bar li').index(jQuery(this)) === 0)
			 jQuery('.mean-bar ul:first > li:last').focus().blur();
		  }
		},

		keyup: function(e) {
		  e.stopPropagation();
		  if (e.keyCode == 9) {
			if (myEvents.cancelKeyup) myEvents.cancelKeyup = false;
			else myEvents.click.apply(this, arguments);
		  }
		}
	  }

	  jQuery(document)
		.on('click', 'li', myEvents.click)
		.on('keydown', 'li', myEvents.keydown)
		.on('keyup', 'li', myEvents.keyup);

	  jQuery('.mean-bar li').each(function(i) { this.tabIndex = i; });
	
  /* back-to-top button*/

  $('.back-to-top').hide();
  $('.back-to-top').on("click", function (e) {
    e.preventDefault();
    $('html, body').animate({
      scrollTop: 0
    }, 'slow');
  });
  $(window).scroll(function () {
    var scrollheight = 400;
    if ($(window).scrollTop() > scrollheight) {
      $('.back-to-top').fadeIn();

    } else {
      $('.back-to-top').fadeOut();
    }
  });
  
  // slider
  var owllogo = $(".owl-slider-demo");
  owllogo.owlCarousel({
    items: 1,
    loop: true,
    nav: true,
    dots: false,
    smartSpeed: 900,
    autoHeight:true,
    autoplay: false,
    autoplayTimeout: 5000,
    fallbackEasing: 'easing',
    transitionStyle: "fade",
    autoplayHoverPause: true,
    animateOut: 'fadeOut'
  });
  var owllogo = $(".service-slider");
  owllogo.owlCarousel({
    items: 1,
    loop: true,
    nav: true,
    dots: false,
    smartSpeed: 900,
    autoplay: true,
    autoplayTimeout: 5000,
    fallbackEasing: 'easing',
    transitionStyle: "fade",
    autoplayHoverPause: true,
    animateOut: 'fadeOut'
  });
  var owllogo = $(".testimonial-slider");
  owllogo.owlCarousel({
    items: 1,
    loop: true,
    nav: false,
    dots: false,
    smartSpeed: 900,
    autoplay: true,
    autoplayTimeout: 5000,
    fallbackEasing: 'easing',
    transitionStyle: "fade",
    autoplayHoverPause: true,
    animateOut: 'fadeOut'
  });
  // custom tab
  jQuery('.tabs .tab-links a').on('click', function (e) {
    var currentAttrValue = jQuery(this).attr('href');

    // Show/Hide Tabs
    jQuery('.tabs ' + currentAttrValue).fadeIn(400).siblings().hide();

    // Change/remove current tab to active
    jQuery(this).parent('li').addClass('active').siblings().removeClass('active');

    e.preventDefault();
  });

});