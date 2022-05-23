define(['bajaux/mixin/subscriberMixIn',
'bajaux/Widget',
'baja!',
'jquery',
'Promise',
'dialogs',
'hbs!nmodule/ntools/rc/smsrecipienttemplate',
'css!nmodule/ntools/rc/smsrecipientstyle'],function (
subscriberMixIn,
Widget,
baja,
$,
Promise,
dialogs,
smsrecipienttemplate) {

'use strict';

var SMSRecipient = function () {
Widget.apply(this, arguments);
subscriberMixIn(this);
console.log("kvg" + this);
};

var recipientArray = [];


SMSRecipient.prototype = Object.create(Widget.prototype);
SMSRecipient.prototype.constructor = SMSRecipient;

SMSRecipient.prototype.doInitialize = function (jq) {
jq.html(smsrecipienttemplate());

};

// create a new row
$("body").on("click","#addRow",function(ev){
    var len = $("#tabprincipal tbody tr").toArray().length;
    var newRow = "<tr id='tabrow' class='tabrow editing'>"
    +"<td><div>name</div><div><input name='personName' type='text' value=''/></div></td>"
    +"<td><div>number</div><div><input name='cellNumber' type='text' value=''/></div></td>"
    +"<td><a class='btn btn-xs editRow'><img src=''/><i title='Edit Recipient' class='icon-icons-x16-edit'></i></a><a class='btn btn-xs saveRow'><i title='Save Recipient' class='icon-icons-x16-save'></i></a><a class='btn btn-xs deleteRow'><i title='Delete Recipient' class='icon-icons-x16-delete'></i></a></td></div>"
+"</tr>";
    $(newRow).appendTo("#tabprincipal tbody");
});
//edit row
$("body").on("click",".editRow",function(ev){
   $(this).parents(".tabrow").removeClass("closed").addClass("editing");

});
// save
$("body").on("click",".saveRow",function(ev){
    var row = $(this).parents(".tabrow");
    // update the cells
    row.find("td").each(function(){
       // new value
       var newVal = $(this).find("input").val();
       $(this).find("div:first").html(newVal);
    });
    row.removeClass("editing").addClass("closed");
});
// on delete
$("body").on("click",".deleteRow",function(SMSRecipient){
SMSRecipient.preventDefault();
//are you sure?
var didConfirm = confirm("Are you sure you want to delete this recipient?");
     if (didConfirm == true) {
      $(this).parents(".tabrow").remove();

        var data = $("form").serializeArray();
        console.log(data);
        data.shift();
        console.log(data);
        var recipientString = '';
                for (var i =0; i < data.length; i++){
                  if(i % 2 === 0) { // index is even
                      recipientString += data[i].value + ":"
                      }

                   else
                   {
                      recipientString += data[i].value + ","
                      }
                    console.log(data[i].value);
                    }
                    var outString = recipientString.slice(0, -1);
                    console.log(outString)
                    // write to string on delete
                    console.log("this" + $(this));
                    baja.Ord.make('station:|slot:/Services/AlarmService/SMSRecipient').get().then(function (point) {point.setTo(outString)});
                    return true;
                    } else {
                    return false;
                    }
});


// on save
$("body").on("click",".saveRow",function(SMSRecipient){
    SMSRecipient.preventDefault();
    var data = $("form").serializeArray();
    console.log(data);
    data.shift();
    console.log(data);
    var recipientString = '';
        for (var i =0; i < data.length; i++){
          if(i % 2 === 0) { // index is even
              recipientString += data[i].value + ":"
              }
              else
              {
              recipientString += data[i].value + ","
              }
            console.log(data[i].value);
            }
            var outString = recipientString.slice(0, -1);
            console.log(outString)
            //write to string on save
            baja.Ord.make('station:|slot:/Services/AlarmService/SMSRecipient').get().then(function (point) {point.setTo(outString)});

});

//update when string is changed
SMSRecipient.prototype.doLoad = function (SMSRecipient) {
var jq = this.jq().find('input');
function update() {
          jq.val(SMSRecipient.getTo());
          var table = document.getElementById('tabprincipal');
          //clear table before rebuilding
          $('.tabrow').remove();
           recipientArray = SMSRecipient.getTo().split(",");
          for (var i =0; i < recipientArray.length; i++){
          var recipArray = recipientArray[i].split(":");
          var newRow = "<tr id='tabrow' class='tabrow closed'>"
              +"<td><div>"+recipArray[0]+"</div><div><input name='personName' type='text' value='"+recipArray[0]+"'/></div></td>"
              +"<td><div>"+recipArray[1]+"</div><div><input name='cellNumber' type='text' value='"+recipArray[1]+"'/></div></td>"
              +"<td><a class='btn btn-xs editRow'><i title='Edit Recipient' class='icon-icons-x16-edit'></i></a><a class='btn btn-xs saveRow'><i title='Save Recipient' class='icon-icons-x16-save'></i></a><a class='btn btn-xs deleteRow'><i title='Delete Recipient' class='icon-icons-x16-delete'></i></a> </td></div>"
          +"</tr>";
              $(newRow).appendTo("#tabprincipal tbody");
          }
          console.log(recipientArray);
          }


this.getSubscriber().attach('changed', update);
update();
};

return SMSRecipient;

});
