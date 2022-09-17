package lib.finpay.sdk.presentation

import lib.finpay.sdk.model.TokenModel

class TokenResponse {
    var posts: TokenModel? = null
    var error: Throwable? = null
    var code : Int? = null

    constructor(posts: TokenModel) {
        this.posts = posts
        this.error = null
    }

    constructor(error: Throwable) {
        this.error = error
        this.posts = null
    }

    constructor(code : Int){
        this.code = code
    }
}