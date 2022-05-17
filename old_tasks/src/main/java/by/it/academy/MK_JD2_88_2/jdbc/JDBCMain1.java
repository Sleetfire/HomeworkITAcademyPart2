package by.it.academy.MK_JD2_88_2.jdbc;

import java.sql.*;

public class JDBCMain1 {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Проблемы с загрузкой");
            return;
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/demo?ApplicationName=TestSweetApp", "postgres", "12378");
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT\n" +
                     "    flight_id,\n" +
                     "    flight_no,\n" +
                     "    scheduled_departure,\n" +
                     "    scheduled_arrival,\n" +
                     "    departure_airport,\n" +
                     "    arrival_airport,\n" +
                     "    status,\n" +
                     "    aircraft_code,\n" +
                     "    actual_departure,\n" +
                     "    actual_arrival\n" +
                     "FROM\n" +
                     "    bookings.flights;\n")

        ) {
            int count = 0;
            while (rs.next()) {
                System.out.println(rs.getString("flight_no"));
                count++;
            }
            System.out.println(count);
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
    }
}
