package com.example.composepractice.data.mapper

import com.example.composepractice.data.local.entity.PostEntity
import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRootItem

fun SocialPostInformationRootItem.toEntity(): PostEntity {
    return PostEntity(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}

fun PostEntity.toDomain(): SocialPostInformationRootItem {
    return SocialPostInformationRootItem(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}