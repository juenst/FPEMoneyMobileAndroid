package lib.finpay.sdk.corekit.helper

class TransactionHelper {
    companion object{
        fun getTransNumber(transNumber: String): String {
            var transactionNumber: String
            if(transNumber == "") {
                transactionNumber = java.util.UUID.randomUUID().toString()
            } else {
                transactionNumber = transNumber
            }

            return transactionNumber
        }

    }
}