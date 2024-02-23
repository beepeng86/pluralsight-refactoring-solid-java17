import java.util.function.Predicate;

public enum ValidationRule {

    CALL_SIGN_NOT_EMPTY(fp -> fp.getCallSign() == null || fp.getCallSign().equals(""), "CallSign is empty"),
    DEPARTURE_DESTINATION_SAME(fp -> fp.getDeparture() != null && fp.getDeparture().equals(fp.getDestination()), "Departure and destination are the same");
    private final Predicate<FlightPlan> validationFunction;
    private final String errorString;

    ValidationRule(Predicate<FlightPlan> validationFunction, String errorString) {
        this.validationFunction = validationFunction;
        this.errorString = errorString;
    }

    public ValidationResult validate(FlightPlan flightPlan) {
        var result = new ValidationResult();
        if (validationFunction.test(flightPlan)) {
            result.errors().add(errorString);
        }
        return result;
    }

}
