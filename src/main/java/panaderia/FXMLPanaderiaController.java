/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia;

//Librerias para usar las Clases

//Librerias para usar Archivos
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//Librerias para usar Excepciones
import java.io.EOFException;
import java.io.IOException;
import java.text.ParseException;
//Librerias para el uso de Fechas
import java.text.SimpleDateFormat;//Permite usar el formato de fecha 
import java.util.Date;//Permite usar el Date
//Librerias para el uso de Arreglos
import java.util.ArrayList;//Permite usar el ArrayList
import javafx.collections.FXCollections;//Permite usar el FXCollections
import javafx.collections.ObservableList;//Permite usar el ObservableList
//Librerias de JavaFX
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//Librerias para usar los Controles
import javafx.scene.control.Alert;//Permite usar Alert
import javafx.scene.control.Button;//Permite usar Button
import javafx.scene.control.ButtonBar.ButtonData;//Permite usar el ButtonData
import javafx.scene.control.ComboBox;//Permite usar ComboBox
import javafx.scene.control.DatePicker;//Permite usar DatePicker
import javafx.scene.control.Label;//Permite usar Label
import javafx.scene.control.Tab;//Permite usar Tab
import javafx.scene.control.TabPane;//Permite usar TabPane
import javafx.scene.control.TableColumn;//Permite usar TableColumn
import javafx.scene.control.TableView;//Permite usar TableView
import javafx.scene.control.TextField;//Permite usar TextField
import javafx.scene.layout.AnchorPane;//Permite usar AnchorPane
import javafx.scene.image.ImageView;//Permite usar el ImageView
//Librerias para el uso de Events
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
//Librerias para editar TableColumn
import javafx.scene.control.cell.PropertyValueFactory;//Permite asignar los valores a las columnas del TableView
import panaderia.clases.Cliente;
import panaderia.clases.ControladorCliente;
import panaderia.clases.ControladorPan;
import panaderia.clases.ControladorVenta;
import panaderia.clases.Direccion;
import panaderia.clases.Pan;
import panaderia.clases.Venta;


/**
 *
 * @author usuario
 */
public class FXMLPanaderiaController implements Initializable {

    @FXML
    private AnchorPane apOperaciones;
    @FXML
    private TabPane tpOperaciones;
    @FXML
    private Tab tbRegistrarCliente, tbRegistrarPan, tbRealizarVenta, tbHistorialCompras, tbMejorCliente, tbPanMasVendido,
            tbVerConchapuntos, tbCanjearConchapuntos, tbVerClientes, tbVerPanes, tbTicket;
    @FXML
    private Button btnGuardarC, btnGuardarP, btnActualizar, btnCargar, btnDevolver, btnCobrar, btnTicket, btnCanjear;
    @FXML
    private Label lbFecha, lbIDV, lbTotal, lbCambio, lbDatosH, lbMC, lbPMV, lbVerCP, lbDatosCanjear, lbCanjearCP, lbCPA, lbDatos, lbDetalles;
    @FXML
    private TextField tfIDC, tfNombreC, tfApellido, tfCalle, tfNumero, tfColonia, tfCP, tfCiudad, tfEstado, tfTelefono,
            tfIDP, tfNombreP, tfPrecio, tfExistencia, tfIDV, tfCantidadPan, tfEfectivo, tfMC, tfVerCP, tfConsultarCP, tfCanjear;
    @FXML
    private ComboBox<String> cbPanes, cbTipo;
    @FXML
    private TableView<Venta> tvVentas, tvHistorial, tvTicket;
    @FXML
    private TableView<Cliente> tvClientes;
    @FXML
    private TableView<Pan> tvPanes;
    @FXML
    private TableColumn<Cliente, Integer> tcIDC, tcConchapuntos;
    @FXML
    private TableColumn<Cliente, String> tcNombreC, tcApellido;
    @FXML
    private TableColumn<Cliente, Direccion> tcDireccion;
    @FXML
    private TableColumn<Pan, Integer> tcIDP, tcExistencia;
    @FXML
    private TableColumn<Pan, String> tcNombreP;
    @FXML
    private TableColumn<Pan, Float> tcPrecio;
    @FXML
    private TableColumn<Venta, String> tcProductoV, tcFechaH, tcProductoH, tcProductoT;
    @FXML
    private TableColumn<Venta, Integer> tcIdPV, tcCantidadH, tcIdH, tcCantidadV, tcIdPT, tcMontoT, tcCantidadT;
    @FXML
    private TableColumn<Venta, Float> tcMontoV, tcMontoH, tcPrecioV, tcPrecioT;
    @FXML
    private DatePicker dpInicioMC, dpFinMC, dpInicioPMV, dpFinPMV;
    @FXML
    private ImageView ivMedallaMC, ivMedallaPMV;

    
    ControladorCliente controladorCliente=new ControladorCliente();
    ControladorPan controladorPan = new ControladorPan();
    ControladorVenta controladorVenta = new ControladorVenta();
    
    //Arreglos para todo el programa
    ArrayList<Cliente> listaClientes;
    ArrayList<Pan> listaPanes;
    ArrayList<Venta> listaVentas;
    //Arreglos para manejar Ventas
    ArrayList<Venta> ventaActual;
    ArrayList<Venta> ventasRango;

    int manteconchas = 20; //Dato de manteconchas disponibles al iniciar el día

    /**
     * Este método permite guardar los datos de las ventas
     */
    public void escribirVentas() {
        try (ObjectOutputStream oosVentas = new ObjectOutputStream(new FileOutputStream("src/main/resources/archivos/ventas.txt"))) {
            //Escribimos en un fichero
            for (int i = 0; i < listaVentas.size(); i++) {
                oosVentas.writeObject(listaVentas.get(i));
            }
        } catch (IOException e) {
            System.out.println("Direccion erronea");
        }
    }

//Anchor Pane apOperaciones
    /**
     * Este método permite habilitar el tab de RegistrarCliente
     *
     * @param RC
     */
    @FXML
    private void registrarCliente(ActionEvent RC) {
        cbTipo.setItems(FXCollections.observableArrayList("Estándar", "Premium"));
        btnGuardarC.setDisable(true);
           
        tpOperaciones.getTabs().add(tbRegistrarCliente);
        apOperaciones.setDisable(true);
        System.out.println("\nTab Registrar Cliente");
    }

    /**
     * Este método permite habilitar el tab RegistrarPan
     *
     * @param RP
     */
    @FXML
    private void registrarPan(ActionEvent RP) {
        tpOperaciones.getTabs().add(tbRegistrarPan);
        apOperaciones.setDisable(true);
        System.out.println("\nTab Registrar Pan");
    }

    /**
     * Este método permite habilitar el tab RealizarCompra
     *
     * @param venta
     */
    @FXML
    private void realizarVenta(ActionEvent venta) {
        ventaActual = new ArrayList<>();

        ObservableList<String> Panes = FXCollections.observableArrayList();
        for (int i = 0; i < listaPanes.size(); i++) {
            Panes.add(listaPanes.get(i).nombrePan);
        }

        cbPanes.setItems(Panes);

        if (cbPanes.getItems().isEmpty()) {
            cbPanes.setPromptText("No hay panes disponibles");
            tfIDV.setEditable(false);
        } else {
            cbPanes.setPromptText("Seleccione un pan...");
            lbIDV.setVisible(true);
            tfIDV.setVisible(true);
            tfIDV.setEditable(true);
            lbTotal.setText("0");
            lbTotal.setVisible(false);
        }
        
        cbPanes.setDisable(false);
        tfCantidadPan.setDisable(true);
        btnCargar.setDisable(true);
        btnDevolver.setDisable(true);
        tvVentas.setItems(null);
        tfEfectivo.setEditable(false);
        btnCobrar.setDisable(true);
        btnTicket.setDisable(true);

        tpOperaciones.getTabs().add(tbRealizarVenta);
        apOperaciones.setDisable(true);
        System.out.println("\nTab Realizar Venta");
    }

    /**
     * Este método permite habilitar el tab HistorialCompras
     *
     * @param historial
     */
    @FXML
    private void historialCompras(ActionEvent historial) {
        tvHistorial.setVisible(false);

        tpOperaciones.getTabs().add(tbHistorialCompras);
        apOperaciones.setDisable(true);
        System.out.println("\nTab Ver Historial de Compras");
    }

    /**
     * Este método permite habilitar el tab HistorialCompras
     *
     * @param MC
     */
    @FXML
    private void mejorCliente(ActionEvent MC) {
        ivMedallaMC.setVisible(false);
        
        tpOperaciones.getTabs().add(tbMejorCliente);
        apOperaciones.setDisable(true);
        System.out.println("\nTab Mejor Cliente");
    }

