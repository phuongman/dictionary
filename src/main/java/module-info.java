module ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens ui to javafx.fxml;
    requires freetts;
    requires org.xerial.sqlitejdbc;
    exports ui;
}
