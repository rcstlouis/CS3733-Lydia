function processLoadedExternalSegmentResponse(result, url){
  if(result = 'N/A'){
    console.log("Something messed up in putting an external segment in the RDS");
    return
  }
  console.log("Remote segment registered: " + result["statusCode"]);
  remoteList = document.getElementById(url)
  remoteList.innerHTML += `
    <div class="segment" id="segment:${result.name}:entry:${result.segmentID}">
    <span class="playlistEntry">${result.name}</span><br>
    <div class="centerable">
      <video id="${result.segmentID}" width="320" height="240" controls>
        <source src="${result.originFilePath}" type="video/ogg">
        Your browser does not support the video tag.
      </video> <br>
    </div>
    <p> Character: ${result.character}</p>
    <p> Sentence: ${result.sentence}</p>
    <form name="DeleteSegmentForm">
      <input type="button" id="deleteSegmentButton:${result.segmentID}" value="Delete Segment" onclick="handleDeleteSegmentClick('${result.segmentID}')">
    </form>
    <form name="playlistSelectForm">
      <select id="playlistSelect:${result.segmentID}" name="playlistSelect" value="Select Playlist"></select>
      <input type="button" id="addToPlaylistButton:${result.segmentID}" value="Add Segment to Selected Playist" onclick="handleAddToPlaylistClick('${result.segmentID}')">
    </form>
    </div>
  `
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