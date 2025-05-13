package usecases.file

import model.entities.ApiKey
import repositories.FileMetadataRepository
import repositories.FileRepository
import java.io.InputStream

class FileGetUseCase(
    private val fileRepository: FileRepository,
    private val fileMetadataRepository: FileMetadataRepository
) {
    suspend operator fun invoke(apiKey: ApiKey){

    }
}