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
		console.log ("XHR:" + xhr.responseText) {
			processListResponse(xhr.responseText, segmentName);
		}
		else{
			processListResponse("N/A");
		}
	};
}

/**
 * Respond to server JSON object
 *
 * Replace the contents of 'segmentList' with a <br>-separated list of names
 */
function processListResponse(result){
	console.log("res:" + result);

	var js = JSON.parse(result);
	var segmentList = document.getElementById('segmentList');

	var output = segmentList.innerHTML;
	for (var i = 0; i < js.list.length; i++){
		var segmentJSON = js.list[i];
		console.log(segmentJSON);

		var segmentID = segmentJSON["segmentID"];
		var name = segmentJSON["name"];
		var originFilePath = segmentJSON["originFilePath"];
		var originSite = segmentJSON["originSite"];
		var remotelyAvailable = segmentJSON["remotelyAvailable"];
		var character = segmentJSON["character"];
		var sentence = segmentJSON["sentence"];

		//Add a check to see if the origin site is our site at some point
		if (remotelyAvailable) {
			output = output + 
			'<div id="segment:' + segmentName + 'entry:' + segmentID + '">'+
			'<b>' + name + ':</b><br>' + 
			'<video id="' + segmentID + '" width="320" height="240">' +
			'<source src="' + originFilePath + '" type="video/ogg">' +
			'Your browser does not support the video tag.' +
			'</video> <br>' + 
			'<p> character: ' + character + '</p>'
			'<p> sentence: ' + sentence + '</p><br>'+
			'<p> remotely available: true' + //currently the only line that differs
			'</div>';
		} else {
			output = output + 
			'<div id="segment:' + segmentName + 'entry:' + segmentID + '">'+
			'<b>' + name + ':</b><br>' + 
			'<video id="' + segmentID + '" width="320" height="240">' +
			'<source src="' + originFilePath + '" type="video/ogg">' +
			'Your browser does not support the video tag.' +
			'</video> <br>' + 
			'<p> character: ' + character + '</p>'
			'<p> sentence: ' + sentence + '</p><br>'+
			'<p> remotely available: false' + //currently the only line that differs
			'</div>';
		}
	}

	// Update computation result
	segmentList.innerHTML = output;
}