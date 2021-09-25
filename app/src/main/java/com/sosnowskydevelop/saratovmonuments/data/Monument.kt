package com.sosnowskydevelop.saratovmonuments.data

data class Monument(
    private val _id: Long,
    private val _categoryId: Long,
    private val _name: String,
    private val _photoName: String,
    private val _installationDate: String,
    private val _authors: String,
    private val _description: String,
    private val _links: String,
    private val _pointLatitude: Double,
    private val _pointLongitude: Double,
) {
    val id: Long get() = _id
    val categoryId: Long get() = _categoryId
    val name: String get() = _name
    val photoName: String get() = _photoName
    val installationDate: String get() = _installationDate
    val authors: String get() = _authors
    val description: String get() = _description
    val links: String get() = _links
    val pointLatitude: Double get() = _pointLatitude
    val pointLongitude: Double get() = _pointLongitude
}