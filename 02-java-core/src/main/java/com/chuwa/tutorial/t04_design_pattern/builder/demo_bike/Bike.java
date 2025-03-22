package com.chuwa.tutorial.t04_design_pattern.builder.demo_bike;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 10:08 AM
 */
public class Bike {
    private String frame;
    private String seat;
    private String brand;
    private String model;

    public Bike(String frame, String seat, String brand, String model) {
        this.frame = frame;
        this.seat = seat;
        this.brand = brand;
        this.model = model;
    }

    private Bike(Builder builder) {
        this.frame = builder.frame;
        this.seat = builder.seat;
        this.brand = builder.brand;
        this.model = builder.model;
    }

    //Static Factory method, to avoid usage of "new" with builder
    public static Builder builder(String brand, String model) {
        return new Builder(brand, model);
    }

    public static class Builder {
        private String seat;
        private String model;
        private String frame;
        private String brand;

        public Builder(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }

        public Builder setFrame(String frame) {
            this.frame = frame;
            return this;
        }
        public Builder setSeat(String seat) {
            this.seat = seat;
            return this;
        }
        public Bike build() {
            return new Bike(this);
        }
    }

    @Override
    public String toString() {
        return "Bike{" +
                "frame='" + frame + '\'' +
                ", seat='" + seat + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
