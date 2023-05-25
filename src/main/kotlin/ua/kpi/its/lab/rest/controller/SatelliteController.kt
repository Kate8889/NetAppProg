package ua.kpi.its.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.SatelliteRequest
import ua.kpi.its.lab.rest.dto.SatelliteResponse
import ua.kpi.its.lab.rest.svc.impl.SatelliteServiceImpl


@RestController
@RequestMapping("/api/satellite")
class SatelliteController {

    private final lateinit var satelliteServiceImpl: SatelliteServiceImpl;

    @Autowired
    fun SatelliteController(satelliteServiceImpl1: SatelliteServiceImpl) {
        this.satelliteServiceImpl = satelliteServiceImpl1;
    }

    @GetMapping("/{id}")
    fun getSatelliteById(@PathVariable Long id): ResponseEntity<SatelliteResponse>? {
        val satelliteResponse: SatelliteResponse = satelliteServiceImpl.getSatelliteById(id);
        if (satelliteResponse == null) {
            println("Satellite not found. Request id: " + id)
        }
        return ResponseEntity.ok(satelliteResponse);
    }

    @PostMapping("/")
    fun createSatellite(@Valid @RequestBody satelliteRequest: SatelliteRequest?): ResponseEntity<SatelliteResponse>? {
        val satelliteResponse: SatelliteResponse? = satelliteRequest?.let { satelliteServiceImpl.createSatellite(it) }
        return ResponseEntity.status(HttpStatus.CREATED).body<SatelliteResponse>(satelliteResponse)
    }

    @PutMapping("/{id}")
    fun updateSatelliteById(
        @PathVariable id: Long,
        @Valid @RequestBody satelliteRequest: SatelliteRequest?
    ): ResponseEntity<SatelliteResponse>? {
        val satelliteResponse: SatelliteResponse? = satelliteRequest?.let { satelliteServiceImpl.updateSatelliteById(id, it) }
        return ResponseEntity.ok<SatelliteResponse>(satelliteResponse)
    }

    @DeleteMapping("/{id}")
    fun deleteSatelliteById(@PathVariable id: Long): ResponseEntity<Void?>? {
        val isDeleted: Boolean = satelliteServiceImpl.deleteSatelliteById(id)
        if (!isDeleted) {
            println("Satellite with id '$id' not found")
        }
        return ResponseEntity.noContent().build()
    }
}