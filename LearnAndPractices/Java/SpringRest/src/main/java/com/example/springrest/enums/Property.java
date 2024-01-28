package com.example.springrest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Property {
    NAME("Name", "Name value is empty or null."),
    AGE("Age", "Age value is null or not greater than 0."),
    IMAGES("Images", "Image list is null or contains empty/null values."),
    CUSTOMER_NAME("Customer Name", "Customer Name value is empty or null."),
    ORDER_NAME("Order Name", "Order Name value is empty or null."),
    STOCK("Stock", "Stock value is null or not greater than 0.");

    private String displayName;
    private String errorMessage;
}
