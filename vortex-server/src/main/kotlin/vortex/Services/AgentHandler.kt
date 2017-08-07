package vortex.Services

import ratpack.exec.Promise
import vortex.model.Payload

interface AgentHandler {

    fun register(payload: Payload) : Promise<Payload>
    fun getTask(payload: Payload) : Promise<Payload>
    fun submitResult(payload: Payload) : Promise<Payload>

}
