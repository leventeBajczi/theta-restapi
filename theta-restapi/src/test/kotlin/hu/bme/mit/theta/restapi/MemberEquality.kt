package hu.bme.mit.theta.restapi

import kotlin.reflect.KProperty

inline fun <reified T, reified R> membersEqual(obj1: T, obj2: R) : Boolean {
    val firstProps = T::class.members.map { it as? KProperty }.filterNotNull()
    val secondProps = R::class.members.map { it as? KProperty }.filterNotNull()
    for(firstProp in firstProps) {
        for (secondProp in secondProps) {
            if(secondProp.name == firstProp.name) {
                val obj1Prop = firstProp.call(obj1)
                val obj2Prop = secondProp.call(obj2)
                if(obj1Prop != null && obj2Prop != null && obj1Prop != obj2Prop)
                    return false
                break
            }
        }
    }
    return true
}
