package com.alexandria.papyrus.adapters.exposition.rest

import com.alexandria.papyrus.adapters.exposition.rest.DocumentView.Companion.toDocumentView
import com.alexandria.papyrus.domain.model.Folder

data class FolderView(
    val identifier: String,
    val name: String,
    val associatedDocumentType: String?,
)

data class DetailedFolderView(
    val identifier: String,
    val name: String,
    val parentFolderIdentifier: String?,
    val associatedDocumentType: String?,
    val documents: List<DocumentView>,
    val subFolders: List<DetailedFolderView>,
) {
    companion object {
        fun toDetailedFolderView(folder: Folder): DetailedFolderView {
            return DetailedFolderView(
                identifier = folder.identifier,
                name = folder.name,
                parentFolderIdentifier = folder.parentFolder?.identifier,
                associatedDocumentType = folder.associatedDocumentType?.identifier,
                documents = folder.documents.map { toDocumentView(it) },
                subFolders = folder.subFolders.map { toDetailedFolderView(it) },
            )
        }
    }
}

data class CreateFolderFromTemplateRequest(
    val templateIdentifier: String,
)
