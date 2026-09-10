package ee.sandra.veebipood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentBody {
    private String account_name;
    private String nonce;
    private String timestamp;
    private Double amount;
    private String order_reference;
    private String customer_url;
    private String api_username;
}
