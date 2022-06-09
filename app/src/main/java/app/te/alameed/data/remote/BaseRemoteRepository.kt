package app.te.alameed.data.remote

interface BaseRemoteRepository {
  suspend fun clearPreferences()
}