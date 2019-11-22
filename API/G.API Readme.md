# G.API Lydia Readme

## Comments & Notes

### API

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
* The lambda functions will generate a UUID whenever they interact with a clip that 
does not yet have a UUID in our database. This means that segments to upload and
requests that need to get clips from other sites will not need to pass in the uuid
in the JSON input.
* As we understand it, consumers and admins cannot delete remote clips. If a user
or admin wants a remote clip to stop showing up on their browser, the only way to
completely remove it is if the admin unregisters its origin site.
* Change mark segment will toggle whether or not a local clip is remotely available.
We designed this assuming that the webpage will display to the admin whether or not
the segment is already remotely available. We may include a warning message to 
refresh the webpage before making the request so that the admin will refresh the
webpage in the edge case where another admin has already changed the permission on
the segment in question.
* All resources tagged with consumer are available to both consumers and admins.
* The search-segments API searches for video segments that fit the user's search 
criteria. If no segments fit the criteria, the request will return an empty list,
and it is the browser's responsibility to inform the user that no results matched
the search.
* We realize that the hyphens in the names may present problems down the line. We
plan to reformat this file in Swagger later.

### Database
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
* If we give our another group a URL to a clip in our S3 bucket, then
our "admin" marks the segment as not remotely available, we can update the
database without issue, but how do we actually prevent the other team from
accessing the clip? We could potentially go through an API and lambda function
to handle incoming requests for our clips, but as I understand it, this would
only be feasible if every group had a standardized API for this.