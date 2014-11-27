/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// http://www.flashuser.net/responsive-flat-menu-tutorial

    $(document).ready(function(){
    var touch = $('#touch-menu');
    var menu = $('.menu');
     
    $(touch).on('click', function(e) {
    e.preventDefault();
    menu.slideToggle();
    });
    
    $(window).resize(function(){
    var w = $(window).width();
    if(w > 767 && menu.is(':hidden')) {
    menu.removeAttr('style');
    }
    });
    });