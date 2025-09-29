/******
 * 
 * 달력 설정
 * 
 */
var dpFunc = $.datepicker._generateHTML;
$.datepicker._generateHTML = function(inst){ 
  var thishtml = $( dpFunc.call($.datepicker, inst) );  
  thishtml = $('<div/>').append(thishtml);  
  $('.ui-datepicker-buttonpane', thishtml).append($('<button class="ui-datepicker-clear ui-state-default ui-priority-primary ui-corner-all"\>삭제</button>').click(function(){
      inst.input.attr('value', '');
      inst.input.datepicker('hide');
    })
  );
  
  thishtml = thishtml.children();
  
  return thishtml; 
};


$.datepicker._gotoToday = function(id) {
    var target = $(id);
    var inst = this._getInst(target[0]);
    if (this._get(inst, 'gotoCurrent') && inst.currentDay) {
            inst.selectedDay = inst.currentDay;
            inst.drawMonth = inst.selectedMonth = inst.currentMonth;
            inst.drawYear = inst.selectedYear = inst.currentYear;
    }else {
            var date = new Date();
            inst.selectedDay = date.getDate();
            inst.drawMonth = inst.selectedMonth = date.getMonth();
            inst.drawYear = inst.selectedYear = date.getFullYear();
            // the below two lines are new
            this._setDateDatepicker(target, date);
            this._selectDate(id, this._getDateDatepicker(target));
    }
    this._notifyChange(inst);
    this._adjustDate(target);
};


$.datepicker.regional['ko'] = {
   closeText: '닫기',
   prevText: '이전',
   nextText: '다음',
   currentText: '오늘',
   monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
   monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
   dayNames: ['일','월','화','수','목','금','토'],
   dayNamesShort: ['일','월','화','수','목','금','토'],
   dayNamesMin: ['일','월','화','수','목','금','토'],
   weekHeader: 'Wk',
   dateFormat: 'yy-mm-dd',   
   firstDay: 0,
   isRTL: false,
   showMonthAfterYear: true,
   yearSuffix: ''};
  
$.datepicker.setDefaults($.datepicker.regional['ko']);