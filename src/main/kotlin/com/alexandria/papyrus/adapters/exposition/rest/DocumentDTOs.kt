package com.alexandria.papyrus.adapters.exposition.rest

import com.alexandria.papyrus.adapters.exposition.rest.DocumentTypeView.Companion.toDocumentTypeView
import com.alexandria.papyrus.domain.model.Document

data class CreateDocumentRequest(val name: String, val folderIdentifier: String)

data class ChangeTypeRequest(val typeIdentifier: String)

data class ChangePredictedTypeRequest(val typeIdentifier: String)

data class ChangeStatusRequest(val status: String)

data class DocumentView(
    val identifier: String,
    val name: String,
    val parentFolderIdentifier: String,
    val type: DocumentTypeView?,
    val predictedType: DocumentTypeView?,
    val user: String,
    val status: String,
) {
    companion object {
        fun toDocumentView(document: Document): DocumentView =
            DocumentView(
                identifier = document.identifier,
                name = document.name,
                parentFolderIdentifier = document.parentFolder.identifier,
                type = if (document.type != null) toDocumentTypeView(document.type!!) else null,
                predictedType = if (document.predictedType != null) toDocumentTypeView(document.predictedType!!) else null,
                user = document.user,
                status = document.status,
            )
    }
}
