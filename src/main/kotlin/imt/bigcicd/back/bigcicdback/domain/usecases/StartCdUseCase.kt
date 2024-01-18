package imt.bigcicd.back.bigcicdback.domain.usecases

import imt.bigcicd.back.bigcicdback.domain.Pipeline
import imt.bigcicd.back.bigcicdback.domain.utils.UseCase
import imt.bigcicd.back.bigcicdback.domain.models.RepositoryReq
import imt.bigcicd.back.bigcicdback.output.services.PipelineService
import org.springframework.stereotype.Component

@Component
class StartCdUseCase(
    val pipelineService: PipelineService
) : UseCase<RepositoryReq, Unit> {
    override fun command(request: RepositoryReq) {
        val (repository, tag) = request
        pipelineService.savePipeline(
            Pipeline(
                ref = tag,
                user = "", //TODO get user
                repository = repository,
            )
        ).let {
            pipelineService.startCd(
                it.id,
                it.repository,
                it.ref
            )
        }
    }
}