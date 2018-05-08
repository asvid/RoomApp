package asvid.github.io.roomapp.utils

import java.util.*

fun Collection<Any>?.getRandomElement(): Any? {
    val rnd = Random()
    val i = rnd.nextInt(this?.size ?: 0)
    return this?.elementAt(i)
}