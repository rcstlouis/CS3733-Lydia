/**
 * Respond to server JSON object.
 *
 */

function registerSite() {
    url = document.getElementById('siteToRegister').value
    
    var data = {};
    data["url"] = url;
  
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", register_site_url, true);
  
    // send the collected data as JSON
    xhr.send(js);
  
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log ("XHR:" + xhr.responseText);
            processRegisterSiteResponse(xhr.responseText);
        } else {
            processRegisterSiteResponse("N/A");
        }
    };
}
  
function processRegisterSiteResponse(result) {
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
