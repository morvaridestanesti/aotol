package ir.aotol.app.retrofit

open class ApiException(val code: Int, message: String, val isNoAnswer: Boolean = false) : Exception(message)

class RegistrationIncompleteException(val role: String, message: String) : ApiException(409, message)
