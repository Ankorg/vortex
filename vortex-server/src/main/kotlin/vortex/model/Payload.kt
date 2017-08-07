package vortex.model

import ratpack.util.MultiValueMap

data class Payload (
    val body: String? = null,
    val headers: MultiValueMap<String, String>? = null,
    val status: Integer? = null,
    val contentType: String? =null
)
