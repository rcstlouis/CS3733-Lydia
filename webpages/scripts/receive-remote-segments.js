function processLoadedExternalSegmentResponse(result){
  if(result = 'N/A'){
    console.log("Something messed up in putting an external segment in the RDS");
    return
  }
  console.log("Remote segment registered: " + result["statusCode"]);
} 

function processReceiveRemoteSegmentsResponse(result, url) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);
  var segments = result["segments"];
  for(i = 0; i < segments.length; i++){
    var data = {}

    data["url"] = segments[i]["url"];
    data["character"] = segments[i]["character"];
    data["text"] = segments[i]["text"];
    data["originSite"] = url;
    var js = JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", get_remote_segments, true);
    xhr.send(js);
    xhr.onloaded = function(){
      if (xhr.readyState == XMLHttpRequest.DONE) {
        console.log ("XHR:" + xhr.responseText);
        processLoadedExternalSegmentResponse(xhr.responseText, url);
      } else {
        processLoadedExternalSegmentResponse("N/A", url);
      }
    }
  }
  refreshSegmentsList();
}

function receiveRemoteSegments(urlapi) {
  var q = urlapi.indexOf("?apikey=");
  if (q == -1) {
    alert("Registered site must be of the form 'url?apikey=...'");
  } else {
    var url = urlapi.substring(0, q);
    var apikey = urlapi.substring(q+8);
    console.log('Remote URL: ' + url);
    console.log('API Key: ' + apikey);

    requestSegments(url, apikey);
  }
  var xhr = new XMLHttpRequest();
  xhr.open("GET", url, true);
  xhr.setRequestHeader("x-api-key", apikey);

  xhr.send();
  
  console.log("sent");
 
  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processReceiveRemoteSegmentResponse(xhr.responseText, url);
    } else {
      processReceiveRemoteSegmentResponse("N/A", url);
    }
  };
 }