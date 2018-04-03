package com.pomoravskivrbaci.cinemareservations.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProjectionSegment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne
    private HallSegment segment;

    @ManyToOne
    private Projection projection;

    @Column(name = "is_closed")
    private boolean isClosed;


}
