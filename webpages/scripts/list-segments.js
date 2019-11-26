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
}