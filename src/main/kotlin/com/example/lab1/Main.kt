import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.time.LocalDate

fun main() {
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)
    val satelliteContainer = context.getBean(Container::class.java) as SatelliteContainer

    val explorerSocket = Socket("Explorer", "JPL", 8, 100, "base", LocalDate.of(1957, 1, 1), false)
    val osumiSocket = Socket("Osumi", "ISAS", 8, 100, "base", LocalDate.of(1968, 1, 1), false)

    satelliteContainer.add(
        Satellite(
            "Explorer",
            "USA",
            LocalDate.of(1958, 2, 1),
            "land survey",
            14,
            200,
            true,
            explorerSocket
        ),
    )

    satelliteContainer.add(
        Satellite(
            "Osumi",
            "Japan",
            LocalDate.of(1970, 2, 11),
            "Observation of the ionosphere, the Sun and energetic particles",
            24,
            350,
            true,
            osumiSocket
        ),
    )
}