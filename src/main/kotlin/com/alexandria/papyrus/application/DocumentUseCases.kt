package com.alexandria.papyrus.application

import com.alexandria.papyrus.domain.DocumentNotFoundException
import com.alexandria.papyrus.domain.FolderNotFoundException
import com.alexandria.papyrus.domain.IdGenerator
import com.alexandria.papyrus.domain.model.Document
import com.alexandria.papyrus.domain.repositories.DocumentRepository
import com.alexandria.papyrus.domain.repositories.FolderRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
class DocumentUseCases(
    private val idGenerator: IdGenerator,
    private val documentRepository: DocumentRepository,
    private val folderRepository: FolderRepository,
) {
    @Transactional(readOnly = true)
    fun findAllDocuments(): List<Document> {
        return documentRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun findByIdentifier(identifier: String): Document {
        return documentRepository.findByIdentifier(identifier) ?: throw DocumentNotFoundException(identifier)
    }

    fun createDocument(
        name: String,
        parentFolderIdentifier: String,
    ): String {
        val parentFolder =
            folderRepository.findByIdentifier(parentFolderIdentifier) ?: throw FolderNotFoundException(
                parentFolderIdentifier,
            )
        val document =
            Document(
                identifier = idGenerator.generate(),
                name = name,
                parentFolder = parentFolder,
            )
        documentRepository.save(document)
        return document.identifier
    }
}