    /**
     * Este método permite habilitar el tab PanMasVendido
     *
     * @param PMV
     */
    @FXML
    private void panMasVendido(ActionEvent PMV) {
        ivMedallaPMV.setVisible(false);
        
        tpOperaciones.getTabs().add(tbPanMasVendido);
        apOperaciones.setDisable(true);
        System.out.println("\nTab Pan Más Vendido");
    }

    /**
     * Este método permite habilitar el tab VerConchapuntos
     *
     * @param verCP
     */
    @FXML
    private void verConchapuntos(ActionEvent verCP) {
        tpOperaciones.getTabs().add(tbVerConchapuntos);
        apOperaciones.setDisable(true);
        System.out.println("\nTab Ver Conchapuntos");
    }

    /**
     * Este método permite habilitar el tab CanjearConchapuntos
     *
     * @param canjearCP
     */
    @FXML
    private void canjearConchapuntos(ActionEvent canjearCP) {
        if (manteconchas == 0) {
            Alert sinMCo = new Alert(Alert.AlertType.INFORMATION);
            sinMCo.setHeaderText("Manteconchas");
            sinMCo.setContentText("Lo sentimos no contamos con manteconchas");
            sinMCo.show();
            System.out.println("\tNo quedan manteconchas");
        }

        lbCanjearCP.setVisible(false);
        tfCanjear.setVisible(false);
        btnCanjear.setVisible(false);
        lbCPA.setVisible(false);

        tpOperaciones.getTabs().add(tbCanjearConchapuntos);
        apOperaciones.setDisable(true);
        System.out.println("\nTab Canjear Conchapuntos");
    }

    /**
     * Este método permite habilitar el tab VerClientes
     *
     * @param verC
     */
    @FXML
    private void verClientes(ActionEvent verC) {
        System.out.println("\nTab Ver Clientes");
        listaClientes = controladorCliente.leerClientes();

        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        for (int i = 0; i < listaClientes.size(); i++) {
            clientes.add(listaClientes.get(i));
        }

        tvClientes.setItems(clientes);

        tcIDC.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        tcNombreC.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        tcApellido.setCellValueFactory(new PropertyValueFactory<>("aPaternoCliente"));
        tcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tcConchapuntos.setCellValueFactory(new PropertyValueFactory<>("conchapuntos"));

        tpOperaciones.getTabs().add(tbVerClientes);
        apOperaciones.setDisable(true);
    }

