# Software Engineering: Amazon Web Services Project
The Group Project for Worcester Polytechnic's Software Engineering Course

## Contents
- [Software Engineering: Amazon Web Services Project](#software-engineering-amazon-web-services-project)
  - [Contents](#contents)
  - [Overview](#overview)
    - [Remote Sites](#remote-sites)
    - [Levels of Privilege](#levels-of-privilege)
    - [Passive App Features](#passive-app-features)
    - [Actions for Users](#actions-for-users)
    - [Actions for Admins](#actions-for-admins)
  - [The Components](#the-components)
    - [The Database](#the-database)
    - [AWS Lambda](#aws-lambda)
    - [The Webpage](#the-webpage)
  - [The EBC Model](#the-ebc-model)
  - [What We Would Change](#what-we-would-change)
    - [Standard Naming Conventions](#standard-naming-conventions)
    - [A More Effective Database](#a-more-effective-database)
    - [Using Joins Rather Than Sub-queries](#using-joins-rather-than-sub-queries)

## Overview

What we built is a web application that can organize Star Trek video clips into playlists. We called this application **iTrek**. The main learning objectives for **iTrek** were to gain experience with a larger-scale programming project than other courses, to learn a framework to organize programming projects (the [Entity Boundary Controller Model](#the-ebc-model)), and to become familiar with Amazon Web Services.

The application displayed each segment alongside some associated data, including the sentence spoken in the clip, the name of the character who speaks, and the clip's origin site.

### Remote Sites

The different Stark Trek video manager projects made by different teams from the Software Engineering course can interact with each other. Each application can mark its own segments as remotely available for other teams to use. In addition, each application can register other site's API's to access their remotely available segments. 

Each site has a [specified API](https://github.com/mastlouis/CS3733-Lydia/blob/master/API/remote-api.yaml) that other teams can use to see a list of that site's segments that are remotely available, along with each segment's URL, character name, and sentence.

### Levels of Privilege

There were two classes of privileges a user could have: [user privileges](#actions-for-users) and [admin privileges](#actions-for-admins). An admin can perform any action that takes user privileges or admin privileges.

### Passive App Features
* View all video segments
* View all playlists
* Play video segment

### Actions for Users
* Upload video segment
* Delete video segment
* Create playlist
* Delete playlist
* Add segment to playlist
* Delete segment from playlist

### Actions for Admins
* Mark segment as remotely available
* Mark segment as remotely unavailable
* Register remote site
* Unregister remote site

## The Components

The application consists of a [webpage](#the-webpage), [AWS lambda functions](#aws-lambda), and a [database](#the-database).

### The Database

The database runs on Amazon Web Services. It stores information about each of the video segments, playlists, and registered [remote sties](#remote-sites). For video segments, this includes the segment's unique ID, character name, sentence, URL, whether or not the segment is remotely available.

The database stores the state of the entire project. We mostly manipulated it with [Lambda Functions](#aws-lambda), but we used MySQLWorkbench to help get it set up and to manage test data. The database [schema](https://github.com/mastlouis/CS3733-Lydia/blob/master/API/schema.sql) is given here.

The actual .ogg video files are stored in an S3 Bucket through AWS.

### AWS Lambda

The Lambda Functions are functions written in Java that run on AWS Lambda. These functions manipulate the data in the database. The Lambda Functions reside in the [Lydia AWS Folder](https://github.com/mastlouis/CS3733-Lydia/tree/master/LydiaAWS), which is the Eclipse Project.

The lambda functions can be accessed through an API gateway that uses a [pre-specified API](https://github.com/mastlouis/CS3733-Lydia/blob/master/API/lydia-api.yaml).

The Lambda Functions we wrote are listed here:
* [Add Segments to Playlist Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/AddSegmentToPlaylistHandler.java)
* [Create Playlist Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/CreatePlaylistHandler.java)
* [Delete Playlist Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/DeletePlaylistHandler.java)
* [Delete Segment Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/DeleteSegmentHandler.java)
* [Get All Playlists Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/GetAllPlaylistsHandler.java)
* [Get All Segments Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/GetAllSegmentsHandler.java)
* [Get Remote Segments Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/GetRemoteSegmentsHandler.java)
  * Retrieves remote segments from a [remote site](#remote-sites)
* [List Playlist Segments Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/ListPlaylistSegmentsHandler.java)
  * Lists all segments in a playlist
* [List Sites Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/ListSitesHandler.java)
  * Lists [remote sites](#remote-sites)
* [Mark Unmark Local Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/MarkUnmarkLocalHandler.java)
* [Register Site Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/RegisterSiteHandler.java)
* [Remote Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/RemoteHandler.java)
  * Gives a list of video segments from our site that are remotely available, allowing other Star Trek video segment managers to use our video segments.
* [Remove Segment from Playlist Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/RemoveSegmentFromPlaylistHandler.java)
* [Unregister Site Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/UnregisterSiteHandler.java)
* [Upload Segment Handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/UploadSegmentHandler.java)
  * Uploads a segment to our S3 Bucket, makes it publicly available, and enters its data into the database.

### The Webpage

There are two landing pages: a [user page](https://github.com/mastlouis/CS3733-Lydia/blob/master/webpages/public.html) with [user privileges](#actions-for-users) and an [admin page](https://github.com/mastlouis/CS3733-Lydia/blob/master/webpages/admin.html) with [admin privileges](#actions-for-admins).

Each webpage is connected to a series of [scripts](https://github.com/mastlouis/CS3733-Lydia/tree/master/webpages/scripts) that send requests to the API gateway when the user interacts with buttons or forms. When those requests resolve, the scripts update the webpage to display the segments.

## The EBC Model

For this course, we learned the Entity Boundary Controller (EBC) Model, which is a framework for organizing large coding projects. The idea is to categorize objects as either an entity, boundary, or controller. Entity objects store the state of the system. Boundary objects make up the user interface and the actionable elements with which the user can interact. Controller objects manipulate the state of the system and update the boundary to show the new state to the user.

In this project, the entity layer is [the database](#the-database), the boundary layer is made from [the webpages](https://github.com/mastlouis/CS3733-Lydia/tree/master/webpages), and the controller layer is made from [the lambda functions](#aws-lambda) and [the scripts](https://github.com/mastlouis/CS3733-Lydia/tree/master/webpages/scripts).

<!-- A more simple example of the EBC model is the [individual project]() from the same Software Engineering course. -->

## What We Would Change

After completing this course, we agreed that there were a few things we would do differently if we could write the project all over again. 

### Standard Naming Conventions

One of the problems that slowed us down a bit and frustrated us is our lack of a standard naming convention. When we perform similar actions on different parts aspects of the project (and even different parts of a single aspect), we used different names.

For example, when we make a new playlist, we "create" the playlist with the "[create playlist handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/CreatePlaylistHandler.java)." When we make a new video segment, we "upload" the segment with the "[upload segment handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/UploadSegmentHandler.java)". When we make a new remote site, we "register" the site with "[unregister site handler](https://github.com/mastlouis/CS3733-Lydia/blob/master/LydiaAWS/src/main/java/com/amazonaws/lambda/demo/UnregisterSiteHandler.java)." Although these actions are different processes altogether, we could have used similar naming conventions to help abstract away the differences, which would make expanding the project much easier for a programmer who did not help write it (or who has not worked on it in a long time).

If we could make this project again, we would sit down with the design specification and devote a section of a meeting to listing out as many aspects of the project as we can think of and reaching an agreement on more standard names for these aspects.

### A More Effective Database

There are a few issues with our database that make managing data more cumbersome than it needs to be. 

We needed a unique identifier for each video segment, so we generated a [UUID](https://docs.oracle.com/javase/7/docs/api/java/util/UUID.html) for each segment. The queries we wrote for the database became dependent on the UUID for each video segment, which became difficult when we tried to register clips from [remote sites](#remote-sites).

We could have instead used the video segment's URL. Each video segment's URL must be unique, and this is one of the three pieces of information that remote sites give us about each of their remotely available segments. If we had used URL as the unique ID of each entry in the database, then we would not need to store remote segments in our table of segments, and we could just include them in the playlist entry table directly. This would have greatly simplified the data management of segments.

### Using Joins Rather Than Sub-queries

When selecting the entries of a given playlist from our table of playlist entires, we used a sub-query. One of the attributes in this table is the playlist entry number, which tracks the order of the segments in the playlist. We ordered our entries into the order in which they would appear in the playlist in the inner query, but the outer query discards this order.

If we could build this project again, we would use a join rather than a sub-query to preserve the order of the playlist entries.