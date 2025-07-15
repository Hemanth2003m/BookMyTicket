import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class SeatBookingUI extends JFrame {
    private SeatAvailabilityManager manager;
    private String trainNo = "101";
    private String className = "Sleeper";
    private JTable table;
    private DefaultTableModel model;

    public SeatBookingUI(SeatAvailabilityManager manager) {
        this.manager = manager;

        setTitle("Seat Booking System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Train: 101 | Class: Sleeper");
        label.setBounds(10, 10, 300, 20);
        add(label);

        String[] columns = {"Seat ID", "Berth Type", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 40, 460, 200);
        add(pane);

        JButton bookBtn = new JButton("Book Seat");
        bookBtn.setBounds(50, 260, 150, 30);
        add(bookBtn);

        JButton cancelBtn = new JButton("Cancel Seat");
        cancelBtn.setBounds(250, 260, 150, 30);
        add(cancelBtn);

        loadSeats();

        // Book Button Action
        bookBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String seatId = (String) model.getValueAt(row, 0);
                if (manager.bookSeat(trainNo, className, seatId)) {
                    JOptionPane.showMessageDialog(this, "Seat booked!");
                } else {
                    JOptionPane.showMessageDialog(this, "Seat already booked.");
                }
                loadSeats();
            }
        });

        // Cancel Button Action
        cancelBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String seatId = (String) model.getValueAt(row, 0);
                if (manager.cancelSeat(trainNo, className, seatId)) {
                    JOptionPane.showMessageDialog(this, "Booking cancelled!");
                } else {
                    JOptionPane.showMessageDialog(this, "Seat is already available.");
                }
                loadSeats();
            }
        });
    }

    private void loadSeats() {
        model.setRowCount(0);
        List<Seat> seats = manager.getSeatMap(trainNo).getSeatsByClass(className);
        for (Seat seat : seats) {
            String status = seat.isAvailable() ? "Available" : "Booked";
            model.addRow(new Object[]{seat.getSeatId(), seat.getBerthType(), status});
        }
    }
}
