module com.budget.budgettracking {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.budget.budgettracking to javafx.fxml;
    exports com.budget.budgettracking;
}