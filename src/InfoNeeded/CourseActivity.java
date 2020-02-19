package InfoNeeded;

import Support.Activity;

public class CourseActivity extends Course {
    private Activity activity;

    public CourseActivity(String title) {
        super(title);
    }

    public CourseActivity(String title, Activity activity){
        super(title);
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
