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
	var table = document.getElementById("markTable");

	var output = '';
	var remoteOutput = '';

	if(table !== null){
		while (table.rows.length > 1){
			table.deleteRow(table.rows.length - 1);
		}
	}
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

		if(originSite == "https://3733lydia.s3.us-east-2.amazonaws.com/segments/"){
			//Add a check to see if the origin site is our site at some point
			output = output + 
				// '<div id="segment:' + segmentID + '">'+
				`<div class="segment" id="segment:${name}:entry:${segmentID}">
					<span class="playlistEntry">${name}</span><br>
					<div class="centerable">
						<video id="${segmentID}" width="320" height="240" controls>
							<source src="${originFilePath}" type="video/ogg">
							Your browser does not support the video tag.
						</video> <br>
					</div>
					<p> Character: ${character}</p>
					<p> Sentence: ${sentence}</p>
					<p> Remotely Available: ${isRemotelyAvailable}</p>
					<form name="DeleteSegmentForm">
						<input type="button" id="deleteSegmentButton:${segmentID}" value="Delete Segment" onclick="handleDeleteSegmentClick('${segmentID}')">
					</form>
					<form name="playlistSelectForm">
						<select id="playlistSelect:${segmentID}" name="playlistSelect" value="Select Playlist"></select>
						<input type="button" id="addToPlaylistButton:${segmentID}" value="Add Segment to Selected Playist" onclick="handleAddToPlaylistClick('${segmentID}')">
					</form>
					</div>`;
			if(table !== null){
				// Create an empty <tr> element and add it to the ith position of the table:
				var row = table.insertRow(i+1);

				// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);

				// Add some text to the new cells:
				cell1.innerHTML = name;
				cell2.innerHTML = character;
				cell3.innerHTML = sentence;
				cell4.innerHTML = isRemotelyAvailable;
				cell5.innerHTML = 
					`<form name="markUnmarkLocalForm">
						<input type="button" id="markUnmarkLocalButton:${segmentID}" value="Change Remote Availability" onclick="handleMarkUnmarkLocalClick('${segmentID}')">
					</form>`;
			}
		} else {
			remoteOutput = remoteOutput + 
			// '<div id="segment:' + segmentID + '">'+
			`<div class="segment" id="segment:${name}:entry:${segmentID}">
				<span class="playlistEntry">${name}</span><br>
				<div class="centerable">
					<video id="${segmentID}" width="320" height="240" controls>
						<source src="${originFilePath}" type="video/ogg">
						Your browser does not support the video tag.
					</video> <br>
				</div>
				<p> Character: ${character}</p>
				<p> Sentence: ${sentence}</p>
				<p> Remotely Available: ${isRemotelyAvailable}</p>
				<form name="DeleteSegmentForm">
					<input type="button" id="deleteSegmentButton:${segmentID}" value="Delete Segment" onclick="handleDeleteSegmentClick('${segmentID}')">
				</form>
				<form name="playlistSelectForm">
					<select id="playlistSelect:${segmentID}" name="playlistSelect" value="Select Playlist"></select>
					<input type="button" id="addToPlaylistButton:${segmentID}" value="Add Segment to Selected Playist" onclick="handleAddToPlaylistClick('${segmentID}')">
				</form>
				</div>`;
		}
	}

	// Update computation result
	segmentList.innerHTML = output;
	remoteSegmentList.innerHTML = remoteOutput;
	refreshPlaylistsList();
}