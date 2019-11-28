// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = "https://c8o0v9nw17.execute-api.us-east-2.amazonaws.com/Beta/"; 
//This is our calculator API. I'll put in the real one when we have it

var upload_segment_url         = base_url + "upload-segment";         //POST
var delete_segment_url         = base_url + "delete-segment";         //POST
var list_segments_url          = base_url + "list-segments";          //GET
var search_segments_url        = base_url + "search-segments";        //GET
var create_playlist_url        = base_url + "create-playlist";        //POST
var delete_playlist_url        = base_url + "delete-playlist";        //POST
var list_playlists_url         = base_url + "list-playlists";         //POST
var list_playlist_segments_url = base_url + "list-playlist-segments"; //GET
var remove_from_playlist_url   = base_url + "remove-from-playlist";   //POST
var add_to_playlist_url        = base_url + "add-to-playlist";        //POST
var mark_unmakr_local_url      = base_url + "mark-unmark-local";      //POST
var register_site_url          = base_url + "register-site";          //POST
var unregister_site_url        = base_url + "unregister-site";        //POST
var list_sites_url             = base_url + "list-sites";             //GET

toggletheme = function(){
    themecss = document.getElementById("theme");
    themebutton = document.getElementById("themebutton");
    if(themebutton.innerHTML === "Memes"){
        themecss.href = "./styles/memes.css";
        themebutton.innerHTML = "No Memes";
    }
    else{
        themecss.href = "./styles/light.css";
        themebutton.innerHTML = "Memes";
    }
}