package views;

import models.*;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GameShopGUI extends JFrame {
    private LinkedList<Game> gameList = new LinkedList<>();
    private UserManager userManager;
    private String currentUser = null;
    private String currentRole = null;
    private CardLayout cardLayout;
    private JPanel containerPanel;

    // Komponen UI
    private JTextField txtUsername, txtPassword;
    private JComboBox<String> roleComboBox;
    private JTextField txtNama, txtHarga, txtKategori, txtMinimumOS, txtRequiredSpecs;
    private JButton btnLogin, btnRegister, btnLogout;
    private JButton btnMobile, btnPC, btnDisplay, btnDelete;
    private JTextArea txtDisplay;

    public GameShopGUI() {
        userManager = new UserManager();
        initializeFrame();
        createPanels();
    }

    private void initializeFrame() {
        setTitle("Penjualan Game");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        add(containerPanel);
    }

    private void createPanels() {
        // Panel Login
        JPanel loginPanel = createLoginPanel();
        JPanel mainPanel = createMainPanel();

        containerPanel.add(loginPanel, "LOGIN");
        containerPanel.add(mainPanel, "MAIN");
        cardLayout.show(containerPanel, "LOGIN");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel Kiri
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(70, 130, 180));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Judul
        JLabel titleLabel = new JLabel("Zetnineteen Shop");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(40));

        // Form Login
        JPanel loginForm = new JPanel();
        loginForm.setLayout(new GridLayout(7, 1, 5, 10));
        loginForm.setOpaque(false);

        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        roleComboBox = new JComboBox<>(new String[]{"seller", "buyer"});
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");

        loginForm.add(new JLabel("Username:", SwingConstants.CENTER));
        loginForm.add(txtUsername);
        loginForm.add(new JLabel("Password:", SwingConstants.CENTER));
        loginForm.add(txtPassword);
        loginForm.add(new JLabel("Role:", SwingConstants.CENTER));
        loginForm.add(roleComboBox);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);
        loginForm.add(buttonPanel);

        leftPanel.add(loginForm);
        panel.add(leftPanel, BorderLayout.WEST);

        setupLoginHandlers();

        return panel;
    }

    private void setupLoginHandlers() {
        btnLogin.addActionListener(e -> {
            if (userManager.authenticateUser(txtUsername.getText(), txtPassword.getText())) {
                currentUser = txtUsername.getText();
                currentRole = userManager.getUserRole(currentUser);
                cardLayout.show(containerPanel, "MAIN");
                updateUIForRole();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah!");
            }
        });

        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String role = (String) roleComboBox.getSelectedItem();

            userManager.addUser(username, password, role);
            JOptionPane.showMessageDialog(this, "Registrasi berhasil!");
        });
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel Kiri
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(70, 130, 180));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("Zetnineteen Shop");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(lblTitle);

        // Info User
        JLabel lblUser = new JLabel("User: " + currentUser);
        lblUser.setForeground(Color.WHITE);
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(lblUser);

        // Tombol Logout
        btnLogout = new JButton("Logout");
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.addActionListener(e -> {
            currentUser = null;
            currentRole = null;
            cardLayout.show(containerPanel, "LOGIN");
        });
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(btnLogout);

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Panel Kanan
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form Input
        gbc.gridx = 0; gbc.gridy = 0;
        rightPanel.add(new JLabel("Nama Game:"), gbc);
        gbc.gridx = 1;
        txtNama = new JTextField(20);
        rightPanel.add(txtNama, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        rightPanel.add(new JLabel("Harga:"), gbc);
        gbc.gridx = 1;
        txtHarga = new JTextField(20);
        rightPanel.add(txtHarga, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        rightPanel.add(new JLabel("Kategori:"), gbc);
        gbc.gridx = 1;
        txtKategori = new JTextField(20);
        rightPanel.add(txtKategori, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        rightPanel.add(new JLabel("Minimum OS (Mobile):"), gbc);
        gbc.gridx = 1;
        txtMinimumOS = new JTextField(20);
        rightPanel.add(txtMinimumOS, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        rightPanel.add(new JLabel("Required Specs (PC):"), gbc);
        gbc.gridx = 1;
        txtRequiredSpecs = new JTextField(20);
        rightPanel.add(txtRequiredSpecs, gbc);

        // Tombol-tombol
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnMobile = new JButton("Add Mobile Game");
        btnPC = new JButton("Add PC Game");
        btnDisplay = new JButton("Display Games");
        btnDelete = new JButton("Delete Game (Purchase)");

        buttonPanel.add(btnMobile);
        buttonPanel.add(btnPC);
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnDelete);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        rightPanel.add(buttonPanel, gbc);

        // Area Display
        txtDisplay = new JTextArea(10, 40);
        txtDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtDisplay);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        rightPanel.add(scrollPane, gbc);

        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Tambahkan action listeners yang sudah ada
        addMainPanelListeners();

        return mainPanel;
    }

    private void addMainPanelListeners() {
        btnMobile.addActionListener(e -> {
            try {
                String nama = txtNama.getText();
                double harga = Double.parseDouble(txtHarga.getText());
                String kategori = txtKategori.getText();
                String minimumOS = txtMinimumOS.getText();

                MobileGame mobileGame = new MobileGame(nama, harga, kategori, minimumOS);
                gameList.add(mobileGame);
                txtDisplay.append(mobileGame.toString() + " [Added]\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Inputan harga tidak valid!");
            }
        });

        btnPC.addActionListener(e -> {
            try {
                String nama = txtNama.getText();
                double harga = Double.parseDouble(txtHarga.getText());
                String kategori = txtKategori.getText();
                String requiredSpecs = txtRequiredSpecs.getText();

                PCGame pcGame = new PCGame(nama, harga, kategori, requiredSpecs);
                gameList.add(pcGame);
                txtDisplay.append(pcGame.toString() + " [Added]\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Inputan harga tidak valid!");
            }
        });

        btnDisplay.addActionListener(e -> {
            txtDisplay.setText("");
            if (gameList.isEmpty()) {
                txtDisplay.append("Tidak Ada Game yang Tersedia!\n");
            } else {
                for (Game game : gameList) {
                    txtDisplay.append(game.toString() + "\n");
                }
            }
        });

        btnDelete.addActionListener(e -> {
            if (!gameList.isEmpty()) {
                Game removedGame = gameList.removeFirst();
                txtDisplay.append(removedGame.getNama() + " telah dibeli. Terima kasih!\n");
            } else {
                txtDisplay.append("Tidak ada game yang dapat dibeli.\n");
            }
        });
    }

    private void updateUIForRole() {
        if ("buyer".equals(currentRole)) {
            btnMobile.setEnabled(false);
            btnPC.setEnabled(false);
        } else {
            btnMobile.setEnabled(true);
            btnPC.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameShopGUI().setVisible(true));
    }
}