package com.sabonay.advantageservices.utils;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.utils.enums.PaymentType;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class AppUtils {

    private static final Logger log = Logger.getLogger(AppUtils.class.getName());

    private static final String ALPHA_NUM_RAND = "1234567890";

    public static String generateBillNarration(String billType, String billId, Integer billYear) {
        try {
            String narration = "";
            if (billType.equals(PaymentType.GROUND_RENT.getLabel())) {
                narration = "Ground rent for " + billYear;
            } else {
                //Gets the month from record id eg. "ADB-ADB/A-555555/HOUSE_RENT-DEBIT/2023#SEPTEMBER"
                String[] parts = billId.split("#");
                String month = parts[parts.length - 1].toLowerCase();
                //capitalize the first letter of the month
                month = month.substring(0, 1).toUpperCase() + month.substring(1);
                narration = "House rent for " + month + "," + billYear;
            }
            return narration;

        } catch (Exception e) {
            AppLogger.error(log, e, "error generating narration");
            return "error narration";
        }
    }

    public static String generateToken(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int ndx = (int) (Math.random() * ALPHA_NUM_RAND.length());
            sb.append(ALPHA_NUM_RAND.charAt(ndx));
        }
        return sb.toString();
    }

    public static HeaderResponse getErrorHeaderResponse(HeaderRequest request) {
        HeaderResponse response = new HeaderResponse();
        response.setZone(request.getZone());
        response.setRequestId(request.getRequestId());
        response.setSourceCode(request.getSourceCode());
        response.setResponseCode(ResponseCodes.ERROR_SERVICING_REQUEST);
        response.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST, ""));
        return response;
    }

    public static HeaderResponse setHeaderResponse(HeaderRequest request) {
        HeaderResponse response = new HeaderResponse();
        String msg = "";
        try {
            response.setZone(request.getZone());
            response.setRequestId(request.getRequestId());
            response.setSourceCode(request.getSourceCode());
            if (null == request.getRegion() || request.getRegion().isEmpty()) {
                msg = ResponseCodes.getAppMsg(msg, "");
                response.setResponseCode(ResponseCodes.LANG_REQUIRED);
                response.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                return response;
            }

            msg = ResponseCodes.getAppMsg(ResponseCodes.SUCCESS, msg);
            response.setResponseCode(ResponseCodes.SUCCESS);
            response.setResponseMessage(msg);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error setting response header");
            response = (AppUtils.getErrorHeaderResponse(request));
            return response;
        }
    }

    public static String maskEmail(String email) {
        try {
            log.info("email to be masked " + email);
            if (null == email) {
                return "";
            }
            return email.replaceAll("(?<=.).(?=[^@]*?.@)", "*");
        } catch (Exception e) {
            AppLogger.error(log, e, "");
            return "Error mail";
        }
    }

    public static String maskPhoneNo(String mobilNo) {
        try {
            log.info("mobile number to be masked " + mobilNo);
            if (null == mobilNo) {
                return "";
            }
            return mobilNo.replaceAll("\\d(?=\\d{4})", "*");
        } catch (Exception e) {
            AppLogger.error(log, e, "");
            return "Error mobile number";
        }
    }

    public static boolean isUatEnvt() throws UnknownHostException {
        try {
            String ip = getUserIpAddress();
            List<String> uatIps = Arrays.asList("10.4.139.49", "10.4.139.50", "192.168.31.82", "172.16.76.249", "10.4.138.163");
            if (uatIps.stream().anyMatch((eachIp) -> (eachIp.equalsIgnoreCase(ip)))) {
                return true;
            }
        } catch (UnknownHostException e) {
            AppLogger.error(log, e, "error getting server environment");
        }
        return false;
    }

    public static String getUserIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String Hash512Msg(String data) {
        String isValid = "";
        try {

            System.out.println("STOKEN::" + data);

            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            // ** NOTE all bytes that are retrieved from the data string must be done so using UTF-8 Character Set.
            byte[] hashBytes = data.getBytes("UTF-8");
            //Create the hash bytes from the data
            byte[] messageDigest = digest.digest(hashBytes);
            //Create a HEX string from the hashed data
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                sb.append(h);
            }
            System.out.println("SBB-TOKEN:" + sb.toString());
            isValid = sb.toString();

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            AppLogger.error(log, ex, "error Hash512Msg");
        }

        return isValid;

    }

    public static String generatesha512(String xx) {
        String result = "";
        byte[] res;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-512");
            byte[] tohash = xx.trim().getBytes("UTF-8");
            md.update(tohash);
            res = md.digest();
            result = new String(Base64.encodeBase64(res));
            return result;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            AppLogger.error(log, e, "Error generating sha512 hash string");
            return null;
        }
    }

    public static boolean validateRequestToken(HeaderRequest request) {
        try {
            log.info("request id" + request.getRequestId() + "\taffcode" + request.getZone() + "\tlang" + request.getRegion());
            log.info("Client requestToken\t" + request.getRequestToken());
            String key = Settings.getInstance("").getProperty("SECRET_KEY");
            String originalString = request.getRequestId() + request.getZone() + key + request.getRegion();
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] encodedHash = digest.digest(originalString.getBytes());
            String hashedString = bytesToHex(encodedHash);
            log.info("Server requestToken\t" + hashedString);
            return hashedString.equals(request.getRequestToken());
        } catch (NoSuchAlgorithmException e) {
            AppLogger.error(log, e, "Error generating sha512 hash string");
            return false;
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String GetRefNumber(String type, int len) {
        String finalString = "";
        int x = 0;
        char[] stringChars = new char[len];
        for (int i = 0; i < len; i++) {
            Random random = new Random();
            x = random.nextInt(9);
            stringChars[i] = Integer.toString(x).toCharArray()[0];
        }
        finalString = new String(stringChars);
        finalString = type + finalString;
        return finalString.trim();
    }

    public static <T> Response sendGetRequest(String url) {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(url.trim());
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            return invocationBuilder.get();
        } catch (Exception e) {
            AppLogger.error(log, e, "Error transforming sendGetJsonRequest");
            return null;
        }

    }

    public static String formQueryString(Map<String, String> map) {
        String webParam = "";
        List<String> list = new LinkedList<>(map.keySet());
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            try {
                String param = iterator.next();
                if (null == param || param.isEmpty()) {
                    param = "";
                }
                webParam += param + "=" + URLEncoder.encode(map.get(param), "UTF-8");
                if (iterator.hasNext()) {
                    webParam += "&";
                }
            } catch (UnsupportedEncodingException ex) {
                log.error("UnsupportedEncodingException thrown reason: " + ex.getMessage());
                log.error("UnsupportedEncodingException exception " + Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n"));
            }
        }
        return webParam;
    }

    public static String generateUsername(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be null or empty.");
        }
        // Convert the full name to lowercase
        fullName = fullName.toLowerCase();

        // Remove leading and trailing whitespaces (if any)
        fullName = fullName.trim();

        // Remove all special characters except hyphen ("-")
        fullName = fullName.replaceAll("[^a-zA-Z0-9\\s-]", "");

        // Remove consecutive hyphens, if any
        fullName = fullName.replaceAll("-+", "-");

        // Trim leading and trailing hyphens, if any
        fullName = fullName.replaceAll("^-|-$", "");

        // Extract the first letter of the first name and the last name
        String[] names = fullName.split("\\s+");
        String firstName = names[0].substring(0, 1);
        String lastName = names[names.length - 1];

        return firstName + lastName;
    }

    public static void main(String[] args) {
        String fullName = "Daud Ainoo";
        String username = generateUsername(fullName);
        System.out.println("Username: " + username); // Output: Username: jdoe
    }

}
