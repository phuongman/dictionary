module ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens ui to javafx.fxml;
    requires freetts;
    requires org.xerial.sqlitejdbc;
    requires jlayer;
    requires javafx.media;
    requires java.desktop;
    exports ui;
}
