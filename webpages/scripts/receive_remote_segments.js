function processReceiveRemoteSegmentsResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    refreshSegmentsList();
}

function receiveRemoteSegments(url) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    xhr.send();
    
    console.log("sent");
 
   // This will process results and update HTML as appropriate. 
   xhr.onloadend = function () {
     if (xhr.readyState == XMLHttpRequest.DONE) {
       console.log ("XHR:" + xhr.responseText);
       processReceiveRemoteSegmentResponse(xhr.responseText);
     } else {
       processReceiveRemoteSegmentResponse("N/A");
     }
   };
 }