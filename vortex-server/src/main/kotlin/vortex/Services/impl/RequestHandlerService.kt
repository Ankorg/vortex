package vortex.Services.impl

import ratpack.exec.Promise
import vortex.Services.RequestHandler
import vortex.model.Payload

class RequestHandlerService : RequestHandler {

    override fun processReuqest(paload: Payload): Promise<Payload> {
        return Promise.value(Payload())
    }

}

