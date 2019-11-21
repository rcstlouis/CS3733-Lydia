// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = " https://8qmw7avi1m.execute-api.us-east-2.amazonaws.com/Beta/"; 

var add_url    = base_url + "calculator";   // POST
var create_url = base_url + "constant";     // POST
var delete_url = base_url + "delete";       // Can't send JSON to DELETE request. This is POST
var list_url   = base_url + "constants";    // GET
