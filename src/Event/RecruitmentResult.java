package Event;

import Interface.IGameEvent;

public record RecruitmentResult(boolean wasSuccessful, String message) implements IGameEvent {
    public static RecruitmentResult success(String successMessage){
        return new RecruitmentResult(true,successMessage);
    }
    public static RecruitmentResult failure(String failureMessage){
        return new RecruitmentResult(false,failureMessage);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
