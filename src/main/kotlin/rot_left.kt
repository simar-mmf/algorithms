fun main() {
    val result = rotLeft(intArrayOf(1, 2, 3, 4, 5) , 4)
    println(result.joinToString())
}

fun rotLeft(a: IntArray, d: Int): IntArray {
    val arraySize = a.size
    //using list comprehension
    return IntArray(arraySize) { index: Int ->
        val rotatedIndex = (index + d) % arraySize
        a[rotatedIndex]
    }
}