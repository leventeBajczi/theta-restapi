package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.exceptions.ThetaRESTException
import hu.bme.mit.theta.restapi.exceptions.Unauthorized
import org.springframework.http.ResponseEntity

inline fun <S> handleRESTStatus(f: () -> S) : ResponseEntity<S> {
    try {
        return ResponseEntity.ok(f())
    } catch (e: ThetaRESTException) {
        when(e) {
            is NoSuchElement -> return ResponseEntity.status(404).build()
            is Unauthorized -> return ResponseEntity.status(401).build()
        }
    } catch (e: Exception) {
        return ResponseEntity.internalServerError().build()
    }
}