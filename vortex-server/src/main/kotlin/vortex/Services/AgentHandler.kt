package vortex.Services

import ratpack.exec.Promise
import ratpack.handling.Context
import vortex.model.Payload

interface AgentHandler {

    fun register(context: Context) : Promise<Payload>
    fun getTask(payload: Payload) : Promise<Payload>
    fun submitResult(payload: Payload) : Promise<Payload>

}
