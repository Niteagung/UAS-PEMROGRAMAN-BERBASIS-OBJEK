import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class dashboard {
    private JPanel dashboard;
    private JPanel barPanel;
    private JPanel akunPanel;
    private JPanel buttonPanel;
    private JButton homeButton;
    private JButton monitorButton;
    private JButton laporanButton;
    private JPanel parentPanel;
    private JPanel homePanel;
    private JPanel monitorPanel;
    private JPanel laporanPanel;
    private JPanel judul;
    private JPanel inout;
    private JTextField textTravel;
    private JComboBox comboBox1;
    private JButton SIMPANButton;
    private JButton CARIButton;
    private JButton PERBARUIButton;
    private JPanel monButtons;
    private JLabel manisparJudul;
    private JTable table1;
    private JPanel tabelPanel;
    private JLabel jumJD;
    private JLabel jumBR;
    private JPanel dataPanel;
    private JTextField textPemandu;
    private JTextField textJumlahPeserta;
    private JComboBox comboBox2;
    private JTextField textField5;
    private JComboBox comboBox3;
    private JLabel jumSL;
    private JButton HAPUSBUTTON;
    private JPanel TERDAFTAR;
    private JLabel jumDF;
    private JTextPane textPane1;
    private JTextArea textArea1;
    private JButton perbaruiButton;
    private JButton tambahkanButton;
    private JButton hapusButton;
    private JPanel panelTabelPaket;
    private JTable table2;
    private JTextField textField1;
    private JButton cariButton;
    private JComboBox comboBox5;
    private JRadioButton aktifRadioButton;
    private JRadioButton nonAktifRadioButton;

    void table_load()
    {
        try
        {
            pst = con.prepareStatement("select id_jamaah, nama_paket, jumlah_peserta, pemandu, paket, jenis_paket, status from paket_jamaah");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            pst = con.prepareStatement("select kategori_paket.id_kategori,paket.nama_paket, kategori_paket.nama_kategori, kategori_paket.harga from kategori_paket join paket ON kategori_paket.id_paket = paket.id_paket GROUP BY  kategori_paket.id_kategori,paket.nama_paket, kategori_paket.nama_kategori, kategori_paket.harga;");
            ResultSet rs = pst.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public dashboard() {
        connect();
        table_load();
        hitungTerdaftar();
        hitungTerjadwal();
        hitungBerangkat();
        hitungSelesai();
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parentPanel.removeAll();
                parentPanel.add(homePanel);
                parentPanel.repaint();
                parentPanel.revalidate();


            }
        });
        monitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parentPanel.removeAll();
                parentPanel.add(monitorPanel);
                parentPanel.repaint();
                parentPanel.revalidate();


            }

        });
        laporanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parentPanel.removeAll();
                parentPanel.add(laporanPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });


        comboBox1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                String sql = "SELECT * FROM paket";
                try{
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        comboBox1.addItem(rs.getString("nama_paket"));
                    }
                }catch(SQLException ex){
                }

            }
        });


        comboBox2.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                String sql = ("SELECT * FROM kategori_paket");

                try{

                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        comboBox2.addItem(rs.getString("nama_kategori"));
                    }
                }catch(SQLException ex){
                }


            }
        });

        comboBox3.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                String sql = ("SELECT * FROM status");

                try{

                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        comboBox3.addItem(rs.getString("nama_status"));
                    }
                }catch(SQLException ex){
                }
            }
        });
        comboBox5.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                super.componentResized(e);
                String sql = "SELECT * FROM paket";
                try{
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        comboBox5.addItem(rs.getString("nama_paket"));
                    }
                }catch(SQLException ex){
                }
            }
        });

        SIMPANButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SIMPANButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        String namJamaah, namPemandu, paket, jenis_paket, status;
                        int jumJamaah;

                        namJamaah = textTravel.getText();
                        namPemandu = textPemandu.getText();
                        jumJamaah = Integer.parseInt(textJumlahPeserta.getText());
                        paket = ((JTextField)comboBox1.getEditor().getEditorComponent()).getText();
                        jenis_paket = ((JTextField)comboBox2.getEditor().getEditorComponent()).getText();;
                        status = ((JTextField)comboBox3.getEditor().getEditorComponent()).getText();;

                        try {
                            pst = con.prepareStatement("insert into paket_jamaah(nama_paket, jumlah_peserta, pemandu, paket, jenis_paket, status, tanggal_pendaftaran, tanggal_berangkat, tanggal_kembali)values(?,?,?,?,?,?, CURRENT_DATE,'2024-12-12','2024-01-12')");
                            pst.setString(1, namJamaah);
                            pst.setInt(2, jumJamaah);
                            pst.setString(3, namPemandu);
                            pst.setString(4, paket);
                            pst.setString(5, jenis_paket);
                            pst.setString(6, status);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan!!!!!");
                            table_load();
                            textTravel.setText("");
                            textPemandu.setText("");
                            textJumlahPeserta.setText("");
                            textTravel.requestFocus();
                            hitungTerdaftar();
                            hitungTerjadwal();
                            hitungBerangkat();
                            hitungSelesai();

                        }

                        catch (SQLException e1)
                        {

                            e1.printStackTrace();
                        }




                    }
                });
            }
        });

        CARIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    String empid = textField5.getText();

                    pst = con.prepareStatement("select nama_paket, pemandu, jumlah_peserta, paket, jenis_paket, status, tanggal_pendaftaran, tanggal_berangkat, tanggal_kembali, id_paket, id_kategori from paket_jamaah where id_jamaah = ?");
                    pst.setString(1, empid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String nama_paket = rs.getString(1);
                        String pemandu = rs.getString(2);
                        Integer jumlah_peserta = rs.getInt(3);
                        String paket = rs.getString(4);
                        String jenis_paket = rs.getString(5);
                        String status = rs.getString(6);


                        textTravel.setText(nama_paket);
                        textPemandu.setText(pemandu);
                        textJumlahPeserta.setText(String.valueOf(jumlah_peserta));
                        comboBox1.getModel().setSelectedItem(paket);
                        comboBox2.getModel().setSelectedItem(jenis_paket);
                        comboBox3.getModel().setSelectedItem(status);


                    }
                    else
                    {
                        textTravel.setText("");
                        textPemandu.setText("");
                        textJumlahPeserta.setText(String.valueOf(""));
                        comboBox1.addItem("");
                        comboBox2.addItem("");
                        comboBox3.addItem("");
                        JOptionPane.showMessageDialog(null,"Nomor Masukan Tidak Valid");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }

        });
        PERBARUIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String namJamaah, namPemandu, paket, jenis_paket, status, empid;
                int jumJamaah;

                namJamaah = textTravel.getText();
                namPemandu = textPemandu.getText();
                jumJamaah = Integer.parseInt(textJumlahPeserta.getText());
                paket = comboBox1.getSelectedItem().toString();
                jenis_paket =  comboBox2.getSelectedItem().toString();
                status = comboBox3.getSelectedItem().toString();
                empid = textField5.getText();


                try {
                    pst = con.prepareStatement("update paket_jamaah set nama_paket = ?, pemandu = ?, jumlah_peserta = ?, paket = ?, jenis_paket = ?, status = ? where id_jamaah = ? ");
                    pst.setString(1, namJamaah);
                    pst.setString(2,  namPemandu);
                    pst.setInt(3, jumJamaah);
                    pst.setString(4, paket);
                    pst.setString(5,  jenis_paket);
                    pst.setString(6, status);
                    pst.setString(7, empid);
                    int k = pst.executeUpdate();

                    if(k==1) {
                        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui ");
                        table_load();
                        textTravel.setText("");
                        textPemandu.setText("");
                        textJumlahPeserta.setText("");
                        textTravel.requestFocus();
                        hitungTerdaftar();
                        hitungTerjadwal();
                        hitungBerangkat();
                        hitungSelesai();

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Data Gagal Diperbarui ");
                    }

                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }

        });

        HAPUSBUTTON.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String empid;
                    empid = textField5.getText();

                    try {
                        pst = con.prepareStatement("delete from paket_jamaah where id_jamaah = ?");

                        pst.setString(1, empid);

                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                        table_load();
                        textTravel.setText("");
                        textPemandu.setText("");
                        textJumlahPeserta.setText("");
                        textTravel.requestFocus();
                        hitungTerdaftar();
                        hitungTerjadwal();
                        hitungBerangkat();
                        hitungSelesai();
                    }

                    catch (SQLException e1)
                    {

                        e1.printStackTrace();
                    }
                }

        });
    }

    Connection con;
    PreparedStatement pst;

    ResultSet rs;
    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/manistra", "root","");
            System.out.println("Successs");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public void hitungTerdaftar(){
        try{
            pst = con.prepareStatement("select count(*) as totaldf from paket_jamaah where status = 'Terdaftar'");
            rs = pst.executeQuery();
            while (rs.next()) {
                int countdf = rs.getInt("totaldf");
                jumDF.setText(String.valueOf(countdf));
            }
        }catch(SQLException ex){
        }
    }
    public void hitungTerjadwal(){
        try{
            pst = con.prepareStatement("select count(*) as totaldf from paket_jamaah where status = 'Terjadwal'");
            rs = pst.executeQuery();
            while (rs.next()) {
                int countdf = rs.getInt("totaldf");
                jumJD.setText(String.valueOf(countdf));
            }
        }catch(SQLException ex){
        }
    }
    public void hitungSelesai(){
        try{
            pst = con.prepareStatement("select count(*) as totaldf from paket_jamaah where status = 'Selesai'");
            rs = pst.executeQuery();
            while (rs.next()) {
                int countdf = rs.getInt("totaldf");
                jumSL.setText(String.valueOf(countdf));
            }
        }catch(SQLException ex){
        }
    }
    public void hitungBerangkat(){
        try{
            pst = con.prepareStatement("select count(*) as totaldf from paket_jamaah where status = 'Diberangkatkan'");
            rs = pst.executeQuery();
            while (rs.next()) {
                int countdf = rs.getInt("totaldf");
                jumBR.setText(String.valueOf(countdf));
            }
        }catch(SQLException ex){
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("dashboard");
        frame.setContentPane(new dashboard().dashboard);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
