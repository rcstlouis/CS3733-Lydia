# G.API Lydia Readme

## Comments & Notes

### YAML

### Databases
These comments are reprinted from the SQL File

* The id from the segments table will correspond to the ids in the 
playlistEntry table
* This id will be generated using uuid
* The playlistName from playlistEntries will correspond to the name in 
playlists
* The url in registeredSites will correspond to the originSite in segments 
if the origin is remote

## Questions

* Eventually, when we're uploading .ogg files to our S3 bucket whenever
the user wants to add a local video segment, will we send these files to
the server in a JSON as a base64encoded string?
