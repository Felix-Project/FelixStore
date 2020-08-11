package com.felix.lib_tools.util.list

fun <T, R : Iterator<T>> R.doOnItem(operation: (T) -> Unit): R {
    while (hasNext()) {
        operation(next())
    }
    return this
}

fun <T, R : List<T>> R.doOnItem(operation: (T) -> Unit): R {
    this.forEach {
        operation(it)
    }
    return this
}

fun <T, R : MutableIterator<T>> R.deleteIf(condition: (T) -> Boolean): R {
    while (hasNext()) {
        if (condition(next())) {
            remove()
        }
    }
    return this
}

fun <T, R : MutableList<T>> R.deleteIf(condition: (T) -> Boolean): R {
    iterator().run {
        while (hasNext()) {
            if (condition(next())) {
                remove()
            }
        }
    }
    return this
}