package com.example.petsaveedu.common.domain.model.animal

data class Media(
    val photos: List<Photo>,
    val videos: List<Video>
) {

    data class Photo(
        val medium: String,
        val full: String
    ) {
        companion object {
            const val EMPTY_PHOTO = ""
        }

        fun getSmallestAvailablePhoto(): String {
            return when {
                isValidPhoto(medium) -> medium
                isValidPhoto(full) -> full
                else -> EMPTY_PHOTO
            }
        }

        private fun isValidPhoto(link: String): Boolean {
            return link.isNotEmpty()
        }
    }

    data class Video(
        val embed: String
    )
}