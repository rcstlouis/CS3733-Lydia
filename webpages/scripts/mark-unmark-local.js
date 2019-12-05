/**
 * Respond to server JSON object.
 *
 */

function handleMarkUnmarkClick() {
    markTable = document.getElementById('markTable')
    var segmentsToChange = []
    // for(i = 0; i < markTable.rows.length; i++){
    //     if(markTable.rows[i].cells[0].innerHTML.match('.*checked.*')){
    //         segmentsToChange.push(markTable.rows[i].cells[1].innerHTML)
    //     }
    // }
    for(i = 0; i < markTable.rows.length - 1; i++){
        if(document.getElementById('markSegmentBox' + i).checked){
            segmentsToChange.push(markTable.rows[i + 1].cells[1].innerText)
        }
    }
    
    var data = {};
    data["segmentNames"] = segmentsToChange;
  
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
            processMarkUnmarkResponse(xhr.responseText);
        } else {
            processMarkUnmarkResponse("N/A");
        }
    };
}
  
function processMarkUnmarkResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status      = js["statusCode"];
    
    if (status == 200) {
        // Update computation result
        window.location.reload()
    } else {
        
    }
}
