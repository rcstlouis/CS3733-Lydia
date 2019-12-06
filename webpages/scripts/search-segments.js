function processSearchSegmentsResponse(result, data) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);
	var segmentList = document.getElementById('searchResults');
	
	var characterSearch = data["character"];
	var sentenceSearch = data["sentence"];
	

	var output = "";
	for (var i = 0; i < js.list.length; i++){
		var segmentJSON = js.list[i];
		console.log(segmentJSON);

		var segmentID = segmentJSON["ID"];
		var name = segmentJSON["name"];
		var originFilePath = segmentJSON["originFilePath"];
		var originSite = segmentJSON["originSite"];
		var remotelyAvailable = segmentJSON["remotelyAvailable"];
		var character = segmentJSON["character"];
		var sentence = segmentJSON["sentence"];
		var isRemotelyAvailable = "false";

	

		if ((!character.match("") && (character.match(`.*${characterSearch}.*`))) || (!sentence.match("") && (sentence.match(`.*${sentenceSearch}.*`)))){
			
		
		//Add a check to see if the origin site is our site at some point
		output = output +
			`<div class="segment" id="segment:${name}:entry:${segmentID}">
				<span class="playlistEntry">${name}</span><br>
				<div class="centerable">
					<video id="${segmentID}" width="320" height="240" controls>
						<source src="${originFilePath}" type="video/ogg">
						Your browser does not support the video tag.
					</video> <br>
				</div>
				<p> character: ${character}</p>
				<p> sentence: ${sentence}</p><br>
				<p> remotely available: ${isRemotelyAvailable}</p>
				Selected: <input type="checkbox">
			</div>`;
		}
	}
	
	segmentList.innerHTML = output;

}
  
function handleSearchSegmentsClick(e) {
    var form = document.searchForm;
   
    var data = {};
    data["character"] = form.character.value;
    data["sentence"] = form.sentence.value;
    
//    // base64EncodedValue":"data:text/plain;base64,My4xND....."
//    var segments = document.searchForm.base64Encoding.value.split(',');
//    data["base64EncodedValue"] = segments[1];  // skip first one 
//  
//    var js = JSON.stringify(data);
//    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", list_segments_url, true);
  
    // send the collected data as JSON
    xhr.send();
  
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
      console.log(xhr);
      console.log(xhr.request);
      if (xhr.readyState == XMLHttpRequest.DONE) {
           if (xhr.status == 200) {
            console.log ("XHR:" + xhr.responseText);
            processSearchSegmentsResponse(xhr.responseText, data);
           } else {
               console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
           }
      } else {
        processSearchSegmentsResponse("N/A", data);
      }
    };
  }