package ee.sandra.veebipood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String account_name;
    private String order_reference;
    private String email;
    private String customer_ip;
    private String customer_url;
    private Date payment_created_at;
    private double initial_amount;
    private double standing_amount;
    private String payment_reference;
    private String payment_link;
    private String api_username;
    private String payment_state;
    private String mobile_access_token;
    private String currency;
    private String applepay_merchant_identifier;
    private String googlepay_merchant_identifier;
    private String descriptor_country;
}
