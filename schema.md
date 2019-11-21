# Planning the Schema

## Playlists

* playlistName PK, NN, UQ

## Segments

* id PK, NN, UQ (generated with UUID)
* originFilePath (absolute filepath for remote segments, relative filepath for local segments)
* originSite (null for local sites)
* remotelyAvailable
* character
* sentence

## PlaylistEntries

* segmentID PK, UQ
* playlistName PK
* playlistEntryNumber

## Registered Sites

* originSite PK, NN, UQ