/**
 *
 *		GET list_segments_url
 *		RESPONSE list of segments
 *
 */

function refreshRegisteredSiteList(){

	var xhr = new XMLHttpRequest();
	xhr.open("GET", list_sites_url, true);
	xhr.send();

	console.log("sent");

	xhr.onloadend = function () {
		console.log ("XHR:" + xhr.responseText) 
		if(xhr.readyState == XMLHttpRequest.DONE){
			processListSitesResponse(xhr.responseText);
		}
		else{
			processListSitesResponse("N/A");
		}
	};
}

/**
 * Respond to server JSON object
 *
 * Replace the contents of 'segmentList' with a <br>-separated list of names
 */
function processListSitesResponse(result){
	console.log("res:" + result);

	var js = JSON.parse(result);
	var table = document.getElementById('siteTable');

	//Clear the table
	if(table !== null){
		while (table.rows.length > 1){
			table.deleteRow(table.rows.length - 1);
		}
	}
    
	for (var i = 0; i < js.list.length; i++){
		var registerdSiteJSON = js.list[i];
		console.log(registerdSiteJSON);
        var url = registerdSiteJSON["URL"]
        
		if(table !== null){
			// Create an empty <tr> element and add it to the ith position of the table:
			var row = table.insertRow(i+1);

			// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);

			// Add some text to the new cells:
			cell1.innerHTML = url;
			cell2.innerHTML = 
				`<form id="unregisterSiteForm">
					<input type="button" id="unregisterSite" value="Unregister Site" onclick="handleUnregisterSite('${url}')">
				</form>`;
		}
	}
}