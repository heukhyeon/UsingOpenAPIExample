package kr.evalon.usingopenapi.api.unsplash

import androidx.recyclerview.widget.DiffUtil

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

    override fun equals(other: Any?): Boolean {
        return other is ModelUnsplashPhoto && other.id == id
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + created_at.hashCode()
        result = 31 * result + updated_at.hashCode()
        result = 31 * result + width.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + alt_description.hashCode()
        result = 31 * result + urls.hashCode()
        result = 31 * result + links.hashCode()
        result = 31 * result + categories.hashCode()
        result = 31 * result + likes.hashCode()
        result = 31 * result + liked_by_user.hashCode()
        result = 31 * result + current_user_collection.hashCode()
        result = 31 * result + user.hashCode()
        return result
    }
}