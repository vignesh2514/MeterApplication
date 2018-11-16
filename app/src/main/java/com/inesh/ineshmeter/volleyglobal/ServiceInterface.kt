package com.inesh.ineshmeter.volleyglobal

import org.json.JSONObject

interface ServiceInterface {
    fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit)

    fun patch(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit)

    fun get(path: String,  completionHandler: (response: String?) -> Unit)




}

