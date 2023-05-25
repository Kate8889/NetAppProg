package ua.kpi.its.lab.rest.repository

import ua.kpi.its.lab.rest.entity.Satellite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SatelliteRepository : JpaRepository<Satellite, Long>