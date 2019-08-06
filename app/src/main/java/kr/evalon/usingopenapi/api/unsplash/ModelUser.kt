package kr.evalon.usingopenapi.api.unsplash

class ModelUser(
    val id:String,
    val updated_at:String,
    val username: String,
    val name:String,
    val first_name:String,
    val last_name:String,
    val portfolio_url:String,
    val bio:String,
    val location:String,
    val links:Link,
    val profile_image:ProfileImage,
    val instagram_username: String,
    val total_collections: Double,
    val total_likes: Double,
    val total_photos: Double,
    val accepted_tos: Boolean
) {
    data class Link(
        val self:String,
        val html:String,
        val photos:String,
        val likes:String,
        val portfolio:String,
        val following:String,
        val followers:String
    )
    data class ProfileImage(
        val small:String,
        val medium:String,
        val large:String
        )

}