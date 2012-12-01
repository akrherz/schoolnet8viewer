/*
 * Viewer.java
 *
 * Created on May 2, 2003, 2:36 PM
 */

/**
 *
 * @author  akrherz
 */
public class Viewer extends javax.swing.JFrame {
    
    /** Construct the Viewer! */
    public Viewer() {
        
        try{
            
            defaultFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, getClass().getResourceAsStream("lte_italic.ttf") );
            defaultFont = defaultFont.deriveFont(24.0f);
            fatFont = defaultFont.deriveFont(48.0f);
            
        } catch (Exception ex){
            //logViewer.append( ex.getMessage() );
            ex.printStackTrace();
        }
        try{
            loadSettings();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        
    
        initComponents();
        loadStationTable();
        loadCameraTable();
        initMyComponents();
    }
    /**
     * Load the settings for this app from a settings.ini file
     */
    public void loadSettings(){
        //System.err.println("loadSettings()");     
        
        ini = new IniFile("settings.ini");
        dataFile = ini.getValueSafe("Default", "dataFile", "http://mesonet.agron.iastate.edu/data/csv/kcci.dat");
        stationFile = ini.getValueSafe("Default", "stationFile", "http://mesonet.agron.iastate.edu/data/csv/kcci.stns");
        singleSiteDefault = ini.getValueSafe("Default", "singleSiteDefault", "SKCI4");
        cameraFile = ini.getValueSafe("Default", "cameraFile", "http://mesonet.agron.iastate.edu/data/csv/camera.dat");
        radarViews = new Integer(ini.getValueSafe("Default", "views", "0")).intValue();
        radarURLS = new String[radarViews];
        radarLabels = new String[radarViews];
        for (int v=0; v<radarViews; v++)
        {
            radarURLS[v] = ini.getValueSafe("View"+v, "url", "http://mesonet.agron.iastate.edu/data/mwcomp.png");
        radarLabels[v] = ini.getValueSafe("View"+v, "label", "Unknown");
            
        }
        
        if (ini.hasSubject("Proxy")){
            
            
            //logViewer.append("Configuring Proxy...\n");
            proxyHost = ini.getValueSafe("Proxy", "proxyHost","127.0.0.1");
            proxyPort = ini.getValueSafe("Proxy", "proxyPort", "8080");
            // http://www.javaworld.com/javaworld/javatips/jw-javatip42.html
            System.getProperties().put( "proxySet", "true" );
            System.getProperties().put( "proxyHost", proxyHost );
            System.getProperties().put( "proxyPort", proxyPort );
            //logViewer.append("Setting proxyHost to :"+ proxyHost +":\n");
            //logViewer.append("Setting proxyPort to :"+ proxyPort +":\n");
            
            proxyUser = ini.getValue("Proxy", "proxyUser");
            proxyPass = ini.getValue("Proxy", "proxyPass");
            if (proxyUser != null && proxyPass != null){
            System.getProperties().put( "proxyUser", proxyUser );
            System.getProperties().put( "proxyPass", proxyPass );
        }}
        
        ini.saveFile();
        
        //logViewer.append("Setting dataFile to :"+ dataFile +":\n");
        //logViewer.append("Setting stationFile to :"+ stationFile +":\n");
    }
    public String getProxyHost(){
        return proxyHost;
    }
    public String getProxyUser(){
    return proxyUser;
    }
    public String getProxyPort(){
        return proxyPort;
    }
    public String getProxyPass(){
        return proxyPass;
    }
    /**
     * initMyComponents() attempts to set up the tableView page with the correct
     * number of columns and rows.  This way we prevent blank rows.
     */
    public void initMyComponents(){
        String[] colNames = {"ID", "STATION", "TIME", "TEMP", "HUMID", "FEEL", "DIR", "SPEED", "GUST", "RAIN"};
        myDataModel.setColumnIdentifiers(colNames);
        myDataModel.setNumRows(100);
        int numStations = 0;
        
        
        
        
        
        for (int i=0; i < 100; i++){
            if (snames[i] != null){
                numStations += 1;
                SiteSelect.addItem( snames[i] );
                myDataModel.setValueAt(sids[i], i,0);
            }
            if (cameraids[i] != null){
                
                
                /** Create a panel to hold this camera and associated widgets */
                //javax.swing.JPanel tmp = new javax.swing.JPanel();
                //tmp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                
                //JALabel sns = new JALabel();
                //sns.setForeground(new java.awt.Color(255, 255, 255));
                //sns.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                //sns.setText( ((String)cameraNames.get(cameraids[i])).toUpperCase() );
                //sns.setBorder(null);
                //sns.setFont(new java.awt.Font("AlternateGothic2 BT", 0, 24));
                //tmp.add(sns, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 44, 200, 30), ci_Name);
                
                
                //javax.swing.JLabel ttOverlay = new javax.swing.JLabel();
                //ttOverlay.setForeground(new java.awt.Color(255,0,0) );
                //ttOverlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wxdopplerbarscurve.gif")));
                //try {
                //   ttOverlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+ cameraids[i] +"_cam.png")));
                //} catch (Exception ex) { }
                //tmp.add(ttOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 640, 480), ci_Logo);
                //tmp.add(ttOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 35, 200, 100), ci_Logo);
                
                //javax.swing.JLabel rv = new javax.swing.JLabel();
                //tmp.add(rv, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 215, 320, 240), ci_OverlayBoxLL);
                
                //JALabel ttt = new JALabel();
                //ttt.setForeground(new java.awt.Color(250, 250, 250));
                //ttt.setBorder(null);
                //ttt.setBackground(new java.awt.Color(0,0,0, 1) );
                //ttt.setVisible(false);
                //ttt.setFont(new java.awt.Font("AlternateGothic2 BT", 1, 24));
                //ttt.setOpaque(false);
                //ttt.setDoubleBuffered(true);
                //ttt.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                //c_Temperatures.add(i, ttt);
                //tmp.add((JALabel) c_Temperatures.get(i),
                // new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 45, 150, 30), ci_Temperature);
                
                
                //JALabel www =new JALabel();
                // www.setForeground(new java.awt.Color(250, 250, 250));
                //www.setBorder(null);
                //www.setBackground(new java.awt.Color(250,250,250, 50) );
                //www.setVisible(false);
                // www.setFont(new java.awt.Font("AlternateGothic2 BT", 1, 24));
                //www.setOpaque(true);
                //www.setDoubleBuffered(true);
                //www.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                //c_Winds.add(i, www);
                //tmp.add((JALabel) c_Winds.get(i),
                //new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 75, 150, 40), ci_Wind);
                
                //  javax.swing.JPanel shadePane = new javax.swing.JPanel();
                //shadePane.setVisible(false);
                //shadePane.setBackground(new java.awt.Color(0,0,0,80) );
                //tmp.add(shadePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 45, 180, 70), ci_ShadePane);
                
                
                //javax.swing.JLabel lbltmp = new javax.swing.JLabel();
                //lbltmp.setText("Waiting For Image (Press 'Refresh Now') to hurry up the process)");
                // tmp.add(lbltmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 640, 480), ci_Image);
                
                
                
                //cameraStatus.add(i, new javax.swing.JCheckBox("Hide in Loop") );
                //tmp.add((javax.swing.JCheckBox)cameraStatus.get(i),
                //new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 40, 130, 30), ci_LoopHide );
                
                
                
                //c_showRadar.add(i, new javax.swing.JCheckBox("Overlay RADAR") );
                //((javax.swing.JCheckBox)c_showRadar.get(i)).addActionListener(new java.awt.event.ActionListener() {
                //   public void actionPerformed(java.awt.event.ActionEvent evt) {
                //        c_showRadarVisible();
                //    }
                //});
                // tmp.add((javax.swing.JCheckBox)c_showRadar.get(i),
                //new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 80, 150, 30), ci_showOverlayBoxLL);
                
                
                //javax.swing.JToggleButton jtb = new javax.swing.JToggleButton("Refresh RADAR");
                //jtb.addActionListener(new java.awt.event.ActionListener() {
                //    public void actionPerformed(java.awt.event.ActionEvent evt) {
                //        c_showRadarLoad();
                //    }
                //});
                //jtb.setVisible(false);
                //tmp.add(jtb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 140, 20), ci_refreshOverlayBoxLL);
                
                /** ------------------ */
                
                //c_showCurrents.add(i, new javax.swing.JCheckBox("Overlay Currents") );
                //((javax.swing.JCheckBox)c_showCurrents.get(i)).addActionListener(new java.awt.event.ActionListener() {
                //    public void actionPerformed(java.awt.event.ActionEvent evt) {
                //        c_showCurrentsVisible();
                //    }
                //});
                //tmp.add((javax.swing.JCheckBox)c_showCurrents.get(i),
                //new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 150, 150, 30), ci_showCurrentsBoxLL);
                
                
                //javax.swing.JToggleButton jtb2 = new javax.swing.JToggleButton("Refresh Currents");
                //jtb2.addActionListener(new java.awt.event.ActionListener() {
                //    public void actionPerformed(java.awt.event.ActionEvent evt) {
                //        c_showCurrentsLoad();
                //    }
                //});
                //jtb2.setVisible(false);
                // tmp.add(jtb2,
                // new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 140, 20), ci_refreshCurrentsBoxLL);
                
                //javax.swing.JCheckBox jcb = new javax.swing.JCheckBox();
                //jcb.setText("Text Currents");
                //jcb.addActionListener(new java.awt.event.ActionListener() {
                //    public void actionPerformed(java.awt.event.ActionEvent evt) {
                //        c_showWeatherVisible();
                //    }
                // });
                // tmp.add(jcb,
                // new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 140, 20), ci_showWeather);
                
                // cameraTabs.addTab( (String)cameraNames.get(cameraids[i])  , tmp);
                cameraDisplayPane cdp = new cameraDisplayPane(cameraids[i], (String)cameradb.get(cameraids[i]), defaultFont );
                cameraTabs.addTab( (String)cameraNames.get(cameraids[i]), cdp);
            }
        }
        //System.err.println(cameraids.toString());
        myDataModel.setRowCount(numStations);
        Integer loc2 = (Integer)reverseIDs.get(singleSiteDefault);
        if (loc2 != null)
            SiteSelect.setSelectedIndex( loc2.intValue() );
        
        cameraTabs.setVisible(false);
        //cameraRefreshCurrent.setVisible(false);
        //cameraRefreshAll.setVisible(false);
        cameraAllOn.setVisible(false);
        cameraCycleFreq.setVisible(false);
        cameraWeatherOn.setVisible(false);
        enableProxyButton.setSelected( ini.hasSubject("Proxy"));
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        tabbedPane = new javax.swing.JTabbedPane();
        SingleView = new javax.swing.JPanel();
        Mercury = new javax.swing.JProgressBar();
        WindDirection = new javax.swing.JLabel();
        singleSiteMode = new javax.swing.JComboBox();
        SiteSelect = new javax.swing.JComboBox();
        SiteName = new JALabel();
        Temperature = new JALabel();
        WindSpeed = new JALabel();
        Pressure = new JALabel();
        FeelsLike = new JALabel();
        Rainfall = new JALabel();
        Humidity = new JALabel();
        PeakGust = new JALabel();
        TimeStamp = new JALabel();
        SingleBack = new javax.swing.JLabel();
        jALabel1 = new JALabel();
        setSiteDefault = new javax.swing.JButton();
        QuadView = new javax.swing.JPanel();
        WindDirection1 = new javax.swing.JLabel();
        WindDirection2 = new javax.swing.JLabel();
        WindDirection3 = new javax.swing.JLabel();
        WindDirection4 = new javax.swing.JLabel();
        WindSpeed1 = new javax.swing.JTextField();
        WindSpeed2 = new javax.swing.JTextField();
        WindSpeed3 = new javax.swing.JTextField();
        WindSpeed4 = new javax.swing.JTextField();
        SiteName1 = new javax.swing.JTextField();
        SiteName2 = new javax.swing.JTextField();
        SiteName3 = new javax.swing.JTextField();
        SiteName4 = new javax.swing.JTextField();
        Mercury1 = new javax.swing.JProgressBar();
        Mercury2 = new javax.swing.JProgressBar();
        Mercury3 = new javax.swing.JProgressBar();
        Mercury4 = new javax.swing.JProgressBar();
        Temperature1 = new javax.swing.JTextField();
        Temperature2 = new javax.swing.JTextField();
        Temperature3 = new javax.swing.JTextField();
        Temperature4 = new javax.swing.JTextField();
        PeakGust1 = new javax.swing.JTextField();
        PeakGust2 = new javax.swing.JTextField();
        PeakGust3 = new javax.swing.JTextField();
        PeakGust4 = new javax.swing.JTextField();
        Humidity1 = new javax.swing.JTextField();
        Humidity2 = new javax.swing.JTextField();
        Humidity3 = new javax.swing.JTextField();
        Humidity4 = new javax.swing.JTextField();
        Rainfall1 = new javax.swing.JTextField();
        Rainfall2 = new javax.swing.JTextField();
        Rainfall3 = new javax.swing.JTextField();
        Rainfall4 = new javax.swing.JTextField();
        FeelsLike1 = new javax.swing.JTextField();
        FeelsLike2 = new javax.swing.JTextField();
        FeelsLike3 = new javax.swing.JTextField();
        FeelsLike4 = new javax.swing.JTextField();
        DewPoint1 = new javax.swing.JTextField();
        DewPoint2 = new javax.swing.JTextField();
        DewPoint3 = new javax.swing.JTextField();
        DewPoint4 = new javax.swing.JTextField();
        quad1 = new javax.swing.JLabel();
        quad2 = new javax.swing.JLabel();
        quad3 = new javax.swing.JLabel();
        quad4 = new javax.swing.JLabel();
        tableView = new javax.swing.JPanel();
        dataTableScrollPane = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        radarView = new javax.swing.JPanel();
        radarLabel = new javax.swing.JLabel();
        radarSource = new javax.swing.JComboBox();
        radarUpdateFrequency = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cameraView = new javax.swing.JPanel();
        cameraOn = new javax.swing.JCheckBox();
        cameraAllOn = new javax.swing.JCheckBox();
        cameraCycleFreq = new javax.swing.JComboBox();
        cameraWeatherOn = new javax.swing.JCheckBox();
        cameraTabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logViewer = new javax.swing.JTextArea();
        switchingMode = new javax.swing.JComboBox();
        tabSwitchingFrequency = new javax.swing.JComboBox();
        enableProxyButton = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        proxyHostField = new javax.swing.JTextField();
        proxyPortField = new javax.swing.JTextField();
        proxyUserField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        proxyPassField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        statusBar = new javax.swing.JLabel();
        memoryMonitor1 = new MemoryMonitor();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutHelpMenuItem = new javax.swing.JMenuItem();

        setTitle("SchoolNet8 Viewer "+ version);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPane.setDoubleBuffered(true);
        tabbedPane.setName("Single Site"); // NOI18N

        SingleView.setForeground(new java.awt.Color(250, 250, 250));
        SingleView.setName("Single View"); // NOI18N
        SingleView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Mercury.setBackground(new java.awt.Color(255, 255, 255));
        Mercury.setForeground(new java.awt.Color(250, 0, 0));
        Mercury.setMaximum(120);
        Mercury.setMinimum(-20);
        Mercury.setOrientation(1);
        Mercury.setToolTipText("Temperature");
        Mercury.setValue(50);
        Mercury.setBorder(null);
        Mercury.setBorderPainted(false);
        Mercury.setOpaque(false);
        SingleView.add(Mercury, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 133, 10, 203));

