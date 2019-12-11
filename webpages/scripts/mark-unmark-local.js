/**
 * Respond to server JSON object.
 *
 */

function handleMarkUnmarkLocalClick(segmentID) {
    markTable = document.getElementById('markTable');
    
    var data = {};
    data["segmentID"] = segmentID;
  
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", mark_unmark_local_url, true);
  
    // send the collected data as JSON
    xhr.send(js);
  
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log ("XHR:" + xhr.responseText);
            processMarkUnmarkLocalResponse(xhr.responseText);
        } else {
            processMarkUnmarkLocalResponse("N/A");
        }
    };
}
  
function processMarkUnmarkLocalResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status      = js["statusCode"];
    
    if (status == 200) {
        // Update computation result
        // window.location.reload();
        refreshSegmentsList();
    } else {
        
    }
}
