/**
 * Refresh playlist list from server
 *
 *    GET list_playlists_url
 *    RESPONSE  list of [name] playlists
 */
function refreshPlaylistList() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", list_playlists_url, true);
    xhr.send();
    
    console.log("sent");
 
   // This will process results and update HTML as appropriate. 
   xhr.onloadend = function () {
     if (xhr.readyState == XMLHttpRequest.DONE) {
       console.log ("XHR:" + xhr.responseText);
       processPlaylistListResponse(xhr.responseText);
     } else {
       processPlaylistListResponse("N/A");
     }
   };
 }

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'playlistList' with a <br>-separated list of names.
 */
function processPlaylistListResponse(result) {
    console.log("res:" + result);
    // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
    var js = JSON.parse(result);
    var playlistList = document.getElementById('playlistList');

    var output = "";
    for (var i = 0; i < js.list.length; i++) {
        var playlistJson = js.list[i];
        console.log(playlistJson);
        
        var name = playlistJson["name"];
        output = output + "<div id=\"playlist" + name + "\"><b>" + name + "</b><br></div>";
    }

    // Update computation result
    playlistList.innerHTML = output;
  }