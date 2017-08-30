package arm;

public class Register {

    private int number;

    public Register(int i) {
        this.number = i;
    }

    public Register next() {
        return new Register(number + 1);
    }

    public Register previous() {
        return new Register(number - 1);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Register && this.number == ((Register) o).number;
    }

    @Override
    public String toString() {
        if (number == 13) {
            return "sp";
        } else if (number == 15) {
            return "pc";
        } else {
            return "r" + number;
        }
    }
}
