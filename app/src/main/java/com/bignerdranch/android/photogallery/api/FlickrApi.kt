package com.bignerdranch.android.photogallery.api

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "your api"

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s" +
                "&per_page=100"
    )
    suspend fun fetchPhotos(@Query("page") page: Int): FlickrResponse
}

