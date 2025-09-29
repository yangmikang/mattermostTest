jQuery(function($){
  // var datepicker = $("#datepicker");
  if(!$.datepicker) return false;
  var datePicker;
  var today = new Date();
  var datePickerHtml = '';
      datePickerHtml += '<div class="modal-datepicker">';
      datePickerHtml += '<div class="modal-datepicker_wrap">';
      datePickerHtml += '<div id="datepicker" class="datepicker">';
      datePickerHtml += '<button type="button" class="modal-datepicker_close">닫기</button>';
      datePickerHtml += '</div>';
      datePickerHtml += '</div>';
      datePickerHtml += '</div>';

  var createDatePicker = function(){
    datepicker.datepicker({
      onSelect: function(date, datepicker){
          obj = $('#'+$('.modal-datepicker').data('obj'));
          obj.val(date);
          $('.modal-datepicker').hide();
      }
    });
  }

  var callDatePicker = function(){
    // $('.modal-calendar').click(function(){

      $('body').append(datePickerHtml);
      datePicker = $("#datepicker");

      datepicker.datepicker();
      // createDatePicker();


      var datepickerWrap = $('.modal-datepicker');
      var input = $(this).prev();
      var sDate = new Date(input.val());
      var minDate = input.date;
  
      datepickerWrap.show();
      datepickerWrap.data('obj',input.attr('id'));
  
      datepicker.datepicker("setDate",sDate);
  
      if(input.data('minDate') != 'N'){
          datepicker.datepicker("option",{
              // minDate: today
          });
      }
    // });  
  }
  
  var dateInit = function(){
    $.datepicker.setDefaults({
      dateFormat: 'yy-mm-dd',
      prevText: '이전 달',
      nextText: '다음 달',
      monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      dayNames: ['일', '월', '화', '수', '목', '금', '토'],
      dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
      dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
      showMonthAfterYear: true,
      yearSuffix: '',
    });
  }

  var dateRange = function(){
    var dateRangSet = $('.cs-datepicker-set');
    dateRangSet.each(function(){
      var row = $(this);
      var from = row.find('.js__date-from');
      var to = row.find('.js__date-to');

      // from.datepicker("setDate",new Date());
      // to.datepicker("setDate","+ 1w");

      from.datepicker({
        // defaultDate: "+1w",
        changeYear: true,
        numberOfMonths: 3,
      })
      .on( "change", function() {
        // console.log('나 바꼈다');
        to.datepicker( "option", "minDate", getDateRang( this ) );
      });

      to.datepicker({
        // defaultDate: "+1w",
        changeYear: true,
        numberOfMonths: 3
      }).on( "change", function() {
        from.datepicker( "option", "maxDate", getDateRang( this ) );
      });

    });
  }

  function getDateRang( element ) {
    var date;
    try {
      date = $.datepicker.parseDate( "yy-mm-dd", element.value );
    } catch( error ) {
      date = null;
    }

    return date;
  }

  var init = function(){
    //한글설정
    dateInit();

    $('.js__datepicker').datepicker({
      changeMonth: false,
      changeYear: true
    });

    $('.js__datepicker + button').on('click', function(){
      $(this).prev().datepicker('show');
    });

    $('.modal-calendar').on('click',function(){
      
    });
    // callDatePicker();


    dateRange();
  }

  init();

  

  

  

  $('.modal-datepicker_close, .modal-datepicker_wrap').click(function(){
      $('.modal-datepicker').hide();
  });
  $('.datepicker').click(function(e){
      e.stopPropagation();
  });

  //date
  getDate = function(date){
    if(!date) date = new Date();
    var y = date.getFullYear();
        m = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        d = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();

        return y + '-' + m + '-' + d;
  }
});