    /**
     * Este método permite habilitar el tab VerPanes
     *
     * @param verP
     */
    @FXML
    private void verPanes(ActionEvent verP) {
        System.out.println("\nTab Ver Panes");
        controladorPan.leerPanes();

        ObservableList<Pan> panes = FXCollections.observableArrayList();
        for (int i = 0; i < listaPanes.size(); i++) {
            panes.add(listaPanes.get(i));
        }

        tvPanes.setItems(panes);

        tcIDP.setCellValueFactory(new PropertyValueFactory<>("idPan"));
        tcNombreP.setCellValueFactory(new PropertyValueFactory<>("nombrePan"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPan"));
        tcExistencia.setCellValueFactory(new PropertyValueFactory<>("existenciaPan"));

        tpOperaciones.getTabs().add(tbVerPanes);
        apOperaciones.setDisable(true);
    }
    
//Tab RegistrarCliente
    /**
     * Este método permite registrar un nuevo cliente en la BD
     *
     * @param nuevoRC
     */
    @FXML
    private void procesarGuardarRC(ActionEvent nuevoRC) throws RuntimeException {
        int b = 0; //Bandera que permite saber si el cliente ya ha sido registrado
        int id;

        if (tfIDC.getText().isEmpty() || tfNombreC.getText().isEmpty() || tfApellido.getText().isEmpty()){
            Alert datos = new Alert(Alert.AlertType.WARNING);
            datos.setHeaderText("Datos incorretos");
            datos.setContentText("Existen campos vacíos");
            datos.show();
            System.out.println("\tHay campos vacíos");
        } else {
            
            try {
                id = Integer.parseInt(tfIDC.getText());
                String tipo = (String) cbTipo.getValue();
                System.out.println(cbTipo.getValue());
                String nombre = tfNombreC.getText();
                String apellido = tfApellido.getText();
                //Datos de la dirección
                String calle = tfCalle.getText();
                String numero = tfNumero.getText();
                String colonia = tfColonia.getText();
                String CP = tfCP.getText();
                String ciudad = tfCiudad.getText();
                String estado = tfEstado.getText();
                String telefono = tfTelefono.getText();
                
                
                boolean verificacionProcesamiento=controladorCliente.guardarRC(listaClientes,id,tipo,nombre,apellido,calle,numero,colonia,CP,ciudad,estado,telefono);
                
                if(verificacionProcesamiento){
                   cbTipo.setValue(null);
                    tfIDC.setText("");
                    tfNombreC.setText("");
                    tfApellido.setText("");
                    tfCalle.setText("");
                    tfNumero.setText("");
                    tfColonia.setText("");
                    tfCP.setText("");
                    tfCiudad.setText("");
                    tfEstado.setText("");
                    tfTelefono.setText("");
                    btnGuardarC.setDisable(true); 
                }else{
                    tfIDC.setText("");
                }
                
            } catch (RuntimeException e) {
                Alert datoID = new Alert(Alert.AlertType.ERROR);
                datoID.setHeaderText("Dato inválido");
                datoID.setContentText("El id debe ser numérico");
                datoID.show();

                tfIDC.setText("");
                System.out.println("\tEl ID ingresado es inválido");
            }
        }
    }
    
    /**
     * Este método permite seleccionar el tipo de cliente a registrar
     * @param select 
     */
    @FXML
    private void seleccionarTipo(ActionEvent select){
        btnGuardarC.setDisable(false);
    }
    
    /**
     * Este método permite cancelar el registro del cliente
     *
     * @param cancelRC
     */
    @FXML
    private void cancelarRC(ActionEvent cancelRC) {
        Alert cancelarRC = new Alert(Alert.AlertType.CONFIRMATION);
        cancelarRC.setHeaderText("Cancelar registro");
        cancelarRC.setContentText("Desea cancelar el registro actual?");
        cancelarRC.showAndWait();

        if (cancelarRC.getResult().getButtonData().equals(ButtonData.OK_DONE)) {
            cbTipo.setValue(null);
            tfIDC.setText("");
            tfNombreC.setText("");
            tfApellido.setText("");
            tfCalle.setText("");
            tfNumero.setText("");
            tfColonia.setText("");
            tfCP.setText("");
            tfCiudad.setText("");
            tfEstado.setText("");
            tfTelefono.setText("");

            tpOperaciones.getTabs().remove(tbRegistrarCliente);
            apOperaciones.setDisable(false);
            System.out.println("Registro de cliente cancelado");
        }
    }

//Tab RegistrarPan
    /**
     * Este método permite registrar un nuevo pan en la BD
     *
     * @param nuevoRP
     */
    @FXML
    private void guardarRP(ActionEvent nuevoRP) throws RuntimeException {
        int b = 0;//Permite saber si el pan ya ha sido registrado

        if (tfIDP.getText().isEmpty() || tfNombreP.getText().isEmpty() || tfPrecio.getText().isEmpty() || tfExistencia.getText().isEmpty()) {
            Alert datos = new Alert(Alert.AlertType.WARNING);
            datos.setHeaderText("Datos incorretos");
            datos.setContentText("Existen campos vacíos");
            datos.show();
            System.out.println("\tHay campos vacíos");
        } else {
            try {
                int id = Integer.parseInt(tfIDP.getText());

                boolean verificar=controladorPan.verificarPanRegistrado(listaPanes,id);
                
                

                if (!verificar) {
                    String nombre = tfNombreP.getText();

                    try {
                        float precio = Float.parseFloat(tfPrecio.getText());

                        try {
                            int existencia = Integer.parseInt(tfExistencia.getText());

                            listaPanes.add(new Pan(id, nombre, precio, existencia));

                            //Guardar datos de los Panes en el archivo panes.txt
                            controladorPan.escribirPanes(listaPanes);

                            tfIDP.setText("");
                            tfNombreP.setText("");
                            tfPrecio.setText("");
                            tfExistencia.setText("");

                            Alert nPan = new Alert(Alert.AlertType.INFORMATION);
                            nPan.setHeaderText("Registrar pan");
                            nPan.setContentText("El pan ha sido registrado");
                            nPan.show();
                            System.out.println("\tPan registrado");
                        } catch (RuntimeException e) {
                            Alert datoE = new Alert(Alert.AlertType.ERROR);
                            datoE.setHeaderText("Dato inválido");
                            datoE.setContentText("La existencia del pan debe ser numérica");
                            datoE.show();

                            tfExistencia.setText("");
                            System.out.println("\tLa existencia del pan debe ser numérica");
                        }
                    } catch (RuntimeException e) {
                        Alert datoP = new Alert(Alert.AlertType.ERROR);
                        datoP.setHeaderText("Dato inválido");
                        datoP.setContentText("El precio del pan debe ser numérico");
                        datoP.show();

                        tfPrecio.setText("");
                        System.out.println("\tEl precio del pan debe ser numérico");
                    }
                }else{
                    tfIDP.setText("");
                }
            } catch (RuntimeException e) {
                Alert datoID = new Alert(Alert.AlertType.ERROR);
                datoID.setHeaderText("Dato inválido");
                datoID.setContentText("El id debe ser numérico");
                datoID.show();

                tfIDP.setText("");
                System.out.println("\tEl ID ingresado es inválido");
            }
        }
    }
    
    /**
     * Este método permite confirmar una actualización
     * @param update 
     */
    @FXML
    private void solicitarActualizacion(ActionEvent update){
        if(tfIDP.getText().isEmpty()){
            Alert datos = new Alert(Alert.AlertType.INFORMATION);
            datos.setHeaderText("ID Pan");
            datos.setContentText("Ingrese el id del pan que desea actualizar");
            datos.show();
                                
            tfIDP.setPromptText("Ingrese el ID");
            tfNombreP.setEditable(false);
            tfPrecio.setEditable(false);
            tfExistencia.setEditable(false);
            btnGuardarP.setDisable(true);
            System.out.println("\tNo se ingreso el id del pan");       
        }else{
            int b = 0;
             try{
                int iDP = Integer.parseInt(tfIDP.getText());

                 for (int i = 0; i < listaPanes.size(); i++) {
                    if(listaPanes.get(i).idPan == iDP){
                        tfIDP.setEditable(false);
                        tfNombreP.setText(listaPanes.get(i).nombrePan);
                        tfIDP.setEditable(true);
                        tfPrecio.setText(Float.toString(listaPanes.get(i).precioPan));
                        tfIDP.setEditable(true);
                        tfExistencia.setPromptText("Existencia por agregar");
                        tfIDP.setEditable(true);
                        btnGuardarP.setDisable(true);
                        btnActualizar.setDisable(false);
                        b=1;
                    }
                 }
                 
                 if(b == 0){
                    Alert datos = new Alert(Alert.AlertType.INFORMATION);
                    datos.setHeaderText("Datos incorreto");
                    datos.setContentText("Pan no registrado");
                    datos.show();
                    
                    tfIDP.setText("");
                    tfIDP.setEditable(true);
                    System.out.println("\tNo ha sido ingresado el id del pan");       
                 }
        
            }catch(RuntimeException e){
            
        
            }
        }
    }
    
    /**
     * Este metodo permite actualizar los datos del pan
     *
     * @param actualizar
     */
    @FXML
    private void actualizarDatos(ActionEvent actualizar) {
        int pos = 0;

        if (tfIDP.getText().isEmpty() || tfNombreP.getText().isEmpty() || tfPrecio.getText().isEmpty() || tfExistencia.getText().isEmpty()) {
            Alert datos = new Alert(Alert.AlertType.WARNING);
            datos.setHeaderText("Datos incorretos");
            datos.setContentText("Existen campos vacíos");
            datos.show();
            
            System.out.println("\tHay campos vacíos");
        } else {
            try {
                int id = Integer.parseInt(tfIDP.getText());

                for (int i = 0; i < listaPanes.size(); i++) {
                    if (listaPanes.get(i).idPan == id) {
                        pos = i;
                    }
                }
                
                String nombre = tfNombreP.getText();

                try {
                    float precio = Float.parseFloat(tfPrecio.getText());

                    try {
                        int existencia = Integer.parseInt(tfExistencia.getText());

                        listaPanes.get(pos).setNombrePan(nombre);
                        listaPanes.get(pos).setPrecioPan(precio);
                        listaPanes.get(pos).setExistenciaPan(existencia);

                        //Guardar datos de los Panes en el archivo panes.txt
                        controladorPan.escribirPanes(listaPanes);
                        
                        tfExistencia.setPromptText("");
                        
                        tfIDP.setText("");
                        tfNombreP.setText("");
                        tfPrecio.setText("");
                        tfExistencia.setText("");
                        
                        tfIDP.setEditable(true);
                        tfIDP.setPromptText("");
                        btnGuardarP.setDisable(false);
                        btnActualizar.setDisable(true);

                        Alert nPan = new Alert(Alert.AlertType.INFORMATION);
                        nPan.setHeaderText("Registrar pan");
                        nPan.setContentText("El datos del pan han sido actualizados");
                        nPan.show();
                        System.out.println("\tPan actualizado");
                    } catch (RuntimeException e) {
                        Alert datoE = new Alert(Alert.AlertType.ERROR);
                        datoE.setHeaderText("Dato inválido");
                        datoE.setContentText("La existencia del pan debe ser numérica");
                        datoE.show();

                        tfExistencia.setText("");
                        System.out.println("\tLa existencia del pan debe ser numérica");
                    }
                    } catch (RuntimeException e) {
                        Alert datoP = new Alert(Alert.AlertType.ERROR);
                        datoP.setHeaderText("Dato inválido");
                        datoP.setContentText("El precio del pan debe ser numérico");
                        datoP.show();

                        tfPrecio.setText("");
                        System.out.println("\tEl precio del pan debe ser numérico");
                    }
            } catch (RuntimeException e) {
                Alert datoID = new Alert(Alert.AlertType.ERROR);
                datoID.setHeaderText("Dato inválido");
                datoID.setContentText("El id debe ser numérico");
                datoID.show();

                tfIDP.setText("");
                System.out.println("\tEl ID ingresado es inválido");
            }
        }
    }
    
    /**
     * Este método permite cancelar el registro del pan
     *
     * @param cancelRP
     */
    @FXML
    private void cancelarRP(ActionEvent cancelRP) {
        Alert cancelarRP = new Alert(Alert.AlertType.CONFIRMATION);
        cancelarRP.setHeaderText("Cancelar registro");
        cancelarRP.setContentText("Desea cancelar el registro actual?");
        cancelarRP.showAndWait();

        if (cancelarRP.getResult().getButtonData().equals(ButtonData.OK_DONE)) {   
            tfIDP.setPromptText("");
            tfIDP.setText("");
            tfIDP.setEditable(true);
            tfNombreP.setText("");
            tfNombreP.setEditable(true);
            tfPrecio.setText("");
            tfPrecio.setEditable(true);
            tfExistencia.setPromptText("");
            tfExistencia.setEditable(true);
            tfExistencia.setText("");
            btnGuardarP.setDisable(false);
            btnActualizar.setDisable(true);    
            
            tpOperaciones.getTabs().remove(tbRegistrarPan);
            apOperaciones.setDisable(false);
            System.out.println("Registro de pan cancelado");
        }
    }

//Tab RealizarCompra
    /**
     * Este método permite adquirir un pan registrado en el sistema
     *
     * @param comprar
     */
    @FXML
    private void cargarCompra(ActionEvent comprar) throws RuntimeException {
        int idCliente = 0;//ID asociado a la venta a un cliente no registrado
        float sumVenta = Float.parseFloat(lbTotal.getText());//Sumador de la venta
        int vA = 0;//Permite saber si la venta se autorizA
        
        Date fecha = new Date();
        String fechaVenta = new SimpleDateFormat("dd/MM/yyyy").format(fecha);

        //Validación del id 
        if (lbIDV.isVisible() && tfIDV.isVisible()) {    
            if (tfIDV.getText().isEmpty()) {
                Alert ventaCNR = new Alert(Alert.AlertType.CONFIRMATION);
                ventaCNR.setHeaderText("Venta");
                ventaCNR.setContentText("Desea realizar una venta a un cliente no registrado?");
                ventaCNR.showAndWait();

                if (ventaCNR.getResult().getButtonData().equals(ButtonData.OK_DONE)) {
                    lbIDV.setVisible(false);
                    tfIDV.setVisible(false);
                    vA = 1;//Autoriza la venta
                    System.out.println("\tIniciar venta a cliente no registrado");
                } else {
                    Alert venta = new Alert(Alert.AlertType.INFORMATION);
                    venta.setHeaderText("Venta");
                    venta.setContentText("Ingrese el id del cliente");
                    venta.show();
                    cbPanes.setValue(null);
                    tfCantidadPan.setText("");
                    btnCargar.setDisable(true);
                }
            } else {
                if(tfIDV.isEditable()){//Aún no se ha confirmado al cliente
                    System.out.println("\tFalta por comprobar al cliente");
                }else{//Cliente ya registrado
                    System.out.println("\n\tCliente comprobado");
                    idCliente = Integer.parseInt(tfIDV.getText());//Tomo el id del cliente ya validado
                    vA = 1;
                }
                
                if (idCliente == 0) {        
                    int aux = 0;//Permite asignar el id del cliente
                    int b = 0;//Permite saber si el id del cliente es válido
                
                    
                    if (vA == 0) {
                        System.out.println("\n\tAsignación id del cliente");
                        try {
                            idCliente = Integer.parseInt(tfIDV.getText());

                            //Validación del cliente
                            for (int i = 0; i < listaClientes.size(); i++) {
                                if (listaClientes.get(i).idCliente == idCliente) {
                                    b = 1;
                                    aux = i;
                                }
                            }

                            if (b == 0) {
                                Alert datoID = new Alert(Alert.AlertType.ERROR);
                                datoID.setHeaderText("Dato inválido");
                                datoID.setContentText("Cliente no registrado");
                                datoID.show();

                                tfIDV.setText("");
                                cbPanes.setValue(null);
                                tfCantidadPan.setText("");
                                tfCantidadPan.setDisable(true);
                                btnCargar.setDisable(true);
                                System.out.println("\tEl cliente no está registrado");
                            } else {
                                System.out.println("\tCliente: " + listaClientes.get(aux).toString());

                                tfIDV.setEditable(false);
                                vA = 1;
                            }
                        } catch (RuntimeException e) {
                            Alert datoID = new Alert(Alert.AlertType.ERROR);
                            datoID.setHeaderText("Dato inválido");
                            datoID.setContentText("El iD del cliente debe ser numérico");
                            datoID.show();
                            
                            tfIDV.setText("");
                            cbPanes.setValue(null);
                            tfCantidadPan.setText("");
                            tfCantidadPan.setDisable(true);
                            btnCargar.setDisable(true);
                            System.out.println("\tEl iD del cliente debe ser numérico");
                        }
                    }
                }else{
                    //System.out.println("Cliente: " + listaClientes.get(aux).toString());
                }
            }
        } else {
            System.out.println("\n\tVenta a cliente no registrado");
            vA = 1;
        }

        //Venta Autorizada
        if (vA == 1) {
            String pan = cbPanes.getValue();
            System.out.println("Pan solicitado: " + pan);

            int index = cbPanes.getSelectionModel().getSelectedIndex();

            if (tfCantidadPan.getText().isEmpty()) {
                Alert datoC = new Alert(Alert.AlertType.WARNING);
                datoC.setHeaderText("Cantidad de pan");
                datoC.setContentText("Ingrese la cantidad de pan que se desea comprar");
                datoC.show();
            } else {
                try {
                    int idPan = listaPanes.get(index).idPan;
                    int cantidad = Integer.parseInt(tfCantidadPan.getText());
                    int existencia = listaPanes.get(index).existenciaPan;
                    System.out.println("\t" + listaPanes.get(index).toString());
                    
                    System.out.println("\tCantidad:" + cantidad);
                    
                    if (existencia < cantidad) {
                        Alert datoE = new Alert(Alert.AlertType.INFORMATION);
                        datoE.setHeaderText("Existencia del pan");
                        datoE.setContentText("Lo sentimos no contamos con la cantidad suficiente de " + pan + "s");
                        datoE.show();

                        cbPanes.setValue(null);
                        tfCantidadPan.setText("");
                        tfCantidadPan.setDisable(true);
                        btnCargar.setDisable(true);
                        System.out.println("\tSin existencia suficiente");
                    } else {
                        float precio = listaPanes.get(index).precioPan;
                        float monto = cantidad * precio;
                        
                        int nExistencia = (cantidad * -1); 
                        listaPanes.get(index).setExistenciaPan(nExistencia);
                        System.out.println("\t" + listaPanes.get(index).toString());
                         
                        
                        //Guardar datos de los Panes en el archivo panes.txt
                        controladorPan.escribirPanes(listaPanes); 
                        
                        sumVenta += monto;
                        
                        ventaActual.add(new Venta(fechaVenta, idCliente, idPan, pan, cantidad, precio, monto));
                        System.out.println("Total de la compra: " + sumVenta);
                        
                        ObservableList<Venta> ventas = FXCollections.observableArrayList();
                        for (int i = 0; i < ventaActual.size(); i++) {
                            ventas.add(ventaActual.get(i));
                        }

                        tvVentas.setItems(ventas);

                        tcIdPV.setCellValueFactory(new PropertyValueFactory<>("idPan"));
                        tcProductoV.setCellValueFactory(new PropertyValueFactory<>("nombrePan"));
                        tcCantidadV.setCellValueFactory(new PropertyValueFactory<>("cantidadPan"));
                        tcPrecioV.setCellValueFactory(new PropertyValueFactory<>("precioPan"));
                        tcMontoV.setCellValueFactory(new PropertyValueFactory<>("monto"));

                        lbTotal.setText(Float.toString(sumVenta));
                        lbTotal.setVisible(true);
                        cbPanes.setValue(null);
                        tfCantidadPan.setText("");
                        tfCantidadPan.setDisable(true);
                        btnCargar.setDisable(true);
                        tfEfectivo.setEditable(true);
                        btnCobrar.setDisable(false);
                    }
                } catch (RuntimeException e) {
                    Alert datoC = new Alert(Alert.AlertType.ERROR);
                    datoC.setHeaderText("Dato inválido");
                    datoC.setContentText("La cantidad de pan debe ser numérica");
                    datoC.show();

                    tfCantidadPan.setText("");
                    System.out.println("\tLa cantidad de pan debe ser numérica");
                }
            }
        }
    }

    /**
     * Este método habilita la lectura de la cantidad de panes solicitados
     *
     * @param seleccionar
     */
    @FXML
    private void seleccionarPan(ActionEvent seleccionar) {
        tfCantidadPan.setDisable(false);
        btnCargar.setDisable(false);
    }

    /**
     * Este método permite descartar una compra
     * @param devolver
     */
    @FXML
    private void descargarCompra(ActionEvent devolver) {
        System.out.println("\nDevolución de producto");
        
        int index = tvVentas.getSelectionModel().getSelectedIndex();
        System.out.println("\t" + listaPanes.get(index).toString());
        
        float total = Float.parseFloat(lbTotal.getText());
        
        Alert descargar = new Alert(Alert.AlertType.CONFIRMATION);
        descargar.setHeaderText("Devolver producto");
        descargar.setContentText("Desea cancelar la compra de este artículo?");
        descargar.showAndWait();

        if (descargar.getResult().getButtonData().equals(ButtonData.OK_DONE)) {
            int idP = tvVentas.getSelectionModel().getSelectedItem().idPan;
            int existencia = tvVentas.getSelectionModel().getSelectedItem().cantidadPan;
            float monto = tvVentas.getSelectionModel().getSelectedItem().monto;
            
            for (int i = 0; i < listaPanes.size(); i++) {
                if(listaPanes.get(i).idPan == idP){
                    listaPanes.get(i).setExistenciaPan(existencia);
                }
            }
            
            System.out.println("\t" + listaPanes.get(index).toString());
            
            //Guardar datos de los Panes en el archivo panes.txt
            controladorPan.escribirPanes(listaPanes); 
                        
            ventaActual.remove(index);
        
            ObservableList<Venta> ventas = FXCollections.observableArrayList();
            for (int i = 0; i < ventaActual.size(); i++) {
                ventas.add(ventaActual.get(i));
            }

            tvVentas.setItems(ventas);

            tcIdPV.setCellValueFactory(new PropertyValueFactory<>("idPan"));
            tcProductoV.setCellValueFactory(new PropertyValueFactory<>("nombrePan"));
            tcCantidadV.setCellValueFactory(new PropertyValueFactory<>("cantidadPan"));
            tcPrecioV.setCellValueFactory(new PropertyValueFactory<>("precioPan"));
            tcMontoV.setCellValueFactory(new PropertyValueFactory<>("monto")); 
            
            float nTotal = total - monto;
            lbTotal.setText(Float.toString(nTotal));
            
            if(Float.parseFloat(lbTotal.getText()) == 0){
                lbTotal.setVisible(false);
            }
            
            System.out.println("Artículo descartado\n");
            
            if(tvVentas.getItems().isEmpty()){
                btnDevolver.setDisable(true);
            }
        }
    }

    /**
     * Este método permite activar la opción devolver, al seleccionar un pan vendido
     * @param e 
     */
    @FXML
    private void obtenerArticulo(MouseEvent e){
        int articulos = ventaActual.size();
        
        if(articulos != 0){
            btnDevolver.setDisable(false);
        }
    }
    
    /**
     * Este método permite una vez desplegado el total e ingresado el efectivo,
     * asi como calculado el cambio, realizar el cobro
     *
     * @param total
     */
    @FXML
    private void cobrarVenta(ActionEvent total) throws RuntimeException {
        if (tfEfectivo.getText().isEmpty()) {
            Alert datoT = new Alert(Alert.AlertType.WARNING);
            datoT.setHeaderText("Cobrar venta");
            datoT.setContentText("Ingrese el efectivo");
            datoT.show();
        } else {
            int conchapuntos = 0;
                
            try {
                float efectivo = Float.parseFloat(tfEfectivo.getText());
                float totalV = Float.parseFloat(lbTotal.getText());

                if (efectivo < totalV) {
                    Alert cobro = new Alert(Alert.AlertType.ERROR);
                    cobro.setHeaderText("Cobrar");
                    cobro.setContentText("No se tiene suficiente efectivo");
                    cobro.show();
                    System.out.println("\tNo se tiene suficiente efectivo");
                } else {
                    float cambio = efectivo - totalV;
                    lbCambio.setText(Float.toString(cambio));

                    if (tfIDV.getText().equals("")) {
                        System.out.println("\n\tVenta ignorada, no se registro  cliente");
                    } else {
                        //Registro de las ventas después de ser pagadas
                        for (int i = 0; i < ventaActual.size(); i++) {
                            listaVentas.add(ventaActual.get(i));
                        }
                        
                        int idC = Integer.parseInt(tfIDV.getText());
                        
                        for (int i = 0; i < listaClientes.size(); i++) {
                            if (listaClientes.get(i).idCliente == idC) {
                                System.out.println("\n" + listaClientes.get(i).toString());
                                
                                listaClientes.get(i).imprimir();
                                conchapuntos = listaClientes.get(i).calcularConchapuntos(totalV);      
                                listaClientes.get(i).setConchapuntos(conchapuntos);
                               
                                System.out.println(listaClientes.get(i).toString());
                            }
                        }
                                
                        //Guardar datos de los Clientes en el archivo clientes.txt
                        boolean exitoso=controladorCliente.escribirClientes(listaClientes);
                        if(exitoso){
                            System.out.println("Proceso Exitoso");
                        }else{
                            System.out.println("Error al guardar");
                        }
                        //Guardar datos de los Panes en el archivo panes.txt
                        controladorPan.escribirPanes(listaPanes); 
                        
                        //Guardar datos de las Ventas en el archivo ventas.txt
                        escribirVentas();
                    }
                        
                    Alert cobro = new Alert(Alert.AlertType.CONFIRMATION);
                    cobro.setHeaderText("Venta finalizada");
                    cobro.setContentText("Desea imprimir el Ticket?");
                    cobro.showAndWait();
                    System.out.println("\tTotal: " + totalV + "\n\tEfectivo: " + efectivo + "\n\tCambio: " + cambio);

                    if (cobro.getResult().getButtonData().equals(ButtonData.OK_DONE)) {
                        lbDatos.setText("Panaderia: El Ángel de Azúcar" + "\t\t\t\t Sucursal: BellaVista" + "\nC.65 #345 x 34 y 56 col. Fovisste \t\t\t Tel. 9861234589");

                        ObservableList<Venta> ventas = FXCollections.observableArrayList();
                        for (int i = 0; i < ventaActual.size(); i++) { 
                            ventas.add(ventaActual.get(i));
                        }

                        tvTicket.setItems(ventas);

                        tcIdPT.setCellValueFactory(new PropertyValueFactory<>("idPan"));
                        tcProductoT.setCellValueFactory(new PropertyValueFactory<>("nombrePan"));
                        tcCantidadT.setCellValueFactory(new PropertyValueFactory<>("cantidadPan"));
                        tcPrecioT.setCellValueFactory(new PropertyValueFactory<>("precioPan"));
                        tcMontoT.setCellValueFactory(new PropertyValueFactory<>("monto"));

                        lbDetalles.setText("Total: " + totalV + "\nEfectivo: " + efectivo + "\nCambio: " + cambio + "\nConchapuntos: " + conchapuntos);

                        tfIDV.setText("");
                        lbTotal.setText("");
                        tfEfectivo.setText("");
                        lbCambio.setText("");

                        tpOperaciones.getTabs().remove(tbRealizarVenta);
                        tpOperaciones.getTabs().add(tbTicket);
                        System.out.println("Ticket Impreso");
                    } else {
                            cbPanes.setDisable(true);
                            btnCobrar.setDisable(true);                     
                            tfEfectivo.setEditable(false);
                            btnTicket.setDisable(false);
                        }
                }
            } catch (RuntimeException e) {
                Alert datoE = new Alert(Alert.AlertType.ERROR);
                datoE.setHeaderText("Dato inválido");
                datoE.setContentText("El efectivo debe ser numérico");
                datoE.show();
                System.out.println("\tEl efectivo debe ser numérico");
            }
        }
    }

    /**
     * Este método permite cancelar la venta
     *
     * @param cancelVenta
     */
    @FXML
    private void cancelarVenta(ActionEvent cancelVenta) {
        int b = 0;//Permite saber si se puede cancelar la venta

        int artVendidos = ventaActual.size();

        if (artVendidos == 0) {
            b = 1;//Como no se han vendido artículos se puede aceptar la cancelación         
            System.out.println("\tCancelación aceptada, no se han realizado compras");
        } else {
            if (btnTicket.isDisable()) {//Como está desabilitado no se ha cobrado
                Alert cancelarVenta = new Alert(Alert.AlertType.CONFIRMATION);
                cancelarVenta.setHeaderText("Cancelar venta");
                cancelarVenta.setContentText("Desea cancelar la venta actual?");
                cancelarVenta.showAndWait();

                if (cancelarVenta.getResult().getButtonData().equals(ButtonData.OK_DONE)) {
                    //Permite devolver la existencia a los productos cancelados
                    for (int i = 0; i < ventaActual.size(); i++) {
                        int idPan = ventaActual.get(i).idPan;

                        for (int j = 0; j < listaPanes.size(); j++) {
                            if (listaPanes.get(i).idPan == idPan) {
                                int existencia = listaPanes.get(j).existenciaPan + ventaActual.get(i).cantidadPan;
                                System.out.println("La existencia de " + listaPanes.get(j).nombrePan + "s es " + existencia);
                                listaPanes.get(i).setExistenciaPan(existencia);
                            }
                        }
                    }
                    System.out.println("\tCancelación aceptada, panes restablecidos");

                    b = 1;//Ya se restauraron las existencias, se puede aceptar la cancelación 

                    //Verificación de existencia de panes
                    for (int i = 0; i < listaPanes.size(); i++) {
                        listaPanes.get(i).toString();
                    }
                }
            } else {  //Ya se cobro
                b = 1;//Ya se cobro, se puede cerrar la ventana
                System.out.println("\tVentana cerrada");
            }
        }

        if (b == 1) {
            tfIDV.setText("");
            cbPanes.setValue(null);
            tfCantidadPan.setText("");
            tvVentas.setItems(null);
            lbTotal.setText("");
            tfEfectivo.setText("");
            lbCambio.setText("");

            tpOperaciones.getTabs().remove(tbRealizarVenta);
            apOperaciones.setDisable(false);
            System.out.println("Realizar Venta Cerrada");
        }
    }

    /**
     * Este método permite dirigirse a la página donde se imprime el ticket
     *
     * @param compra
     */
    @FXML
    private void imprimirTicket(ActionEvent compra) throws IOException {
        float total = Float.parseFloat(lbTotal.getText());
        float efectivo = Float.parseFloat(tfEfectivo.getText());
        float cambio = Float.parseFloat(lbCambio.getText());

        lbDatos.setText("Panaderia: El Ángel de Azúcar" + "\t\t\t\t Sucursal: BellaVista" + "\nC.65 #345 x 34 y 56 col. Fovisste \t\t\t Tel. 9861234589");

        ObservableList<Venta> ventas = FXCollections.observableArrayList();
        for (int i = 0; i < ventaActual.size(); i++) {
            ventas.add(ventaActual.get(i));
        }

        tvTicket.setItems(ventas);

        tcIdPT.setCellValueFactory(new PropertyValueFactory<>("idPan"));
        tcProductoT.setCellValueFactory(new PropertyValueFactory<>("nombrePan"));
        tcCantidadT.setCellValueFactory(new PropertyValueFactory<>("cantidadPan"));
        tcPrecioT.setCellValueFactory(new PropertyValueFactory<>("precioPan"));
        tcMontoT.setCellValueFactory(new PropertyValueFactory<>("monto"));

        lbDetalles.setText("Total: " + total + "\nEfectivo: " + efectivo + "\nCambio: " + cambio);

        tfIDV.setText("");
        tfCantidadPan.setText("");
        tvVentas.setItems(null);
        lbTotal.setText("");
        tfEfectivo.setText("");
        lbCambio.setText("");

        tpOperaciones.getTabs().remove(tbRealizarVenta);
        tpOperaciones.getTabs().add(tbTicket);
        System.out.println("Ticket Impreso");
    }

    /**
     * Este método permite cerrar la ventana de impresión
     * @param e 
     */
    @FXML
    private void cancelarTicket(ActionEvent e) {
        tpOperaciones.getTabs().remove(tbTicket);
        apOperaciones.setDisable(false);
    }
    
//Tab HistorialCompras
    /**
     * Este método permite consultar las compras realizadas por un cliente
     *
     * @param iDCliente
     */
    @FXML
    private void consultarHistorial(ActionEvent iDCliente) throws RuntimeException {
        ArrayList<Venta> historialCompras = new ArrayList<>();

        int b = 0;//Permite saber si el cliente está registrado

        if (tfMC.getText().isEmpty()) {
            Alert datoID = new Alert(Alert.AlertType.INFORMATION);
            datoID.setHeaderText("Dato erróneo");
            datoID.setContentText("Ingrese un id para realizar la consulta");
            datoID.show();
            System.out.println("\tNo se ha ingresado el id");
        } else {
            try {
                int idC = Integer.parseInt(tfMC.getText());

                for (int i = 0; i < listaClientes.size(); i++) {
                    if (listaClientes.get(i).idCliente == idC) {
                        b = 1;
                    }
                }

                if (b == 0) {
                    Alert datoID = new Alert(Alert.AlertType.ERROR);
                    datoID.setHeaderText("Dato inválido");
                    datoID.setContentText("Cliente no registrado");
                    datoID.show();

                    tfMC.setText("");
                    System.out.println("\tEl cliente no está registrado");
                } else {
                    //Obtención de las ventas realizadas por el cliente
                    for (int i = 0; i < listaVentas.size(); i++) {
                        if (listaVentas.get(i).idCliente == idC) {
                            historialCompras.add(listaVentas.get(i));
                        }
                    }
                     
                    for (int i = 0; i < listaClientes.size(); i++) {
                        if(listaClientes.get(i).idCliente == idC){
                            String nombre = listaClientes.get(i).nombreCliente;
                            String apellido = listaClientes.get(i).aPaternoCliente;
                            lbDatosH.setText("Cliente: " + "[" + idC + "]   " + nombre + " " + apellido);
                        }
                    }
                    
                    ObservableList<Venta> ventas = FXCollections.observableArrayList();
                    for (int i = 0; i < historialCompras.size(); i++) {
                        ventas.add(historialCompras.get(i));
                    }

                    tvHistorial.setItems(ventas);

                    tcFechaH.setCellValueFactory(new PropertyValueFactory<>("fecha"));
                    tcIdH.setCellValueFactory(new PropertyValueFactory<>("idPan"));
                    tcProductoH.setCellValueFactory(new PropertyValueFactory<>("nombrePan"));
                    tcCantidadH.setCellValueFactory(new PropertyValueFactory<>("cantidadPan"));
                    tcMontoH.setCellValueFactory(new PropertyValueFactory<>("monto"));

                    tfMC.setText("");

                    if (historialCompras.isEmpty()) {
                        Alert datoID = new Alert(Alert.AlertType.INFORMATION);
                        datoID.setHeaderText("Historial compras");
                        datoID.setContentText("El cliente no ha realizado ninguna compra");
                        datoID.show();

                        tvHistorial.setVisible(false);
                        System.out.println("\tEl cliente no ha realiza compras");
                    } else {
                        tvHistorial.setVisible(true);
                    }
                }
            } catch (RuntimeException e) {
                Alert datoID = new Alert(Alert.AlertType.ERROR);
                datoID.setHeaderText("Dato inválido");
                datoID.setContentText("El iD del cliente debe ser numérico");
                datoID.show();

                tfMC.setText("");
                System.out.println("\tEl iD ingresado debe ser numérico");
            }
        }
    }

    /**
     * Este método permite realizar otra consulta de historial de compras
     *
     * @param e
     */
    @FXML
    private void nuevaConsulta(MouseEvent e) {
        tvHistorial.setItems(null);
        tvHistorial.setVisible(false);
        lbDatosH.setText("");
    }

    /**
     * Este método permite cancelar la consulta del historial de compras del
     * cliente
     *
     * @param cancelHistorial
     */
    @FXML
    private void cancelarHistorial(ActionEvent cancelHistorial) {
        tfMC.setText("");
        lbDatosH.setText("");
        
        tpOperaciones.getTabs().remove(tbHistorialCompras);
        apOperaciones.setDisable(false);
        System.out.println("Consulta de historial de compras cancelado");
    }

//Tab Mejor Cliente
    /**
     * Este método permite consultar cual es el mejor cliente en un rango de
     * fechas
     *
     * @param verMC
     */
    @FXML
    private void consultarMC(ActionEvent verMC) throws ParseException {
        if (dpInicioMC.getValue() == null || dpFinMC.getValue() == null) {
            Alert fechas = new Alert(Alert.AlertType.WARNING);
            fechas.setHeaderText("Error en consulta");
            fechas.setContentText("Debe ingresar ambas fechas");
            fechas.show();
            System.out.println("\tNo se han ingresado ambas fechas");
        } else {
            try {
                Date fechaI = new SimpleDateFormat("yyyy-MM-dd").parse(dpInicioMC.getValue().toString());
                Date fechaF = new SimpleDateFormat("yyyy-MM-dd").parse(dpFinMC.getValue().toString());

                dpInicioMC.setValue(null);
                dpFinMC.setValue(null);

                if (fechaI.after(fechaF)) {
                    Alert fecha = new Alert(Alert.AlertType.ERROR);
                    fecha.setHeaderText("Error de fechas");
                    fecha.setContentText("La fecha final no debe ser anterior que la inicial");
                    fecha.show();
                    System.out.println("\tFecha final está antes que la inicial");
                } else {
                    if (listaVentas.isEmpty()) {
                        Alert compras = new Alert(Alert.AlertType.INFORMATION);
                        compras.setHeaderText("Error de consulta");
                        compras.setContentText("No se han realizado ventas");
                        compras.show();
                        System.out.println("\tSin ventas realizadas");
                    } else {
                        
                        String mejorCliente = "";//Permite conocer  el MC
                        obtenerVentas(fechaI, fechaF);
                        
                        mejorCliente=controladorCliente.obtenerDatosMejorCliente(ventasRango,listaClientes);
                        
                        //mensajes a usuario
                        if(mejorCliente.isEmpty()){
                            Alert datoMP = new Alert(Alert.AlertType.INFORMATION);
                            datoMP.setHeaderText("Consulta finalizada");
                            datoMP.setContentText("Aún no se han hecho compras");
                            datoMP.show();
                        }else{
                            lbMC.setText("El mejor cliente es: \n" + mejorCliente);
                            ivMedallaMC.setVisible(true);
                        }
                    }
                }
            } catch (ParseException e) {
                System.out.println("\tConversión de fechas errónea");
            }
        }
    }

    /**
     * Este método permite realizar una nueva consulta de Mejor Cliente
     * @param e 
     */
    @FXML
    private void nuevaConsultaMC(MouseEvent e) {
        ivMedallaMC.setVisible(false);
        lbMC.setText("");
    }

    /**
     * Este método permite cancelar la consulta del mejor cliente
     *
     * @param cancelMC
     */
    @FXML
    private void cancelarMC(ActionEvent cancelMC) {
        lbMC.setText("");

        tpOperaciones.getTabs().remove(tbMejorCliente);
        apOperaciones.setDisable(false);
        System.out.println("Consulta de mejor cliente cancelado");
    }

//Tab PanMasVendido
    /**
     * Este método permite consultar cual es el pan más vendido en un rango de
     * fechas
     *
     * @param verPMV
     */
    @FXML
    private void consultarPMV(ActionEvent verPMV) {
        if (dpInicioPMV.getValue() == null || dpFinPMV.getValue() == null) {
            Alert fechas = new Alert(Alert.AlertType.WARNING);
            fechas.setHeaderText("Error en consulta");
            fechas.setContentText("Debe ingresar ambas fechas");
            fechas.show();
            System.out.println("\tNo se han ingresado ambas fechas");
        } else {
            try {
                Date fechaI = new SimpleDateFormat("yyyy-MM-dd").parse(dpInicioPMV.getValue().toString());
                Date fechaF = new SimpleDateFormat("yyyy-MM-dd").parse(dpFinPMV.getValue().toString());

                dpInicioPMV.setValue(null);
                dpFinPMV.setValue(null);

                if (fechaI.after(fechaF)) {
                    Alert fecha = new Alert(Alert.AlertType.ERROR);
                    fecha.setHeaderText("Error de fechas");
                    fecha.setContentText("La fecha final no debe ser anterior que la inicial");
                    fecha.show();
                    System.out.println("\tFecha final antes que la inicial");
                } else {
                    if (listaVentas.isEmpty()) {
                        Alert compras = new Alert(Alert.AlertType.INFORMATION);
                        compras.setHeaderText("Error de consulta");
                        compras.setContentText("No se han realizado ventas");
                        compras.show();
                        System.out.println("\tSin ventas realizadas");
                    } else {
                        double trono = 0; //Permite conocer el total de panes vendidos
                        String mejorPan = "";//Permite conocer  el PMV
                        int numPanes = listaPanes.size();//Permite saber cuántos panes se evaluarán
                        int indexPan = 0;//Define el index del pan evaluado

                        obtenerVentas(fechaI, fechaF);

                        while (numPanes > 0) {
                            float total = 0;

                            int idPan = listaPanes.get(indexPan).idPan;
                            String nombrePan = listaPanes.get(indexPan).nombrePan;

                            for (int i = 0; i < ventasRango.size(); i++) {
                                if (ventasRango.get(i).idPan == idPan) {
                                    total = total + ventasRango.get(i).cantidadPan;
                                }
                            }

                            numPanes--;//Se termino la consulta del pan

                            //Se evalua si la cantidad total de panes vendidos del pan actual es mayor al anterior 
                            if (total > trono) {
                                trono = total;
                                mejorPan = nombrePan;
                            }

                            indexPan++;//Se selecciona el siguiente pan 
                        }
                        
                        if(mejorPan.isEmpty()){
                            Alert datoMP = new Alert(Alert.AlertType.INFORMATION);
                            datoMP.setHeaderText("Consulta finalizada");
                            datoMP.setContentText("Aún no se han hecho compras");
                            datoMP.show();
                        }else{
                            
                            lbPMV.setText("El pan más vendido es: \n" + mejorPan);
                            ivMedallaPMV.setVisible(true);
                        }
                    }
                }
            } catch (ParseException e) {
                System.out.println("\tConversión de fechas errónea");
            }
        }
    }
    
    /**
     * Este método permite realizar una nueva consulta de Pan Más Vendido 
     * @param e 
     */
    @FXML
    private void nuevaConsultaPMV(MouseEvent e) {
        ivMedallaPMV.setVisible(false);
        lbPMV.setText("");
    }

    /**
     * Este método permite cancelar la consulta del pan más vendido
     *
     * @param cancelPMV
     */
    @FXML
    private void cancelarPMV(ActionEvent cancelPMV) {
        lbPMV.setText("");

        tpOperaciones.getTabs().remove(tbPanMasVendido);
        apOperaciones.setDisable(false);
        System.out.println("Consulta de pan más vendido cancelado");
    }

    /**
     * Este método permite obtener las ventas realizadas durante un rango de
     * fechas
     *
     * @param fechaI
     * @param fechaF
     * @throws ParseException
     */
    public void obtenerVentas(Date fechaI, Date fechaF) throws ParseException {
        ventasRango = new ArrayList<>();
        
        String fechaActual, fechaVenta;

        int dia = Integer.parseInt(new SimpleDateFormat("dd").format(fechaI));
        String mes = new SimpleDateFormat("MM").format(fechaI);
        String anio = new SimpleDateFormat("yyyy").format(fechaI);

        while (fechaI.before(fechaF) || fechaI.equals(fechaF)) {
            fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaI);
            for (int i = 0; i < listaVentas.size(); i++) {
                if (listaVentas.get(i).fecha.equals(fechaActual)) {
                    String diaVenta = listaVentas.get(i).fecha;
                    int idCVenta = listaVentas.get(i).idCliente;
                    int idPVenta = listaVentas.get(i).idPan;
                    String panVenta = listaVentas.get(i).nombrePan;
                    int cantidadPan = listaVentas.get(i).cantidadPan;
                    float precioVenta = listaVentas.get(i).precioPan;
                    float totalVenta = listaVentas.get(i).monto;
                    ventasRango.add(new Venta(diaVenta, idCVenta, idPVenta, panVenta, cantidadPan, precioVenta, totalVenta));
                }
            }
                                
            dia += 1;
            fechaVenta = (dia + "/" + mes + "/" + anio);

            try {
                fechaI = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVenta);
            } catch (ParseException e) {
                System.out.println("Fecha inexistenten\n");
            }
        }
    }

//Tab VerConchapuntos
    /**
     * Este método permite consultar el total de conchapuntos acumulados por un
     * cliente
     *
     * @param verCP
     */
    @FXML
    private void verCP(ActionEvent verCP) throws RuntimeException {
        int b = 0;//Permite saber si el cliente está registrado

        if (tfVerCP.getText().isEmpty()) {
            Alert datoID = new Alert(Alert.AlertType.INFORMATION);
            datoID.setHeaderText("Dato inválido");
            datoID.setContentText("Ingrese un id para realizar la consulta");
            datoID.show();
            System.out.println("\tNo se ha ingresado un id");
        } else {
            try {
                int idCliente = Integer.parseInt(tfVerCP.getText());
                
                for (int i = 0; i < listaClientes.size(); i++) {
                    if (listaClientes.get(i).idCliente == idCliente) {
                        lbVerCP.setText("ID: " + listaClientes.get(i).idCliente
                                + "\nCliente: " + listaClientes.get(i).nombreCliente + " " + listaClientes.get(i).aPaternoCliente
                                + "\nConchapuntos:  " + listaClientes.get(i).conchapuntos);
                        tfVerCP.setText("");

                        System.out.println("ID: " + listaClientes.get(i).idCliente
                                + "\nCliente: " + listaClientes.get(i).nombreCliente + " " + listaClientes.get(i).aPaternoCliente
                                + "\nConchapuntos:  " + listaClientes.get(i).conchapuntos);
                        b = 1;//Se encontró al cliente
                    }
                }

                if (b == 0) {
                    Alert datoID = new Alert(Alert.AlertType.INFORMATION);
                    datoID.setHeaderText("Dato inválido");
                    datoID.setContentText("El cliente no está registrado");
                    datoID.show();

                    tfVerCP.setText("");
                    System.out.println("\tCliente no registrado");
                }
            } catch (RuntimeException e) {
                Alert datoID = new Alert(Alert.AlertType.ERROR);
                datoID.setHeaderText("Dato inválido");
                datoID.setContentText("El iD del cliente debe ser numérico");
                datoID.show();

                tfVerCP.setText("");
                System.out.println("\tEl iD del cliente debe ser numérico");
            }
        }
    }

