package ua.kpi.its.lab.rest.entity
import java.time.LocalDate

data class Satellite(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val name: String,
    val country: String,
    val launchDate: LocalDate,
    val appointment: String,
    val weight: Int,
    val height: Int,
    val geostationary: Boolean,
    @OneToMany(mappedBy = "satellite", cascade = [CascadeType.ALL], orphanRemoval = true)
    val socket: Socket
) : Comparable<Satellite> {
    override fun compareTo(satellite: Satellite): Int {
        val compare = name.compareTo(satellite.name)
        return if (compare != 0) compare else socket.compareTo(satellite.socket)
    }
}