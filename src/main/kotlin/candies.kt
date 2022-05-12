fun main() {
    val inputs = listOf(
        intArrayOf(4, 6, 4, 5, 6, 2),
        intArrayOf(1, 2, 2),
        intArrayOf(2, 4, 2, 6, 1, 7, 8, 9, 2, 1),
        intArrayOf(2, 4, 3, 5, 2, 6, 4, 5),
    )

    inputs.forEach {
        val count = CandiesHelper.getCandyCount(it, it.size)
        println("we need $count candies")
        println()
    }
}

//Problem: If two children sit next to each other, then the one with the higher rating must get more candies.
object CandiesHelper {

    fun getCandyCount(ratings: IntArray, childCount: Int): Int {
        //optimizing for the case when there is just one child
        if (childCount == 1) {
            return 1
        }

        //initially giving one candy to each child.
        val candies = IntArray(childCount) {
            1
        }

        ratings.forEachIndexed { index, currentChildRating ->
            //finding neighboring ratings and candy count
            val (beforeRating, afterRating) = getNeighbours(index, ratings)
            val (beforeCandy, afterCandy) = getNeighbours(index, candies)

            //comparing ratings with neighbours
            if (currentChildRating > beforeRating) {
                val updatedRating = beforeCandy + 1
                candies[index] = updatedRating
            }
            if (currentChildRating > afterRating) {
                val updatedRating = afterCandy + 1
                candies[index] = updatedRating
            }
            if (currentChildRating > beforeRating && currentChildRating > afterRating) {
                val updatedRating = maxOf(beforeCandy, afterCandy) + 1
                candies[index] = updatedRating
            }
        }
        return candies.sum()
    }

    /*** Returns both neighbours of the element at [index] in [items] array.
     *
     * In the boundary conditions when the element does not have any surrounding neighbour,
     * then returns the value of the element at [index] instead.
     */
    private fun getNeighbours(index: Int, items: IntArray): Pair<Int, Int> {
        val placeHolder = items[index]
        //boundary conditions
        if (index == 0) { //first element
            return placeHolder to items[1]
        } else if (index == items.size - 1) { //last element
            return items[index - 1] to placeHolder
        }

        return items[index - 1] to items[index + 1]
    }
}
