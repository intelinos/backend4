package org.example.back4.beans;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "result", schema = "s392251")
public class AreaCheckerBean implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;

    @Column(name = "r")
    private double r;

    @Column(name = "status")
    private boolean status;
    @Column(name = "ownerid")
    private int ownerid;

    public AreaCheckerBean() {
        super();
    }

    @Column(name = "x")
    public double getX() {
        return Math.round(x * 100.0) / 100.0;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Column(name = "y")
    public double getY() {
        return Math.round(y * 100.0) / 100.0;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Column(name = "r")
    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Column(name = "status")
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "ownerid")
    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaCheckerBean bean)) return false;
        return getId() == bean.getId() &&
                Double.compare(getX(), bean.getX()) == 0 &&
                Double.compare(getY(), bean.getY()) == 0 &&
                Double.compare(getR(), bean.getR()) == 0 &&
                getStatus() == bean.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getX(), getY(), getR(), getStatus());
    }

    @Override
    public String toString() {
        return "AreaCheckerBean{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", status=" + status +
                ", ownerId=" + ownerid +
                '}';
    }
}
