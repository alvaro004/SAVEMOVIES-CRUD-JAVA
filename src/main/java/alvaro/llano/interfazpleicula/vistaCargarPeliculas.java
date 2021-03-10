/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alvaro.llano.interfazpleicula;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexionDB.Conectar;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

/**
 *
 * @author Alvaro Llano
 */
public final class vistaCargarPeliculas extends javax.swing.JInternalFrame {

    
    //----------------------------------------------------------//
    Connection coneccionsql;
    Statement sentenciasql;
    DefaultTableModel modelo_tabla;
    //----------------------------------------------------------//  
    ImageIcon iconobtn = new ImageIcon("src/main/java/imagenes/refresh2.png");
    ImageIcon iconobtn2 = new ImageIcon("src/main/java/imagenes/add.png");
    
    /**
     * Creates new form vistaCargarPeliculas
     */
    public vistaCargarPeliculas() {
        initComponents();
        
        updatecombobox.setIcon(iconobtn);
        registrar_genero.setIcon(iconobtn2);
        this.getContentPane().setBackground(getBackground());
        TextPrompt placeholder1 = new TextPrompt("Ingrese el nombre de la pelicula", nombre_pelicula);
        TextPrompt placeholder2 = new TextPrompt("Ingrese el director de la pelicula", director_pelicula);
        coneccionsql = Conectar.getConnection();
        LlenarTabla();
        CargarGenero();
        
        //updatecombobox.setIcon(new ImageIcon("/alvaro.llano.interfazpleicula/iconfinder_free-38_974778.png"));
        //registrar_genero.setIcon(new ImageIcon("iconfinder_free-38_974778.png"));
        
        BotonEditar.setEnabled(false);
        BotonBorrar.setEnabled(false);
    }
    
    
    public void CargarGenero() {
        
        try {
            String stsql = "SELECT * fROM genero WHERE status <> 0 order by nombre_genero";
            sentenciasql = coneccionsql.createStatement();
            ResultSet rs = sentenciasql.executeQuery(stsql);
            while (rs.next()) {
                 genero.addItem(rs.getString("nombre_genero"));
                 
            }
            rs.close();
        } catch (Exception e) {
            System.err.println("ERROR AL INTENTAR LISTAR EL GENERO:" + e);
        }
        
    }
    
