package vortex.config

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import vortex.Services.AgentHandler
import vortex.Services.RequestHandler
import vortex.Services.impl.AgentHandlerService
import vortex.Services.impl.RequestHandlerService

class ConfigModule : AbstractModule {

    constructor() : super()

    override fun configure() {
        bind(RequestHandler::class.java)
                .to(RequestHandlerService::class.java).`in`(Scopes.SINGLETON)
        bind(AgentHandler::class.java)
                .to(AgentHandlerService::class.java).`in`(Scopes.SINGLETON)
    }

}
