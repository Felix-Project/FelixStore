package com.felix.lib_store.base.util

import java.util.regex.Pattern

val AppUrl: String
    get() = "http://app.mi.com"

val AdmPkg: String
    get() = "com.dv.adm.pay"

val <T> T.CommentNumPattern: Pattern by lazy {
    Pattern.compile("\\d+")
}
val <T> T.AppSizePattern: Pattern by lazy {
    Pattern.compile("\\s*[\\d,]+(.[0-9]{0,2})?\\s*[kKmMgG]\\s*")
}

val <T> T.VersionPattern: Pattern by lazy {
    Pattern.compile("[1-9]\\d*(.\\d+)*")
}

val <T> T.DatePattern: Pattern by lazy {
    Pattern.compile("\\d{1,4}[-]\\d{1,2}[-]\\d{1,2}")
}
val <T> T.PkgPattern: Pattern by lazy {
    Pattern.compile("\\w+(.\\w+)*")
}