import java.time.LocalDate

data class Socket(
    val name: String,
    val manufacturer: String,
    val numOfCores: Int,
    val frequency: Int,
    val connector: String,
    val productionDate: LocalDate,
    val mmxSupport: Boolean
) : Comparable<Socket> {
    override fun compareTo(socket: Socket): Int {
        val compare = name.compareTo(socket.name)
        return if (compare != 0) compare else name.compareTo(socket.name) + manufacturer.compareTo(socket.manufacturer)
    }
}