    void LlenarTabla() {
        try {
            //encabezados de la tabla
            String[] titulos = {"ID", "Pelicula", "Director", "Año", "Genero"};
            String stsql = "SELECT pelicula.id_pelicula, pelicula.nombre_pelicula, director_pelicula, pelicula.fecha_pelicula, genero.nombre_genero FROM `pelicula` JOIN `genero` ON pelicula.genero_id = genero.id_genero";
            modelo_tabla = new DefaultTableModel(null, titulos);
            sentenciasql = coneccionsql.createStatement();
            ResultSet rs = sentenciasql.executeQuery(stsql);

            //arreglo fila, para almacenar registros
            String[] fila = new String[5];
            
            //while para insertar registros en la tabla
            
            while (rs.next()) {
                fila[0] = rs.getString("id_pelicula");
                fila[1] = rs.getString("nombre_pelicula");
                fila[2] = rs.getString("director_pelicula");
                fila[3] = rs.getString("fecha_pelicula");
                fila[4] = rs.getString("nombre_genero");

                modelo_tabla.addRow(fila);
            }

            //agrego el default model
            TablaPersonas.setModel(modelo_tabla);

            //definir el ancho de las columnas
            int[] anchos = {25, 100, 100, 25, 50};
            for (int i = 0; i < TablaPersonas.getColumnCount(); i++) {
                TablaPersonas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            //escondemos la columna id en la tabla (finalidad estetica)
            //TablaPersonas.getColumnModel().getColumn(0).setMinWidth(0);
            //TablaPersonas.getColumnModel().getColumn(0).setMaxWidth(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Funcion que busca en la base de datos el el numero del id pasandole el nombre del genero
    int getIdGenero(String nombregenero){
        try {
            String stsql = "SELECT id_genero FROM genero WHERE nombre_genero = '" + nombregenero + "'";
            sentenciasql = coneccionsql.createStatement();
            ResultSet rs = sentenciasql.executeQuery(stsql);
            if (rs.next()) {
                return Integer.parseInt(rs.getString("id_genero"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(vistaCargarPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktoppanel = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombre_pelicula = new javax.swing.JTextField();
        director_pelicula = new javax.swing.JTextField();
        genero = new javax.swing.JComboBox<>();
        registrar_genero = new javax.swing.JButton();
        fecha_pelicula = new com.toedter.calendar.JYearChooser();
        updatecombobox = new javax.swing.JButton();
        BotonGuardar = new javax.swing.JButton();
        BotonEditar = new javax.swing.JButton();
        BotonBorrar = new javax.swing.JButton();
        CampoID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPersonas = new javax.swing.JTable();
        btnPDF = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setTitle("Cargar Peliculas");

        jPanel1.setBackground(new java.awt.Color(227, 234, 248));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(29, 43, 105));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTAR PELICULAS");

        nombre_pelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_peliculaActionPerformed(evt);
            }
        });

        director_pelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                director_peliculaActionPerformed(evt);
            }
        });

        genero.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                generoMouseMoved(evt);
            }
        });
        genero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                generoMousePressed(evt);
            }
        });
        genero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generoActionPerformed(evt);
            }
        });

        registrar_genero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registrar_generoMouseClicked(evt);
            }
        });
        registrar_genero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrar_generoActionPerformed(evt);
            }
        });

        fecha_pelicula.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        updatecombobox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatecomboboxMouseClicked(evt);
            }
        });
        updatecombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatecomboboxActionPerformed(evt);
            }
        });

        BotonGuardar.setBackground(new java.awt.Color(0, 255, 51));
        BotonGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BotonGuardar.setForeground(new java.awt.Color(255, 255, 255));
        BotonGuardar.setText("GUARDAR");
        BotonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonGuardarMouseClicked(evt);
            }
        });
        BotonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGuardarActionPerformed(evt);
            }
        });

        BotonEditar.setBackground(new java.awt.Color(102, 102, 102));
        BotonEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BotonEditar.setForeground(new java.awt.Color(255, 255, 255));
        BotonEditar.setText("EDITAR");
        BotonEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonEditarMouseClicked(evt);
            }
        });
        BotonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEditarActionPerformed(evt);
            }
        });

        BotonBorrar.setBackground(new java.awt.Color(255, 0, 0));
        BotonBorrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BotonBorrar.setForeground(new java.awt.Color(255, 255, 255));
        BotonBorrar.setText("BORRAR");
        BotonBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonBorrarMouseClicked(evt);
            }
        });
        BotonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBorrarActionPerformed(evt);
            }
        });

        CampoID.setEditable(false);
        CampoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(director_pelicula)
                    .addComponent(nombre_pelicula)
                    .addComponent(BotonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoID, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(fecha_pelicula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(genero, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updatecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(registrar_genero, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre_pelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(director_pelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(genero, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registrar_genero, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(updatecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(fecha_pelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(BotonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CampoID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        TablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaPersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPersonasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaPersonas);

        btnPDF.setBackground(new java.awt.Color(227, 234, 248));
        btnPDF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPDF.setText("Generar PDF");
        btnPDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPDFMouseClicked(evt);
            }
        });
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        desktoppanel.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktoppanel.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktoppanel.setLayer(btnPDF, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktoppanelLayout = new javax.swing.GroupLayout(desktoppanel);
        desktoppanel.setLayout(desktoppanelLayout);
        desktoppanelLayout.setHorizontalGroup(
            desktoppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktoppanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(desktoppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(desktoppanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
                .addContainerGap())
        );
        desktoppanelLayout.setVerticalGroup(
            desktoppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktoppanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktoppanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktoppanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktoppanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnPDFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPDFMouseClicked
        try {
            //Buscar la carpeta temporar del sistema operativo
            //Path carpetaTemporal = Files.createTempDirectory(null);
            String carpetaTemporal = "C:\\reportes/";

            //Generar un nombre aleatorio para el PDF
            UUID uuid = UUID.randomUUID();
            String nombreAleatorio = uuid.toString() + ".pdf";

            //Generar el PDF el disco usando los datos de la tabla
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
            PdfWriter.getInstance(doc, new FileOutputStream(carpetaTemporal + nombreAleatorio));
            doc.open();
            PdfPTable pdfTable = new PdfPTable(TablaPersonas.getColumnCount());

            //Agregamos el titulo
            Paragraph titulo = new Paragraph("Listado de Peliculas",
                FontFactory.getFont("arial", // fuente
                    20, // tamaño
                    Font.BOLD, // estilo
                    BaseColor.BLUE));               // color
            titulo.setLeading(1, 1);
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);

            doc.add(new Paragraph(" "));

            //Cabeceras de la tabla
            for (int i = 0; i < TablaPersonas.getColumnCount(); i++) {
                pdfTable.addCell(TablaPersonas.getColumnName(i));
            }
            //Extraer los datos de cada fila del Jtable e insertar en el PDF
            for (int rows = 0; rows < TablaPersonas.getRowCount(); rows++) {
                for (int cols = 0; cols < TablaPersonas.getColumnCount(); cols++) {
                    pdfTable.addCell(TablaPersonas.getModel().getValueAt(rows, cols).toString());
                }
            }
            doc.add(pdfTable);
            doc.close();

            //Abrimos el archivo generado en el disco con el programa predeterminado
            // del sistema operativo, es quivalente a darle doble click
            File myFile = new File(carpetaTemporal + nombreAleatorio);
            Desktop.getDesktop().open(myFile);

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }//GEN-LAST:event_btnPDFMouseClicked

    private void TablaPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPersonasMouseClicked
        if (evt.getButton() == 1) {
            BotonBorrar.setEnabled(true);
            int fila = TablaPersonas.getSelectedRow();
            try {
                String stsql = "SELECT pelicula.id_pelicula, pelicula.nombre_pelicula, director_pelicula, pelicula.fecha_pelicula, genero.nombre_genero FROM `pelicula` JOIN `genero` ON pelicula.genero_id = genero.id_genero WHERE pelicula.id_pelicula = " + TablaPersonas.getValueAt(fila, 0);
                sentenciasql = coneccionsql.createStatement();
                ResultSet rs = sentenciasql.executeQuery(stsql);
                rs.next();

                CampoID.setText(rs.getString("id_pelicula"));
                nombre_pelicula.setText(rs.getString("nombre_pelicula"));
                director_pelicula.setText(rs.getString("director_pelicula"));
                fecha_pelicula.setYear(rs.getInt("fecha_pelicula"));
                genero.setSelectedItem(rs.getString("nombre_genero"));

                BotonEditar.setEnabled(true);
                BotonGuardar.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_TablaPersonasMouseClicked

    private void CampoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoIDActionPerformed

    private void BotonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBorrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonBorrarActionPerformed

    private void BotonBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonBorrarMouseClicked
        try {
            String id = CampoID.getText();

            //Borrar registro en la base de datos
            String stborrar = "delete from pelicula where id_pelicula =" + id;
            PreparedStatement ps = coneccionsql.prepareStatement(stborrar);
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(this, "Se borro correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al borrar");
            }
            ps.close();

            LlenarTabla();

            //limpiar formulario
            CampoID.setText("");
            genero.setSelectedIndex(0);
            nombre_pelicula.setText(""); //AL GUARDAR ESTE METODO VACIA EL CAMPO PARA INGRESAR UN NUEVO DATO
            director_pelicula.setText("");
            fecha_pelicula.setYear(2020); //AL GUARDAR SE COLOCA COMO VALOR POR DEFECTO 2020

            nombre_pelicula.requestFocus(); // SE ENFOCA A ESTE ELEMENT PARA VOLVER A ESCRIBIR TEXTO

            //Reiniciar el estado de los botones
            BotonGuardar.setEnabled(true);
            BotonEditar.setEnabled(false);
            BotonBorrar.setEnabled(false);

        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_BotonBorrarMouseClicked

    private void BotonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonEditarActionPerformed

    private void BotonEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonEditarMouseClicked
        try {
            String id = CampoID.getText();
            String nombrepelicula = nombre_pelicula.getText();
            String directorpelicula = director_pelicula.getText();
            Integer fechapelicula = fecha_pelicula.getYear(); //SE TRAE DEL FORMULARIO UN DATO DE TIPO ANHO Y SE GUARDA EN ENTERO
            String nombregenero = genero.getSelectedItem().toString();

            int  id_genero = getIdGenero(nombregenero);

            //Guardar en la base de datos
            String stactualizar = "UPDATE pelicula SET nombre_pelicula=?, director_pelicula=?, fecha_pelicula=?, genero_id=? WHERE id_pelicula=?";
            PreparedStatement ps = coneccionsql.prepareStatement(stactualizar);
            ps.setString(1, nombrepelicula);
            ps.setString(2, directorpelicula);
            ps.setInt(3, fechapelicula);
            ps.setInt(4, id_genero);
            ps.setString(5, id);
            int n = ps.executeUpdate();
            if (n > 0) {

                //limpiar formulario
                CampoID.setText("");
                genero.setSelectedIndex(0);
                nombre_pelicula.setText(""); //AL GUARDAR ESTE METODO VACIA EL CAMPO PARA INGRESAR UN NUEVO DATO
                director_pelicula.setText("");
                fecha_pelicula.setYear(2020); //AL GUARDAR SE COLOCA COMO VALOR POR DEFECTO 2020
                nombre_pelicula.requestFocus(); // SE ENFOCA A ESTE ELEMENT PARA VOLVER A ESCRIBIR TEXTO

                //reiniciar el estado de los botones
                BotonGuardar.setEnabled(true);
                BotonEditar.setEnabled(false);
                BotonBorrar.setEnabled(false);

                LlenarTabla();
                JOptionPane.showMessageDialog(this, "Se actualizo correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar");
            }
            ps.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_BotonEditarMouseClicked

    private void BotonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonGuardarActionPerformed

    //GUARDAR 
    
    private void BotonGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonGuardarMouseClicked
        try {
            String nombrepelicula = nombre_pelicula.getText();
            String directorpelicula = director_pelicula.getText();
            Integer fechapelicula = fecha_pelicula.getYear(); //SE TRAE DEL FORMULARIO UN DATO DE TIPO ANHO Y SE GUARDA EN ENTERO
            String nombregenero = genero.getSelectedItem().toString();

            int  id_genero = getIdGenero(nombregenero);

            String sqlguardar = "insert into pelicula (nombre_pelicula,director_pelicula, fecha_pelicula, genero_id) values (?, ?, ?, ?)";
            PreparedStatement ps = coneccionsql.prepareStatement(sqlguardar);

            ps.setString(1, nombrepelicula);
            ps.setString(2, directorpelicula);
            ps.setInt(3, fechapelicula);
            ps.setInt(4, id_genero);

            int n = ps.executeUpdate();
            if (n > 0) {
                nombre_pelicula.setText(""); //AL GUARDAR ESTE METODO VACIA EL CAMPO PARA INGRESAR UN NUEVO DATO
                director_pelicula.setText("");
                genero.setSelectedIndex(0);
                fecha_pelicula.setYear(2020); //AL GUARDAR SE COLOCA COMO VALOR POR DEFECTO 2020
                nombre_pelicula.requestFocus(); // SE ENFOCA A ESTE ELEMENT PARA VOLVER A ESCRIBIR TEXTO
                JOptionPane.showMessageDialog(this, "Se guardó correctamente");
                LlenarTabla();

            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar");
            }
            //SE CIERRA LA CONEXION CON LA BASE DE DATOS
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Error: No se pudo registrar a la pelicula!");
        }
    }//GEN-LAST:event_BotonGuardarMouseClicked

    private void updatecomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatecomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatecomboboxActionPerformed

    private void updatecomboboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatecomboboxMouseClicked
        //SE LLAMA AL COMBO BOX Y SE RETIRAN LOS ELEMENTOS

        genero.removeAllItems();

        // SE LLAMA AL METODO  PARA RELLENAR EL COMBO BOX
        CargarGenero();
    }//GEN-LAST:event_updatecomboboxMouseClicked

    private void registrar_generoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrar_generoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registrar_generoActionPerformed

    private void registrar_generoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrar_generoMouseClicked
        //SE INSTANCIA EL NUEVO OBJETO DE LA CLASE VISTACARGARPELICULAS PARA MOSTAR
        //EN EL JFORM PRINCIPAL CON LA SIGUIENTE LINEA DE CODIGO

        RegistrarGenero m = new RegistrarGenero();
        desktoppanel.add(m); // SE ANHADE AL DESKOP PANEL QUE HABIA CREADO
        Dimension desktopSize = desktoppanel.getSize();
        Dimension FrameSize = m.getSize();
        m.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        m.show();

    }//GEN-LAST:event_registrar_generoMouseClicked

    private void generoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generoActionPerformed

    }//GEN-LAST:event_generoActionPerformed

    private void generoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generoMousePressed

    }//GEN-LAST:event_generoMousePressed

    private void generoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generoMouseClicked

    }//GEN-LAST:event_generoMouseClicked

    private void generoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generoMouseMoved
        //SE LLAMA AL COMBO BOX Y SE RETIRAN LOS ELEMENTOS

        genero.removeAllItems();

        // SE LLAMA AL METODO  PARA RELLENAR EL COMBO BOX
        CargarGenero();
    }//GEN-LAST:event_generoMouseMoved

    private void director_peliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_director_peliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_director_peliculaActionPerformed

    private void nombre_peliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_peliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_peliculaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBorrar;
    private javax.swing.JButton BotonEditar;
    private javax.swing.JButton BotonGuardar;
    private javax.swing.JTextField CampoID;
    private javax.swing.JTable TablaPersonas;
    private javax.swing.JButton btnPDF;
    private javax.swing.JDesktopPane desktoppanel;
    private javax.swing.JTextField director_pelicula;
    private com.toedter.calendar.JYearChooser fecha_pelicula;
    public javax.swing.JComboBox<String> genero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre_pelicula;
    private javax.swing.JButton registrar_genero;
    private javax.swing.JButton updatecombobox;
    // End of variables declaration//GEN-END:variables
}