        WindDirection.setForeground(new java.awt.Color(255, 255, 255));
        WindDirection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dirs/wind_N.gif"))); // NOI18N
        WindDirection.setText("jLabel3");
        SingleView.add(WindDirection, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 115, 140, 140));

        singleSiteMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "User Select (Normal)", "Peak Wind Gust", "Max Precipitation" }));
        singleSiteMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSingleSiteMode(evt);
            }
        });
        SingleView.add(singleSiteMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 160, 30));

        SiteSelect.setMaximumRowCount(16);
        SiteSelect.setDoubleBuffered(true);
        SiteSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSingleSiteID(evt);
            }
        });
        SingleView.add(SiteSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 160, 30));

        SiteName.setForeground(new java.awt.Color(245, 245, 245));
        SiteName.setText("My Sites Name");
        SiteName.setFont(defaultFont);
        SingleView.add(SiteName, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 85, 200, 30));

        Temperature.setForeground(new java.awt.Color(250, 250, 250));
        Temperature.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Temperature.setText("-99");
        Temperature.setFont(fatFont);
        Temperature.setMaximumSize(new java.awt.Dimension(120, 60));
        Temperature.setMinimumSize(new java.awt.Dimension(120, 60));
        SingleView.add(Temperature, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 395, 120, 70));

        WindSpeed.setForeground(new java.awt.Color(250, 250, 250));
        WindSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WindSpeed.setText("99");
        WindSpeed.setFont(fatFont);
        SingleView.add(WindSpeed, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 137, 80, 90));

        Pressure.setForeground(new java.awt.Color(245, 245, 245));
        Pressure.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pressure.setText("PRESS");
        Pressure.setFont(fatFont);
        SingleView.add(Pressure, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 403, 150, 50));

        FeelsLike.setForeground(new java.awt.Color(245, 245, 245));
        FeelsLike.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FeelsLike.setText("FEELS");
        FeelsLike.setFont(fatFont);
        SingleView.add(FeelsLike, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 323, 150, 50));

        Rainfall.setForeground(new java.awt.Color(245, 245, 245));
        Rainfall.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Rainfall.setText("RAIN");
        Rainfall.setFont(fatFont);
        SingleView.add(Rainfall, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 403, 160, 50));

        Humidity.setForeground(new java.awt.Color(245, 245, 245));
        Humidity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Humidity.setText("HUMID");
        Humidity.setFont(fatFont);
        Humidity.setVerifyInputWhenFocusTarget(false);
        SingleView.add(Humidity, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 245, 150, 50));

        PeakGust.setForeground(new java.awt.Color(245, 245, 245));
        PeakGust.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PeakGust.setText("Peak Gust");
        PeakGust.setFont(defaultFont);
        SingleView.add(PeakGust, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 160, 60));

        TimeStamp.setForeground(new java.awt.Color(245, 245, 245));
        TimeStamp.setText("Timestamp");
        SingleView.add(TimeStamp, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 500, 230, -1));

        SingleBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/base640.png"))); // NOI18N
        SingleBack.setText("jLabel2");
        SingleBack.setAlignmentY(0.0F);
        SingleBack.setMaximumSize(new java.awt.Dimension(640, 480));
        SingleBack.setMinimumSize(new java.awt.Dimension(640, 480));
        SingleView.add(SingleBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 640, 480));

        jALabel1.setText("Select Site");
        SingleView.add(jALabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        setSiteDefault.setText("Set Site as Default");
        setSiteDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setSiteDefaultActionPerformed(evt);
            }
        });
        SingleView.add(setSiteDefault, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        tabbedPane.addTab("Single View", SingleView);

        QuadView.setName("Quad View"); // NOI18N
        QuadView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        WindDirection1.setForeground(new java.awt.Color(255, 255, 255));
        WindDirection1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdirs/wind_N.gif"))); // NOI18N
        WindDirection1.setText("jLabel3");
        QuadView.add(WindDirection1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 70, 70));

        WindDirection2.setForeground(new java.awt.Color(255, 255, 255));
        WindDirection2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdirs/wind_N.gif"))); // NOI18N
        WindDirection2.setText("jLabel3");
        QuadView.add(WindDirection2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 70, 70));

        WindDirection3.setForeground(new java.awt.Color(255, 255, 255));
        WindDirection3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdirs/wind_N.gif"))); // NOI18N
        WindDirection3.setText("jLabel3");
        QuadView.add(WindDirection3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 70, 70));

        WindDirection4.setForeground(new java.awt.Color(255, 255, 255));
        WindDirection4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdirs/wind_N.gif"))); // NOI18N
        WindDirection4.setText("jLabel3");
        QuadView.add(WindDirection4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 70, 70));

        WindSpeed1.setEditable(false);
        WindSpeed1.setFont(new java.awt.Font("Dialog", 0, 18));
        WindSpeed1.setForeground(offWhite);
        WindSpeed1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        WindSpeed1.setText("jTextField1");
        WindSpeed1.setBorder(null);
        WindSpeed1.setOpaque(false);
        QuadView.add(WindSpeed1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 50, 40));

        WindSpeed2.setEditable(false);
        WindSpeed2.setFont(new java.awt.Font("Dialog", 0, 18));
        WindSpeed2.setForeground(offWhite);
        WindSpeed2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        WindSpeed2.setText("jTextField1");
        WindSpeed2.setBorder(null);
        WindSpeed2.setOpaque(false);
        QuadView.add(WindSpeed2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 40, 40));

        WindSpeed3.setEditable(false);
        WindSpeed3.setFont(new java.awt.Font("Dialog", 0, 18));
        WindSpeed3.setForeground(offWhite);
        WindSpeed3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        WindSpeed3.setText("jTextField1");
        WindSpeed3.setBorder(null);
        WindSpeed3.setOpaque(false);
        QuadView.add(WindSpeed3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 40, 40));

        WindSpeed4.setEditable(false);
        WindSpeed4.setFont(new java.awt.Font("Dialog", 0, 18));
        WindSpeed4.setForeground(offWhite);
        WindSpeed4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        WindSpeed4.setText("jTextField1");
        WindSpeed4.setBorder(null);
        WindSpeed4.setOpaque(false);
        QuadView.add(WindSpeed4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, 40, 40));

        SiteName1.setBackground(new java.awt.Color(51, 0, 0));
        SiteName1.setEditable(false);
        SiteName1.setFont(new java.awt.Font("Dialog", 0, 10));
        SiteName1.setForeground(new java.awt.Color(255, 255, 255));
        SiteName1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        SiteName1.setText("jTextField1");
        SiteName1.setBorder(null);
        SiteName1.setOpaque(false);
        QuadView.add(SiteName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 130, 15));

        SiteName2.setBackground(new java.awt.Color(51, 0, 0));
        SiteName2.setEditable(false);
        SiteName2.setFont(new java.awt.Font("Dialog", 0, 10));
        SiteName2.setForeground(new java.awt.Color(255, 255, 255));
        SiteName2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        SiteName2.setText("jTextField1");
        SiteName2.setBorder(null);
        SiteName2.setOpaque(false);
        QuadView.add(SiteName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 130, 15));

        SiteName3.setBackground(new java.awt.Color(51, 0, 0));
        SiteName3.setEditable(false);
        SiteName3.setFont(new java.awt.Font("Dialog", 0, 10));
        SiteName3.setForeground(new java.awt.Color(255, 255, 255));
        SiteName3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        SiteName3.setText("jTextField1");
        SiteName3.setBorder(null);
        SiteName3.setOpaque(false);
        QuadView.add(SiteName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 130, 15));

        SiteName4.setBackground(new java.awt.Color(51, 0, 0));
        SiteName4.setEditable(false);
        SiteName4.setFont(new java.awt.Font("Dialog", 0, 10));
        SiteName4.setForeground(new java.awt.Color(255, 255, 255));
        SiteName4.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        SiteName4.setText("jTextField1");
        SiteName4.setBorder(null);
        SiteName4.setOpaque(false);
        QuadView.add(SiteName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 310, 130, 15));

        Mercury1.setBackground(new java.awt.Color(255, 255, 255));
        Mercury1.setForeground(new java.awt.Color(250, 0, 0));
        Mercury1.setMaximum(120);
        Mercury1.setMinimum(-20);
        Mercury1.setOrientation(1);
        Mercury1.setToolTipText("Temperature");
        Mercury1.setValue(50);
        Mercury1.setBorder(null);
        Mercury1.setBorderPainted(false);
        Mercury1.setOpaque(false);
        QuadView.add(Mercury1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 5, 102));

        Mercury2.setBackground(new java.awt.Color(255, 255, 255));
        Mercury2.setForeground(new java.awt.Color(250, 0, 0));
        Mercury2.setMaximum(120);
        Mercury2.setMinimum(-20);
        Mercury2.setOrientation(1);
        Mercury2.setToolTipText("Temperature");
        Mercury2.setValue(50);
        Mercury2.setBorder(null);
        Mercury2.setBorderPainted(false);
        Mercury2.setOpaque(false);
        QuadView.add(Mercury2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 5, 102));

        Mercury3.setBackground(new java.awt.Color(255, 255, 255));
        Mercury3.setForeground(new java.awt.Color(250, 0, 0));
        Mercury3.setMaximum(120);
        Mercury3.setMinimum(-20);
        Mercury3.setOrientation(1);
        Mercury3.setToolTipText("Temperature");
        Mercury3.setValue(50);
        Mercury3.setBorder(null);
        Mercury3.setBorderPainted(false);
        Mercury3.setOpaque(false);
        QuadView.add(Mercury3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 5, 102));

        Mercury4.setBackground(new java.awt.Color(255, 255, 255));
        Mercury4.setForeground(new java.awt.Color(250, 0, 0));
        Mercury4.setMaximum(120);
        Mercury4.setMinimum(-20);
        Mercury4.setOrientation(1);
        Mercury4.setToolTipText("Temperature");
        Mercury4.setValue(50);
        Mercury4.setBorder(null);
        Mercury4.setBorderPainted(false);
        Mercury4.setOpaque(false);
        QuadView.add(Mercury4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 5, 102));

        Temperature1.setColumns(3);
        Temperature1.setEditable(false);
        Temperature1.setFont(new java.awt.Font("Dialog", 1, 18));
        Temperature1.setForeground(offWhite);
        Temperature1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Temperature1.setText("jTextField1");
        Temperature1.setToolTipText("Temperature");
        Temperature1.setBorder(null);
        Temperature1.setOpaque(false);
        QuadView.add(Temperature1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 60, 30));

        Temperature2.setColumns(3);
        Temperature2.setEditable(false);
        Temperature2.setFont(new java.awt.Font("Dialog", 1, 18));
        Temperature2.setForeground(offWhite);
        Temperature2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Temperature2.setText("jTextField1");
        Temperature2.setToolTipText("Temperature");
        Temperature2.setBorder(null);
        Temperature2.setOpaque(false);
        QuadView.add(Temperature2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 60, 30));

        Temperature3.setColumns(3);
        Temperature3.setEditable(false);
        Temperature3.setFont(new java.awt.Font("Dialog", 1, 18));
        Temperature3.setForeground(offWhite);
        Temperature3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Temperature3.setText("jTextField1");
        Temperature3.setToolTipText("Temperature");
        Temperature3.setBorder(null);
        Temperature3.setOpaque(false);
        QuadView.add(Temperature3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 60, 30));

        Temperature4.setColumns(3);
        Temperature4.setEditable(false);
        Temperature4.setFont(new java.awt.Font("Dialog", 1, 18));
        Temperature4.setForeground(offWhite);
        Temperature4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Temperature4.setText("jTextField1");
        Temperature4.setToolTipText("Temperature");
        Temperature4.setBorder(null);
        Temperature4.setOpaque(false);
        QuadView.add(Temperature4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 60, 30));

        PeakGust1.setEditable(false);
        PeakGust1.setForeground(offWhite);
        PeakGust1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PeakGust1.setText("jTextField1");
        PeakGust1.setBorder(null);
        PeakGust1.setOpaque(false);
        QuadView.add(PeakGust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 80, 25));

        PeakGust2.setEditable(false);
        PeakGust2.setForeground(offWhite);
        PeakGust2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PeakGust2.setText("jTextField1");
        PeakGust2.setBorder(null);
        PeakGust2.setOpaque(false);
        QuadView.add(PeakGust2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 80, 25));

        PeakGust3.setEditable(false);
        PeakGust3.setForeground(offWhite);
        PeakGust3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PeakGust3.setText("jTextField1");
        PeakGust3.setBorder(null);
        PeakGust3.setOpaque(false);
        QuadView.add(PeakGust3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 80, 25));

        PeakGust4.setEditable(false);
        PeakGust4.setForeground(offWhite);
        PeakGust4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PeakGust4.setText("jTextField1");
        PeakGust4.setBorder(null);
        PeakGust4.setOpaque(false);
        QuadView.add(PeakGust4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, 80, 25));

        Humidity1.setEditable(false);
        Humidity1.setFont(new java.awt.Font("Dialog", 0, 18));
        Humidity1.setForeground(offWhite);
        Humidity1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Humidity1.setText("jTextField2");
        Humidity1.setBorder(null);
        Humidity1.setOpaque(false);
        QuadView.add(Humidity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 80, -1));

        Humidity2.setEditable(false);
        Humidity2.setFont(new java.awt.Font("Dialog", 0, 18));
        Humidity2.setForeground(offWhite);
        Humidity2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Humidity2.setText("jTextField2");
        Humidity2.setBorder(null);
        Humidity2.setOpaque(false);
        QuadView.add(Humidity2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, 80, -1));

        Humidity3.setEditable(false);
        Humidity3.setFont(new java.awt.Font("Dialog", 0, 18));
        Humidity3.setForeground(offWhite);
        Humidity3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Humidity3.setText("jTextField2");
        Humidity3.setBorder(null);
        Humidity3.setOpaque(false);
        QuadView.add(Humidity3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 80, -1));

        Humidity4.setEditable(false);
        Humidity4.setFont(new java.awt.Font("Dialog", 0, 18));
        Humidity4.setForeground(offWhite);
        Humidity4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Humidity4.setText("jTextField2");
        Humidity4.setBorder(null);
        Humidity4.setOpaque(false);
        QuadView.add(Humidity4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 390, 80, -1));

        Rainfall1.setEditable(false);
        Rainfall1.setFont(new java.awt.Font("Dialog", 0, 18));
        Rainfall1.setForeground(offWhite);
        Rainfall1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Rainfall1.setText("jTextField1");
        Rainfall1.setBorder(null);
        Rainfall1.setOpaque(false);
        QuadView.add(Rainfall1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 80, 35));

        Rainfall2.setEditable(false);
        Rainfall2.setFont(new java.awt.Font("Dialog", 0, 18));
        Rainfall2.setForeground(offWhite);
        Rainfall2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Rainfall2.setText("jTextField1");
        Rainfall2.setBorder(null);
        Rainfall2.setOpaque(false);
        QuadView.add(Rainfall2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 80, 35));

        Rainfall3.setEditable(false);
        Rainfall3.setFont(new java.awt.Font("Dialog", 0, 18));
        Rainfall3.setForeground(offWhite);
        Rainfall3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Rainfall3.setText("jTextField1");
        Rainfall3.setBorder(null);
        Rainfall3.setOpaque(false);
        QuadView.add(Rainfall3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, 80, 35));

        Rainfall4.setEditable(false);
        Rainfall4.setFont(new java.awt.Font("Dialog", 0, 18));
        Rainfall4.setForeground(offWhite);
        Rainfall4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Rainfall4.setText("jTextField1");
        Rainfall4.setBorder(null);
        Rainfall4.setOpaque(false);
        QuadView.add(Rainfall4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 80, 35));

        FeelsLike1.setEditable(false);
        FeelsLike1.setFont(new java.awt.Font("Dialog", 0, 18));
        FeelsLike1.setForeground(offWhite);
        FeelsLike1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FeelsLike1.setText("jTextField1");
        FeelsLike1.setBorder(null);
        FeelsLike1.setOpaque(false);
        QuadView.add(FeelsLike1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 85, 35));

        FeelsLike2.setEditable(false);
        FeelsLike2.setFont(new java.awt.Font("Dialog", 0, 18));
        FeelsLike2.setForeground(offWhite);
        FeelsLike2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FeelsLike2.setText("jTextField1");
        FeelsLike2.setBorder(null);
        FeelsLike2.setOpaque(false);
        QuadView.add(FeelsLike2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 85, 35));

        FeelsLike3.setEditable(false);
        FeelsLike3.setFont(new java.awt.Font("Dialog", 0, 18));
        FeelsLike3.setForeground(offWhite);
        FeelsLike3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FeelsLike3.setText("jTextField1");
        FeelsLike3.setBorder(null);
        FeelsLike3.setOpaque(false);
        QuadView.add(FeelsLike3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 85, 35));

        FeelsLike4.setEditable(false);
        FeelsLike4.setFont(new java.awt.Font("Dialog", 0, 18));
        FeelsLike4.setForeground(offWhite);
        FeelsLike4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FeelsLike4.setText("jTextField1");
        FeelsLike4.setBorder(null);
        FeelsLike4.setOpaque(false);
        QuadView.add(FeelsLike4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, 85, 35));

        DewPoint1.setEditable(false);
        DewPoint1.setFont(new java.awt.Font("Dialog", 0, 18));
        DewPoint1.setForeground(offWhite);
        DewPoint1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DewPoint1.setText("jTextField1");
        DewPoint1.setBorder(null);
        DewPoint1.setOpaque(false);
        QuadView.add(DewPoint1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 80, 30));

        DewPoint2.setEditable(false);
        DewPoint2.setFont(new java.awt.Font("Dialog", 0, 18));
        DewPoint2.setForeground(offWhite);
        DewPoint2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DewPoint2.setText("jTextField1");
        DewPoint2.setBorder(null);
        DewPoint2.setOpaque(false);
        QuadView.add(DewPoint2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, 80, 30));

        DewPoint3.setEditable(false);
        DewPoint3.setFont(new java.awt.Font("Dialog", 0, 18));
        DewPoint3.setForeground(offWhite);
        DewPoint3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DewPoint3.setText("jTextField1");
        DewPoint3.setBorder(null);
        DewPoint3.setOpaque(false);
        QuadView.add(DewPoint3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 80, 30));

        DewPoint4.setEditable(false);
        DewPoint4.setFont(new java.awt.Font("Dialog", 0, 18));
        DewPoint4.setForeground(offWhite);
        DewPoint4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DewPoint4.setText("jTextField1");
        DewPoint4.setBorder(null);
        DewPoint4.setOpaque(false);
        QuadView.add(DewPoint4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 430, 80, 30));

        quad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quadbase.png"))); // NOI18N
        quad1.setText("jLabel1");
        quad1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        quad1.setAlignmentY(0.0F);
        quad1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maximum Wind Gust", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        quad1.setMaximumSize(new java.awt.Dimension(320, 240));
        quad1.setMinimumSize(new java.awt.Dimension(320, 240));
        QuadView.add(quad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 300, 240));

        quad2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quadbase.png"))); // NOI18N
        quad2.setText(null);
        quad2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        quad2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maximum Rainfall", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        QuadView.add(quad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 300, 240));

        quad3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quadbase.png"))); // NOI18N
        quad3.setText("jLabel4");
        quad3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        quad3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maximum Temperature", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        QuadView.add(quad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 300, 240));

        quad4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quadbase.png"))); // NOI18N
        quad4.setText("View 4");
        quad4.setToolTipText("View 4");
        quad4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        quad4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Minimum Temperature", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        QuadView.add(quad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 300, 240));

        tabbedPane.addTab("Quad View", QuadView);

        tableView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataTable.setModel(dataSorter);
        dataTable.setShowVerticalLines(false);
        dataSorter.addMouseListenerToHeaderInTable(dataTable);
        dataTableScrollPane.setViewportView(dataTable);

        tableView.add(dataTableScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 640, 480));

        tabbedPane.addTab("Table View", tableView);

        radarView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        radarLabel.setText(" RADAR view will update shortly.");
        radarView.add(radarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 640, 480));

        radarSource.setModel(new javax.swing.DefaultComboBoxModel(radarLabels));
        radarSource.setDoubleBuffered(true);
        radarSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeRadarSource(evt);
            }
        });
        radarView.add(radarSource, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 0, 240, 30));

        radarUpdateFrequency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NEVER", "2 minutes", "4 minutes", "6 minutes", "8 minutes", "10 minutes" }));
        radarUpdateFrequency.setSelectedIndex(2);
        radarUpdateFrequency.setDoubleBuffered(true);
        radarUpdateFrequency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setRadarSourceRefresh(evt);
            }
        });
        radarView.add(radarUpdateFrequency, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 0, 120, 30));

        jLabel1.setText("RADAR Source");
        radarView.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 0, 100, 30));

        jLabel2.setText("Update Interval");
        radarView.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 5, 110, 20));

        tabbedPane.addTab("RADAR View", radarView);

        cameraView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cameraOn.setText("Enable");
        cameraOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameraOnActionPerformed(evt);
            }
        });
        cameraView.add(cameraOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 20));

        cameraAllOn.setText("Auto Refresh All");
        cameraAllOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAllCameraTabRefresh(evt);
            }
        });
        cameraView.add(cameraAllOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 150, 20));

        cameraCycleFreq.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Auto Switch Off", "5 sec" }));
        cameraCycleFreq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameraCycleFreqActionPerformed(evt);
            }
        });
        cameraView.add(cameraCycleFreq, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 130, 20));

        cameraWeatherOn.setText("All Weather On");
        cameraWeatherOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameraWeatherSwitch(evt);
            }
        });
        cameraView.add(cameraWeatherOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, 20));

        cameraTabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        cameraTabs.setDoubleBuffered(true);
        cameraView.add(cameraTabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 800, 540));

        tabbedPane.addTab("Camera View", cameraView);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Log Viewer", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP));
        jScrollPane1.setAutoscrolls(true);

        logViewer.setEditable(false);
        logViewer.setText("Log Viewer Initialized");
        logViewer.append("\n");
        jScrollPane1.setViewportView(logViewer);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 230));

        switchingMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default (No auto tab switching)", "Rotate Single Site & RADAR", "Rotate Single Site & Camera", "Rotate All" }));
        switchingMode.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Auto Tab Flipping Mode", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        switchingMode.setDoubleBuffered(true);
        switchingMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeOperationMode(evt);
            }
        });
        jPanel1.add(switchingMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 245, 300, 60));

        tabSwitchingFrequency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15 seconds", "30 seconds", "1 minute" }));
        tabSwitchingFrequency.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Frequency", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        tabSwitchingFrequency.setDoubleBuffered(true);
        tabSwitchingFrequency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAutoTabRefreshInterval(evt);
            }
        });
        jPanel1.add(tabSwitchingFrequency, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 245, 210, 60));

        enableProxyButton.setText("Enable Proxy");
        enableProxyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableProxyButtonActionPerformed(evt);
            }
        });
        jPanel1.add(enableProxyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        jLabel4.setText("Proxy Host");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableProxyButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), jLabel4, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        jLabel5.setText("Proxy Port");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableProxyButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), jLabel5, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, -1, -1));

        jLabel6.setText("Proxy Pass");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableProxyButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), jLabel6, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        proxyHostField.setText(getProxyHost());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableProxyButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), proxyHostField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        proxyHostField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proxyHostFieldActionPerformed(evt);
            }
        });
        jPanel1.add(proxyHostField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 140, -1));

        proxyPortField.setText(getProxyPort());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableProxyButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), proxyPortField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        proxyPortField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proxyPortFieldActionPerformed(evt);
            }
        });
        jPanel1.add(proxyPortField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 140, -1));

        proxyUserField.setText(getProxyUser());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableProxyButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), proxyUserField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        proxyUserField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proxyUserFieldActionPerformed(evt);
            }
        });
        jPanel1.add(proxyUserField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 140, -1));

        jLabel7.setText("Proxy User");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableProxyButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), jLabel7, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        proxyPassField.setText(getProxyPass());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableProxyButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), proxyPassField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        proxyPassField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proxyPassFieldActionPerformed(evt);
            }
        });
        jPanel1.add(proxyPassField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 140, -1));

        jButton1.setText("Save Proxy Settings");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableProxyButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), jButton1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, -1, -1));

        tabbedPane.addTab("Preferences / Log", jPanel1);

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 550));

        statusBar.setForeground(new java.awt.Color(102, 102, 0));
        statusBar.setText("Application Status");
        getContentPane().add(statusBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 550, 540, 20));
        getContentPane().add(memoryMonitor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, -1, -1));

        fileMenu.setText("File");

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText("Help");

        aboutHelpMenuItem.setText("About");
        aboutHelpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayAbout(evt);
            }
        });
        helpMenu.add(aboutHelpMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setSiteDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setSiteDefaultActionPerformed
        // TODO add your handling code here:
        if (sids[SiteSelect.getSelectedIndex()] != null){
            singleSiteID = sids[SiteSelect.getSelectedIndex()];
            ini.setValue("Default", "singleSiteDefault",singleSiteID);
            ini.saveFile();
            logViewer.append("Set singleSiteDefault to "+ singleSiteID +"\n");
            
            statusBar.setText("Settings saved....");
        }
    }//GEN-LAST:event_setSiteDefaultActionPerformed
    
    private void cameraWeatherSwitch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameraWeatherSwitch
        // Add your handling code here:
        boolean b = cameraWeatherOn.isSelected();
        for (int i=0; i < cameraTabs.getTabCount(); i++){
            ((cameraDisplayPane)cameraTabs.getComponentAt(i)).setWeatherPane(b);
        }
    }//GEN-LAST:event_cameraWeatherSwitch
    
    private void setAllCameraTabRefresh(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setAllCameraTabRefresh
        // Add your handling code here:
        for (int i=0; i < cameraTabs.getTabCount(); i++){
            ((cameraDisplayPane)cameraTabs.getComponentAt(i)).setRefreshing( cameraAllOn.isSelected() );
            
        }
    }//GEN-LAST:event_setAllCameraTabRefresh
    
    /***
     * Cycle the camera windows, once every 5 seconds
     */
    private void cameraCycleFreqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameraCycleFreqActionPerformed
        // Add your handling code here:
        int s = cameraCycleFreq.getSelectedIndex();
        if (s == 0) return;
        
        Thread worker = new Thread() {
            public void run() {
                int tabCnt = cameraTabs.getTabCount();
                
                while( cameraCycleFreq.getSelectedIndex() > 0){
                    for (int i =0; i < tabCnt; i++){
                        /** If the cycling has been set to the zero position, quit */
                        if ( cameraCycleFreq.getSelectedIndex() == 0) break;
                        if ( ((cameraDisplayPane) cameraTabs.getComponentAt(i)).hideInLoop()) continue;
                        //if ( ((javax.swing.JCheckBox)cameraStatus.get(i)).isSelected() ) continue;
                        cameraTabs.setSelectedIndex(i);
                        try{
                            Thread.sleep(5000); } catch(Exception ex){ logViewer.append( ex.getMessage() );}
                    }
                }
                
            }
            
        };
        worker.start();
    }//GEN-LAST:event_cameraCycleFreqActionPerformed
    
    private void cameraOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameraOnActionPerformed
        // Add your handling code here:
        if (cameraOn.isSelected()){
            cameraTabs.setVisible(true);
            //cameraRefreshCurrent.setVisible(true);
            //cameraRefreshAll.setVisible(true);
            cameraAllOn.setVisible(true);
            cameraWeatherOn.setVisible(true);
            cameraCycleFreq.setVisible(true);
        } else{
            cameraTabs.setVisible(false);
            //cameraRefreshCurrent.setVisible(false);
            //cameraRefreshAll.setVisible(false);
            cameraWeatherOn.setVisible(false);
            cameraAllOn.setVisible(false);
            cameraCycleFreq.setVisible(false);
        }
    }//GEN-LAST:event_cameraOnActionPerformed
    
    private void displayAbout(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayAbout
        // Add your handling code here:
        String s = "SchoolNet8Viewer written by Daryl Herzmann <akrherz@iastate.edu>"
                +"\n\n License: GPL\n"
                +" Version: "+ version +"\n"
                +" URL: http://www.schoolnet8.com/sview/ \n";
        javax.swing.JOptionPane.showMessageDialog(null, s,
                "About SchoolNet8Viewer", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_displayAbout
    
    /**  Called after a user selects the option they want for the single View page
     * Currently, we support three options
     * 0 - User Select, which means only data dynamically updates, no site switching
     * 1 - Always show the peak wind gust for the site site view
     * 2 - Always show the maxium precipitation site
     */
    private void changeSingleSiteMode(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSingleSiteMode
        // Add your handling code here:
        int i = singleSiteMode.getSelectedIndex();
        singleSiteModeSet = i;
        if (singleSiteModeSet == 0) {
            SiteSelect.setVisible(true);
        }
        if (singleSiteModeSet == 1) {
            singleSiteID = quadExGust;
            Integer loc = (Integer)reverseIDs.get(singleSiteID);
            SiteSelect.setSelectedIndex( loc.intValue() );
            SiteSelect.setVisible(false);
        } else if (singleSiteModeSet == 2) {
            singleSiteID = quadExRain;
            Integer loc2 = (Integer)reverseIDs.get(singleSiteID);
            SiteSelect.setSelectedIndex( loc2.intValue() );
            SiteSelect.setVisible(false);
        }
    }//GEN-LAST:event_changeSingleSiteMode
    /**
     * Set the update interval for refreshing the RADAR image.
     *  Currently, this is sort of a hackish way of doing things...
     */
    private void setRadarSourceRefresh(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setRadarSourceRefresh
        // Add your handling code here:
        int i = radarUpdateFrequency.getSelectedIndex();
        radarSourceRefreshSet = i * 2;
    }//GEN-LAST:event_setRadarSourceRefresh
    
    /**
     * Set the counter for how often the view should switch between the RADAR
     * image and the single site image, which is only mode we currently
     * support
     */
    private void setAutoTabRefreshInterval(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setAutoTabRefreshInterval
        // Add your handling code here:
        int i = tabSwitchingFrequency.getSelectedIndex();
        autoTabSwitchSet = (i + 1) * 15;
        if (i == 2) autoTabSwitchSet = 60; // One Minute
        System.err.println("SET autoTabSwitchSet to "+ autoTabSwitchSet );
    }//GEN-LAST:event_setAutoTabRefreshInterval
    
    /*
    public void c_showRadarLoad(){
        int selectedCamera = cameraTabs.getSelectedIndex();
        final String selectedID = cameraids[selectedCamera];
        java.util.Date now = new java.util.Date();
        final long ticks = now.getTime();
        javax.swing.JPanel sp = (javax.swing.JPanel) cameraTabs.getComponentAt(selectedCamera);
        javax.swing.JLabel mylbl = (javax.swing.JLabel) sp.getComponent(ci_OverlayBoxLL);
        mylbl.setIcon( new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                    new java.net.URL("http://www.schoolnet8.com/GIS/apps/radar/site.php?station="+ selectedID +"&ticks"+ ticks)
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        javax.swing.JToggleButton jtb = (javax.swing.JToggleButton)sp.getComponent(ci_refreshOverlayBoxLL);
        jtb.setSelected(false);
    } */
    /*
    public void c_showCurrentsLoad(){
        int selectedCamera = cameraTabs.getSelectedIndex();
        final String selectedID = cameraids[selectedCamera];
        java.util.Date now = new java.util.Date();
        final long ticks = now.getTime();
        javax.swing.JPanel sp = (javax.swing.JPanel) cameraTabs.getComponentAt(selectedCamera);
        javax.swing.JLabel mylbl = (javax.swing.JLabel) sp.getComponent(ci_OverlayBoxLL);
        mylbl.setIcon( new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                    new java.net.URL("http://kcci.mesonet.agron.iastate.edu/gen/kcci.php?station="+ selectedID +"&ticks"+ ticks)
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        javax.swing.JToggleButton jtb = (javax.swing.JToggleButton)sp.getComponent(ci_refreshCurrentsBoxLL);
        jtb.setSelected(false);
    }
     */
    /*
    public void c_showWeatherVisible(){
        int selectedCamera = cameraTabs.getSelectedIndex();
        javax.swing.JPanel sp = (javax.swing.JPanel) cameraTabs.getComponentAt(selectedCamera);
        if ( ((javax.swing.JCheckBox)sp.getComponent(ci_showWeather)).isSelected() ){
            ((JALabel)sp.getComponent(ci_Temperature)).setVisible(true);
            ((JALabel)sp.getComponent(ci_Wind)).setVisible(true);
            ((javax.swing.JPanel)sp.getComponent(ci_ShadePane)).setVisible(true);
        } else {
            ((JALabel)sp.getComponent(ci_Temperature)).setVisible(false);
            ((JALabel)sp.getComponent(ci_Wind)).setVisible(false);
            ((javax.swing.JPanel)sp.getComponent(ci_ShadePane)).setVisible(true);
        }
    } */
    /*
    public void c_showRadarVisible(){
        int selectedCamera = cameraTabs.getSelectedIndex();
        final String selectedID = cameraids[selectedCamera];
        java.util.Date now = new java.util.Date();
        final long ticks = now.getTime();
        javax.swing.JPanel sp = (javax.swing.JPanel) cameraTabs.getComponentAt(selectedCamera);
        ((javax.swing.JCheckBox)sp.getComponent(ci_showCurrentsBoxLL)).setSelected(false);
        ((javax.swing.JToggleButton)sp.getComponent(ci_refreshCurrentsBoxLL)).setVisible(false);
        javax.swing.JLabel jl = (javax.swing.JLabel) sp.getComponent(ci_OverlayBoxLL);
        javax.swing.JCheckBox jcb = (javax.swing.JCheckBox)sp.getComponent(ci_showOverlayBoxLL);
        javax.swing.JToggleButton jtb = (javax.swing.JToggleButton)sp.getComponent(ci_refreshOverlayBoxLL);
        if (jcb.isSelected()){
            c_showRadarLoad();
            jl.setVisible(true);
            jtb.setVisible(true);
        }else{
            jl.setVisible(false);
            jtb.setVisible(false);
        }
    } */
    /*
    public void c_showCurrentsVisible(){
     
        int selectedCamera = cameraTabs.getSelectedIndex();
        final String selectedID = cameraids[selectedCamera];
        java.util.Date now = new java.util.Date();
        final long ticks = now.getTime();
        javax.swing.JPanel sp = (javax.swing.JPanel) cameraTabs.getComponentAt(selectedCamera);
        ((javax.swing.JCheckBox)sp.getComponent(ci_showOverlayBoxLL)).setSelected(false);
        ((javax.swing.JToggleButton)sp.getComponent(ci_refreshOverlayBoxLL)).setVisible(false);
        javax.swing.JLabel jl = (javax.swing.JLabel) sp.getComponent(ci_OverlayBoxLL);
        javax.swing.JCheckBox jcb = (javax.swing.JCheckBox)sp.getComponent(ci_showCurrentsBoxLL);
        javax.swing.JToggleButton jtb = (javax.swing.JToggleButton)sp.getComponent(ci_refreshCurrentsBoxLL);
        if (jcb.isSelected()){
            c_showCurrentsLoad();
            jl.setVisible(true);
            jtb.setVisible(true);
        }else{
            jl.setVisible(false);
            jtb.setVisible(false);
        }
    } */
    
    /**
     * Change the source ID of the RADAR imagery pane
     */
    private void changeRadarSource(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeRadarSource
        // Add your handling code here:
        int idx = radarSource.getSelectedIndex();
        radarSourceID = idx;
        //System.err.println("SET radarSourceID to "+ radarSourceID );
        refreshRadar();
    }//GEN-LAST:event_changeRadarSource
    
    /**
     * Switch the mode of the autoswitching.  You can turn it on or off.
     */
    private void changeOperationMode(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeOperationMode
        // Add your handling code here:
        int idx = switchingMode.getSelectedIndex();
        if (idx >= 1){
            autoTabSwitch = true;
            String s = "You must switch to the 'Single View'  \n"
                    +" in order for the auto switching to start.\n";
            javax.swing.JOptionPane.showMessageDialog(null, s,
                    "About SchoolNet8Viewer", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            autoTabSwitch = false;
        }
    }//GEN-LAST:event_changeOperationMode
    
    /**
     * Change the single Site ID for the main viewer window
     */
    private void changeSingleSiteID(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSingleSiteID
        // Add your handling code here:
        if (sids[SiteSelect.getSelectedIndex()] != null){
            singleSiteID = sids[SiteSelect.getSelectedIndex()];
            //logViewer.append("Set singleSiteID to "+ singleSiteID +"\n");
            updateValues();
        }
    }//GEN-LAST:event_changeSingleSiteID
    
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

private void proxyHostFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proxyHostFieldActionPerformed
   
    

}//GEN-LAST:event_proxyHostFieldActionPerformed

private void proxyUserFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proxyUserFieldActionPerformed



}//GEN-LAST:event_proxyUserFieldActionPerformed

private void proxyPortFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proxyPortFieldActionPerformed


}//GEN-LAST:event_proxyPortFieldActionPerformed

