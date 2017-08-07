package vortex.Services

import ratpack.exec.Promise
import vortex.model.Payload

interface RequestHandler {
    fun processReuqest(paload: Payload) : Promise<Payload>
}
