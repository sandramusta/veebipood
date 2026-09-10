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
public class PaymentCheckResponse {
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
    private String stan;
    private int fraud_score;
    private String payment_state;
    private String payment_method;
    private String trace_id;
    private Date transaction_time;
    private String sca_exemption;
    private String acquiring_completed_at;
}