    /**
     * Este método permite realizar una nueva consulta de conchapuntos
     * acumulados
     *
     * @param e
     */
    @FXML
    private void nuevaConsultaCP(MouseEvent e) {
        lbVerCP.setText("");
    }

    /**
     * Este método permite cancelar la consulta de los conchapuntos acumulados
     *
     * @param cancelVerCP
     */
    @FXML
    private void cancelarCP(ActionEvent cancelVerCP) {
        tfVerCP.setText("");
        lbVerCP.setText("");

        tpOperaciones.getTabs().remove(tbVerConchapuntos);
        apOperaciones.setDisable(false);
        System.out.println("Consulta de conchapuntos cancelada");
    }

//Tab CanjearConchapuntos
    /**
     * Este método permite consultar los conchapuntos acumulados por el cliente
     *
     * @param verCPA
     */
    @FXML
    private void verCPA(ActionEvent verCPA) {
        int b = 0;//Permite saber si el cliente está registrado

        if (tfConsultarCP.getText().isEmpty()) {
            Alert datoID = new Alert(Alert.AlertType.INFORMATION);
            datoID.setHeaderText("Dato inválido");
            datoID.setContentText("Ingrese un id para realizar la consulta");
            datoID.show();
            System.out.println("\tNo se ha ingresado un id");
        } else {
            try {
                int idCliente = Integer.parseInt(tfConsultarCP.getText());

                
                for (int i = 0; i < listaClientes.size(); i++) {
                    if (listaClientes.get(i).idCliente == idCliente) {

                        lbDatosCanjear.setText("ID: " + listaClientes.get(i).idCliente
                                + "\nCliente: " + listaClientes.get(i).nombreCliente + " " + listaClientes.get(i).aPaternoCliente
                                + "\nConchapuntos:  " + listaClientes.get(i).conchapuntos
                                + "\n\nManteconchas disponibles: " + listaClientes.get(i).conchapuntos / 10
                                + "\nManteconchas en existencia: " + manteconchas);
                        lbCPA.setText(Integer.toString(listaClientes.get(i).conchapuntos));
        
                        System.out.println("ID: " + listaClientes.get(i).idCliente
                                + "\nCliente: " + listaClientes.get(i).nombreCliente + " " + listaClientes.get(i).aPaternoCliente
                                + "\nConchapuntos:  " + listaClientes.get(i).conchapuntos);
                        
                        if (Integer.parseInt(lbCPA.getText())/10 < 1) {
                            Alert CPA = new Alert(Alert.AlertType.INFORMATION);
                            CPA.setHeaderText("Conchapuntos");
                            CPA.setContentText("El cliente no tiene suficientes conchapuntos");
                            tfConsultarCP.setText("");
                        }else{
                            System.out.println("\tCanjeo autorizado");
                            lbCanjearCP.setVisible(true);
                            tfCanjear.setVisible(true);
                            btnCanjear.setVisible(true);
                            tfConsultarCP.setEditable(false);
                        }

                        b = 1;
                    }
                }

                if (b == 0) {
                    Alert datoID = new Alert(Alert.AlertType.INFORMATION);
                    datoID.setHeaderText("Dato inválido");
                    datoID.setContentText("El cliente no está registrado");
                    datoID.show();
                    System.out.println("\tCliente no registrado");
                }
            } catch (RuntimeException e) {
                Alert datoID = new Alert(Alert.AlertType.ERROR);
                datoID.setHeaderText("Dato inválido");
                datoID.setContentText("El iD del cliente debe ser numérico");
                datoID.show();

                tfConsultarCP.setText("");
                System.out.println("\tEl iD del cliente debe ser numérico");
            }
        }
    }

