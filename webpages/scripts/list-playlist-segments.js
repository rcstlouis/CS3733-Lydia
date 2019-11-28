/**
 * Refresh playlist entries from server
 *
 *    GET list_playlist_segments_url
 *    RESPONSE  list of [SegmentID, name, originFilePath, originSite, remotelyAvailable, character, sentence] segments
 */
function refreshPlaylistSegments(playlistName) {

    //Sanitize playlistName so that an arbitrary query string can't be appended to the url

    var xhr = new XMLHttpRequest();
    xhr.open("GET", list_playlist_segments_url + "?playlistName=" + playlistName, true);
    xhr.send();
    
    console.log("sent");
 
   // This will process results and update HTML as appropriate. 
   xhr.onloadend = function () {
     if (xhr.readyState == XMLHttpRequest.DONE) {
       console.log ("XHR:" + xhr.responseText);
       processListResponse(xhr.responseText, playlistName);
     } else {
       processListResponse("N/A");
     }
   };
 }
 
 /**
  * Respond to server JSON object.
  *
  * Replace the contents of 'playlistSegments' with a <br>-separated list of name,value pairs.
  */
 function processListResponse(result, playlistName) {
   console.log("res:" + result);
   // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
   var js = JSON.parse(result);
   var playlistEntries = document.getElementById('playlist:' + playlistName); //Make sure each playlist makes a div
   
   var output = playlistEntries.innerHTML;
   for (var i = 0; i < js.list.length; i++) {
     var playlistEntryJson = js.list[i];
     console.log(playlistEntryJson);
     
    //  [segmentID, name, originFilePath, originSite, remotelyAvailable, character, sentence]
    //  We eventually will need to store all of this information in an object that the browser can access again
    //  for other requests like removals and such

    var segmentID = playlistEntryJson["ID"];
    var name = playlistEntryJson["name"];
    var originFilePath = playlistEntryJson["originFilePath"];
    var originSite = playlistEntryJson["originSite"];
    var remotelyAvailable = playlistEntryJson["remotelyAvailable"];
    var character = playlistEntryJson["character"];
    var sentence = playlistEntryJson["sentence"];

    var isRemotelyAvailable = "false";
    if(remotelyAvailable){
      isRemotelyAvailable = "true";
    }

    //Add a check to see if the origin site is our site at some point
    output = output + 
        `<div id="playlist:${playlistName}:entry:${segmentID}" class="segment">
          <span class="playlistEntry">${name}</span><br>
          <video id="${playlistName}:${segmentID}:video" width="320" height="240" controls>
            <source src="${originFilePath}" type="video/ogg">
            Your browser does not support the video tag.
          </video> <br>
          <p> character: ${character}</p>
          <p> sentence: ${sentence}</p><br>
          <p> remotely available: ${isRemotelyAvailable}</p>
          Selected: <input type="checkbox">
        </div>`;
   }
 
   // Update computation result
   playlistEntries.innerHTML = output;
 }
 