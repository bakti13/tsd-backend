package co.id.menanga.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
public class Datatables {
    @Setter
    @Getter
    private int draw;

    @Setter
    @Getter
    private int recordsTotal;

    @Setter
    @Getter
    private int recordsFiltered;

    @Setter
    @Getter
    private List data;
}
