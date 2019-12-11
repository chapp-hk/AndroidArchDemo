# Android Arch Demo


# Project Specification

  1. Project is developed using MVVM
  2. Programming language - KOTLIN
  3. App is fetching data from network and saving them into the DB for local caching.
  4. Then the app displays the list from cache and request from server if needed.


# Libraries used

  1. android architecture components
  2. android JetPack
  3. Retrofit
  4. Dagger2
  5. MockK  -- Mocking framework for testing
  6. Fresco
  7. Room
  8. RxJava
  9. Espresso
 10. Paging
 11. Navigation


# How to compile
  1. Android Studio (3.5.2)
  2. Android SDK (29)


# Layout Resource naming

  Prefer `timeline_list_fragment.xml` over `fragment_timeline_list.xml`.
  It is because it can group the related files together if it is sorted alphabetically.
  Also, The databinding codegen will generate `TimelineListFragmentBinding.java` which looks more natural than `FragmentTimelineListBinding.java`.


# Improvements / Not implemented

  1. UI test should cover more cases
  2. Can create code generator for MVVM boilerplate


# Misc

  1. Can use `./gradlew useLatestVersions` to update gradle dependency versions