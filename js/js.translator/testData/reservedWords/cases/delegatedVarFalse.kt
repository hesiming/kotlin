package foo

// NOTE THIS FILE IS AUTO-GENERATED by the generateTestDataForReservedWords.kt. DO NOT EDIT!

interface Trait {
    var `false`: Int
}

class TraitImpl : Trait {
    override var `false`: Int = 0
}

class TestDelegate : Trait by TraitImpl() {
    fun test() {
        testNotRenamed("false", { `false` })
    }
}

fun box(): String {
    TestDelegate().test()

    return "OK"
}