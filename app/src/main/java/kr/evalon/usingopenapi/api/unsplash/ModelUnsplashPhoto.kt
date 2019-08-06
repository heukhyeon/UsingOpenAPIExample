package kr.evalon.usingopenapi.api.unsplash

class ModelUnsplashPhoto(
    val id:String,
    val created_at:String,
    val updated_at:String,
    val width: Double,
    val height: Double,
    val color:String,
    val alt_description:String,
    val urls:ImageUrl,
    val links:ImageLink,
    val categories: List<Any>,
    val likes:Double,
    val liked_by_user:Boolean,
    val current_user_collection:List<Any>,
    val user:ModelUser
) {
    data class ImageUrl(
        val raw:String,
        val full:String,
        val regular:String,
        val small:String,
        val thumb:String
    )

    data class ImageLink(
        val self:String,
        val html:String,
        val download:String,
        val download_location:String
    )

}