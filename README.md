# CS3733-Lydia
The Software Engineering course group project

## Links

[Public Link](https://3733lydia.s3.us-east-2.amazonaws.com/presentation/public.html)  
[Admin Link](https://3733lydia.s3.us-east-2.amazonaws.com/presentation/admin.html)

## Using the Website

Most functionality on the website is fairly intutitive. There are, however, a few idiosyncrasies that we should explain.

### Registering a remote site

When an admin registers a new remote site, the request goes to the database, but the page will try to reload when the request is sent. This reload can happen before the data actually reaches the database, meaning that the admin will not see the new site. This means that an admin who wants to register a new site should wait a bit for the data to reach the database, then reload the page. Reloading the page too fast may mean that the request never gets sent, especially if the admin tries to register a site before all of the segments have fully loaded. This may possibly take a few attempts, but it does work.

### Segments from a new Remote Site

It may be possible to see that a site is registered without seeing segments from that site. This is because our javascript needs a site to be registered in our database before we can request its segments. Once a site is in the database, a reload of the page will register all of that site's segments in our database. The admin may have to wait a bit to make sure that all requests to put a remote segment into our database get sent out. After this waiting period, an admin may refresh the page again to see the segments. This is because all functions that display segments go through the database, and remote segments take a few reloads for everything to get where it should be when a site is first registered.

### Removing a Remote Site

Removing a remote site can run into a problem similar to adding a remote site. The data must reach the database before the site will actually be removed, and this may fail to trigger the table to update. The admin may need to refresh the page to see the site unregistered. Refreshing too soon may prevent the request from being sent. This may possibly take a few attempts, but it does work.

### Searching for a segment

Our search is a very inclusive search. Rather than narrowing down a search, using multiple fields expands the search. Any search with the entered character as a substring of the character or with the entered sentence as a substring of the sentence will be returned. The search is case sensitive.

### Playlist Entry Ordering

In the database, the playlist entries have a regimented order. We thought that we could translate this order to the site with a GROUP BY clause in the query, but this is not the case. The subquery we use to get segments from the playlist entries undoes this ordering, so the playlist entries will display on the site according to their segment IDs, not by playlist order. Although the user cannot see it, the playlists definitely have an order.