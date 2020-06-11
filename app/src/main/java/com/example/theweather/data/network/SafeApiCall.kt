package com.example.theweather.data.network

import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.lang.Exception


/**\
 * This abstract class is going to be used in the apps repository for when we make api calls
 *
 */

abstract class SafeApiCall {

    suspend fun <T : Any> WeatherApiCall(call: suspend () -> Response<T>): T {
        val apiResponse = call.invoke()
        val errorMessage = StringBuilder()
        val apiError = apiResponse.errorBody()?.string()

        // if the api call was successful we want to return the response body
        if (apiResponse.isSuccessful) {
            return apiResponse.body()!!
        } else {

            // if the api call was not successful we want to return the response.errorBody
            apiError?.let {
                try {
                    errorMessage.append(JSONObject(apiError).getString("message"))
                }catch (e: Exception){}

            }

            // if the message from the error body is empty we still want the errorMessage to say something
            errorMessage.append("Both the response.body and response.errorBody were empty")
            throw IOException(errorMessage.toString())
        }
        }

}