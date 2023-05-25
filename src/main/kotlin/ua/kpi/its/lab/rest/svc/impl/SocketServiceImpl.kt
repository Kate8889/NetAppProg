package ua.kpi.its.lab.rest.svc.impl

import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.dto.SocketRequest
import ua.kpi.its.lab.rest.dto.SocketResponse
import ua.kpi.its.lab.rest.entity.Socket
import ua.kpi.its.lab.rest.repository.SocketRepository
import ua.kpi.its.lab.rest.svc.SocketService
import org.springframework.security.access.prepost.PreAuthorize

@Service
class SocketServiceImpl(private val socketRepository: SocketRepository) : SocketService {
    @PreAuthorize("hasAuthority('EDITOR')")
    override fun createSocket(socketRequest: SocketRequest): SocketResponse {
        val socket = Socket(name = socketRequest.name, manufacturer = socketRequest.manufacturer)
        val newSocket = socketRepository.save(socket)
        return SocketResponse.fromEntity(newSocket)
    }

    @PreAuthorize("hasAuthority('VIEWER')")
    override fun getSocketById(id: Long): SocketResponse {
        val socket = socketRepository.findById(id).orElseThrow()
        return SocketResponse.fromEntity(socket)
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    override fun updateSocketById(id: Long, socketRequest: SocketRequest): SocketResponse {
        val socket = socketRepository.findById(id).orElseThrow()
        socket.name = socketRequest.name
        socket.manufacturer = socketRequest.manufacturer
        val newSocket = socketRepository.save(socket)
        return SocketResponse.fromEntity(newSocket)
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    override fun deleteSocketById(id: Long): Boolean {
        socketRepository.deleteById(id)
        return true
    }
}