package vortex

import ratpack.guice.Guice
import ratpack.server.RatpackServer
import vortex.Services.AgentHandler
import vortex.Services.RequestHandler
import vortex.config.ConfigModule
import vortex.config.VertexServerConfig
import vortex.model.Payload


fun main(args: Array<String>) {

    RatpackServer.start { it ->
        it.serverConfig { it ->
            it.require("/vortex-sever", VertexServerConfig::class.java)
                    .env("VORTEX_SERVER_").build()
        }.registry(Guice.registry { it ->
            it.module(ConfigModule())
        }).handlers { it ->
            it.all { ctx ->
                val request = ctx.request
                request.body.then { it ->
                    val requestPayload = Payload(body = it.text, headers = request.headers.asMultiValueMap(),
                            contentType = request.contentType.type)
                    val service = ctx.get(RequestHandler::class.java)
                    ctx.render(service.processReuqest(requestPayload))
                }
            }
            it.prefix("agent",  { it ->
                val agentHandler = it.registry.get(AgentHandler::class.java)
                it.path("register", { ctx ->
                    val request = ctx.request
                    request.body.then { it ->
                        val requestPayload = Payload(body = it.text, headers = request.headers.asMultiValueMap(),
                                contentType = request.contentType.type)
                        ctx.render(agentHandler.register(requestPayload))
                    }
                })
                it.prefix(":id", { it ->
                    it.path("task", { ctx ->
                        ctx.byMethod { method ->
                            method.get { ctx ->
                                val request = ctx.request
                                request.body.then { it ->
                                    val requestPayload = Payload(body = it.text, headers = request.headers.asMultiValueMap(),
                                            contentType = request.contentType.type)
                                    ctx.render(agentHandler.getTask(requestPayload))
                                }
                            }.post { ctx ->
                                val request = ctx.request
                                request.body.then { it ->
                                    val requestPayload = Payload(body = it.text, headers = request.headers.asMultiValueMap(),
                                            contentType = request.contentType.type)
                                    ctx.render(agentHandler.submitResult(requestPayload))
                                }
                            }
                        }
                    })
                })
            })
        }
    }
}
