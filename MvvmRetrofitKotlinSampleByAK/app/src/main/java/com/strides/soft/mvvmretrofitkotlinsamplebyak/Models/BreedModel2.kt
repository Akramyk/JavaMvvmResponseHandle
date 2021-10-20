package com.strides.soft.apptunixassignmentkotlin.Models

import com.google.gson.annotations.SerializedName

/*
data class BreedModel2(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("message")
var message: String? = null
)*/

data class BreedModel2(val status: String, val message: String) : List<BreedModel2> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: BreedModel2): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<BreedModel2>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): BreedModel2 {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: BreedModel2): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<BreedModel2> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: BreedModel2): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<BreedModel2> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<BreedModel2> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<BreedModel2> {
        TODO("Not yet implemented")
    }
}