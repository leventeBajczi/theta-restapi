package hu.bme.mit.theta.restapi.utils.iface

import hu.bme.mit.theta.restapi.model.dtos.ResourcesDto

interface ResourceUtils {
    fun getResources() : ResourcesDto
}