package com.ozantopuz.dicetask.shared.dispatcher

import com.ozantopuz.dicetask.shared.extension.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import javax.net.ssl.HttpsURLConnection

class SuccessDispatcher(private val fileName: String) : Dispatcher() {

    private val endpoint = "artist"
    private val responsePrefix = "response"

    override fun dispatch(request: RecordedRequest): MockResponse {
        val path = request.path
        return if (path.toString().contains(endpoint)) {
            val response = asset("$responsePrefix/$fileName")
            if (response.isNullOrEmpty()) MockResponse().setResponseCode(HttpsURLConnection.HTTP_NOT_FOUND)
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_OK).setBody(response!!)
        } else {
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_NOT_FOUND)
        }
    }
}
