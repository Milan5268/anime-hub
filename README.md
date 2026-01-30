AnimeHub is a simple Android application that fetches and displays popular anime using the Jikan API, with support for offline access, and a clean MVVM architecture built using modern Android tools.



Assumptions Made:

The Jikan API is treated as the primary remote data source.

The room database is used as the single source of truth to support offline-first behavior.

Anime trailers provided by the API are YouTube embed links, which cannot be played inline due to YouTube restrictions.

As a result, trailers are opened externally in the YouTube app or browser using the youtube_id.

Cached data may become stale if the device remains offline for a long period.

Pagination and search are considered out of scope for this assignment.


Features:

Anime List (Home Screen)

  Fetches top anime from Jikan API

  Displays: Title, Number of episodes, Rating, Poster image

  Offline support using cached data

  Empty-state message when no data is available

Anime Detail Screen:

  Displays: Poster with play button, Title, Plot/Synopsis, Main cast, Episodes, Rating
    
  Works fully offline using cached detail data

Offline & Sync:

  The app works without an internet connection
  
  Automatically refreshes data when the network becomes available
  
  Snackbar notifications when switching between offline and online states


Architecture & Tech Stack:

  MVVM architecture
  
  Repository pattern with offline-first approach
  
  Retrofit for API calls
  
  Room for local persistence
  
  Hilt for dependency injection
  
  StateFlow for reactive state management
  
  Jetpack Compose for UI
  
  Clean separation of concerns and test-friendly design



Known Limitations:

  No pagination or infinite scrolling for anime list
  
  No search or filter functionality
  
  Trailer playback is external due to YouTube streaming restrictions
  
  No manual refresh button (refresh happens automatically on reconnect)
  
  UI is optimized for phones; tablet-specific layouts are not implemented
  
  Minimal animations to prioritize performance and stability


Demo:

https://github.com/Milan5268/anime-hub/blob/master/Demo%20(1).mp4
