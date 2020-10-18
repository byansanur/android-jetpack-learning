package com.byandev.subjetpackpro1.data

data class MovieEntity (
    var movieId: String,
    var title: String,
    var description: String,
    var genre: String,
    var releaseDate: String,
    var imgPath: String,
    var url: String,
    var poster1: String?,
    var poster2: String?,
    var poster3: String?
)