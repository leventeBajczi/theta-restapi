package hu.bme.mit.theta.restapi.api.workers

import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.input.InWorkerDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutWorkerDto
import hu.bme.mit.theta.restapi.model.entities.Worker
import hu.bme.mit.theta.restapi.repository.WorkerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class WorkersApiServiceImpl(@Autowired val repository: WorkerRepository) : WorkersApiService {

    override suspend fun workersGet(): List<OutWorkerDto> = repository.findAll().map { OutWorkerDto(it) }

    override suspend fun workersIdDelete(id: Int): IdObjectDto {
        repository.deleteById(id)
        return IdObjectDto(id)
    }

    override suspend fun workersIdGet(id: Int): OutWorkerDto = OutWorkerDto(repository.findById(id).orElseThrow{ NoSuchElement() })

    override suspend fun workersIdPut(workerDto: InWorkerDto, id: Int): IdObjectDto = IdObjectDto(repository.save(Worker(workerDto, id)).id)

    override suspend fun workersPost(workerDto: InWorkerDto): IdObjectDto = IdObjectDto(repository.save(Worker(workerDto)).id)
}
