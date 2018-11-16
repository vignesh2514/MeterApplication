package com.inesh.ineshmeter.volleyglobal

import org.json.JSONObject

class APIController constructor(serviceInjection: ServiceInterface):
    ServiceInterface {
    private val service: ServiceInterface = serviceInjection

    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        service.post(path, params, completionHandler)
    }

    override fun patch(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        service.patch(path, params, completionHandler)
    }

    override fun get(path: String,  completionHandler: (response: String?) -> Unit) {
        service.get(path, completionHandler)
    }


}