package usecases.file

import model.entities.ApiKey
import repositories.FileMetadataRepository
import repositories.FileRepository

class FileEditUseCase(
    private val fileRepository: FileRepository,
    private val fileMetadataRepository: FileMetadataRepository
) {
    suspend operator fun invoke(apiKey: ApiKey){

    }
}