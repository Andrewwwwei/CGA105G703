//取消body scroll
$('body').css('overflow','hidden');

// 讓以自訂的景點打開資訊
$('.custom-loc').click((e) => {
  $('#locInfo-tab').addClass('active');  $('#locInfo').addClass('show active');
  $('#cusLoc-tab').removeClass('active');
  $('#cusLoc').removeClass('show active');
});

//在活動名稱後方的自訂景點開啟
$('#custom-Loc').click((e) => {
  let locInfo = $('#loc-info');
  //如果地點資訊卡片沒有打開 那就把它開啟
  if (!locInfo.hasClass('show')) {
    locInfo.addClass('show');
    //如果資訊卡都開啟，在看是不再在自訂景點內，如果是就把資訊卡整個關掉
  } else if ($('#cusLoc-tab').hasClass('active') || $('#cusLoc').hasClass('show active')) {
    ;
    locInfo.removeClass('show');
  }
  $('#cusLoc-tab').addClass('active');
  $('#cusLoc').addClass('show active');
  $('#locInfo-tab').removeClass('active');
  $('#locInfo').removeClass('show active');
});


//聊天室的開啟
$('#mbrMsg-icon').click((e) => {
  $('#mbr-chat').css('display', 'block');
  $('#mbrMsg-icon').css('display', 'none');
  //當聊天室打開將scroll固定在最下方
  let scrollHeight = $('#msg-content').prop('scrollHeight');
  $('#msg-content').animate({ scrollTop: scrollHeight }, 400);
});

//聊天室關閉
$('#close-msg').click((e) => {
$('#mbrMsg-icon').css('display', 'block');
$('#mbr-chat').css('display', 'none');
});

// 聊天室內開啟筆記本
$('#notes-btn').click((e) => {
  let memNotes = $('#mem-notes');
  if (memNotes.css('display') === 'none') {
    memNotes.css('display', 'block');
  } else if ($('#note-btn').hasClass('active') || $('#notes').hasClass('show active')) {
    memNotes.css('display', 'none');
  }
  $('#note-btn').addClass('active');
  $('#notes').addClass('show active');
  $('#mbr-btn').removeClass('active');
  $('#mbr').removeClass('show active');
});

//聊天室內開啟活動成員
$('#mbrs-btn').click((e) => {
  let memNotes = $('#mem-notes');
  if (memNotes.css('display') === 'none') {
    memNotes.css('display', 'block');
  } else if ($('#mbr-btn').hasClass('active') || $('#mbr').hasClass('show active')) {
    memNotes.css('display', 'none');
  }
  $('#mbr-btn').addClass('active');
  $('#mbr').addClass('show active');
  $('#note-btn').removeClass('active');
  $('#notes').removeClass('show active');
});

$('#invite').click((e) => {
  let memNotes = $('#mem-notes');
  if (memNotes.css('display') === 'none') {
    memNotes.css('display', 'block');
  } else if ($('#mbr-btn').hasClass('active') || $('#mbr').hasClass('show active')) {
    memNotes.css('display', 'none');
  }
  $('#mbr-btn').addClass('active');
  $('#mbr').addClass('show active');
  $('#note-btn').removeClass('active');
  $('#notes').removeClass('show active');
});


