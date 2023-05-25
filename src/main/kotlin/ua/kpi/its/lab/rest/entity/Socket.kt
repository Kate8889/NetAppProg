package ua.kpi.its.lab.rest.entity
import java.time.LocalDate

data class Socket(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val name: String,
    val manufacturer: String,
    val numOfCores: Int,
    val frequency: Int,
    val connector: String,
    val productionDate: LocalDate,
    val mmxSupport: Boolean,
    @ManyToOne
    @JoinColumn(name = "satellite_id")
    val satellite: Satellite
) : Comparable<Socket> {
    override fun compareTo(socket: Socket): Int {
        val compare = name.compareTo(socket.name)
        return if (compare != 0) compare else name.compareTo(socket.name) + manufacturer.compareTo(socket.manufacturer)
    }
}