package ctr;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Elev1 on 2016-09-15.
 */
@Named
@SessionScoped
public class AdminPanelBean implements Serializable{

    private boolean showStudentTable;
    private boolean showTeacherTable;
    private boolean showCourseTable;





    public boolean isShowStudentTable() {
        return showStudentTable;
    }

    public void setShowStudentTable(boolean showStudentTable) {
        this.showStudentTable = showStudentTable;
        this.showCourseTable = false;
        this.showTeacherTable = false;
    }

    public boolean isShowTeacherTable() {
        return showTeacherTable;
    }

    public void setShowTeacherTable(boolean showTeacherTable) {
        this.showTeacherTable = showTeacherTable;
        this.showStudentTable = false;
        this.showCourseTable = false;

    }

    public boolean isShowCourseTable() {
        return showCourseTable;
    }

    public void setShowCourseTable(boolean showCourseTable) {
        this.showCourseTable = showCourseTable;
        this.showTeacherTable = false;
        this.showStudentTable = false;

    }
}
