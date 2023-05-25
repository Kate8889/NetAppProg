package ua.kpi.its.lab.rest.dto

class SocketRequest {
    var name: String? = null
        private set
    var manufacturer: String? = null
        private set
    var numOfCores: Int? = null
        private set

    constructor()
    constructor(name: String?, manufacturer: String?, numOfCores: Int?) {
        this.name = name
        this.manufacturer = manufacturer
        this.numOfCores = numOfCores
    }
}


class SocketResponse {
    var id: Long? = null
        private set
    var name: String? = null
        private set
    var manufacturer: String? = null
        private set
    var numOfCores: Int? = null
        private set

    constructor()
    constructor(id: Long?, name: String?, manufacturer: String?, numOfCores: Int?) {
        this.id = id
        this.name = name
        this.manufacturer = manufacturer
        this.numOfCores = numOfCores
    }
}


class SatelliteRequest {
    var name: String? = null
        private set
    var country: String? = null
        private set
    var appointment: String? = null
        private set

    constructor()
    constructor(name: String?, country: String?, appointment: String?) {
        this.name = name
        this.country = country
        this.appointment = appointment
    }
}


class SatelliteResponse {
    var id: Long? = null
        private set
    var name: String? = null
        private set
    var country: String? = null
        private set
    var appointment: String? = null
        private set

    constructor()
    constructor(id: Long?, name: String?, country: String?, appointment: String?) {
        this.id = id
        this.name = name
        this.country = country
        this.appointment = appointment
    }
}

