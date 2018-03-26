package com.pomoravskivrbaci.cinemareservations.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class HallSegment {

    public static enum Type {
        VIP, LEFT_BALCONY, RIGHT_BALCONY, PARTER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name="type",nullable=false)
    protected Type type;

    @Column(name="number_of_rows")
    protected int numberOfRows;

    @Column(name="number_of_columns")
    protected int numberOfColumns;

    @Column(name="is_closed")
    protected boolean isClosed;

    @ManyToOne
    protected Hall hall;

    @OneToMany(mappedBy = "hallSegment")
    protected List<Seat> seats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Hall getHall() {
        return hall;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
