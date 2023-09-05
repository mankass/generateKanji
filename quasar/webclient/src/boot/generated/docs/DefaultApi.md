# DefaultApi

All URIs are relative to *https://generateKanji*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createWord**](DefaultApi.md#createWord) | **POST** /v1/examples/word | POST v1/examples/word
[**deleteALl**](DefaultApi.md#deleteALl) | **POST** /v1/examples/delete | POST v1/examples/delete
[**generateAll**](DefaultApi.md#generateAll) | **POST** /v1/examples/generateAll | POST v1/examples/generateAll
[**generateToday**](DefaultApi.md#generateToday) | **GET** /v1/examples/generate-today | GET v1/examples/generate-today
[**getAllWords**](DefaultApi.md#getAllWords) | **GET** /v1/examples/get-all | GET v1/examples/get-all
[**getAllWordsToday**](DefaultApi.md#getAllWordsToday) | **GET** /v1/examples/today | GET v1/examples/today


<a id="createWord"></a>
# **createWord**
> createWord(wordView)

POST v1/examples/word

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import com.example.generatekanji.application.controllers.*

val apiInstance = DefaultApi()
val wordView : WordView =  // WordView | 
try {
    apiInstance.createWord(wordView)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#createWord")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#createWord")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **wordView** | [**WordView**](WordView.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a id="deleteALl"></a>
# **deleteALl**
> deleteALl()

POST v1/examples/delete

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import com.example.generatekanji.application.controllers.*

val apiInstance = DefaultApi()
try {
    apiInstance.deleteALl()
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#deleteALl")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#deleteALl")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a id="generateAll"></a>
# **generateAll**
> ResponseEntity generateAll()

POST v1/examples/generateAll

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import com.example.generatekanji.application.controllers.*

val apiInstance = DefaultApi()
try {
    val result : ResponseEntity = apiInstance.generateAll()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#generateAll")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#generateAll")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a id="generateToday"></a>
# **generateToday**
> ResponseEntity generateToday()

GET v1/examples/generate-today

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import com.example.generatekanji.application.controllers.*

val apiInstance = DefaultApi()
try {
    val result : ResponseEntity = apiInstance.generateToday()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#generateToday")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#generateToday")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a id="getAllWords"></a>
# **getAllWords**
> kotlin.collections.List&lt;WordData&gt; getAllWords()

GET v1/examples/get-all

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import com.example.generatekanji.application.controllers.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.collections.List<WordData> = apiInstance.getAllWords()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getAllWords")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getAllWords")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;WordData&gt;**](WordData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a id="getAllWordsToday"></a>
# **getAllWordsToday**
> kotlin.collections.List&lt;WordData&gt; getAllWordsToday()

GET v1/examples/today

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import com.example.generatekanji.application.controllers.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.collections.List<WordData> = apiInstance.getAllWordsToday()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getAllWordsToday")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getAllWordsToday")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;WordData&gt;**](WordData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

