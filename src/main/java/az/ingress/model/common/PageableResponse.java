package az.ingress.model.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageableResponse<T> {
    private List<T> content;
    private Long totalElements;
    private Integer totalPages;
    private Boolean hasNextPage;
}