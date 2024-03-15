package com.apexnova.common.model

import java.time.Instant
import java.util.UUID
import org.springframework.data.cassandra.core.mapping.PrimaryKey

abstract class BaseModel {
    @PrimaryKey private var id: UUID = UUID.randomUUID()
    private var createdAt: Instant? = null
    private var createdBy: UUID? = null
    private var updatedAt: Instant? = null
    private var updatedBy: UUID? = null

    // Constructors, getters, and setters

    // Initialize createdAt and updatedAt to now in the constructor
    init {
        val now = Instant.now()
        this.createdAt = now
        this.updatedAt = now
    }

    fun getId(): UUID {
        return id
    }

    fun setId(id: UUID) {
        this.id = id
    }

    fun getCreatedAt(): Instant? {
        return createdAt
    }

    fun setCreatedAt(createdAt: Instant) {
        this.createdAt = createdAt
    }

    fun getCreatedBy(): UUID? {
        return createdBy
    }

    fun setCreatedBy(createdBy: UUID) {
        this.createdBy = createdBy
    }

    fun getUpdatedAt(): Instant? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: Instant) {
        this.updatedAt = updatedAt
    }

    fun getUpdatedBy(): UUID? {
        return updatedBy
    }

    fun setUpdatedBy(updatedBy: UUID) {
        this.updatedBy = updatedBy
    }

    // Method to call before saving (new entity)
    fun onCreate(creatorId: UUID) {
        val now = Instant.now()
        this.createdAt = now
        this.updatedAt = now // Same as createdAt on initial creation
        this.createdBy = creatorId
        this.updatedBy = creatorId // Creator is the updater at the time of creation
    }

    // Method to call before updating an existing entity
    fun onUpdate(updaterId: UUID) {
        this.updatedAt = Instant.now()
        this.updatedBy = updaterId
    }
}
