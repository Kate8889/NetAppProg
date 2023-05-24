interface Container<T> {
    fun add(element: T)

    fun get(index: Int): T

    fun getAll(): List<T>

    fun update(index: Int, element: T)

    fun remove(index: Int)
}


class SatelliteContainer : Container<Satellite> {
    private val satellites: MutableList<Satellite> = mutableListOf()

    override fun add(element: Satellite) {
        satellites.add(element)
    }

    override fun get(index: Int): Satellite {
        return satellites[index]
    }

    override fun getAll(): List<Satellite> {
        return satellites
    }

    override fun update(index: Int, element: Satellite) {
        satellites[index] = element
    }

    override fun remove(index: Int) {
        satellites.removeAt(index)
    }
}