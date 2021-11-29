package hu.bme.mit.theta.restapi.utils.iface

import hu.bme.mit.theta.restapi.model.dtos.StaticResourcesDto

interface ResourceUtils {
    fun getResources() : StaticResourcesDto
}