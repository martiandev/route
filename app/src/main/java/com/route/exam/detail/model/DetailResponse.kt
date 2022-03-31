package com.route.exam.detail.model

import com.google.gson.annotations.SerializedName
import java.util.jar.Attributes

data class DetailResponse(
    @SerializedName("data")
    val data:Data,
    @SerializedName("included")
    val included:List<Data>
)
{

}

data class Data(
    @SerializedName("id")
    val id:String?,
    @SerializedName("type")
    val type:String?,
    @SerializedName("attributes")
    val attribute:Attribute?,
    @SerializedName("relationships")
    val relationships:Relationships?
){
    companion object {
        val TYPE_ROUTES = "routes"
        val TYPE_GROUPS = "groups"
        val TYPE_ROUTE_POINTS = "routepoints"
        val TYPE_CATEGORIES = "categories"
        val TYPE_IMAGES = "images"
        val TYPE_POIS = "pois"
        val TYPE_BICYCLE_JUNCTION = "BICYCLE_JUNCTION"
        val TYPE_POI_CATEGORY = "POI_CATEGORY"
    }

}



data class Attribute(
    @SerializedName("name")
    val name:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("shortDescription")
    val shortDescription:String?,
    @SerializedName("published")
    val isPublished:Boolean?,
    @SerializedName("totalDistance")
    val totalDistance:Int?,
    @SerializedName("totalTime")
    val totalTime:Double?,
    @SerializedName("transportationMode")
    val transportationMode:String?,
    @SerializedName("viewCount")
    val viewCount:Int?,
    @SerializedName("favoritesCount")
    val favoritesCount:Int?,
    @SerializedName("averageRating")
    val averageRating:Int?,
    @SerializedName("ratingCount")
    val ratingCount:Int?,
    @SerializedName("isRoundTrip")
    val isRoundTrip:Boolean?,
    @SerializedName("routeMapImgUrl")
    val routeMapImgUrl:String?,
    @SerializedName("elevationMeters")
    val elevationMeters:Int?,
    @SerializedName("street")
    val street:String?,
    @SerializedName("postalCode")
    val postalCode:String?,
    @SerializedName("state")
    val state:String?,
    @SerializedName("city")
    val city:String?,
    @SerializedName("country")
    val country:String?,
    @SerializedName("latitude")
    val latitude:Double?,
    @SerializedName("longitude")
    val longitude:Double?,
    @SerializedName("index")
    val index:Int?,
    @SerializedName("URL")
    val url:String?,
    @SerializedName("Copyright")
    val copyRight:String?,
    @SerializedName("Alt-tekst")
    val alternateText:String?,
){
    companion object{
        val TRANSPO_BIKE = "BICYCLING"
    }
}

data class Relationships(
    @SerializedName("group")
    val group: Group?,
    @SerializedName("startPoint")
    val startPoint: StartPoint?,
    @SerializedName("routePoints")
    val routePoints: RoutePoints?,
    @SerializedName("categories")
    val categories: Categories?,
    @SerializedName("mainImage")
    val mainImage: MainImage?,
    @SerializedName("images")
    val images: Images?,
    @SerializedName("poi")
    val poi: POI?,
)

data class Group(
    @SerializedName("data")
    val data:Data
)

data class StartPoint(
    @SerializedName("data")
    val data:Data
)


data class RoutePoints(
    @SerializedName("data")
    val data:List<Data>
)

data class Categories(
    @SerializedName("data")
    val data:List<Data>
)

data class MainImage(
    @SerializedName("data")
    val data:Data
)
data class POI(
    @SerializedName("data")
    val data:Data
)

data class Images(
    @SerializedName("data")
    val data:List<Data>
)
