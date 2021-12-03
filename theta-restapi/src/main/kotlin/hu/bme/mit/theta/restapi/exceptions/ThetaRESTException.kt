package hu.bme.mit.theta.restapi.exceptions

sealed class ThetaRESTException(msg: String? = null) : Exception(msg)