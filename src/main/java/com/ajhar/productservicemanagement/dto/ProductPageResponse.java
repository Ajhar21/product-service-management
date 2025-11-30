package com.ajhar.productservicemanagement.dto;

import java.util.List;

// ===================== Start AJHAR20251129 Converted to record class =====================

public record ProductPageResponse(List<ProductResponse> content, long totalElements, int totalPages, int pageNumber,
                                  int pageSize, boolean last) {

}
// ===================== End AJHAR20251129 Converted to record class =====================