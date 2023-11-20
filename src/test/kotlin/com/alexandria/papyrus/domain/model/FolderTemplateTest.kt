package com.alexandria.papyrus.domain.model

import com.alexandria.papyrus.fakes.aDocumentType
import com.alexandria.papyrus.fakes.aFolderTemplate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FolderTemplateTest {
    @Test
    fun `a folderTemplate can be renamed`() {
        val folderTemplate = aFolderTemplate(name = "oldName")

        folderTemplate.rename("newName")

        Assertions.assertEquals("newName", folderTemplate.name)
    }

    @Test
    fun `a folderTemplate's associated document type can be changed`() {
        val oldDocumentType = aDocumentType(identifier = "oldDocumentTypeIdentifier")
        val folderTemplate = aFolderTemplate(associatedDocumentType = oldDocumentType)

        val newDocumentType = aDocumentType(identifier = "newDocumentTypeIdentifier")
        folderTemplate.changeAssociatedDocumentType(newDocumentType)

        Assertions.assertEquals(newDocumentType, folderTemplate.documentType)
    }

    @Test
    fun `a folderTemplate can be added to another folderTemplate`() {
        val folderTemplate = aFolderTemplate()
        val subFolderTemplate = aFolderTemplate()

        folderTemplate.addSubFolder(subFolderTemplate)

        org.assertj.core.api.Assertions.assertThat(folderTemplate.subFolders).contains(subFolderTemplate)
        org.assertj.core.api.Assertions.assertThat(subFolderTemplate.parentFolder).isEqualTo(folderTemplate)
    }

    @Test
    fun `a folderTemplate document type can be changed`() {
        val folderTemplate = aFolderTemplate()
        val documentType = aDocumentType()

        folderTemplate.changeAssociatedDocumentType(documentType)

        org.assertj.core.api.Assertions.assertThat(folderTemplate.documentType).isEqualTo(documentType)
    }
}