    /**
     * Este método permite hacer una nueva consulta de conchapuntos acumulados
     * para canjear
     */
    @FXML
    private void nuevaConsultaCCP(MouseEvent e) {
        tfConsultarCP.setEditable(true);
        tfConsultarCP.setText("");
        lbDatosCanjear.setText("");
        lbCanjearCP.setVisible(false);
        tfCanjear.setVisible(false);
        btnCanjear.setVisible(false);
        lbCPA.setText("");
    }

    /**
     * Este método permite canjear los conchapuntos acumulados
     *
     * @param canjearCP
     */
    @FXML
    private void canjearCP(ActionEvent canjearCP) throws RuntimeException {
        if (tfCanjear.getText().isEmpty()) {
            Alert datoID = new Alert(Alert.AlertType.INFORMATION);
            datoID.setHeaderText("Dato inválido");
            datoID.setContentText("Ingrese la cantidad de conchapuntos que desea canjear");
            datoID.show();
            System.out.println("\tNo se ha ingresado la cantidad de conchapuntos que se desea canjear");
        } else {
            int pos = 0;
            
            try {
                int cantidad = Integer.parseInt(tfCanjear.getText());
                
                int idC = Integer.parseInt(tfConsultarCP.getText());
                
                for (int i = 0; i < listaClientes.size(); i++) {
                    if(listaClientes.get(i).idCliente == idC)
                        pos = i;
                }
                
                int CPA = listaClientes.get(pos).conchapuntos;
                System.out.println("Conchapuntos acumulados: " + CPA);
                int CPN = cantidad * 10;
                System.out.println("Conchapuntos necesarios: " + CPN);
                
                if (CPA < CPN) {
                    Alert datoMc = new Alert(Alert.AlertType.INFORMATION);
                    datoMc.setHeaderText("Sin conchapuntos");
                    datoMc.setContentText("Lo sentimos no se cuenta con suficientes conchapuntos");
                    datoMc.show();
                    
                    System.out.println("\tConchapuntos insuficientes");
                    tfCanjear.setText("");
                } else {     
                    int existenciaMC = manteconchas - cantidad;
                        
                    if(existenciaMC >= 0){
                        manteconchas -= cantidad;
                        System.out.println("Quedan " + manteconchas + " manteconchas");

                        int CP = (CPN * -1); 
                        listaClientes.get(pos).setConchapuntos(CP);
                        controladorCliente.escribirClientes(listaClientes);
                        
                        Alert CCP = new Alert(Alert.AlertType.INFORMATION);
                        CCP.setHeaderText("Canjeo completado");
                        CCP.setContentText("Puede otorgar " + cantidad + " manteconchas al cliente");
                        CCP.show();
                        
                        tfCanjear.setText("");
                        lbDatosCanjear.setText("ID: " + listaClientes.get(pos).idCliente
                                + "\nCliente: " + listaClientes.get(pos).nombreCliente + " " + listaClientes.get(pos).aPaternoCliente
                                + "\nConchapuntos:  " + listaClientes.get(pos).conchapuntos
                                + "\n\nManteconchas disponibles: " + listaClientes.get(pos).conchapuntos / 10
                                + "\nManteconchas en existencia: " + manteconchas);
                        
                        System.out.println("CP: " + listaClientes.get(pos).conchapuntos);
                    }else{                     
                        Alert existencia = new Alert(Alert.AlertType.INFORMATION);
                        existencia.setHeaderText("Existencia de manteconchas");
                        existencia.setContentText("No se cuenta con suficientes manteconchas para canjear");
                        existencia.showAndWait();
                        
                        tfCanjear.setText("");
                        System.out.println("\tNo quedan suficientes manteconchas para canjear");
                    }
                    
                    int restantes = listaClientes.get(pos).conchapuntos;
                        
                    if(manteconchas == 0 || (restantes/10) < 1){
                        lbCanjearCP.setVisible(false);
                        tfCanjear.setVisible(false);
                        btnCanjear.setVisible(false);
                    }
                }
            } catch (RuntimeException e) {
                Alert datoC = new Alert(Alert.AlertType.ERROR);
                datoC.setHeaderText("Dato inválido");
                datoC.setContentText("La cantidad de conchapuntos a canjear debe ser numérica");
                datoC.show();
                System.out.println("La cantidad de conchapuntos a canjear debe ser numérica");
            }

        }
    }

