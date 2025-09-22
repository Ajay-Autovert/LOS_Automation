//package apiLibrary;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import static io.restassured.RestAssured.*;
//
//public class MobileOtpUtility {
//
//    public static String fetchOTP(String mobileNumber) {
//        RestAssured.baseURI = "https://<actual-api-hostname>"; // UPDATE THIS
//
//        Response response = given()
//                .header("Content-Type", "application/json")
//                .body("{ \"mobile\": \"" + mobileNumber + "\" }")
//                .when()
//                .post("/api/otp/fetch"); // UPDATE THIS as per actual endpoint
//
//        response.then().statusCode(200);
//
//        String otp = response.jsonPath().getString("otp"); // validate key name
//        System.out.println("Fetched OTP: " + otp);
//        return otp;
//    }
//}
//
//package apiLibrary;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//
//public class MobileOtpUtility {
//
//    public static String fetchOtpFromSendApi(String mobileNumber) {
//        String userId = "2000227347";
//        String password = "r6UVr7NQ";
//        String dltTemplateId = "1207168863366453314";
//
//        // Just for testing, hardcoding the OTP here; in real case, OTP should be generated dynamically
//        String otp = "4323";
//        String msg = "Dear Customer, use OTP " + otp + " to provide your consent for a credit & KYC check. For complete consent details, click https://autovert.s3.ap-south-1.amazonaws.com/common-files/ecofy_consent_details.pdf. Team Ecofy";
//
//        // Send OTP via GET request
//        Response response = RestAssured.given()
//                .baseUri("https://enterprise.smsgupshup.com")
//                .basePath("/GatewayAPI/rest")
//                .queryParam("v", "1.1")
//                .queryParam("msg", msg)
//                .queryParam("userid", userId)
//                .queryParam("password", password)
//                .queryParam("send_to", mobileNumber)
//                .queryParam("msg_type", "text")
//                .queryParam("method", "sendMessage")
//                .queryParam("dltTemplateId", dltTemplateId)
//                .when()
//                .get();
//
//        // Validate response
//        String responseText = response.getBody().asString();
//        System.out.println("SMS API Response: " + responseText);
//
//        // Return the OTP we sent in the message
//        return otp;
//    }
//}
