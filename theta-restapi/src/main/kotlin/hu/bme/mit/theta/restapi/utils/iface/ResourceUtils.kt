package hu.bme.mit.theta.restapi.utils.iface

import hu.bme.mit.theta.restapi.model.dtos.output.OutStaticResourcesDto

interface ResourceUtils {
    fun getResources() : OutStaticResourcesDto
}