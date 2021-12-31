package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.exceptions.CommandException
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.exceptions.ThetaRESTException
import hu.bme.mit.theta.restapi.exceptions.Unauthorized
import org.springframework.http.ResponseEntity

inline fun <S> handleRESTStatus(f: () -> S) : ResponseEntity<*> {
    try {
        return ResponseEntity.ok(f())
    } catch (e: ThetaRESTException) {
        e.printStackTrace()
        return when(e) {
            is NoSuchElement -> ResponseEntity.status(404).body("Element Not found.")
            is Unauthorized -> ResponseEntity.status(401).body("Unauthorized.")
            is CommandException -> ResponseEntity.status(500).body("Command error:\n${e.printStackTrace()}")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return ResponseEntity.internalServerError().body(e.printStackTrace())
    }
}