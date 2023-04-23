module com.budget.budgettracking {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.budget.budgettracking to javafx.fxml;
    exports com.budget.budgettracking;
}