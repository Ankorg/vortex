package vortex.Services.impl

import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.websocket.WebSocket
import ratpack.websocket.WebSocketClose
import ratpack.websocket.WebSockets
import vortex.Services.AgentHandler
import vortex.model.Payload
import java.security.AccessControlContext
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class AgentHandlerService : AgentHandler {

     val agents : MutableMap<UUID, WebSocket> = ConcurrentHashMap()
    val agentsLookup : MutableMap<WebSocket, UUID> = ConcurrentHashMap()

    override fun submitResult(payload: Payload): Promise<Payload> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTask(payload: Payload): Promise<Payload> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun register(context: Context): Promise<Payload> {
        var agentId = UUID.randomUUID()
        WebSockets.websocket(context) { websocket ->
            agents.put(agentId, websocket)
            agentsLookup.put(websocket, agentId)
            websocket.send(agentId.toString())
        }.connect { websocket ->
            websocket.onClose { it ->
                it.
            }.onMessage { it ->
                val text = it.getText()

            }
        }
    }
}
