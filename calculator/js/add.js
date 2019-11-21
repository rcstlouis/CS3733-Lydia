/**
 * Respond to server JSON object.
 *
 */
function processAddResponse(arg1, arg2, result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);
  var js = JSON.parse(result);

  var computation = js["result"];
  var status      = js["statusCode"];
  
  if (status == 200) {
    // Update computation result
    document.addForm.result.value = computation
  } else {
    var msg = js["error"];
    document.addForm.result.value = "error:" + msg
  }
}

function handleAddClick(e) {
  var form = document.addForm;
  var arg1 = form.arg1.value;
  var arg2 = form.arg2.value;

  var data = {};
  data["arg1"] = arg1;
  data["arg2"] = arg2;

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", add_url, true);

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processAddResponse(arg1, arg2, xhr.responseText);
    } else {
      processAddResponse(arg1, arg2, "N/A");
    }
  };
}

