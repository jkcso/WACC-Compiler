package arm;

public class MOV extends Instruction {

    private SecondOperand origin;
    private Number value = Integer.MIN_VALUE;
    private Enumerables.Condition condition;

    public MOV(Register destination, SecondOperand origin) {
        this.destination = destination;
        this.origin = origin;
    }

    public MOV(Register destination, Number value) {
        this.destination = destination;
        this.value = value;
    }

    public MOV withCondition(Enumerables.Condition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public String toString() {

        String additionalInformation = "";

        if (condition != null) {
            additionalInformation += condition.name();
        }

        if (origin != null) {
            return String.format("\tMOV%s %s, %s\n", additionalInformation, destination, origin);
        } else {
            return String.format("\tMOV%s %s, #%s\n", additionalInformation, destination, value);
        }
    }
}
