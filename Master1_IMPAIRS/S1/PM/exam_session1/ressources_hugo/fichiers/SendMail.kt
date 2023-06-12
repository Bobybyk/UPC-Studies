/**
* Returns Intent object that can be used to activate a send mail application.
 *
 * @param  mailAdresses an array of mail adresses
 * @param subject       message's subject
 * @param message       a text message
 * @return    Intent such that startActivity( intent ) launches a send mail activity (under the condition that there exists
 * such an activity specified for your device
 */
fun sendMail(mailAdresses: Array<String>, subject: String, message: String): Intent =
    Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_EMAIL, mailAdresses)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, message)
        // set type of intent
        setType("message/rfc822");
    }