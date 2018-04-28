package pl.hgawrys.nasaapi.models;

public class AsteroidModel {

    private int number;
    private String name;
    private double distance;
    private double speed;
    private boolean hazardous;
    private double avgDiameter;

    public AsteroidModel(int number, String name, double distance, double speed, boolean hazardous, double avgDiameter) {
        this.number = number;
        this.name = name;
        this.distance = distance;
        this.speed = speed;
        this.hazardous = hazardous;
        this.avgDiameter = avgDiameter;
    }

    private AsteroidModel(Builder builder) {
        number = builder.getNumber();
        name = builder.getName();
        distance = builder.getDistance();
        speed = builder.getSpeed();
        hazardous = builder.isHazardous();
        avgDiameter = builder.getAvgDiameter();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isHazardous() {
        return hazardous;
    }

    public void setHazardous(boolean hazardous) {
        this.hazardous = hazardous;
    }

    public double getAvgDiameter() {
        return avgDiameter;
    }

    public void setAvgDiameter(int avgDiameter) {
        this.avgDiameter = avgDiameter;
    }

    @Override
    public String toString() {
        return "AsteroidModel{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", speed=" + speed +
                ", hazardous=" + hazardous +
                ", avgDiameter=" + avgDiameter +
                '}';
    }

    public static class Builder {
        private int number;
        private String name;
        private double distance;
        private double speed;
        private boolean hazardous;
        private double avgDiameter;


        public Builder(){
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setNumber(int number){
            this.number = number;
        return this;
        }

        public Builder setDistance(double distance){
            this.distance = distance;
            return this;
        }

        public Builder setSpeed(double speed){
            this.speed = speed;
            return this;
        }

        public Builder setHazardous(boolean hazardous){
            this.hazardous  = hazardous;
            return this;
        }

        public Builder setAvgDiamater(double avgDiamaterter){
            this.avgDiameter = avgDiamaterter;
            return this;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public double getDistance() {
            return distance;
        }

        public double getSpeed() {
            return speed;
        }

        public boolean isHazardous() {
            return hazardous;
        }

        public double getAvgDiameter() {
            return avgDiameter;
        }

        public AsteroidModel build(){
            return new AsteroidModel(this);
        }
    }
}
