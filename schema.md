# Planning the Schema

## Playlists

* playlistName PK, NN, UQ

## Segments

* id PK, NN, UQ
* playlistName (can be null)
* originFilePath (absolute filepath for remote segments, relative filepath for local segments)
* originSite (null for local sites)
* remotelyAvailable

## Registered Sites

* originSite PK, NN, UQ