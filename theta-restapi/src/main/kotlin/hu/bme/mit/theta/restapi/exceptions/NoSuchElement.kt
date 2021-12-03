package hu.bme.mit.theta.restapi.exceptions

class NoSuchElement(val msg: String = "") : NoSuchElementException(msg), ThetaRESTException {
}