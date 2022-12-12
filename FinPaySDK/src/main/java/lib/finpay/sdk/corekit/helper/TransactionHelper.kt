package lib.finpay.sdk.corekit.helper

class TransactionHelper {
    companion object {
        fun getTransNumber(transNumber: String): String {
            val transactionNumber: String
            if (transNumber == "") {
                var temp = java.util.UUID.randomUUID().toString()
                val re = Regex("[^A-Za-z0-9]")
                temp = re.replace(temp, "")
                transactionNumber = temp
            } else {
                transactionNumber = transNumber
            }
            return transactionNumber
        }

    }
}