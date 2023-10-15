
import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a)\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"sendMail", "Landroid/content/Intent;", "mailAdresses", "", "", "subject", "message", "([Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;", "app_debug"})
public final class SendMailKt {
    
    /**
     * Returns Intent object that can be used to activate a send mail application.
     *
     * @param  mailAdresses an array of mail adresses
     * @param subject       message's subject
     * @param message       a text message
     * @return    Intent such that startActivity( intent ) launches a send mail activity (under the condition that there exists
     * such an activity specified for your device
     */
    @org.jetbrains.annotations.NotNull()
    public static final android.content.Intent sendMail(@org.jetbrains.annotations.NotNull()
    java.lang.String[] mailAdresses, @org.jetbrains.annotations.NotNull()
    java.lang.String subject, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
        return null;
    }
}