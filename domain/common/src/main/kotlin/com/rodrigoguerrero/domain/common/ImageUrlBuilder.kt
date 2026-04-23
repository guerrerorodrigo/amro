package com.rodrigoguerrero.domain.common

import javax.inject.Inject

/**
 * Builds the URL for the images
 */
class ImageUrlBuilder @Inject constructor() {
    /**
     * Builds the image URL for the provided [path] and [imageSize]
     * @param path the path of the image
     * @param imageSize the [ImageSize] of the image
     */
    fun buildUrl(path: String, imageSize: ImageSize): String {
        return "$IMAGE_BASE_URL${imageSize.size}$path"
    }
}
