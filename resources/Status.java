package resources;

public enum Status {
    TOPROCESS,
    TOPACKAGE,
    TODELIVER,
    FINALIZED;

    public static Status getNext(Status currentStatus){
        switch (currentStatus) {
            case TOPROCESS:
                return TOPACKAGE;
            case TOPACKAGE:
                return TODELIVER;
            case TODELIVER:
                return FINALIZED;
            default:
                return null;
        }
    }
}
