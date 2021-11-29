package hu.bme.mit.theta.restapi.api.workers

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.WorkerDto
import hu.bme.mit.theta.restapi.model.entities.Worker
import hu.bme.mit.theta.restapi.repository.WorkerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class WorkersApiServiceImpl(@Autowired val repository: WorkerRepository) : WorkersApiService {

    override suspend fun workersGet(): List<WorkerDto> = repository.findAll().map { WorkerDto(it) }

    override suspend fun workersIdDelete(id: Int): IdObjectDto {
        repository.deleteById(id)
        return IdObjectDto(id)
    }

    override suspend fun workersIdGet(id: Int): WorkerDto = WorkerDto(repository.findById(id).orElseThrow())

    override suspend fun workersIdPut(workerDto: WorkerDto): IdObjectDto = IdObjectDto(repository.save(Worker(workerDto)).id)

    override suspend fun workersPost(workerDto: WorkerDto): IdObjectDto = IdObjectDto(repository.save(Worker(workerDto)).id)
}
