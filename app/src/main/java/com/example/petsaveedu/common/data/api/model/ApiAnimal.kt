package com.example.petsaveedu.common.data.api.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class ApiAnimal(
    @Json(name = "age") val age: String? = null,
    @Json(name = "attributes") val attributes: ApiAttributes? = null,
    @Json(name = "breeds") val breeds: ApiBreeds? = null,
    @Json(name = "coat") val coat: String? = null,
    @Json(name = "colors") val colors: ApiColors? = null,
    @Json(name = "contact") val contact: ApiContact? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "distance") val distance: Any? = null,
    @Json(name = "environment") val environment: ApiEnvironment? = null,
    @Json(name = "gender") val gender: String? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "_links") val links: Links? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "organization_id") val organizationId: String? = null,
    @Json(name = "photos") val photos: List<ApiPhotoSizes>? = null,
    @Json(name = "published_at") val publishedAt: String? = null,
    @Json(name = "size") val size: String? = null,
    @Json(name = "species") val species: String? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "tags") val tags: List<String>? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "videos") val videos: List<ApiVideoLink>? = null
)

@JsonClass(generateAdapter = true)
data class ApiAttributes(
    @Json(name = "declawed") val declawed: Boolean? = null,
    @Json(name = "house_trained") val houseTrained: Boolean? = null,
    @Json(name = "shots_current") val shotsCurrent: Boolean? = null,
    @Json(name = "spayed_neutered") val spayedNeutered: Boolean? = null,
    @Json(name = "special_needs") val specialNeeds: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class ApiBreeds(
    @Json(name = "mixed") val mixed: Boolean? = null,
    @Json(name = "primary") val primary: String? = null,
    @Json(name = "secondary") val secondary: String? = null,
    @Json(name = "unknown") val unknown: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class ApiColors(
    @Json(name = "primary") val primary: String? = null,
    @Json(name = "secondary") val secondary: Any? = null,
    @Json(name = "tertiary") val tertiary: Any? = null
)

@JsonClass(generateAdapter = true)
data class ApiContact(
    @Json(name = "address") val address: ApiAddress? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "phone") val phone: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiEnvironment(
    @Json(name = "cats") val cats: Boolean? = null,
    @Json(name = "children") val children: Boolean? = null,
    @Json(name = "dogs") val dogs: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "organization")
    val organization: ApiOrganization? = null,
    @Json(name = "self")
    val self: ApiSelf? = null,
    @Json(name = "type")
    val type: ApiType? = null
)

@JsonClass(generateAdapter = true)
data class ApiPhotoSizes(
    @Json(name = "full") val full: String? = null,
    @Json(name = "large") val large: String? = null,
    @Json(name = "medium") val medium: String? = null,
    @Json(name = "small") val small: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiVideoLink(
    @Json(name = "embed") val embed: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiAddress(
    @Json(name = "address1") val address1: Any? = null,
    @Json(name = "address2") val address2: Any? = null,
    @Json(name = "city") val city: String? = null,
    @Json(name = "country") val country: String? = null,
    @Json(name = "postcode") val postcode: String? = null,
    @Json(name = "state") val state: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiOrganization(
    @Json(name = "href") val href: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiSelf(
    @Json(name = "href") val href: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiType(
    @Json(name = "href") val href: String? = null
)