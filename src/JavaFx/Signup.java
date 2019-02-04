package JavaFx;

import javafx.event.ActionEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;

public class Signup {

    final String SenderId = "thivya1998@gmail.com";
    final String Senderpass = "aiyaamma";
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailServerPort = "465";
    String receiveremailID = null;
    String emailSubject = null;

    String emailBody = null;


    public class SMTP extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(SenderId, Senderpass);
        }
    }

    public void button(ActionEvent actionEvent) throws SQLException {

/*

ConnectionClass connectionClass = new ConnectionClass();
 Connection connection = connectionClass.getConnection();

        String sql = "INSERT INTO LOGIN VALUES ('" + userName.getText() + "','" + dob.getValue() + "','" + address.getText() + "','" + userid.getText() + "','" + userPassword.getText() + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        System.out.println("Program is running");

*/

        receiveremailID = "thivya1998@gmail.com";
        emailSubject = " MONTHLY News Letter";
        emailBody = "test mail shit";



        Properties properties = System.getProperties();
        properties.put("mail.smtp.user", SenderId);
        properties.put("mail.smtp.host", emailSMTPserver);
        properties.put("mail.smtp.port", emailServerPort);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", emailServerPort);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        SecurityManager security = System.getSecurityManager();


        try {

            Authenticator auth = new SMTP();
            // getPasswordAuthentication();
            Session session = Session.getInstance(properties, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setSubject(emailSubject);

            msg.setContent("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Newsletter Volume 12 - Episode 47</title>\n" +
                    "    <style type=\"text/css\">\n" +
                    "\t/* Backgrounds */\n" +
                    ".email_background {\n" +
                    "\twidth: 640px;\n" +
                    "\tbackground: url('email_bg.jpg') repeat-y;\n" +
                    "}\n" +
                    "\n" +
                    "/* Images */\n" +
                    "img {\n" +
                    "\tdisplay: block;\n" +
                    "\tborder: none;\n" +
                    "}\n" +
                    "/* Fonts and Typography */\n" +
                    ".footer {\n" +
                    "\tfont-family: Tahoma, Arial, sans-serif;\n" +
                    "\tfont-size: 11px;\n" +
                    "\tcolor: #fff;\n" +
                    "\tpadding-right: 20px;\n" +
                    "}\n" +
                    "\n" +
                    "h1 {\n" +
                    "\tcolor: #e95f03;\n" +
                    "\tfont-family: Futura, Verdana, Sans-Serif;\n" +
                    "\tpadding: 0;\n" +
                    "\tmargin: 0;\n" +
                    "\tfont-size: 24px;\n" +
                    "\tfont-weight: normal;\n" +
                    "\ttext-align: left;\n" +
                    "}\n" +
                    "h2 {\n" +
                    "\tcolor: #e95f03;\n" +
                    "\tfont-family: Futura, Verdana, Sans-Serif;\n" +
                    "\tpadding: 0;\n" +
                    "\tmargin: 0;\n" +
                    "\tfont-size: 18px;\n" +
                    "\tfont-weight: normal;\n" +
                    "\ttext-align: left;\n" +
                    "}\n" +
                    ".content {\n" +
                    "\tpadding: 0;\n" +
                    "\tmargin: 0;\n" +
                    "}\n" +
                    "p {\n" +
                    "\tfont-family: Arial, Helvetica, sans-serif;\n" +
                    "\tfont-size: 13px;\n" +
                    "\tline-height: 20px;\n" +
                    "\ttext-align: left;\n" +
                    "}\n" +
                    "\t</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<!-- Container Table -->\n" +
                    "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"99%\" bgcolor=\"#0f6da1\">\n" +
                    "    <tr>\n" +
                    "        <td align=\"center\">\n" +
                    "\n" +
                    "            <!-- Email Wrapper Table -->\n" +
                    "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"640\">\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "\n" +
                    "                        <!-- Actual Email Content -->\n" +
                    "                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"640\">\n" +
                    "                            <tr>\n" +
                    "                                <td valign=\"bottom\" height=\"30\" align=\"right\" class=\"footer\">Having trouble viewing this email? Click here to view the web version.</td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td><img src=\"top_fade.jpg\" width=\"640\" height=\"20\" border=\"0\" /></td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"center\" background=\"email_bg.jpg\" class=\"email_background\">\n" +
                    "                                    <!-- Email Header -->\n" +
                    "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"600\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td align=\"left\" style=\"padding-bottom: 20px;\"><img src=\"logo.jpg\" width=\"246\" height=\"41\" border=\"0\" style=\"padding-left:20px; padding-top: 20px;\" /></td>\n" +
                    "                                            <td style=\"padding-bottom: 20px;\"><img src=\"facebook.jpg\" width=\"23\" height=\"41\" border=\"0\" style=\"padding-top: 20px;\" /></td>\n" +
                    "                                            <td style=\"padding-bottom: 20px;\"><img src=\"twitter.jpg\" width=\"23\" height=\"41\" border=\"0\" style=\"padding-top: 20px;\" /></td>\n" +
                    "                                            <td style=\"padding-bottom: 20px;\"><img src=\"linkedin.jpg\" width=\"23\" height=\"41\" border=\"0\" style=\"padding-top: 20px;\" /></td>\n" +
                    "                                            <td style=\"padding-bottom: 20px;\"><img src=\"youtube.jpg\" width=\"23\" height=\"41\" border=\"0\" style=\"padding-top: 20px;\" /></td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td align=\"left\" colspan=\"5\"><img src=\"featured_image.jpg\" width=\"560\" height=\"250\" style=\"padding-left: 20px; padding-right: 20px;\" /></td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td align=\"left\" colspan=\"5\"><img src=\"featured_shadow.jpg\" width=\"560\" height=\"18\" style=\"padding-left: 20px; padding-right: 20px;\" /></td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td align=\"left\" colspan=\"5\" align=\"left\" style=\"padding-left:20px; padding-right: 20px; padding-bottom: 10px;\">\n" +
                    "                                                <h1>JEFF'S FISHING SHACK MONTHLY NEWSLETTER</h1>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td align=\"left\" valign=\"top\" colspan=\"5\" style=\"padding-left: 20px; padding-right: 20px; padding-bottom: 20px;\">\n" +
                    "                                                <p class=\"content\">IT IS FULL SPEED AHEAD HERE IN JEFF'S FISHING SHACK. <BR>\n" +
                    "                                                THE STORE IS FULL OF GREAT MERCHANDISE. <BR>\n" +
                    "                                                STOP ON BY THE STORE AND PICK UP THE LATEST EQUIPMENT AND GET THE LATEST INFO. <BR>\n" +
                    "                                                T  WE LOOK FORWARD TO SEEING YOU!.  <BR>\n" +
                    "\n" +
                    "                                                AS ALWAYS IF WE CAN HELP IN ANY WAY PLEASE LET US KNOW.       <BR>\n" +
                    "\n" +
                    "                                                GOOD FISHING AND GOD BLESS, <BR>\n" +
                    "\n" +
                    "                                                JEFF<BR>\n" +
                    "                                                JEFF'S FISHING SHACK<BR>\n" +
                    "                                                406-682-4263<BR>\n" +
                    "                                                JEFF@JFS.COM<BR>\n" +
                    "                                                <img src=\"readmore.jpg\" border=\"0\" align=\"right\" width=\"114\" height=\"27\" />\n" +
                    "                                                </p>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "\n" +
                    "                            <tr>\n" +
                    "                                <td>\n" +
                    "                                    <img src=\"bottom_fade.jpg\" width=\"640\" border=\"0\" height=\"28\" />\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td valign=\"top\" height=\"80\" align=\"right\" class=\"footer\">\n" +
                    "                                    Newsletter<br />\n" +
                    "                                    55 Onextrapixel Lane<br />\n" +
                    "                                    New York, NY 22222<br />\n" +
                    "                                    <br />\n" +
                    "                                    Want to Unsubscribe? Click here.\n" +
                    "                                </td>\n" +
                    "                        </table>\n" +
                    "                        <!-- End Actual Email Content -->\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "            <!-- End Email Wrapper Table -->\n" +
                    "\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "<!-- End Container Table -->\n" +
                    "</body>\n" +
                    "</html>\n", "text/html; charset=utf-8");
            msg.setFrom(new InternetAddress(SenderId));
            msg.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(receiveremailID)));
            Transport.send(msg);
            System.out.println("Message Sent");


        } catch (Exception e) {
            e.printStackTrace();

        }



    }
}
