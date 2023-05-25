package ua.kpi.its.lab.rest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.SocketRequest
import ua.kpi.its.lab.rest.dto.SocketResponse
import ua.kpi.its.lab.rest.svc.impl.SocketServiceImpl

@RestController
@RequestMapping("/socket")
class SocketController(private val socketServiceImpl: SocketServiceImpl) {
    @PostMapping
    fun createSocket(@RequestBody socketRequest: SocketRequest): ResponseEntity<SocketResponse> {
        val socketResponse = socketServiceImpl.createSocket(socketRequest)
        return ResponseEntity(socketResponse, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getSocketById(@PathVariable id: Long): ResponseEntity<SocketResponse> {
        val socketResponse = socketServiceImpl.getSocketById(id)
        return ResponseEntity(socketResponse, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateSocketById(@PathVariable id: Long, @RequestBody socketRequest: SocketRequest): ResponseEntity<SocketResponse> {
        val socketResponse = socketServiceImpl.updateSocketById(id, socketRequest)
        return ResponseEntity(socketResponse, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteSocketById(@PathVariable id: Long): ResponseEntity<Void> {
        socketServiceImpl.deleteSocketById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}