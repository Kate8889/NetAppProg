package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.SocketRequest
import ua.kpi.its.lab.rest.dto.SocketResponse
interface SocketService {
    fun createSocket(drugsRequest: SocketRequest): SocketResponse
    fun getSocketById(id: Long): SocketResponse
    fun updateSocketById(id: Long, drugsRequest: SocketRequest): SocketResponse
    fun deleteSocketById(id: Long): Boolean
}