    /**
     * Este método permite cancelar el canjeo de los conchapuntos
     *
     * @param cancelCCP
     */
    @FXML
    private void cancelarCCP(ActionEvent cancelCCP) {
        tfConsultarCP.setText("");
        lbDatosCanjear.setText("");
        tfCanjear.setText("");

        tpOperaciones.getTabs().remove(tbCanjearConchapuntos);
        apOperaciones.setDisable(false);
        System.out.println("Canjeo de conchapuntos cancelado");
    }

    /**
     * Este método permite cancelar la consulta de clientes
     *
     * @param cancelVC
     */
    @FXML
    private void cancelarVC(ActionEvent cancelVC) {
        tvClientes.setItems(null);

        tpOperaciones.getTabs().remove(tbVerClientes);
        apOperaciones.setDisable(false);
        System.out.println("Ver clientes cancelado");
    }

    /**
     * Este método permite cancelar la consulta de clientes
     *
     * @param cancelVP
     */
    @FXML
    private void cancelarVP(ActionEvent cancelVP) {
        tvPanes.setItems(null);

        tpOperaciones.getTabs().remove(tbVerPanes);
        apOperaciones.setDisable(false);
        System.out.println("Ver panes cancelado");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Date fechaActual = new Date();
        String fechaSistema = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
        Alert fecha = new Alert(Alert.AlertType.CONFIRMATION);
        fecha.setTitle("Confirmar fecha");
        fecha.setHeaderText("Fecha del sistema: " + fechaSistema);
        fecha.setContentText("¿Desea iniciar el programa?");
        fecha.showAndWait();

        if (fecha.getResult().getButtonData().equals(ButtonData.OK_DONE)) {
            lbFecha.setText(fechaSistema);

            listaClientes = controladorCliente.leerClientes();
            listaPanes = controladorPan.leerPanes();
            listaVentas = controladorVenta.leerVentas();
            
            tpOperaciones.getTabs().remove(tbRegistrarCliente);
            tpOperaciones.getTabs().remove(tbRegistrarPan);
            tpOperaciones.getTabs().remove(tbRealizarVenta);
            tpOperaciones.getTabs().remove(tbHistorialCompras);
            tpOperaciones.getTabs().remove(tbMejorCliente);
            tpOperaciones.getTabs().remove(tbPanMasVendido);
            tpOperaciones.getTabs().remove(tbVerConchapuntos);
            tpOperaciones.getTabs().remove(tbCanjearConchapuntos);
            tpOperaciones.getTabs().remove(tbVerClientes);
            tpOperaciones.getTabs().remove(tbVerPanes);
            tpOperaciones.getTabs().remove(tbTicket);    
        } else {
            System.exit(0);
        }
    }

}
