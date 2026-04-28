package es.unican.is2.segurosgui;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.segurosbusiness.GestionSeguros;
import es.unican.is2.seguroscommon.IClientesDAO;
import es.unican.is2.seguroscommon.ISegurosDAO;
import es.unican.is2.segurosdaoh2.ClientesDAO;
import es.unican.is2.segurosdaoh2.SegurosDAO;

public class VistaAgenteIT {

    private FrameFixture window;
    private GestionSeguros negocio;

    @BeforeEach
    public void setUp() {
        IClientesDAO daoClientes = new ClientesDAO();
        ISegurosDAO daoSeguros = new SegurosDAO();
        negocio = new GestionSeguros(daoClientes, daoSeguros);
        
        VistaAgente frame = GuiActionRunner.execute(() -> new VistaAgente(negocio, negocio, negocio));
        window = new FrameFixture(frame);
        window.show(new java.awt.Dimension(500, 400)); 
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp(); 
    }

    @Test
    public void testCP_IT_01_ClienteConSeguros() {
        window.textBox("txtDNICliente").enterText("11111111A");
        window.button("btnBuscar").click();
        
        window.textBox("txtNombreCliente").requireText("Juan");
        window.list("listSeguros").requireItemCount(3);
        
        String totalTexto = window.textBox("txtTotalCliente").text();
        org.junit.jupiter.api.Assertions.assertFalse(totalTexto.isEmpty(), "El total no debe estar vacío");
        org.junit.jupiter.api.Assertions.assertNotEquals("0.0", totalTexto, "El total no debe ser 0.0 para Juan");
    }

    @Test
    public void testCP_IT_02_ClienteSinSeguros() {
        window.textBox("txtDNICliente").enterText("33333333A");
        window.button("btnBuscar").click();
        
        window.textBox("txtNombreCliente").requireText("Luis");
        window.list("listSeguros").requireItemCount(0);
        window.textBox("txtTotalCliente").requireText("0.0"); 
    }

    @Test
    public void testCP_IT_03_ClienteNoExiste() {
        window.textBox("txtDNICliente").enterText("99999999Z");
        window.button("btnBuscar").click();
        
        window.textBox("txtNombreCliente").requireText("Error en BBDD");
        window.textBox("txtTotalCliente").requireText("");
        window.list("listSeguros").requireItemCount(0);
    }
    
    @Test
    public void testCP_IT_06_FalloBBDD() {
        // 1. Cerramos la ventana que abrió el @BeforeEach automáticamente
        window.cleanUp();

        // 2. Creamos un DAO "falso" (Stub) que SIEMPRE lance la excepción al buscar
        IClientesDAO daoRoto = new ClientesDAO() {
            @Override
            public es.unican.is2.seguroscommon.Cliente cliente(String dni) throws es.unican.is2.seguroscommon.DataAccessException {
                throw new es.unican.is2.seguroscommon.DataAccessException();
            }
        };
        
        // 3. Montamos el negocio y la vista inyectando este DAO falso
        GestionSeguros negocioRoto = new GestionSeguros(daoRoto, new SegurosDAO());
        VistaAgente frameRoto = GuiActionRunner.execute(() -> new VistaAgente(negocioRoto, negocioRoto, negocioRoto));
        
        // 4. Conectamos AssertJ a la nueva ventana para interactuar
        window = new FrameFixture(frameRoto);
        window.show(new java.awt.Dimension(500, 400)); 

        // 5. Hacemos la prueba (el DNI da igual porque el DAO siempre va a fallar)
        window.textBox("txtDNICliente").enterText("11111111A");
        window.button("btnBuscar").click();

        // 6. Verificamos que el sistema entra por el 'catch' y muestra el error en la interfaz
        window.textBox("txtNombreCliente").requireText("Error en BBDD");
        window.textBox("txtTotalCliente").requireText("");
        window.list("listSeguros").requireItemCount(0);
    }
}