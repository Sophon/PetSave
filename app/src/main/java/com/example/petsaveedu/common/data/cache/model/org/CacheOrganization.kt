package com.example.petsaveedu.common.data.cache.model.org

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.petsaveedu.common.domain.model.organization.Organization


@Entity(tableName = "organizations")
data class CacheOrganization(
    @PrimaryKey(autoGenerate = false) val organizationId: String,

    val email: String,
    val phone: String,

    val address1: String,
    val address2: String,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String,

    val distance: Float
) {
    companion object {
        fun fromDomain(organization: Organization): CacheOrganization {
            return CacheOrganization(
                organizationId = organization.id,
                email = organization.contact.email,
                phone = organization.contact.phone,
                address1 = organization.contact.address.address1,
                address2 = organization.contact.address.address2,
                city = organization.contact.address.city,
                state = organization.contact.address.state,
                postcode = organization.contact.address.postcode,
                country = organization.contact.address.country,
                distance = organization.distance
            )
        }
    }

    fun toDomain(): Organization {
        return Organization(
            id = organizationId,
            contact = Organization.Contact(
                email = email,
                phone = phone,
                address = Organization.Address(
                    address1 = address1,
                    address2 = address2,
                    city = city,
                    state = state,
                    postcode = postcode,
                    country = country
                )
            ),
            distance = distance
        )
    }
}
