package ua.kpi.its.lab.rest.svc.impl

import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.dto.SatelliteRequest
import ua.kpi.its.lab.rest.dto.SatelliteResponse
import ua.kpi.its.lab.rest.entity.Satellite
import ua.kpi.its.lab.rest.repository.SatelliteRepository
import ua.kpi.its.lab.rest.svc.SatelliteService

@Service
class SatelliteServiceImpl(private val satelliteRepository: SatelliteRepository) : SatelliteService {
    override fun createSatellite(satelliteRequest: SatelliteRequest): SatelliteResponse {
        val satellite = Satellite(name = satelliteRequest.name, country = satelliteRequest.country)
        val savedHospital = satelliteRepository.save(satellite)
        return SatelliteResponse.fromEntity(savedHospital)
    }

    override fun getSatelliteById(id: Long): SatelliteResponse {
        val satellite = satelliteRepository.findById(id).orElseThrow()
        return SatelliteResponse.fromEntity(satellite)
    }

    override fun updateSatelliteById(id: Long, satelliteRequest: SatelliteRequest): SatelliteResponse {
        val satellite = satelliteRepository.findById(id).orElseThrow()
        satellite.name = satelliteRequest.name
        satellite.country = satelliteRequest.country
        val newSatellite = satelliteRepository.save(satellite)
        return SatelliteResponse.fromEntity(newSatellite)
    }

    override fun deleteSatelliteById(id: Long): Boolean {
        satelliteRepository.deleteById(id)
        return true
    }
}