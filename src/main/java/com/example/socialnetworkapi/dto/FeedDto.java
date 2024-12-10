package com.example.socialnetworkapi.dto;

import java.util.List;

public record FeedDto (List<FeedItemDto> item, int page, int pageSize, int totalPages, long totalElements) {
}
