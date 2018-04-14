package com.pomoravskivrbaci.cinemareservations.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
@Entity
public class HallSegment {

    public enum Type {
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
    protected boolean closed;

    @JsonIgnore
    @ManyToOne
    protected Hall hall;

    @OneToMany(mappedBy = "hallSegment", cascade = {CascadeType.ALL, CascadeType.REFRESH, CascadeType.PERSIST})
    protected List<Seat> seats = new ArrayList<>();

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
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Hall getHall() {
        return hall;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