private void proxyPassFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proxyPassFieldActionPerformed



  
}//GEN-LAST:event_proxyPassFieldActionPerformed

private void enableProxyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableProxyButtonActionPerformed
// TODO add your handling code here:
    if (! ini.hasSubject("Proxy") ) ini.addSubjectLine("Proxy");
    if (! enableProxyButton.isSelected()) ini.deleteSubject("Proxy");
    ini.saveFile();
    logViewer.append("Proxy Settings Saved!\n");
}//GEN-LAST:event_enableProxyButtonActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
     proxyHost = proxyHostField.getText();
     proxyUser = proxyUserField.getText();
     proxyPort = proxyPortField.getText();
     proxyPass = proxyPassField.getText();
     System.getProperties().put( "proxySet", "true" );
     System.getProperties().put( "proxyHost", proxyHost );
     System.getProperties().put( "proxyPort", proxyPort );
     if (proxyUser != null && proxyPass != null){
     System.getProperties().put( "proxyUser", proxyUser );
     System.getProperties().put( "proxyPass", proxyPass );}
    ini.setValue("Proxy", "proxyPort",proxyPort);
    ini.setValue("Proxy", "proxyPass",proxyPass);
    ini.setValue("Proxy", "proxyUser",proxyUser);
    ini.setValue("Proxy", "proxyHost",proxyHost);
    ini.saveFile();
    logViewer.append("Proxy Settings Saved!\n");
}//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws java.lang.InterruptedException{
        
        Viewer v = new Viewer();
        v.show();
        v.refreshRadar(); /** Load it after we init the display */
        while(1==1){
            v.processData();
            v.figureExtremes();
            v.updateValues();
            v.updateCameraValues();
            v.statusBar.setText("Sleeping for one minute");
            Thread.sleep(15000);
            v.autoSwitch();
            Thread.sleep(15000);
            v.autoSwitch();
            Thread.sleep(15000);
            v.autoSwitch();
            Thread.sleep(15000);
            v.autoSwitch();
            v.checkRefreshRadar();
            v.checkRefreshCamera();
        }
    }
    
    public void checkRefreshCamera() {
        
        cameraRefreshCount = cameraRefreshCount + 1;
        if (cameraRefreshCount >= cameraRefreshSet && cameraOn.isSelected() ){
            if ( cameraAllOn.isSelected() ){
                refreshCurrentCameraAll();
            } else {
                refreshCurrentCamera();
            }
            cameraRefreshCount = 0;
        }
    }
    
    public void refreshCurrentCamera(){
        statusBar.setText("Refreshing Web Camera....");
        int selectedCamera = cameraTabs.getSelectedIndex();
        updateCamera(selectedCamera);
        //if ( ((javax.swing.JCheckBox)c_showRadar.get(selectedCamera)).isSelected() ){
        //    c_showRadarLoad();
        // }
        //if ( ((javax.swing.JCheckBox)c_showCurrents.get(selectedCamera)).isSelected() ){
        //    c_showCurrentsLoad();
        //}
    }
    
    public void refreshCurrentCameraAll(){
        statusBar.setText("Refreshing All Web Cameras!");
        for (int i=0; i < cameraTabs.getTabCount(); i++){
            updateCamera(i);
        }
    }
    
    
    public void updateCamera(int selectedCamera){
        
        ((cameraDisplayPane) cameraTabs.getComponentAt(selectedCamera)).refreshCameraScreen();
        
    }
    
    
    /**
     * This runs once per loop to check and see if we need to update the radar source
     * I am still not sure if we can avoid the timeout issues that seem to plague this
     * app if the ibsys site is not responding.
     */
    public void checkRefreshRadar() {
        radarSourceRefreshCount = radarSourceRefreshCount + 1;
        //System.err.println("radarSourceRefreshCount "+ radarSourceRefreshCount +" = radarSourceRefreshSet "+ radarSourceRefreshSet );
        if (radarSourceRefreshCount >= radarSourceRefreshSet && radarSourceRefreshSet > 0) {
            refreshRadar();
        }
    }
    /**
     * Actually, refresh the RADAR source!
     */
    public void refreshRadar(){
        
        java.util.Date now = new java.util.Date();
        final long ticks = now.getTime();
        if (radarURLS.length == 0) return;
        
        statusBar.setText("Fetching RADAR image: "+ radarURLS[radarSourceID] +"?"+ ticks );
        radarLabel.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                            new java.net.URL(radarURLS[radarSourceID] +"?"+ ticks )
                            );
                } catch (java.net.MalformedURLException ex) {
                    logViewer.append( ex.getMessage() );
                }
                return null;
            }
        }.getIcon() );
        radarSourceRefreshCount = 0;
    } // End of refreshRadar()
    
    /**
     * Logic that handles automatically switching between the singleSite pane
     * and the RADAR pane.  Note, that currently you must be in either pane
     * in order to switch over to the other pane.
     */
    public void autoSwitch() {
        //System.err.println("At autoSwitch()");
        if (autoTabSwitch){
            int idx = switchingMode.getSelectedIndex();
            autoTabSwitchCount = autoTabSwitchCount + 15;
            //System.err.println("Switch Count "+ autoTabSwitchCount +" - Switch Set "+ autoTabSwitchSet );
            if ( autoTabSwitchCount  >= autoTabSwitchSet){
                int p = tabbedPane.getSelectedIndex();
                if (switchingMode.getSelectedIndex() == 1) // Single Site -> RADAR
                {
                    //System.err.println("Current Pane is "+ p);
                    if (p == 0)  tabbedPane.setSelectedIndex(3);
                    if (p == 3)  tabbedPane.setSelectedIndex(0);
                } else if (switchingMode.getSelectedIndex() == 2) // Single Site -> Camera
                {
                    if (p == 0)  tabbedPane.setSelectedIndex(4);
                    if (p == 4)  tabbedPane.setSelectedIndex(0);
                } else if (switchingMode.getSelectedIndex() == 3) // Single Site -> RADAR -> Camera
                {
                    if (p == 0)  tabbedPane.setSelectedIndex(3);
                    if (p == 3)  tabbedPane.setSelectedIndex(4);
                    if (p == 4)  tabbedPane.setSelectedIndex(0);
                }
                
                autoTabSwitchCount = 0;
                
            }
        }
    }
    
    /** We need to figure out what extremes exist in the network! */
    public void figureExtremes(){
        int maxHigh = -99;
        int maxLow = 99;
        int maxGust = 0;
        double maxRain = 0.00;
        for(int i=0; i < sids.length; i++){
            String myKey = (String)sids[i];
            if (myKey != null){
                schoolNetOb sob = (schoolNetOb)db.get(myKey);
                if (sob != null){
                    java.lang.Integer v = sob.getAirTemperature().getInteger();
                    if (v != null){
                        if (v.intValue() > maxHigh) {
                            //System.err.println("MAX HI "+ myKey +" "+ v.toString() );
                            maxHigh = v.intValue();
                            quadExHigh = myKey;
                        }
                        if (v.intValue() < maxLow){
                            
                            maxLow = v.intValue();
                            quadExLow = myKey;
                        }
                    }
                    v = sob.getGust().getInteger();
                    if (v != null){
                        if (v.intValue() > maxGust){
                            maxGust = v.intValue();
                            quadExGust = myKey;
                        }}
                    java.lang.Double d = sob.getTodayRainfall().getDouble();
                    if (d != null){
                        if (d.doubleValue() > maxRain){
                            // System.err.println("RAIN "+ myKey +" "+ d.toString() );
                            maxRain = d.doubleValue();
                            quadExRain = myKey;
                        }}
                    
                }
            }
        }
        //System.err.println("Value of singleSiteModeSet is "+ singleSiteModeSet +","+ singleSiteID );
        if (singleSiteModeSet == 1) {
            singleSiteID = quadExGust;
            Integer loc = (Integer)reverseIDs.get(singleSiteID);
            SiteSelect.setSelectedIndex( loc.intValue() );
        } else if (singleSiteModeSet == 2) {
            singleSiteID = quadExRain;
            Integer loc2 = (Integer)reverseIDs.get(singleSiteID);
            SiteSelect.setSelectedIndex( loc2.intValue() );
        }
        //System.err.println("  Leaving with singleSiteID "+ singleSiteID );
    }
    
    public void updateCameraValues(){
        for (int i=0; i < cameraTabs.getTabCount(); i++) {
            ((cameraDisplayPane)cameraTabs.getComponentAt(i)).setSOB( (schoolNetOb)db.get(cameraXref[i]) );
        }
        
        
    }
    
    /**
     * Update the values on the display
     */
    public void updateValues(){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm a");
        
        for (int i=0; i < myDataModel.getRowCount(); i++) {
            String id = (String)myDataModel.getValueAt(i,0);
            //System.err.println("Position "+ i +" is "+ id);
            if (id != null){
                schoolNetOb sob = (schoolNetOb)db.get(id);
                myDataModel.setValueAt(snames[i], i, 1);
                if (sob != null){
                    //System.err.println("Getting updates for "+ id);
                    myDataModel.setValueAt(id, i, 0);
                    
                    myDataModel.setValueAt(sdf.format(sob.getTS()), i, 2);
                    myDataModel.setValueAt(sob.getAirTemperature().getTableValue(-99), i, 3);
                    myDataModel.setValueAt(sob.getHumidity().getTableValue(-99), i, 4);
                    myDataModel.setValueAt(sob.getFeelsLike().getTableValue(-99), i, 5);
                    myDataModel.setValueAt(sob.getWindDirectionText(), i, 6);
                    myDataModel.setValueAt(sob.getWindSpeed().getTableValue(-99), i, 7);
                    myDataModel.setValueAt(sob.getGust().getTableValue(-99), i, 8);
                    myDataModel.setValueAt(sob.getTodayRainfall().getTableValue(-1.00), i, 9);
                }
            }
        }
        dataSorter.sortByColumn( dataSorter.getSortedColumn(), dataSorter.ascending);
        schoolNetOb sob = (schoolNetOb)db.get(singleSiteID);
        if (sob != null){
            WindDirection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dirs/wind_"+ sob.getWindDirectionText() +".gif")));
            
            SiteName.setText(snames[SiteSelect.getSelectedIndex()]);
            Temperature.setText(sob.getAirTemperature().viewString() );
            WindSpeed.setText(sob.getWindSpeed().viewString() );
            Mercury.setValue(sob.getAirTemperature().getInteger(-20).intValue() );
            Pressure.setText(sob.getPressure().viewString("00.00"));
            FeelsLike.setText(sob.getFeelsLike().viewString());
            Rainfall.setText(sob.getTodayRainfall().viewString("0.00"));
            Humidity.setText(sob.getHumidity().viewString("0'%'"));
            PeakGust.setText(sob.getGustDirection() +" @ "+ sob.getGust().viewString() );
            TimeStamp.setText(sob.getTS().toString());
            pack();
        } else {
            WindDirection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dirs/wind_N.gif")));
            //        System.err.println("Setting val to "+ vals[3][3] );
            SiteName.setText(snames[SiteSelect.getSelectedIndex()]);
            Temperature.setText("M");
            WindSpeed.setText("M");
            Mercury.setValue(-20);
            Pressure.setText("M");
            FeelsLike.setText("M");
            Rainfall.setText("M");
            Humidity.setText("M");
            PeakGust.setText("M");
            TimeStamp.setText("Site Offline!");
        }
        
        // ----------------------------------------------------------------------------------
        schoolNetOb q1sob = (schoolNetOb)db.get(quadExGust);
        if (q1sob != null){
            WindDirection1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdirs/wind_"+ q1sob.getWindDirectionText() +".gif")));
            
            long secs1 = new java.lang.Long(q1sob.getTicks()).longValue();
            java.util.Date ts1 = new java.util.Date(secs1 * 1000);
            //        System.err.println("Setting val to "+ vals[3][3] );
            SiteName1.setText( (String)sdb.get(quadExGust) );
            Temperature1.setText(q1sob.getAirTemperature().viewString());
            WindSpeed1.setText(q1sob.getWindSpeed().viewString());
            Mercury1.setValue(q1sob.getAirTemperature().getInteger(-20).intValue() );
            DewPoint1.setText(q1sob.getDewPoint().viewString());
            FeelsLike1.setText(q1sob.getFeelsLike().viewString());
            Rainfall1.setText(q1sob.getTodayRainfall().viewString("0.00"));
            Humidity1.setText(q1sob.getHumidity().viewString("0'%'"));
            PeakGust1.setText(q1sob.getGustDirection() +" @ "+ q1sob.getGust().viewString() );
            //            TimeStamp1.setText(ts1.toString());
            pack();
        }
        // ----------------------------------------------------------------------------------
        schoolNetOb q2sob = (schoolNetOb)db.get(quadExRain);
        if (q2sob != null){
            WindDirection2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdirs/wind_"+ q2sob.getWindDirectionText() +".gif")));
            
            long secs2 = new java.lang.Long(q2sob.getTicks()).longValue();
            java.util.Date ts2 = new java.util.Date(secs2 * 1000);
            //        System.err.println("Setting val to "+ vals[3][3] );
            SiteName2.setText( (String)sdb.get(quadExRain) );
            Temperature2.setText(q2sob.getAirTemperature().viewString());
            WindSpeed2.setText(q2sob.getWindSpeed().viewString());
            Mercury2.setValue(q2sob.getAirTemperature().getInteger(-20).intValue() );
            DewPoint2.setText(q2sob.getDewPoint().viewString());
            FeelsLike2.setText(q2sob.getFeelsLike().viewString());
            Rainfall2.setText(q2sob.getTodayRainfall().viewString("0.00"));
            Humidity2.setText(q2sob.getHumidity().viewString("0'%'"));
            PeakGust2.setText(q2sob.getGustDirection() +" @ "+ q2sob.getGust().viewString() );
            //            TimeStamp1.setText(ts1.toString());
            pack();
        }
        
        // ----------------------------------------------------------------------------------
        schoolNetOb q3sob = (schoolNetOb)db.get(quadExHigh);
        if (q3sob != null){
            WindDirection1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdirs/wind_"+ q3sob.getWindDirectionText() +".gif")));
            
            long secs3 = new java.lang.Long(q3sob.getTicks()).longValue();
            java.util.Date ts3 = new java.util.Date(secs3 * 1000);
            //        System.err.println("Setting val to "+ vals[3][3] );
            SiteName3.setText( (String)sdb.get(quadExHigh) );
            Temperature3.setText(q3sob.getAirTemperature().viewString());
            WindSpeed3.setText(q3sob.getWindSpeed().viewString());
            Mercury3.setValue(q3sob.getAirTemperature().getInteger(-20).intValue() );
            DewPoint3.setText(q3sob.getDewPoint().viewString());
            FeelsLike3.setText(q3sob.getFeelsLike().viewString());
            Rainfall3.setText(q3sob.getTodayRainfall().viewString("0.00"));
            Humidity3.setText(q3sob.getHumidity().viewString("0'%'"));
            PeakGust3.setText(q3sob.getGustDirection() +" @ "+ q3sob.getGust().viewString() );
            //            TimeStamp1.setText(ts1.toString());
            pack();
        }
        
        // ----------------------------------------------------------------------------------
        schoolNetOb q4sob = (schoolNetOb)db.get(quadExLow);
        if (q4sob != null){
            WindDirection4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sdirs/wind_"+ q4sob.getWindDirectionText() +".gif")));
            
            long secs4 = new java.lang.Long(q4sob.getTicks()).longValue();
            java.util.Date ts4 = new java.util.Date(secs4 * 1000);
            //        System.err.println("Setting val to "+ vals[3][3] );
            SiteName4.setText( (String)sdb.get(quadExLow));
            Temperature4.setText(q4sob.getAirTemperature().viewString());
            WindSpeed4.setText(q4sob.getWindSpeed().viewString());
            Mercury4.setValue(q4sob.getAirTemperature().getInteger(-20).intValue() );
            DewPoint4.setText(q4sob.getDewPoint().viewString());
            FeelsLike4.setText(q4sob.getFeelsLike().viewString());
            Rainfall4.setText(q4sob.getTodayRainfall().viewString("0.00"));
            Humidity4.setText(q4sob.getHumidity().viewString("0'%'"));
            PeakGust4.setText(q4sob.getGustDirection() +" @ "+ q4sob.getGust().viewString() );
            //            TimeStamp1.setText(ts1.toString());
            pack();
        }
    }
    
    
    public void loadStationTable(){
        statusBar.setText("Loading station table...");
        java.net.URL url ;
        java.io.DataInputStream dis;
        try {
            url = new java.net.URL(stationFile +"?v="+ version);
            java.net.URLConnection con = url.openConnection();
            dis = new java.io.DataInputStream(con.getInputStream());
            String s;
            int rowID = 0;
            String[] cols;
            java.util.StringTokenizer st;
            while ((s = dis.readLine()) != null){
                int j = 0;
                
                st = new java.util.StringTokenizer(s, ",");
                cols = new String[st.countTokens()];
                while (st.hasMoreTokens()){
                    cols[j] = st.nextToken();
                    j = j + 1;
                }
                if (rowID > 0){
                    sids[rowID - 1] = cols[0];
                    snames[rowID - 1] = cols[1];
                    sdb.put(cols[0], cols[1]);
                    reverseIDs.put(cols[0], new java.lang.Integer(rowID - 1) );
                }
                rowID = rowID + 1;
            }
        }catch(Exception ex){
            logViewer.append( ex.getMessage() );
        }
    }
    
    /**
     * Something to load a camera settings file from the server
     */
    public void loadCameraTable(){
        statusBar.setText("Loading camera table...");
        java.net.URL url ;
        java.io.DataInputStream dis;
        try {
            url = new java.net.URL(cameraFile +"?v="+ version);
            java.net.URLConnection con = url.openConnection();
            dis = new java.io.DataInputStream(con.getInputStream());
            String s;
            int rowID = 0;
            String[] cols;
            java.util.StringTokenizer st;
            while ((s = dis.readLine()) != null){
                int j = 0;
                st = new java.util.StringTokenizer(s, ",");
                cols = new String[st.countTokens()];
                while (st.hasMoreTokens()){
                    cols[j] = st.nextToken();
                    j = j + 1;
                }
                if (rowID > 0){ /* Skip the Comma delimited header */
                    cameraids[rowID - 1] = cols[0];
                    cameradb.put(cols[0], cols[1]);
                    cameraNames.put(cols[0], cols[2]);
                    cameraXref[rowID -1] = cols[3];
                }
                rowID = rowID + 1;
            }
        }catch(Exception ex){
            logViewer.append( ex.getMessage() );
        }
        statusBar.setText("Done loading camera table.");
    }
    
    public void processData(){
        java.lang.StringBuffer sb = new java.lang.StringBuffer();
        java.io.DataInputStream dis;
        java.util.Date now = new java.util.Date();
        long ticks = now.getTime();
        try{
            java.net.URL myURL = new java.net.URL(dataFile +"?"+ ticks );
            java.net.URLConnection con = myURL.openConnection();
            dis = new java.io.DataInputStream(con.getInputStream());
            String s;
            int rowID = 0;
            String[] cols = new String[30];
            while ((s = dis.readLine()) != null){
                if (rowID == 0){ // Format String!
                    java.util.StringTokenizer st = new java.util.StringTokenizer(s, ",");
                    int j = 0;
                    while(st.hasMoreTokens()){
                        cols[j] = st.nextToken();
                        j = j +1;
                    }
                } else { // Data
                    //System.err.println(s+"\n");
                    //java.util.StringTokenizer st = new java.util.StringTokenizer(s, ",");
                    schoolNetOb sob = new schoolNetOb(s, cols);
                    //System.err.println(sob.stationID);
                    //System.err.println( (sob.getTicks() * 1000)  - (ticks) );
                    if ( (sob.getTicks() * 1000) > (ticks - 3600000) ) {
                        db.put(sob.stationID, sob);
                    } else { /** If a station goes offline, make sure to set null */
                        db.remove(sob.stationID);
                    }
                }
                rowID = rowID + 1;
                
            }
            
        }catch(Exception ex){
            logViewer.append( ex.getMessage() );
            
        }
        //logViewer.append(new java.util.Date().toString() +" Processed new data...\n");
    }
    
    private final static String version = "1.5.3";
    private IniFile ini;
    private static String dataFile;
    private static String stationFile;
    private static String cameraFile;
    
    private static String proxyHost;
    private static String proxyPort;
    private static String proxyUser;
    private static String proxyPass;
    
    private java.util.Hashtable db = new java.util.Hashtable(70);
    private java.util.Hashtable sdb = new java.util.Hashtable(70);
    private java.util.Hashtable cameradb = new java.util.Hashtable();
    private java.util.Hashtable cameraNames = new java.util.Hashtable();
    
    private java.util.Hashtable reverseIDs = new java.util.Hashtable(70);
    private String singleSiteID = new String("SKCI4");
    private String singleSiteDefault = null;
    private String quadExGust = new String("SKCI4");
    private String quadExRain = new String("SKCI4");
    private String quadExHigh = new String("SKCI4");
    private String quadExLow  = new String("SKCI4");
    private String[] sids = new String[100];
    private String[] cameraids = new String[100];
    private String[] cameraXref = new String[100];
    private String[] snames = new String[100];
    private int radarViews = 6;
    private String[] radarURLS ;
    private String[] radarLabels ;
    /*
    private String[] radarURLS = new String[]
    {"http://images.ibsys.com/des/images/weather/auto/radar_640x480.jpg",
             "http://images.ibsys.com/des/images/weather/auto/metroradar_640x480.jpg",
             "http://images.ibsys.com/des/images/weather/auto/northeast_radar_640x480.jpg",
             "http://images.ibsys.com/des/images/weather/auto/northwest_radar_640x480.jpg",
             "http://images.ibsys.com/des/images/weather/auto/southeast_radar_640x480.jpg",
             "http://images.ibsys.com/des/images/weather/auto/southwest_radar_640x480.jpg"};
    private String[] radarLabels = new String[]
    {"Statewide", "Metro", "Northeast", "Northwest", "Southeast", "Southwest"};
     */
             private int radarSourceID = 0;
             private int radarSourceRefreshCount = 0;
             private int radarSourceRefreshSet = 5;
             private int cameraRefreshCount = 0;
             private int cameraRefreshSet = 5;
             
             private myTableModel myDataModel = new myTableModel();
             private TableSorter dataSorter = new TableSorter(myDataModel);
             
          private static boolean autoTabSwitch = false;
             private int autoTabSwitchCount = 0;
             private int autoTabSwitchSet = 15;
             
             private int singleSiteModeSet = 0;
             
             /** Indexes on the camera pans
              * private int ci_Name = 0;
              * private int ci_Logo = 1;
              * private int ci_OverlayBoxLL = 2;
              * private int ci_Temperature = 3;
              * private int ci_Wind = 4;
              * private int ci_ShadePane = 5;
              * private int ci_Image = 6;
              * private int ci_LoopHide = 7;
              * private int ci_showOverlayBoxLL = 8;
              * private int ci_refreshOverlayBoxLL = 9;
              * private int ci_showCurrentsBoxLL = 10;
              * private int ci_refreshCurrentsBoxLL = 11;
              * private int ci_showWeather = 12; */
             
             
             /** GUI Constants */
             private java.util.Vector cameraStatus = new java.util.Vector(15);
             private java.util.Vector c_showRadar = new java.util.Vector(15);
             private java.util.Vector c_showCurrents = new java.util.Vector(15);
             private java.util.Vector c_Temperatures = new java.util.Vector(15);
             private java.util.Vector c_Winds = new java.util.Vector(15);
             private final java.awt.Color offWhite = new java.awt.Color(250, 250, 250);
             private java.awt.Font defaultFont = new java.awt.Font("Dialog", 0, 12);
             private java.awt.Font fatFont = new java.awt.Font("Dialog", 0, 12);
             
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DewPoint1;
    private javax.swing.JTextField DewPoint2;
    private javax.swing.JTextField DewPoint3;
    private javax.swing.JTextField DewPoint4;
    private JALabel FeelsLike;
    private javax.swing.JTextField FeelsLike1;
    private javax.swing.JTextField FeelsLike2;
    private javax.swing.JTextField FeelsLike3;
    private javax.swing.JTextField FeelsLike4;
    private JALabel Humidity;
    private javax.swing.JTextField Humidity1;
    private javax.swing.JTextField Humidity2;
    private javax.swing.JTextField Humidity3;
    private javax.swing.JTextField Humidity4;
    private javax.swing.JProgressBar Mercury;
    private javax.swing.JProgressBar Mercury1;
    private javax.swing.JProgressBar Mercury2;
    private javax.swing.JProgressBar Mercury3;
    private javax.swing.JProgressBar Mercury4;
    private JALabel PeakGust;
    private javax.swing.JTextField PeakGust1;
    private javax.swing.JTextField PeakGust2;
    private javax.swing.JTextField PeakGust3;
    private javax.swing.JTextField PeakGust4;
    private JALabel Pressure;
    private javax.swing.JPanel QuadView;
    private JALabel Rainfall;
    private javax.swing.JTextField Rainfall1;
    private javax.swing.JTextField Rainfall2;
    private javax.swing.JTextField Rainfall3;
    private javax.swing.JTextField Rainfall4;
    private javax.swing.JLabel SingleBack;
    private javax.swing.JPanel SingleView;
    private JALabel SiteName;
    private javax.swing.JTextField SiteName1;
    private javax.swing.JTextField SiteName2;
    private javax.swing.JTextField SiteName3;
    private javax.swing.JTextField SiteName4;
    private javax.swing.JComboBox SiteSelect;
    private JALabel Temperature;
    private javax.swing.JTextField Temperature1;
    private javax.swing.JTextField Temperature2;
    private javax.swing.JTextField Temperature3;
    private javax.swing.JTextField Temperature4;
    private JALabel TimeStamp;
    private javax.swing.JLabel WindDirection;
    private javax.swing.JLabel WindDirection1;
    private javax.swing.JLabel WindDirection2;
    private javax.swing.JLabel WindDirection3;
    private javax.swing.JLabel WindDirection4;
    private JALabel WindSpeed;
    private javax.swing.JTextField WindSpeed1;
    private javax.swing.JTextField WindSpeed2;
    private javax.swing.JTextField WindSpeed3;
    private javax.swing.JTextField WindSpeed4;
    private javax.swing.JMenuItem aboutHelpMenuItem;
    private javax.swing.JCheckBox cameraAllOn;
    private javax.swing.JComboBox cameraCycleFreq;
    private javax.swing.JCheckBox cameraOn;
    private javax.swing.JTabbedPane cameraTabs;
    private javax.swing.JPanel cameraView;
    private javax.swing.JCheckBox cameraWeatherOn;
    private javax.swing.JTable dataTable;
    private javax.swing.JScrollPane dataTableScrollPane;
    private javax.swing.JCheckBox enableProxyButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private JALabel jALabel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logViewer;
    private MemoryMonitor memoryMonitor1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField proxyHostField;
    private javax.swing.JPasswordField proxyPassField;
    private javax.swing.JTextField proxyPortField;
    private javax.swing.JTextField proxyUserField;
    private javax.swing.JLabel quad1;
    private javax.swing.JLabel quad2;
    private javax.swing.JLabel quad3;
    private javax.swing.JLabel quad4;
    private javax.swing.JLabel radarLabel;
    private javax.swing.JComboBox radarSource;
    private javax.swing.JComboBox radarUpdateFrequency;
    private javax.swing.JPanel radarView;
    private javax.swing.JButton setSiteDefault;
    private javax.swing.JComboBox singleSiteMode;
    private javax.swing.JLabel statusBar;
    private javax.swing.JComboBox switchingMode;
    private javax.swing.JComboBox tabSwitchingFrequency;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel tableView;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    
    class myTableModel extends javax.swing.table.DefaultTableModel {
        
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class
        };
        
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false, false, false, false
        };
        
        
        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }
        
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
        
    }
}

