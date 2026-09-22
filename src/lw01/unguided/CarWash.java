package lw01.unguided;

public class CarWash extends WashService {

    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int dailyCost;

        if (days <= 3) {
            dailyCost = days * 35000;
        } else {
            dailyCost = (3 * 35000) + ((days - 3) * 25000);
        }

        return dailyCost + 15000;
    }

    @Override
    public String label() {
        return "Car";
    }
}