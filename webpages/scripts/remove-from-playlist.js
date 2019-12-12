function processRemoveFromPlaylistResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    refreshPlaylistSegments();
}
  
function handleRemoveFromPlaylistClick(segmentID, playlistName, playlistEntryNum) {
    var form = document.RemoveFromPlaylistForm;
   
    var data = {};
    data["segmentID"] = segmentID;
    data["playlistName"] = playlistName;
    data["playlistEntryNum"] = playlistEntryNum+1;
  
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", remove_from_playlist_url, true);
  
    // send the collected data as JSON
    xhr.send(js);
  
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
      console.log(xhr);
      console.log(xhr.request);
      if (xhr.readyState == XMLHttpRequest.DONE) {
           if (xhr.status == 200) {
            console.log ("XHR:" + xhr.responseText);
            processRemoveFromPlaylistResponse(xhr.responseText);
           } else {
               console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
           }
      } else {
        processRemoveFromPlaylistResponse("N/A");
      }
    };
  }