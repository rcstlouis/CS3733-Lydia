# G.API Lydia Readme

## Comments & Notes

### YAML

* For uploading a segment, we required the user to have the clip's character and
sentence ready at the time of uploading. We chose to do this to simplify the
process of keeping the database up to date.
* For searching for a segment, we chose to use a get request where we feed the
search information in through a query string, as recommended by an SA. The get
request takes a string representing the consumer's search and a string representing
the criterion by which the consumer is searching
* For operations that use local clips, we use the name as a unique identifier
because our application will require all local clips to have unique names within
the local clips.
* When we need to search for a clip, we have no way to guarantee that a local
clip will not have the same name as a remote clip, so we use the UUID stored in our
database to identify the cips. We plan to store the UUID's in some collection using
JavaScript on the browser-side. This lets the browser send the UUID in the post
functions where it is needed.

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
* If we upload a video to the S3 bucket manually without entering the proper
database information, should the webpage display it to the user as a segment
without metadata, or should the webpage not display it at all?