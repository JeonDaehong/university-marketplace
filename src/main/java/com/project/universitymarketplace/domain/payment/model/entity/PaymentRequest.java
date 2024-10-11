package com.project.universitymarketplace.domain.payment.model.entity;

import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
        @NotNull Payment payment,
        @NotNull Long productId
) {
}
