/**
 * Respond to server JSON object.
 *
 */

function unregisterSelected() {
    siteTable = document.getElementById('siteTable')
    var sitesToUnregister = []
    // for(i = 0; i < siteTable.rows.length; i++){
    //     if(siteTable.rows[i].cells[0].innerHTML.match('.*checked.*')){
    //         sitesToUnregister.push(siteTable.rows[i].cells[1].innerHTML)
    //     }
    // }
    for(i = 0; i < siteTable.rows.length - 1; i++){
        if(document.getElementById('unregisterSiteBox' + i).checked){
            sitesToUnregister.push(siteTable.rows[i + 1].cells[1].innerText)
        }
    }
    
    var data = {};
    data["url"] = sitesToUnregister[0];
  
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", unregister_site_url, true);
  
    // send the collected data as JSON
    xhr.send(js);
  
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log ("XHR:" + xhr.responseText);
            processUnregisterSiteResponse(xhr.responseText);
        } else {
            processUnregisterSiteResponse("N/A");
        }
    };
}
  
function processUnregisterSiteResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status      = js["statusCode"];
    
    if (status == 200) {
        // Update computation result
        refreshRegisteredSiteList();
        location.reload(true)
    } else {
        
    }
}
