/**
 *
 *		GET list_segments_url
 *		RESPONSE list of segments
 *
 */

function refreshSegmentsList(){

	var xhr = new XMLHttpRequest();
	xhr.open("GET", list_segments_url, true);
	xhr.send();

	console.log("sent");

	xhr.onloadend = function () {
		console.log ("XHR:" + xhr.responseText) 
		if(xhr.readyState == XMLHttpRequest.DONE){
			processSegmentListResponse(xhr.responseText);
		}
		else{
			processSegmentListResponse("N/A");
		}
	};
}

/**
 * Respond to server JSON object
 *
 * Replace the contents of 'segmentList' with a <br>-separated list of names
 */
function processSegmentListResponse(result){
	console.log("res:" + result);

	var js = JSON.parse(result);
	var segmentList = document.getElementById('segmentList');

	var output = segmentList.innerHTML;
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
		if(remotelyAvailable){
			isRemotelyAvailable = "true";
		}

		//Add a check to see if the origin site is our site at some point
		output = output + 
			// '<div id="segment:' + segmentID + '">'+
			'<div class="segment" id="segment:' + name + 'entry:' + segmentID + '">'+
				'<b>' + name + ':</b><br>' + 
				'<video id="' + segmentID + '" width="320" height="240" controls>' +
					'<source src="' + originFilePath + '" type="video/ogg">' +
					'Your browser does not support the video tag.' +
				'</video> <br>' + 
				'<p> character: ' + character + '</p>' +
				'<p> sentence: ' + sentence + '</p><br>'+
				'<p> remotely available: ' + isRemotelyAvailable + ' </p>' +
			'</div>';
	}

	// Update computation result
	segmentList.innerHTML = output;
}