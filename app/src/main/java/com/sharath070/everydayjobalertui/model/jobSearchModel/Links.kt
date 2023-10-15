package com.sharath070.everydayjobalertui.model.jobSearchModel

data class Links(
    val about: List<About>,
    val author: List<Author>,
    val collection: List<Collection>,
    val curies: List<Cury>,
    val predecessor-version: List<PredecessorVersion>,
    val replies: List<Reply>,
    val self: List<Self>,
    val version-history: List<VersionHistory>,
    val wp:attachment: List<WpAttachment>,
    val wp:term: List<WpTerm>
)